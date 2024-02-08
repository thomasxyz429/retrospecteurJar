# AnalyseurJar
AnalyseurJar est une application Java conçue pour analyser le contenu des fichiers JAR et en extraire des informations structurées sur les classes, les méthodes et les attributs sous forme de fichier JSON.

## Prérequis
Pour exécuter ce projet, vous aurez besoin de :

Java JDK 17 ou supérieur. Le projet a été testé et est compatible avec Java 17. Utiliser une version antérieure peut entraîner des erreurs inattendues.

## Compilation
Le projet utilise Maven pour la gestion des dépendances et la construction. Pour compiler le projet et créer un fichier JAR exécutable contenant toutes les dépendances nécessaires, exécutez la commande suivante à la racine du projet :

```bash
mvn clean package
```
Cette commande génère un fichier AnalyseurJar-1.0-SNAPSHOT-jar-with-dependencies.jar dans le dossier target.

## Exécution
Une fois le projet compilé, vous pouvez exécuter l'application en utilisant la commande suivante :

```bash

java -jar target/AnalyseurJar-1.0-SNAPSHOT-jar-with-dependencies.jar
```
Assurez-vous que le fichier JAR que vous souhaitez analyser se trouve dans le même répertoire que l'exécutable du projet ou spécifiez le chemin approprié dans le code source avant la compilation si nécessaire.

## Fonctionnalités
Analyse des fichiers JAR spécifiés pour extraire des informations sur les classes, y compris :

Noms des classes
Attributs avec types
Méthodes avec types de paramètres
Exportation des informations collectées au format JSON.

## Limitations
Le projet doit être exécuté avec Java 17 ou une version ultérieure en raison de la compatibilité avec les bibliothèques utilisées.