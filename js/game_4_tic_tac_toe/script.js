class Game {

    constructor(){
        this.board = document.getElementById("l-main");
        this.FREE = 0;
        this.IA = 1;
        this.PLAYER = 2;
        this.boardStates = [
            [0,0,0],
            [0,0,0],
            [0,0,0]
        ];
        this.addEventOnCases();
        this.end = false;
    }

    addEventOnCases(){
        for (let x = 1; x <= 3; x++){
            let curentRow = this.board.querySelector(".row:nth-child(" + x + ")");
            for (let y = 1; y <= 3; y++){
                let curentCase = curentRow.querySelector(".case:nth-child(" + y + ")");
                curentCase.addEventListener("click", () =>{
                    if (this.end != true){
                        this.playedCase(x,y);
                    } 
                })
            }
        }
    }

    writeUser(msg){
        document.getElementById("msguser").innerText = msg;
    }

    playedCase(x,y){
        // if case is free
        if (this.boardStates[x-1][y-1] == this.FREE ){
            this.boardStates[x-1][y-1] = this.PLAYER;
            this.getCurentCase(x-1,y-1).innerText = "X";

            // if player win the game
            if ( this.win(this.PLAYER) ){
                this.writeUser("player win !")
                this.end = true;

            // if player don't win the game and game is not finish (continue = true)  
            }else{
                if (this.gameContinue()){
                    this.iaPlay();
                }else{
                    this.writeUser("Equality");
                    this.end = true;
                }
                
            }
        // case already played    
        }else{
            this.writeUser("this case is already used")
        }
    }

    gameContinue(){
        let notFinish = false;
        for (let x = 0; x <= 2; x++){
            for (let y = 0; y <= 2; y++){
                if (this.boardStates[x][y] == this.FREE){
                    notFinish = true;
                    break;
                }
            }
        }
        return notFinish;
    }

    iaPlay(){
        let stopWhile = false;
        while (stopWhile == false){
            let x = this.getRandomInt(3);
            let y = this.getRandomInt(3);
            if (this.boardStates[x][y] == this.FREE){
                this.boardStates[x][y] = this.IA;
                let curentCase = this.getCurentCase(x,y);
                curentCase.innerText = "O";
                curentCase.style.color = "blue";
                stopWhile = true;
                if (this.win(this.IA)){
                    this.writeUser("IA WIN");
                    this.end = true;
                }
            }
        } 
    }

    getCurentCase(x,y){
        return this.board.querySelector(".row:nth-child(" + (x + 1) + ")").querySelector(".case:nth-child(" + (y + 1) + ")");
    }

    getRandomInt(max) {
        return Math.floor(Math.random() * Math.floor(max));
    }

    //check if this game is win by user
    win(player){
        if (
            // row position
            (this.boardStates[0][0] == player && this.boardStates[0][1] == player && this.boardStates[0][2] == player) ||
            (this.boardStates[1][0] == player && this.boardStates[1][1] == player && this.boardStates[1][2] == player) ||
            (this.boardStates[2][0] == player && this.boardStates[2][1] == player && this.boardStates[2][2] == player) ||

            // col position
            (this.boardStates[0][0] == player && this.boardStates[1][0] == player && this.boardStates[2][0] == player) ||
            (this.boardStates[0][1] == player && this.boardStates[1][1] == player && this.boardStates[2][1] == player) ||
            (this.boardStates[0][2] == player && this.boardStates[1][2] == player && this.boardStates[2][2] == player) ||

            // diag position
            (this.boardStates[0][0] == player && this.boardStates[1][1] == player && this.boardStates[2][2] == player) ||
            (this.boardStates[0][2] == player && this.boardStates[1][1] == player && this.boardStates[2][0] == player)             
        ){
            return true;
        }else{
            return false;
        }
    }

}


// Run the game
let game = new Game();
