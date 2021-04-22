let drag = document.getElementById("drag");
let drop = document.getElementById("drop");
let speakInfo = document.getElementById("speaker");
let body = document.querySelector("body");

drag.addEventListener('dragstart', dragStart);
drag.addEventListener('dragend', dragEnd);

drop.addEventListener('dragover', dragOver);
drop.addEventListener('dragenter', dragEnter);
drop.addEventListener('dragleave', dragLeave);
drop.addEventListener('drop', dragDrop);

function dragStart() { console.log("start"); setTimeout(() => (this.className = 'invisible'), 0);}
function dragEnd() { console.log("end"); }

function dragOver(e) { e.preventDefault(); console.log("over"); }
function dragEnter(e) { e.preventDefault(); console.log("enter"); }
function dragLeave() { console.log("leave"); }
function dragDrop() { console.log("drop"); drag.style.visibility = "hidden"; drop.setAttribute("src", "GuyInbox.png"); speakInfo.innerText = "Oh... thanks !"; body.style.backgroundColor = "SteelBlue"; }