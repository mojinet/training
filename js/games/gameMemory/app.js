var main = document.getElementById("main");

//creation des carte
var allCards;
var randTab = [0,0,0,0,0,0];
var orderCard = [];
for (let i = 0; i < 12; i++) {
    let randNb;
    let check
    do{
        check = true;
        randNb = parseInt(Math.random() * 6 );
        if(randTab[randNb] < 2){
            check=false;
            randTab[randNb] = randTab[randNb] + 1;
        }
    }while(check);
    orderCard.push(randNb);

    //creation des element
    var cardElm = document.createElement("div");
    cardElm.classList.add("card");
    var frontElm = document.createElement("div");
    frontElm.classList.add("front");
    var backElm = document.createElement("div");
    backElm.classList.add("back");
    backElm.classList.add("bg"+(randNb+1));
    var imgElm = document.createElement("img");
    imgElm.setAttribute("src","img/back.png");
    imgElm.classList.add("icone");

    //append
    frontElm.append(imgElm);
    cardElm.append(frontElm);
    cardElm.append(backElm);
    main.append(cardElm);
}
//fonction sur listener
function listener(e){
    let card = e.currentTarget;
    card.classList.add("return");
    var nodes = card.childNodes;
    var nextCard;

    //renvois la valeur de la carte
    for (const node of nodes) {
        if(node.classList.contains("back")){
            if(node.classList.contains("bg1")){
                nextCard = 1;
            }else if(node.classList.contains("bg2")){
                nextCard = 2;
            }else if(node.classList.contains("bg3")){
                nextCard = 3;
            }else if(node.classList.contains("bg4")){
                nextCard = 4;
            }else if(node.classList.contains("bg5")){
                nextCard = 5;
            }else if(node.classList.contains("bg6")){
                nextCard = 6;
            }
        }
    }
    checkCard(nextCard,card)
}
// selection de toutes les cards et ajoute eventListener qui retourne la carte
var cards = document.querySelectorAll(".card");
for (const card of cards) {
    card.addEventListener("click",listener);
}

// vérifie
var previousCard = -1;
var previousNode = -1;
function checkCard(card, nodes){
    console.log("carte :",card)
    if (previousCard == -1){
        previousCard = card;
        previousNode = nodes;
    }else{

        if (previousCard == card && previousNode != nodes){
            previousCard = -1;
            previousNode = -1;
            // on laisse les carte retourner et on retire les listener
            nodes.removeEventListener("click",listener);
            previousNode.removeEventListener("click",listener);

        }else if(previousCard != card && previousNode != nodes){
            // on retourne les carte a l'envers
            timeOut = setTimeout(function() {
                nodes.classList.remove("return");
                previousNode.classList.remove("return");
                previousCard = -1;
                previousNode = -1;
            }, 1000);
        }
    }

}