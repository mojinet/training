/**
 * Exercice 1 : écrivez un programme qui s'arrête lorsque tous les nombres de la plage [0;100[ sont tirés au sort.
 */
console.log("****************Exercice 1");

var numberArray = [];

//Tant que le tableaux n'est pas remplis on ajoute un nombre aléatoire
while(!checkNumber()){
    let randomNumber = parseInt(Math.random()*100);
    if (numberArray[randomNumber] == undefined){
        numberArray[randomNumber] = 1
    }else{
        numberArray[randomNumber]++;
    }
}


//Vérifie si tout le tableau est remplis ou non
function checkNumber(){
let check = true;
    for (let i = 0; i < 100; i++) {
        if (numberArray[i] == undefined){
            check = false;
        }
    }
return check;
}

//Affiche le resultat
console.log(numberArray.join(' '))

/**
 * Exercice 2 :Trier les lettres d'une phrase par ordre alphabétique.
 */
console.log("****************Exercice 2");

var str = "une chaine avec des lettres dans un certain ordre pour donner du sens";
str = str.replaceAll(' ','')    // retire les espaces
var strArray = str.split('');               // créer un tableau
strArray.sort();                                    // tri
console.log(strArray.join(' '));                    // sépare les données avec un espace pour affichage

/**
 * Exercice 3 : Mettre en majuscule la première lettre de chaque mot d une phrase.
 */
console.log("****************Exercice 3");

str = "une phrase sans majuscule";
str = str.split(' ');
for (let i = 0; i < str.length; i++) {
    str[i] = firstInMaj(str[i]);
}

// Retourne un mot avec la premiere lettre en majuscule
function firstInMaj(word){
    let goodWord = "";
    for (let i = 0; i < word.length; i++) {
        if(i==0){
            goodWord += word.charAt(0).toUpperCase();
        }else{
            goodWord += word.charAt(i);
        }
    }
    return goodWord;
}

console.log(str.join(' '));