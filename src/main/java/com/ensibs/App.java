package com.ensibs;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.json.JSONObject;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class App {
    public static void main(String[] args) throws Exception {
        String jarPath = "./simple-calc.jar";
        Map<String, Object> classesInfo = new HashMap<>();

        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(jarPath))) {
    ZipEntry entry; // Déclaration de la variable en dehors de la boucle
    while ((entry = zip.getNextEntry()) != null) { // Assigne 'entry' à chaque itération
        if (entry.getName().endsWith(".class")) {
            ClassReader classReader = new ClassReader(zip);
            Map<String, Object> classInfo = new HashMap<>();
            Map<String, String> attributes = new HashMap<>();
            List<Map<String, Object>> methods = new ArrayList<>();

            classReader.accept(new ClassVisitor(Opcodes.ASM9) {
                // Implémentation des visiteurs (FieldVisitor et MethodVisitor) inchangée
                @Override
                public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                    attributes.put(name, Type.getType(descriptor).getClassName());
                    return super.visitField(access, name, descriptor, signature, value);
                }

                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    List<Map<String, String>> params = new ArrayList<>();
                    Type[] argumentTypes = Type.getArgumentTypes(descriptor);
                    for (int i = 0; i < argumentTypes.length; i++) {
                        Map<String, String> param = new HashMap<>();
                        param.put("param" + (i + 1), argumentTypes[i].getClassName());
                        params.add(param);
                    }
                    Map<String, Object> method = new HashMap<>();
                    method.put("name", name);
                    method.put("params", params);
                    methods.add(method);
                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }
            }, ClassReader.SKIP_DEBUG);

            classInfo.put("attributes", attributes);
            classInfo.put("methods", methods);
            classesInfo.put(entry.getName().replace("/", ".").replace(".class", ""), classInfo);
        }
    }
}

JSONObject json = new JSONObject(classesInfo);
try (FileWriter file = new FileWriter("classesInfo.json")) {
    file.write(json.toString(2));
    System.out.println("Successfully Copied JSON Object to File...");
    System.out.println("\nJSON Object: " + json);
}

    }
}