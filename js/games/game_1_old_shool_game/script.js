// dom node
const player = document.getElementById('player');
const main = document.getElementById('main');
const body = document.querySelector('body');
// object type
const OBSTACLE = 1;
const KEY = 2;
const DOOR = 3;
const ENEMY = 4;

class Game {
    constructor() {
        // top and left is position of player
        this.top = 0;
        this.left = 0;
        // id for new object
        this.id = 1;
        this.over = false;
        this.allObject = [];
        this.initKeytListener();
        this.getKey = false;
        this.key = new Object(KEY, this);
        this.door = new Object(DOOR, this);
        // TODO : voir avec combioen d'enemy démarer
        this.enemy1 = new Object(ENEMY, this);
        this.enemy2 = new Object(ENEMY, this);
        this.enemy3 = new Object(ENEMY, this);
        this.enemy4 = new Object(ENEMY, this);

        this.obstacle1 = new Object(OBSTACLE, this);
        this.obstacle2 = new Object(OBSTACLE, this);
        this.obstacle3 = new Object(OBSTACLE, this);
    }

    // init player moove listener and border constraint
    initKeytListener() {
        body.addEventListener('keydown', (e) => {
            let nameOfKey = e.key;
            if (nameOfKey == "s" || nameOfKey == "ArrowDown") {
                // if player don't go out of board game
                if ((this.top + 30) >= 500) { console.log("out of board : top >=500") }
                else {
                    //check if player take a colision with object
                    if (this.playerColision(this.top + 30, this.left)) {
                        this.top += 30;
                        player.style.top = this.top + "px";
                    }
                }
            }
            if (nameOfKey == "z" || nameOfKey == "ArrowUp") {
                if ((this.top) <= 0) { console.log("out of board : top <= 0") }
                else {
                    if (this.playerColision(this.top - 30, this.left)) {
                        this.top -= 30;
                        player.style.top = this.top + "px";
                    }
                }
            }
            if (nameOfKey == "q" || nameOfKey == "ArrowLeft") {
                if ((this.left) <= 0) { console.log("out of board : left <= 0") }
                else {
                    if (this.playerColision(this.top, this.left - 30)) {
                        this.left -= 30;
                        player.style.left = this.left + "px";
                    }
                }
            }
            if (nameOfKey == "d" || nameOfKey == "ArrowRight") {
                if ((this.left + 30) >= 500) { console.log("out of board : left >=500") }
                else {
                    if (this.playerColision(this.top, this.left + 30)) {
                        this.left += 30;
                        player.style.left = this.left + "px";
                    }
                }
            }

        })
    }

    // check the colision between player and object in board
    playerColision(top, left) {
        let moove = true;
        this.allObject.forEach((objet) => {
            if (top == objet.top && left == objet.left) {
                if (objet.type == OBSTACLE) { console.log('this move is impossible : obstacle ! '); moove = false; }
                else if (objet.type == KEY) { console.log('you get a key !'); this.getKey = true; document.querySelector(".key").style.display = "none"; }
                else if (objet.type == DOOR) {
                    if (this.getKey) { main.innerHTML = "<h1>WIN !</h1>" }
                    else { console.log('need a key !'); }
                }
                else if (objet.type == ENEMY) { console.log('you loose !'); main.innerHTML = "<h1>GAME OVER !</h1>"; this.over = true; this.allObject = []; }
            }
        })
        return moove;
    }
}

class Object {
    constructor(type, game) {
        this.type = type;
        this.id = game.id;
        this.top;
        this.left;
        this.buildObject(this.type, game);
        this.node;
    }

    buildObject(type, game) {
        // randomize position
        let position = this.randomPosition();

        // create new element
        //TODO : attention de ne pas mettre un objet ou il y en aurais deja un + eviter boucle infinie au cas ou il n'y est aucune place
        let objet = document.createElement('div');
        objet.style.top = position[0] + "px";
        this.top = position[0];
        this.left = position[1];
        objet.style.left = position[1] + "px";
        objet.style.position = "absolute";

        // with good class
        if (type == OBSTACLE) { objet.className = "obstacle"; }
        else if (type == KEY) { objet.className = "key"; }
        else if (type == DOOR) { objet.className = "door"; }
        // Todo : si c'est un ennemie : deplacement automatique toute les x secondes + si colission avec un autre enemy = pop d'un nouveau
        else if (type == ENEMY) {
            objet.className = "enemy";
            let interval = setInterval(() => {
                // random enemy move
                let rand = (Math.floor(Math.random() * Math.floor(4))) + 1;
                switch (rand) {
                    case 1:
                        if ((this.top + 30) >= 500) {
                            this.top -= 30; this.node.style.top = this.top + "px"; break;
                        } else { this.top += 30; this.node.style.top = this.top + "px"; break; }
                    case 2:
                        if (this.top <= 0) {
                            this.top += 30; this.node.style.top = this.top + "px"; break;
                        } else { this.top -= 30; this.node.style.top = this.top + "px"; break; }
                    case 3:
                        if ((this.left + 30) >= 500) {
                            this.left -= 30; this.node.style.left = this.left + "px"; break;
                        } else { this.left += 30; this.node.style.left = this.left + "px"; break; }
                    case 4:
                        if (this.left <= 0) {
                            this.left += 30; this.node.style.left = this.left + "px"; break;
                        } else { this.left -= 30; this.node.style.left = this.left + "px"; break; }
                }

                // if colision with player : game is over
                if (this.top == game.top && this.left == game.left) {
                    main.innerHTML = "<h1>GAME OVER !</h1>"
                    game.over = true;
                    game.allObject = [];
                }

                //if colision with other enemy : create new
                game.allObject.forEach((Nobjet) => {
                    if (Nobjet.type == ENEMY && Nobjet.top == this.top && Nobjet.left == this.left && Nobjet.id != this.id) {
                        let enemy = new Object(ENEMY, game);
                    }
                })

                //if game over stop interval
                if (game.over) {
                    clearInterval(interval);
                }
            }, 300 * (Math.floor(Math.random() * Math.floor(3)) + 1))
        }

        // position it on board 
        this.node = objet;
        game.id += 1;
        main.appendChild(objet);
        game.allObject.push(this);
        return objet;
    }

    // return random position[top,left]
    randomPosition() {
        let position = [];
        // random (between 1 and 16) * 30
        let top = (Math.floor(Math.random() * Math.floor(16))) * 30;
        let left = (Math.floor(Math.random() * Math.floor(16))) * 30;
        position.push(top);
        position.push(left);
        this.top = top;
        this.left = left;
        return position;
    }

}

var game = new Game();