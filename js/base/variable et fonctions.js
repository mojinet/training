/**
 * Demonstration utilisation variable, function et particularité == ===
 */
var unNombre = 2;
var sonDouble = doubler(unNombre);
console.log("le double de", unNombre, "est" , sonDouble);

// == et ===
var uneValeur = "3";    //type string
console.log("valeur == 3", uneValeur == 3);
console.log("valeur === 3", uneValeur === 3);

//undefined
var uneVariableNonDeclarer;
console.log("ma var vaut '%s' dans le programme",uneVariableNonDeclarer);

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