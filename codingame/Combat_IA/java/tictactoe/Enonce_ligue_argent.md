Ultimate Tic-Tac-Toe
Ligue Argent
 
 
Ce challenge se déroule en ligue.

Pour ce challenge, plusieurs versions du même jeu seront disponibles. Quand vous aurez prouvé votre valeur dans la première version, vous accéderez à la ligue supérieure et débloquerez de nouvelles règles.
 	Objectif
Nous ajoutons un nouveau degré de complexité : le jeu se joue maintenant sur une grille de tic-tac-toe où chaque case est un tic-tac-toe classique. L'objectif est d'aligner 3 marques sur la grille de tic-tac-toe principale en gagnant sur les grilles plus petites.
 	Règles
Lorsqu'un joueur joue sur une petite grille, il décide également de là où pourra jouer le prochain joueur : par exemple, si un joueur joue sur la case située en bas à gauche de l'une des petites grilles, alors le prochain joueur devra jouer sur la petite grille située en bas à gauche de la grille principale.

Si un joueur est envoyé sur une grille déjà gagnée, ou complète, alors le joueur est autorisé à jouer sur n'importe quelle case vide.

Le code source de ce jeu est disponible sur GitHub. Vous aussi, vous pouvez créer votre jeu à l'aide du CodinGame SDK !

 
Conditions de victoire
Vous avez gagné sur 3 grilles de tic-tac-toe alignées. Si aucun joueur n'arrive à aligner 3 marques, le joueur ayant gagné le plus de petites grilles gagne.
 	Entrées du jeu
Entrées pour un tour de jeu
Ligne 1: 2 entiers séparés par un espace opponentRow et opponentCol, les coordonnées jouées par l'adversaire au tour précédent (-1 -1 pour le tout premier tour).
Ligne 2: le nombre d'actions valides pour ce tour de jeu, validActionCount.
Les validActionCount lignes suivantes: 2 entiers séparés par un espace row et col, les coordonnées où vous pouvez jouer.
Sortie pour un tour de jeu
Ligne 1: 2 entiers séparés par un espace row et col.
Contraintes
Temps de réponse premier tour ≤ 1000ms
Temps de réponse par tour ≤ 100ms