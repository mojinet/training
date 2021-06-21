var zone = document.getElementById("mainZone"); // recupere la zone d'ecriture
var count = 1; //compteur

//set date du jour
var date = document.getElementById("date");
var ojd = new Date();
date.valueAsDate = ojd;

// evenement sur bouton submit
document.getElementById("submit").addEventListener("click",addNewElement);

function addNewElement(){

    // si les 3 champs sont remplis
    if(document.getElementById("savoir").value != "" &&
        document.getElementById("auteur").value != "" &&
        document.getElementById("date").value != ""){
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

        elDiv.setAttribute("class","box");
        elPhrase.innerText = count + ". " + phrase;
        elAuteur.innerText = "par " + auteur + ", le " + date;

        elDiv.appendChild(elPhrase);
        elDiv.appendChild(elAuteur)

        // ajoute à la zone
        zone.appendChild(elDiv);

        //event
        elDiv.addEventListener("click",delBox);

        //incremente le compteur
        count++;
    }
}

function delBox(e){
    e.currentTarget.style.display = "none";
}