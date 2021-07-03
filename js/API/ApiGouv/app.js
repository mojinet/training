const ojd = new Date();
console.log(ojd.toLocaleDateString())
const year = ojd.getFullYear();
const url = `https://calendrier.api.gouv.fr/jours-feries/metropole/${year}.json`;
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

    let dayBetweenDate = (ojd,next)=>Math.round((next.getTime() - ojd.getTime())/(1000*3600*24))
    