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