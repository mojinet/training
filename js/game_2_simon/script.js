class Game {
    constructor() {
        this.score = document.getElementById("score");
        this.score.innerText = 0;
        this.board = document.getElementById("board");
        this.infoUser = document.getElementById("userInfo")
        this.sequence = [];
        this.playerSequence = [];

        this.do = new Audio("son/do.mp3");
        this.re = new Audio("son/re.mp3");
        this.fa = new Audio("son/fa.mp3");
        this.sol = new Audio("son/sol.mp3");
        this.mi1 = new Audio("son/mioct.mp3");
        this.mi2 = new Audio("son/mioct2.mp3");

        this.initStart();
        this.initCase();
    }

    initStart() {
        let start = document.getElementById("start");
        start.addEventListener('touchstart', () => {
            this.start();
        })
        start.addEventListener('mousedown', () => {
            this.start();
        })
    }

    initCase() {
        for (let i = 1; i <= 4; i++) {
            let currentCase = this.board.querySelector('.case:nth-child(' + i + ')');
            currentCase.addEventListener('touchstart', (ev) => {
                if (i == 1) { this.do.currentTime = 0; this.do.play(); this.playerSequence.push(1);}
                else if (i == 2) { this.re.currentTime = 0; this.re.play(); this.playerSequence.push(2);}
                else if (i == 3) { this.fa.currentTime = 0; this.fa.play(); this.playerSequence.push(3);}
                else if (i == 4) { this.sol.currentTime = 0; this.sol.play(); this.playerSequence.push(4);}
                ev.preventDefault();
            })
            currentCase.addEventListener('mousedown', () => {
                if (i == 1) { this.do.currentTime = 0; this.do.play(); this.playerSequence.push(1);}
                else if (i == 2) { this.re.currentTime = 0; this.re.play(); this.playerSequence.push(2);}
                else if (i == 3) { this.fa.currentTime = 0; this.fa.play(); this.playerSequence.push(3);}
                else if (i == 4) { this.sol.currentTime = 0; this.sol.play(); this.playerSequence.push(4);}
            })            
        }
    }

    nextValue() {
        return (Math.floor(Math.random() * Math.floor(4)) + 1)
    }

    start() {
        document.getElementById("start").style.display = 'none';
        this.infoUser.style.display = 'block';
        this.score.innerText = 0;
        this.sequence = [];
        this.infoUser.innerText = 3;

        let i = 2;
        let interval = setInterval(() => {
            this.infoUser.innerText = i;
            if (i > 0){ this.mi1.currentTime = 0; this.mi1.play(); }
            else if (i == 0){ this.mi2.currentTime = 0; this.mi2.play(); }
            i--;
            if (i == -2) { clearInterval(interval); this.playSequence(); }
        }, 1000)
    }

    returnCase(caseNumber) {
        return this.board.querySelector('.case:nth-child(' + caseNumber + ')');
    }

    playSequence() {
        this.infoUser.innerText = "memorize !";
        this.sequence.push(this.nextValue());
        let indexSequence = 0;
        let lenghtSequence = this.sequence.length;
        let interval = setInterval(() => {
            this.playCase(this.sequence[indexSequence]);
            indexSequence++;
            if (lenghtSequence == 0) { clearInterval(interval); this.playerTurn(); }
            else{ lenghtSequence--; }
        }, 500)
    }

    playCase(caseNumber) {
        this.reinitColorCase()
        if (caseNumber == 1) { this.do.currentTime = 0; this.do.play(); this.returnCase(1).style.backgroundColor = "rgb(255, 0, 0)";}
        else if (caseNumber == 2) { this.re.currentTime = 0; this.re.play(); this.returnCase(2).style.backgroundColor = "rgb(0, 0, 255)" }
        else if (caseNumber == 3) { this.fa.currentTime = 0; this.fa.play(); this.returnCase(3).style.backgroundColor = "rgb(255, 255, 0)" }
        else if (caseNumber == 4) { this.sol.currentTime = 0; this.sol.play(); this.returnCase(4).style.backgroundColor = "rgb(0, 255, 0)" }
    }

    reinitColorCase(){
        this.returnCase(1).removeAttribute("style");
        this.returnCase(2).removeAttribute("style");
        this.returnCase(3).removeAttribute("style");
        this.returnCase(4).removeAttribute("style");
    }

    // trouver un moyen de vérifier que la sequence est entré dans l'ordre
    // si reponse correct attribu un score
    // TODO : ne pusher que quand c'est le tour de l'utilisateur
    playerTurn() {
        this.reinitColorCase()
        this.playerSequence = [];
        this.infoUser.innerText = "it's you'r turn !";
        
        let index = 0;
        let player = this.playerSequence;
        let sequence = this.sequence;
        let winSequence = true;

        var interval = setInterval( ()=>{
            // si l'index actuel est supérieur a l'index de la derniere valeur de sequence on arrete
            if ( (sequence.length - 1) < index ){
                clearInterval(interval);
                console.log("verification terminer");
                if (winSequence){
                    this.playSequence();
                }
            }else{
                // Si le joueur n'a  pas déja soumis sa réponse pour [index]
                if ( player[index] == undefined ){
                    console.log("valeur " + (index + 1) + " en attente ");
                }else{
                    if ( player[index] == sequence[index]){
                        console.log("vrai !");
                        this.score.innerText++;
                    }else{
                        console.log("faux !");
                        winSequence = false;
                        this.infoUser.innerText = "Game Over";
                        document.getElementById("start").style.display = 'block';
                        document.getElementById("start").innerText = "(re)Start";
                        clearInterval(interval);
                    }
                    index++;
                }
            }
            
        },200);

    }
}

let game = new Game();