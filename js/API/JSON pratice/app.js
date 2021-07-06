/************/
/* PROMESSE */
/************/

// Creation d'une promesse
const promiseGetUsers = new Promise((resolve,reject) =>{
    if(typeof USERS !== 'undefined'){ // renvois le resolve
        resolve(USERS);
    }else{
        reject('Acces au users impossible')
    }
});

//Recupere l'état de la promesse
promiseGetUsers
    .then( users => users.length)// fonction fléché recuperes param users et renvois la taille
    .then( nbUsers => console.log(nbUsers)) // recupere la taille et l'affiche
    .catch( erreur => console.log(erreur)); //en cas d'erreur de chargement

/*************/
/* Fetch API */
/*************/

let cp = document.getElementById("cp");
cp.addEventListener('input',function(){ // input = a chaque saisie d'un caractere
    // Si l'utilisateur tappe 5 caracteres
    if (cp.value.length == 5){
        let url = `https://geo.api.gouv.fr/communes?codePostal=${this.value}`; // URL de l'api
        fetch(url) //ajax
            .then(response => response.json()) // recupere la reponse et la parse en json
            .then(data => { // response.json() est renommé en data et chaque itération est afficher
                let affichage = `<ul>`;
                for(let ville of data){
                    affichage += `<li> ${ville.nom} </li>`;
                }
                affichage += `</ul>`;
                document.querySelector("#info").innerHTML = affichage; // remplace et affiche
            });
    }
})

/***************/
/* Async Await */ // fait la meme chose qu'au dessus
/***************/

async function maFonction() {
    try{
        const response = await fetch("https://geo.api.gouv.fr/communes?codePostal=77100");
        const data = await response.json();
        console.log(data);
    }catch(e){
        console.log(e);
    }
}

maFonction();

