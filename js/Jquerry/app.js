$("#box1").hide(); // selectione l'elmt avec l'id box1 et le cache
$("#box1").show(); // selectione l'elmt avec l'id box1 et le montre
$(".box:last").hide() // selectionne le dernier element avec la classe box et le cache

//evenement
$("#box1").click(function(){
    console.log("click sur id #box1 !")
})

$("#box1").on("click", function(){
    console.log("click sur id #box1 !")
})

$("#box1").bind("click", function(){
    console.log("click sur id #box1 !")
})

// retirer un ecouteur
$("#box1").preventDefault();

// attendre que tout le document sois chargé
JQuerry(document).ready(
    function($){
        console.log("tout est pret :)")
    }
)
