
# ShingShangFRONT
![](https://github.com/k-samir/ShingShang/blob/main/image/board.png?raw=true)

----------------
# Présentation
Bienvenue sur le git de la partie FRONT du projet ShingShang !

L’objectif de ce projet est d’implémenter un jeu de plateau, appelé SHING SHANG.
Il s’agit d’un jeu à deux joueurs. 
Chaque joueur possède une armée de 12 Bushis ( Pièces ). Cette armée est composée de 3 groupes : 2 dragons, 4 lions et 6 singes. 

[Documentation du projet](https://docs.google.com/document/d/1ZtqhFwuuNymzyjvvDptcIIbRV_NNXb4CPYg6uDdT0ng/edit#)

[Lien de téléchargement du jeu](https://github.com/k-samir/ShingShang/blob/main/jar/ShingShang_SK.jar?raw=true)

[Structure du Projet](https://github.com/k-samir/ShingShang/blob/main/src/UML3.jpg)

----------------
# Objectifs pédagogiques

Prendre connaissance du projet, savoir déterminer les objectifs du projet. 

L’accent est mis davantage sur les aspects conception logiciels (au sens documentation, réutilisabilité...) et suivi de projet que sur la difficulté technique du sujet.

----------------
# Base du jeu

Les joueurs exécutent chacun leur tour une action parmi les deux actions suivantes :

un joueur peut déplacer l’une de ses pièces présentes sur le plateau vers une autre case du plateau.

un joueur peut sauter par-dessus une autre pièce si celle-ci est plus petite ou de même taille que la pièce du sauteur.

----------------

# Déplacement

Pour déplacer une pièce sur le plateau, il est nécessaire que la case d’arrivée soit libre. 

On peut se déplacer horizontalement, verticalement ou en diagonale, aussi bien en avant qu’en arrière. 

Pour sauter, la pièce du sauteur doit se trouver sur une case contiguë à une case occupée par l’une de ses propres pièces ou par celle du joueur adverse. 

Le saut peut se faire verticalement, horizontalement ou en diagonale, à condition que la case suivante soit vide. 

----------------

# Séquence Shing Shang

On peut enchaîner plusieurs sauts au cours d’un même tour. Cet enchaînement de sauts s’appelle un SHING SHANG.

Si, lors d’un SHING SHANG, on saute par dessus une pièce adverse, on doit s’arrêter et la pièce de l’adversaire est retirée du plateau. Toutefois, on gagne un tour de jeu supplémentaire avec une autre pièce.

----------------


# Règle spécifique

Les singes peuvent se déplacer d’une ou deux cases dans n’importe quelle direction, horizontalement,verticalement ou en diagonale, mais sans changer de direction au cours du tour.
Les lions peuvent se déplacer d’une case dans n’importe quelle direction, horizontalement, verticalement ou en diagonale.
Les dragons ne peuvent se déplacer qu’en sautant.

----------------

# Fin de partie

Un joueur remporte la partie lorsqu’il parvient à amener l’un de ses dragons sur l’un des portails (cases spéciales) de son adversaire ou qu’il capture les deux dragons de son adversaire.

----------------

# Lancement du jeu

Pour lancer le jeu vous devez telecharger de l'exe ou du jar depuis github.
[Lien de téléchargement du .exe](https://github.com/k-samir/ShingShang/blob/main/jar/ShingShang_SK.jar?raw=true)
[Lien de téléchargement du .jar](https://github.com/k-samir/ShingShang/blob/main/jar/ShingShang_SK.jar?raw=true)


----------------

## Executer le jeu

Sur Windows/Linux ou Mac : Ouvrir un command prompt/bash, rendez-vous à l'emplacement du .jar et taper : java -jar ShingShangSK.jar 

----------------

# Technologies utilisés

Le jeu a entièrement été développé sur [Eclipse](https://www.eclipse.org/) en `Java` JDK 14.0.2.


