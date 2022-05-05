# Pacman

***

Jeu Pacman en Java
Projet opensource et téléchargeable sur GitHub:
https://github.com/jcomble/Pacman

Concepteurs et développeurs: Jean-Yves Comble, Mohammed Ez-Zraidy, Noa Descoursiere

## Java 18 nécessaire pour lancer le jeu:

* Windows : https://www.oracle.com/java/technologies/downloads/#jdk18-windows
* Linux : https://www.oracle.com/java/technologies/downloads/#jdk18-linux
* macOS : https://www.oracle.com/java/technologies/downloads/#jdk18-mac

## Conseils pour avoir une bonne expérience de jeu:

* Il est conseillé d'avoir une mise à l'échelle de l'écran à 100%. (Panneau de configuration -> Système et sécurité  -> Écran -> Mise à l'échelle et disposition sur Windows 10)

## Comment lancer le jeu?

* Sur Windows il suffit de cliquer sur le raccourci nommé PACMAN_WIN
* Sur Linux ou MacOS, ouvrez un invite de commande, dirigez-vous vers le dossier Pacman et
lancez la commande java -jar pacman.jar

## Comment fonctionne le jeu?

Pacman est poursuivi par des fantômes, dans des couloirs étroits, il doit manger tous les Pac-Dots et les fruits pour s'échapper et changer de niveau.
Les super-gommes permettent de rendre les fantômes vulnérables pour que Pacman puisse les dévorer.
Obtenez le meilleur score!
* Les flèches directionnelles vous permettent de déplacer Pacman dans les 4 sens cardinaux.
* Vous pouvez enregistrer la prochaine direction de déplacement juste par une pression de la touche directionnelle.
* Les fantômes donnent 200, 400, 800 ou 1600 points selon le nombre de fantômes dévoré d'affilé après avoir mangé une super-gomme.
* Les Pac-Dots donnent 10 points.
* Les fruits donnent de 100 à 5000 points.
Attention! Les fantômes suivent les couloirs et changent de direction au choix (aléatoire) à chaque embranchement.
Pacman dispose de 4 vies et à chaque fois qu'un fantôme non vulnérable touche Pacman, il perd une vie.
Une cerise apparait au bout de 80 fruits/Pac-dots mangés à chaque niveau.
Il y a 3 niveaux, à chaque niveau, tout va plus vite! À vous de jouer pour remporter la victoire!