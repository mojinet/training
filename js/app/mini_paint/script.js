// Dom node
var plateau = document.getElementById("l-main");
var picker = document.getElementById("l-picker");

// Button Click
document.getElementById("randomPix").addEventListener("click", function () { generatePix() });
document.getElementById("clear").addEventListener("click", function () { clear() });

// global param
var xSize = 9;
var ySize = 9;
var curentColor = "red";

// build a paint zone with size(x,y), add an event per case
var buildPaintZone = function (xSize, ySize) {
    let paintZone = plateau;
    let newDiv = function (className) {
        let div = document.createElement("div");
        div.className = className;
        return div;
    }
    for (let x = 1; x <= xSize; x++) {
        paintZone.appendChild(newDiv("row"));
        for (let y = 1; y <= ySize; y++) {
            let curCase = document.querySelector(".row:nth-child(" + x + ")").appendChild(newDiv("case"));
            curCase.addEventListener('mousedown', function () {
                colorCase(x, y, curentColor);
            })
        }
    }
}

// refresh value of currentColor when click on picker's color
for (let i = 1; i <= 5; i++) {
    let color = picker.querySelector(".color:nth-child(" + i + ")");
    color.addEventListener('mousedown', function () {
        curentColor = color.getAttribute("data-color");
    })
}

// color all paintZone with random color rgb
var generatePix = function () {
    for (let x = xSize; x >= 1; x--) {
        let row = plateau.querySelector(".row:nth-child(" + x + ")");
        for (let y = 1; y <= ySize; y++) {
            let sCase = row.querySelector(".case:nth-child(" + y + ")")
            let rand = random(5);
            switch (rand) {
                case 1: colorCase(x, y, "red"); break;
                case 2: colorCase(x, y, "green"); break;
                case 3: colorCase(x, y, "blue"); break;
                case 4: colorCase(x, y, "white"); break;
                case 5: colorCase(x, y, "black"); break;
            }
        }
    }
}

// clear paintZone
var clear = function () {
    for (let x = xSize; x >= 1; x--) {
        let row = plateau.querySelector(".row:nth-child(" + x + ")");
        for (let y = 1; y <= ySize; y++) {
            let sCase = row.querySelector(".case:nth-child(" + y + ")")
            colorCase(x, y, "white");
        }
    }
}

// keyboard control
window.addEventListener(("keydown"), function (e) {
    switch (e.key) {
        case "a":
            curentColor = "red";
            break;
        case "z":
            curentColor = "green";
            break;
        case "e":
            curentColor = "blue";
            break;
        case "r":
            curentColor = "white";
            break;
        case "t":
            curentColor = "black";
            break;
        case "y":
            clear();
            break;
        case "u":
            generatePix();
            break;
    }
})

// Paint case(x,y) void
var colorCase = function (x = 1, y = 1, color) { selectCase(x, y).style.backgroundColor = color; }

// return node : case(x,y)
var selectCase = function (x, y) { return plateau.querySelector(".row:nth-child(" + x + ")").querySelector(".case:nth-child(" + y + ")"); }

// return int : random between 1 and max
var random = function (max) { return 1 + Math.floor(Math.random() * max); }

// return string : color of case(x,y)
var infoColor = function (x, y) { return selectCase(x, y).style.backgroundColor; }

// Build !
buildPaintZone(xSize, ySize);