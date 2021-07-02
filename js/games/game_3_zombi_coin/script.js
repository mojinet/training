const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
const body = document.querySelector('body');
const score = document.getElementById('score');
const imgPlayer = document.getElementById('player');
//
let imgEnemy = [];
for (let i = 1; i <= 16; i++){
    let pixEnemy = new Image();
    pixEnemy.src = 'enemy' + i + '.png';
    imgEnemy.push(pixEnemy);
}
/* const imgEnemy1 = document.getElementById('enemy1');
const imgEnemy2 = document.getElementById('enemy2');
let imgEnemy = [imgEnemy1,imgEnemy2]; */
//
const coin = document.getElementById('coin');
// game values
const playerSize = 40;
const NumberOfCases = 20;
const boardSize = NumberOfCases * playerSize;
const intervalTimePlayer = 100;
const intervalTimeEnemy= 200;


class Game{
    constructor(){
        this.direction;
        this.xPosition = 0;
        this.yPosition = 0;
        this.initEvent();
        this.initSizeCanvas();
        this.initPlayer();
        this.refresh();
        this.enemyList = [];
    }

    initSizeCanvas(){
        canvas.width = boardSize;
        canvas.height = boardSize;
    }

    initEvent(){
        body.addEventListener('keydown', (e)=>{
            console.log(e.key);
            switch (e.key){
                case "z" :
                    this.direction = "z";
                    break;
                case "q" :
                    this.direction = "q";
                    break;
                case "s" :
                    this.direction = "s";
                    break;
                case "d" :
                    this.direction = "d";
                    break;            
            }
        });
    }

    drawPlayer(){
        ctx.drawImage(imgPlayer,this.xPosition,this.yPosition,playerSize,playerSize);
    }

    initPlayer(){
        ctx.drawImage(imgPlayer,0,0,playerSize,playerSize);        
    }

    refresh(){
        let intervalPlayer = setInterval(()=>{
            switch(this.direction){
                case "z" :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = "z";
                    this.yPosition < playerSize ? this.yPosition = (boardSize - playerSize) : this.yPosition -= playerSize;
                    this.drawPlayer()
                    break;
                case "q" :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = "q";
                    this.xPosition < playerSize ? this.xPosition = (boardSize - playerSize) : this.xPosition -= playerSize;
                    this.drawPlayer()
                    break;
                case "s" :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = "s";
                    this.yPosition >= (boardSize - playerSize) ? this.yPosition = 0 : this.yPosition += playerSize;
                    this.drawPlayer()
                    break;
                case "d" :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = "d";
                    this.xPosition >= (boardSize - playerSize) ? this.xPosition = 0 : this.xPosition += playerSize;
                    this.drawPlayer()
                    break;
            }
        },intervalTimePlayer);
    }
}

class Enemy{
    constructor(type = 1){
        this.xPosition;
        this.yPosition;
        this.rand = Math.floor(Math.random() * Math.floor(16) );
        this.initPosition();
        this.direction;
        this.refresh(type);
        game.enemyList.push(this);
    }


    initPosition(){
        this.xPosition = (Math.floor( Math.random()*Math.floor(NumberOfCases) ))*playerSize;
        this.yPosition = (Math.floor( Math.random()*Math.floor(NumberOfCases) ))*playerSize;

        ctx.drawImage(imgEnemy[this.rand] ,this.xPosition,this.yPosition,playerSize,playerSize);
    }

    drawNewPosition(){
        ctx.drawImage(imgEnemy[this.rand] ,this.xPosition,this.yPosition,playerSize,playerSize);
    }
    
    typeFolow(type){
        switch(type){
            case 1 :
                if (this.xPosition == game.xPosition){
                    if (this.yPosition > game.yPosition){ this.direction = 1}
                    else if (this.yPosition < game.yPosition){ this.direction = 3}
                }
                else if (this.yPosition == game.yPosition){ 
                    if (this.xPosition < game.xPosition){this.direction = 4}
                    else if (this.xPosition > game.xPosition){ this.direction = 2}
                }else{
                    if (this.yPosition > game.yPosition){ this.direction = 1}
                    else if (this.yPosition < game.yPosition){ this.direction = 3}
                    else if (this.xPosition < game.xPosition){this.direction = 4}
                    else if (this.xPosition > game.xPosition){ this.direction = 2}
                }
                break;
            case 2 :    
                if (this.xPosition == game.xPosition){
                    if (this.yPosition > game.yPosition){ this.direction = 1}
                    else if (this.yPosition < game.yPosition){ this.direction = 3}
                }
                else if (this.yPosition == game.yPosition){ 
                    if (this.xPosition < game.xPosition){this.direction = 4}
                    else if (this.xPosition > game.xPosition){ this.direction = 2}
                }else{
                    if (this.xPosition < game.xPosition){this.direction = 4}
                    else if (this.xPosition > game.xPosition){ this.direction = 2}
                    else if (this.yPosition > game.yPosition){ this.direction = 1}
                    else if (this.yPosition < game.yPosition){ this.direction = 3}

                }
                break;
                case 3 :
                    this.typeFolow(Math.floor(Math.random()*Math.floor(2))+1);
                    break;
        }

    }

    refresh(type){
        let intervalEnemy = setInterval(()=>{
            // find the direction to player
            if (this.xPosition == game.xPosition && this.yPosition == game.yPosition){body.innerHTML = "<h1>Game Over</h1>"}
            this.typeFolow(type);

            switch(this.direction){
                case 1 :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = 1;
                    this.yPosition < playerSize ? this.yPosition = (boardSize - playerSize) : this.yPosition -= playerSize;
                    this.drawNewPosition()
                    break;
                case 2 :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = 2;
                    this.xPosition < playerSize ? this.xPosition = (boardSize - playerSize) : this.xPosition -= playerSize;
                    this.drawNewPosition()
                    break;
                case 3 :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = 3;
                    this.yPosition >= (boardSize - playerSize) ? this.yPosition = 0 : this.yPosition += playerSize;
                    this.drawNewPosition()
                    break;
                case 4 :
                    ctx.clearRect(this.xPosition, this.yPosition,playerSize,playerSize);
                    this.direction = 4;
                    this.xPosition >= (boardSize - playerSize) ? this.xPosition = 0 : this.xPosition += playerSize;
                    this.drawNewPosition()
                    break;
            }
            if (this.xPosition == game.xPosition && this.yPosition == game.yPosition){body.innerHTML = "<h1>Game Over</h1>"}
        },intervalTimeEnemy);
    }
}

class Coin{
    constructor(){
        this.score =0;
        this.xPosition;
        this.yPosition;
        this.initPosition();
    }

    initPosition(){
        this.xPosition = (Math.floor( Math.random()*Math.floor(NumberOfCases) ))*playerSize;
        this.yPosition = (Math.floor( Math.random()*Math.floor(NumberOfCases) ))*playerSize;

        let intervalCoin = setInterval(()=>{
            ctx.drawImage(coin ,this.xPosition,this.yPosition,playerSize,playerSize);
            if (game.yPosition == this.yPosition && game.xPosition == this.xPosition){
                this.score += 1;
                score.innerText = this.score;
                this.xPosition = (Math.floor( Math.random()*Math.floor(NumberOfCases) ))*playerSize;
                this.yPosition = (Math.floor( Math.random()*Math.floor(NumberOfCases) ))*playerSize;   
                let enemy = new Enemy((Math.floor( Math.random()*Math.floor(3)+1 )));             
            }
        },intervalTimePlayer);  
    }
}

let game = new Game();
let coin1 = new Coin();

