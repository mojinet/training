class SavoirInutile {
    constructor() {
        this.count = 1;
    }

    ajouter() {
        // si les 3 champs sont remplis
        if (document.getElementById("savoir").value != "" &&
            document.getElementById("auteur").value != "" &&
            document.getElementById("date").value != "") {
            //recupere les elements
            var phrase = document.getElementById("savoir").value;
            var auteur = document.getElementById("auteur").value;
            var date = document.getElementById("date").value;

            //clean les input
            document.getElementById("savoir").value = "";
            document.getElementById("auteur").value = "";

            //créer le nouvel element
            var elDiv = document.createElement("div");
            var elPhrase = document.createElement("h2");
            var elAuteur = document.createElement("h6");

            elDiv.setAttribute("class", "box");
            elPhrase.innerText = this.count + ". " + phrase;
            elAuteur.innerText = "par " + auteur + ", le " + date;

            elDiv.appendChild(elPhrase);
            elDiv.appendChild(elAuteur)

            // ajoute à la zone
            zone.appendChild(elDiv);

            //event
            elDiv.addEventListener("click", this.suprimer);

            //incremente le compteur
            this.count++;
            return elDiv;
        }
    }
    suprimer(e) {
        e.currentTarget.style.display = "none";
    }
}

var savoirInutile = new SavoirInutile();
var tabSavoir = [];

// recupere la zone d'ecriture
var zone = document.getElementById("mainZone");

//set date du jour
document.getElementById("date").valueAsDate = new Date();

// evenement sur bouton submit
document.getElementById("submit").addEventListener("click",function(){ tabSavoir.push(savoirInutile.ajouter()); });

//evenement sur les radio
var choices = document.getElementsByName("choice");
var choice;
for (const choicesElm of choices) {
    choicesElm.addEventListener("click",filter);
    if(choicesElm.checked == true) {
        choice = choicesElm.value;
    }
}

// retourne le nom du filtre actuel et remet en page si selection
function filter(){
    for (const choicesElm of choices) {
        if(choicesElm.checked == true) {
            choice = choicesElm.value;
        }
    }
    getorder(choice);
    return choice;
}

// tri les resultat differement
function getorder(choice){
    switch (choice){
        case "defaut" : console.log("d");
        break;
        case "ordreAlpha" : console.log("a");
        break;
        case "ordreChrono" : console.log("c");
        break;
    }
}

