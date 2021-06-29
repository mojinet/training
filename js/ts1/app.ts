//declaration de variable
let count:number = 0;
let check:boolean = false;
let nom:string = "Moi";
let maVar:any = 10;

//dans une fonction
function bonjour(qui:string){
    console.log("bonjour " + qui)
}

//enuméré
enum Language{PHP,JavaScript,HTML,CSS,Java,"C++"}
let monLanguagePrefere = Language.JavaScript;

if (monLanguagePrefere == Language.JavaScript){
    console.log("oui c'est le meilleur")
}else{
    console.log("bof bof")
}

//class
class Sport{
    public nom:string;
    private description:string;

    constructor(nom:string, description:string){
        this.nom = nom;
        this.description = description;
    }

    public afficher():void{
        console.log(this.nom,this.description);
    }
}

//héritage
class SportCombat extends Sport{
    constructor(nom:string,description:string) {
        super(nom,description);
    }

    public afficher():void{
        super.afficher();
        console.log("c'est un sport un peu spécial !")
    }
}

let sport:SportCombat = new SportCombat("Boxe","est un sport de combat");
sport.afficher();

//interface
interface Jouable{
    score?:string; // element optionnel noté avec le ?
    jouer(joueur1:string, joueur2:string):string;
}
// Implémentation
class SportJouable extends Sport implements Jouable{

    jouer(joueur1: string, joueur2: string): string {
        return "Les deux joueurs jouent !";
    }

}

//interface de méthode
interface jouer{
    (joueur1:string, joueur2:string):string;
}
// Implmentation
var jouerAuBadminton: jouer = function(joueur1:string, joueur2:string):string{
    return "";
}

// tableau avec genériques
let genericArray: Array<number> = [5,6,7];

// avec une classes qui utilisent des generiques
class Sandwich<V extends Viande, L extends Legume>{
    // les parametres sont auto déclarer car ils sont public
    constructor(public viande : V, public legume : L) {}
    public afficher():void{
        //
    }
}
//exemple d'utilisation de generique
class Viande{};
class Legume{};
class Poulet extends Viande{};
class Tomate extends Legume{};

let monSandwitch: Sandwich<Poulet, Tomate> = new Sandwich<Poulet,Tomate>(
    new Poulet(),
    new Tomate()
);

//les modules : creation
module BusinessObject{
    export class Sandwich{
        //
    };
    export class Viande{
        //
    };
    export class Tomate{
        //
    };
}
// utilisation
let sanditchModule: BusinessObject.Sandwich;
// ou encore...
import BO = BusinessObject;
let sanditchAliasModule : BO.Sandwich;