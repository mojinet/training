const ojd = new Date();
let year = ojd.getFullYear();
const yearChoice = document.querySelector("#year")
let url = `https://calendrier.api.gouv.fr/jours-feries/metropole/${year}.json`;
let jourFerieElm = `<ul>`;
fetch(url)
    .then(response=>response.json())
    .then(data=>{
        for (const key in data){
            if (new Date(key) > ojd){
                let currentDate = new Date(key);
                let nbJourRestant = dayBetweenDate(ojd,currentDate);
                jourFerieElm += `<li> dans ${nbJourRestant} jours ! ${currentDate.toLocaleString('fr-FR',{
                    weekday: 'long',
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',})
            } ${data[key]}</li>`
            }
    
        }
        jourFerieElm += `</ul>`
        document.querySelector("#zone").innerHTML = jourFerieElm;  
    })
    .catch(e=>console.log(e));


// remplis la liste deroule avec les 5 prochaines années
for(let elm of yearChoice){
    elm.innerHTML= year 
    elm.value = year
    year++
}

// A la selection d'un choix de la liste
yearChoice.addEventListener("input",function(){
    let url = `https://calendrier.api.gouv.fr/jours-feries/metropole/${yearChoice.value}.json`;
    let jourFerieElm = `<ul>`;
    fetch(url)
        .then(response=>response.json())
        .then(data=>{
            for (const key in data){
                if (new Date(key) > ojd){
                    let currentDate = new Date(key);
                    let nbJourRestant = dayBetweenDate(ojd,currentDate);
                    jourFerieElm += `<li> dans ${nbJourRestant} jours ! ${currentDate.toLocaleString('fr-FR',{
                        weekday: 'long',
                        year: 'numeric',
                        month: 'long',
                        day: 'numeric',})
                } ${data[key]}</li>`
                }
        
            }
            jourFerieElm += `</ul>`
            document.querySelector("#zone").innerHTML = jourFerieElm;  
        })
        .catch(e=>console.log(e));
})

    //retourne le temps en jours entre 2 dates
    let dayBetweenDate = (ojd,next)=>Math.round((next.getTime() - ojd.getTime())/(1000*3600*24));