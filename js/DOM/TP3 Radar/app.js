var vG = document.getElementById("vitesseGeneral");
var vI = document.getElementById("vitesseInter");
var zone = document.getElementById("zone");

//focus sur zone
zone.addEventListener("focus", function(){
    console.log("focus!");
    countInput();
});



//count sur 5 seconde
function countInput(){
    setTimeout(function(){
        //saisir sur zone
        zone.addEventListener("keydown", function(e){
            console.log("coucu");
        });
    },1000);

};