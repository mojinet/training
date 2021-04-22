const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d'); 

let x = document.getElementById('x');
let y = document.getElementById('y');
let w = document.getElementById('w');
let h = document.getElementById('h');
let color = document.getElementById('color');
let rendering = document.getElementById('render');

class Canvas{
    constructor(){
        this.initEvent();
        this.makeGrid(20);
    }

    makeGrid(space){
        ctx.beginPath();
        ctx.setLineDash([1, 2]);
        // set y line
        for (let x = space; x < 500; x +=space){
            ctx.moveTo(x,0);
            ctx.lineTo(x,500);
        }
        // set x line
        for (let y = space; y < 500; y +=space){
            ctx.moveTo(0,y);
            ctx.lineTo(500,y);
        }
        // render
        ctx.stroke();
    }

    clearRect(){
        ctx.clearRect(0,0,500,500);
    }

    initEvent(){
        rendering.addEventListener('click', ()=>{
            this.clearRect();
            this.makeGrid(20);
            let vX = parseInt(x.value);
            let vY = parseInt(y.value);
            let vW = parseInt(w.value);
            let vH = parseInt(h.value);
            this.drawRect(vX,vY,vW,vH,color.value);
        });
    }
    
    drawRect(x,y,w,h,color){
        ctx.fillStyle = color;
        ctx.fillRect(x,y,w,h);
    }

}

let render = new Canvas();