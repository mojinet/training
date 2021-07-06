enum Zone {
    "alsace-moselle",
    "guadeloupe",
    "guyane",
    "la-reunion",
    "martinique",
    "mayotte",
    "metropole",
    "nouvelle-caledonie",
    "polynesie-francaise",
    "saint-barthelemy",
    "saint-martin",
    "saint-pierre-et-miquelon",
    "wallis-et-futuna"
}
const ojd:Date = new Date();
const currentYear:number = ojd.getFullYear();

let zone:string = Zone[Zone.metropole];
let url:string = `https://calendrier.api.gouv.fr/jours-feries/${zone}/${currentYear}.json`

fetch(url)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        for(let date of data){
            console.log("test2"+date)
        }
    })
    .catch(e => console.log(`erreur : ${e}`))

