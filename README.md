# Kata tondeuse à gazon

# Sommaire

- [Lancer les tests](#lancer-les-tests)
- [Liste des tags (tests)](#liste-des-tags-tests)
- [Implémentation pas à pas](#implementation-pas-a-pas)

## Lancer les tests

`mvn clean test`

## Liste des tags (tests)

"Start"
- init
- test_1_create_mower

Avancer
- test_2_1_forward_north_without_limit
- test_2_2_forward_north_with_limit
- test_3_1_forward_west_without_limit
- test_3_2_forward_west_with_limit
- test_4_1_forward_east_without_limit
- test_4_2_forward_east_with_limit
- test_5_1_forward_south_without_limit
- test_5_2_forward_south_with_limit

Tourner à gauche
- test_6_1_turn_left_facing_north
- test_6_2_turn_left_facing_west
- test_6_3_turn_left_facing_south
- test_6_4_turn_left_facing_east

Tourner à droite
- test_7_1_turn_right_facing_north
- test_7_2_turn_right_facing_east
- test_7_3_turn_right_facing_south
- test_7_4_turn_right_facing_west

Exécuter les séquences d'instructions de l'énoncé
- test_8_1_execute_first_command_sequence
- test_8_2_execute_second_command_sequence

"Left side", lecture du fichier d'entrée, éxécution des tondeuses et écriture du fichier de sortie 
- test_9_1_create_MowerApplication_with_existing_file
- test_9_2_create_MowerApplication_with_non_existing_afile
- test_9_3_create_environment_from_string
- test_9_4_create_mower_from_string
- test_9_5_create_commands_from_string

"Fin" (avec `HEAD`)
- test_10_putting_it_all_together


## Implementation pas a pas

### Bootstrap du projet

tag `start`

Dans un tout premier temps, on met en place le nécessaire pour écrire des tests unitaires.

On vérifie que l'on peut lancer un test en échec: depuis l'IDE (dans mon cas IntelliJ), et via maven en ligne de commande.

### Etude du problème

Même si on fait du TDD, d'expérience, se lancer immédiatement dans le code sans avoir effectué une étude préliminaire 
du problème peut amener à perdre de vue la problématique en cours de route 
et rendre le processus pénible et moins efficace.

Il s'agit de clarifier sa compréhension du problème, et les hypothèses implicites que l'on peut faire sur la manière 
dont on pense le résoudre, ce qui est, à mon humble avis, utile quand on travaille seul, 
et particulièrement important quand on travaille à plusieurs, pour aligner nos compréhensions et limiter les incompréhensions futures.

Il ne s'agit pas nécessairement de faire du "big upfront design",à suivre à la lettre. 
On autorise les tests à faire émerger un design différent de ce que l'on a conçu initialement.

Et si analyser le problème devait devenir un point de douleur et nous empêcher d'avancer, alors on se lance et on 
affinera ou rectifiera par la suite, restons agiles et pragmatiques.

#### Algo de l'application dans les grandes lignes

```
- On récupère les coordonnées du coin supérieur droit en 1e ligne
- jusqu'à la fin du fichier:
    - On crée un tondeuse à partir de la 2e (2i -ème) ligne
    - On récupère et éxécute les instructions sur la tondeuse à partir de la 3e (2i+1 -ème) ligne
    - On récupère la position de la tondeuse et on l'écrit dans le fichier de sortie
```

![](docs/1.0-design-initial.png)

1. `MowerApplication` lit le fichier d'entrée
2. `MowerApplication` crée la tondeuse et éxécute les commandes dessus
3. `MowerApplication` récupère la position et l'orientation de la tondeuse pour l'écrire dans le fichier de sortie

On sépare la logique d'éxécution de la tondeuse et la lecture du fichier pour une meilleure testabilité du comportement 
de la tondeuse, et par habitude, en tant que pratiquant de l'architecture héxagonale.

### test #1 - on peut créer une tondeuse avec sa position initiale

tag `test_1_create_mower`
Pour se lancer, on crée une tondeuse avec sa position initiale et on vérifie que celle-ci est bien settée.

### test #2.1 - avancer la tondeuse quand celle-ci fait face au nord

tag `test_2_1_forward_north_without_limit`
2e test, on vérifie que la coordonnée `y` de la tondeuse augmente de 1 quand elle avance en faisant face au nord.

### test #2.2 - avancer la tondeuse quand celle-ci fait face au nord et est deja à la limite

tag `test_2_2_forward_north_with_limit`
2e test, on vérifie que la coordonnée `y` de la tondeuse augmente de 1 quand elle avance en faisant face au nord.

Refacto:

Lors de cette étape, on a modifié l'idée du design de la façon suivante:

![](docs/2.0-intro-environment.png)

Initialement, les limites de l'environnement étaient définies comme variables statiques de la classe `Mower`.
Après réflexion, elles ne semblent pas être des attributs des tondeuses, mais externes aux tondeuses.

L'idée de l'algo résolvant notre problème devenant dans les grandes lignes quelque chose comme:

```
- On crée l'environnement à partir de la 1e ligne du fichier d'entrée
- jusqu'à la fin du fichier:
    - On crée un tondeuse à partir de la 2e (2i -ème) ligne
    - On récupère et éxécute les instructions sur la tondeuse à partir de la 3e (2i+1 -ème) ligne
    - On récupère la position de la tondeuse et on l'écrit dans le fichier de sortie
```

### test #3.1 - avancer la tondeuse quand celle-ci fait face à l'ouest

tag `test_3_1_forward_west_without_limit`

### test #3.2 - avancer la tondeuse quand celle-ci fait face à l'ouest et est deja à la limite

tag `test_3_2_forward_west_with_limit`

### test #4.1 - avancer la tondeuse quand celle-ci fait face à l'est 

tag `test_4_1_forward_east_without_limit`

### test #4.2 - avancer la tondeuse quand celle-ci fait face à l'est et est deja à la limite

tag `test_4_2_forward_east_with_limit`

### test #5.1 - avancer la tondeuse quand celle-ci fait face au sud

tag `test_5_1_forward_south_without_limit`

### test #5.2 - avancer la tondeuse quand celle-ci fait face au sud et est deja à la limite

tag `test_5_2_forward_south_with_limit`

### test #6.1 - tourner la tondeuse à gauche quand celle-ci fait face au nord

tag `test_6_1_turn_left_facing_north`

### test #6.2 - tourner la tondeuse à gauche quand celle-ci fait face à l'ouest

tag `test_6_2_turn_left_facing_west`

### test #6.3 - tourner la tondeuse à gauche quand celle-ci fait face au sud

tag `test_6_3_turn_left_facing_south`

### test #6.4 - tourner la tondeuse à gauche quand celle-ci fait face à l'est

tag `test_6_4_turn_left_facing_east`

### test #7.1 - tourner la tondeuse à droite quand celle-ci fait face au nord

tag `test_7_1_turn_right_facing_north`

### test #7.2 - tourner la tondeuse à droite quand celle-ci fait face à l'est

tag `test_7_2_turn_right_facing_east`

### test #7.3 - tourner la tondeuse à droite quand celle-ci fait face au sud

tag `test_7_3_turn_right_facing_south`

### test #7.4 - tourner la tondeuse à droite quand celle-ci fait face à l'ouest

tag `test_7_4_turn_right_facing_west`

### test #8.1 - éxécuter la première séquence de l'énoncé 

tag `test_8_1_execute_first_command_sequence`

### test #8.2 - éxécuter la deuxième séquence de l'énoncé 

tag `test_8_2_execute_second_command_sequence`

### test #9.1 - créer une `MowerApplication` à partir du chemin d'un fichier d'entrée

On lève une exception si le fichier n'existe pas.
On teste que:

Si le fichier existe bien, alors on peut créer une `MowerApplication` et aucune exception n'est levée

tag `test_9_1_create_MowerApplication_with_existing_file`

"make the implicits explicit": 

Pourquoi ce test ?
Et bien à ce stade, dans les grandes lignes, j'imagine l'application de la manière suivante:

![](docs/3.0-mowerApplication-initial-idea.png)

Où main ressemblerait à:

``` java
public static void main(String[] args) {
    String inputFile = "...";
    String outputFile = "...";
    MowerApplication mowerApplication = new MowerApplication(inputFile, outputFile);
    mowerApplication.run();
}
``` 

et `MowerApplication.run()` ressemblerait à:

``` java
public void run(){
    Iterator<String> inputLines = inputFile.readLines();
    Environment environment = createEnvironment(inputLines.next());
    while (inputLines.hasNext()){
      Mower mower = createMower(inputLines.next(),environment);
      createInstructions(inputLines.next()).foreach(mower::execute);
      outputFile.append(mower.getPosition());
    }
  }
```

### test #9.2 - créer une `MowerApplication`, mais le fichier n'existe pas

On lève une exception si le fichier n'existe pas.
On teste que:

Si le fichier n'existe pas, alors une exception est levée

tag `test_9_2_create_MowerApplication_with_non_existing_file`

### test #9.3 - créer un environnement à partir d'une chaîne de caractères

tag `test_9_3_create_environment_from_string`

### test #9.4 - créer une tondeuse à partir d'une chaîne de caractères

tag `test_9_4_create_mower_from_string`

### test #9.5 - créer des commandes à partir d'une chaîne de caractères

tag `test_9_5_create_commands_from_string`

### test #10 - On assemble tout

tag `test_10_putting_it_all_together`

### Ajout d'un `main`

Pour faire bonne mesure, on ajoute un `main`.