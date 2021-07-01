/* PROMESSE test avec fichier locaux */

// Creation d'une promesse
const promiseGetUsers = new Promise((resolve,reject) =>{
    setTimeout(function(){
        if(typeof USERS !== 'undefined'){
            resolve(USERS);
        }else{
            reject('Acces au users impossible')
        }
    },2000)
});

//Recupere l'état de la promesse
promiseGetUsers
    .then( users =>{
        console.log(users);
        return users.length;
    })
    .then( nbUsers =>{
        console.log(nbUsers);
    })
    .catch( erreur =>{
        console.log(erreur);
    })

/* Fetch API */

let cp = document.getElementById("cp");

cp.addEventListener('input',function(){
    // au moins 5 caractere
    if (cp.value.length == 5){
        let url = 'https://geo.api.gouv.fr/communes?codePostal='+this.value;
        fetch(url)
            .then(response => response.json())
            .then(data => {
                let affichage = `<ul>`;
                for(let ville of data){
                    affichage += `<li> ${ville.nom} </li>`;
                }
                affichage += `</ul>`;
                document.querySelector("#info").innerHTML = affichage;
            });
    }
})