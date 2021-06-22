/**
 * Demonstration utilisation variable, function et particularité == ===
 */
let varLimite = 2;  //variable de porté limite au bloc
var unNombre = 2;   //variable de porté global
var sonDouble = doubler(unNombre);
console.log("le double de", unNombre, "est" , sonDouble);

// == et ===
var uneValeur = "3";    //type string
console.log("valeur == 3", uneValeur == 3); //true
console.log("valeur === 3", uneValeur === 3); //false

//undefined
var uneVariableNonDeclarer;
console.log("ma var vaut '%s' dans le programme", uneVariableNonDeclarer); // undefined

// affiche une variable qui n'a pas été déclarer avec var et est donc global
attention();
console.log(valeurTest);

//fonction
function doubler(param){
    return param * 2;
}

function attention(){
    valeurTest = "oulala";
}

//Objet disponible : Math
var nbAleatoire = Math.random()*100;
console.log(nbAleatoire);
// convertir en valeur entiere : méthode global
nbAleatoire = parseInt(nbAleatoire);
console.log(nbAleatoire);

//Gestion des date
var noel2018 = new Date(2018,11,25); // 11 = decembre
console.log(noel2018);
console.log(noel2018.toLocaleDateString());
console.log(noel2018.getDate(),noel2018.getMonth(), noel2018.getDay());

//Création d'un objet
var objet ={
    cle: "maClef",
    afficher:function(){console.log("la clé est : " + this.cle);}
}
// On peut lui ajouter des propriété
objet.cle2 = "maClef2";

// Lecture des attributs
console.log(objet.cle2);
objet.afficher();

// Déclarer une classe
var Sport = function(nom, description){
    // définir les attributs
    this.nom = nom || "pas de nom";
    this.description = description || "NC";
    // fonction
    this.afficher = function(){
        console.log(this.nom, this.description)
    }
}

var foot = new Sport("foot","un jeu de balle");
foot.afficher();

class Sport2{
    constructor(nom, description) {
        this.nom = nom || "NC";
        this.description = description || "NC";
    }

    afficher(){
        console.log(this.nom, this.description)
    }
}

var handBall = new Sport2("handBall","un jeu de balle avec les mains");
handBall.afficher();

// héritage
class SportDeCombat2 extends Sport2{
    test(){
        console.log("nouvelle methode");
    }
};

var sc2 = new SportDeCombat2("boxe","ça tappe");
sc2.afficher();
sc2.test();