Objectif
Votre objectif est de produire un maximum de cyborgs afin de détruire ceux de votre adversaire. Pour cela, vous devrez prendre le contrôle d'usines qui vous permettront d'augmenter la taille de votre armée de cyborgs.
 	Règles
Le jeu se joue à 2 joueurs sur un plateau sur lequel est disposé un nombre variable d'usines (de 7 à 15 usines). Chaque joueur détient initialement une seule usine dans laquelle il dispose d'un stock de 15 à 30 cyborgs. Les autres usines sont neutres et disposent également de quelques cyborgs les défendant.

À chaque tour, le joueur peut décider d'envoyer une troupe constituée du nombre de cyborgs de son choix depuis une usine vers une autre usine. Cette troupe mettra entre 1 et 20 tours pour atteindre sa destination. Lorsque la troupe arrive, elle se bat contre les cyborgs ennemis si l'usine ne lui appartient pas, sinon les cyborgs viennent simplement s'ajouter au stock de cette usine.

Emplacement des usines :

L'emplacement des usines est fixé aléatoirement au début de chaque partie. Le joueur reçoit en entrée la distance entre chaque usine, exprimée en nombre de tours nécessaires pour aller d'une usine à l'autre.

Tour de jeu :

Voici l'ordre dans lequel un tour de jeu se déroule :

Avancée des troupes existantes
Exécution des ordres de chaque joueur
Production de nouveaux cyborgs dans chaque usine
Résolution des combats
Vérification des conditions de fin
 

Production de cyborgs :

À chaque tour, chaque usine non neutre produit entre 0 et 3 cyborgs.


 

Combats :

Pour conquérir une usine, vous devez envoyer des cyborgs vers l'usine convoitée. Les combats se déroulent dans cet ordre :

Les cyborgs qui arrivent au même tour dans une usine commencent par se battre entre eux.
Les cyborgs restants se battent avec les cyborgs déjà présents dans l'usine (attention, les cyborgs qui partent au même tour ne participent pas).
Si le nombre de cyborgs en attaque est strictement supérieur au nombre de cyborgs présents en défense, alors l'usine appartient désormais au joueur qui attaque et produira des cyborgs pour ce joueur dès le prochain tour.

 

 
Conditions de victoire
Votre adversaire ne possède plus de cyborgs, ni d'usines pouvant produire de nouveaux cyborgs.
À la fin du nombre maximum de tours (200), vous disposez de plus de cyborgs que votre adversaire.
 	Entrées du jeu
Entrées d'initialisation
Ligne 1 : en entier factoryCount, le nombre d'usines.
Ligne 2 :linkCount, le nombre de liaisons entre les usines.
Les linkCount lignes suivantes : 3 entiers séparés par un espace factory1, factory2 et distance. distance est le nombre de tours nécessaires pour envoyer une troupe de factory1 à factory2 (et inversement).
Entrées pour un tour de jeu
Ligne 1 : un entier entityCount le nombre d'entités.
Les entityCount lignes suivantes : un entier entityId, une chaîne de caractères entityType suivi par 5 entiers arg1, arg2, arg3, arg4 et arg5.

Si entityType vaut FACTORY alors les arguments représentent :
arg1 : joueur qui possède l'usine : 1 pour vous, -1 pour l'adversaire et 0 si neutre
arg2 : nombre de cyborgs dans l'usine
arg3 : production de l'usine (entre 0 et 3)
arg4 : inutilisé
arg5 : inutilisé
Si entityType vaut TROOP alors les arguments représentent :
arg1 : joueur qui possède la troupe : 1 pour vous, -1 pour l'adversaire
arg2 : identifiant de l'usine de départ
arg3 : identifiant de l'usine d'arrivée
arg4 : nombre de cyborgs au sein de la troupe (entier strictement positif)
arg5 : nombre de tours avant d'arriver à destination (entier strictement positif)
Sortie pour un tour de jeu
La liste des actions possibles est :
MOVE source destination cyborgCount : crée une troupe de cyborgCount cyborgs partant de l'usine dont l'identifiant est source à destination de l'usine dont l'identifiant est destination. Exemple : MOVE 2 4 12 enverra 12 cyborgs de l'usine 2 vers l'usine 4.
WAIT : ne fait rien.
Si vous essayez de déplacer plus de cyborgs qu'il n'y a de cyborgs dans l'usine de départ, toutes les unités disponibles sont envoyées.
Contraintes
7 ≤ factoryCount ≤ 15
21 ≤ linkCount ≤ 105
1 ≤ distance ≤ 20
Temps de réponse premier tour ≤ 1000ms
Temps de réponse par tour ≤ 50ms

Qu'est-ce qui m'attend dans les prochaines ligues ?

Les nouvelles règles débloquées dans les prochaines ligues sont :
Possibilité d'exécuter plusieurs commandes dans le même tour
Possibilité d'envoyer des bombes
Possibilité d'augmenter la production d'une usine