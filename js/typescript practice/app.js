var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
//declaration de variable
var count = 0;
var check = false;
var nom = "Moi";
var maVar = 10;
//dans une fonction
function bonjour(qui) {
    console.log("bonjour " + qui);
}
//enuméré
var Language;
(function (Language) {
    Language[Language["PHP"] = 0] = "PHP";
    Language[Language["JavaScript"] = 1] = "JavaScript";
    Language[Language["HTML"] = 2] = "HTML";
    Language[Language["CSS"] = 3] = "CSS";
    Language[Language["Java"] = 4] = "Java";
    Language[Language["C++"] = 5] = "C++";
})(Language || (Language = {}));
var monLanguagePrefere = Language.JavaScript;
if (monLanguagePrefere == Language.JavaScript) {
    console.log("oui c'est le meilleur");
}
else {
    console.log("bof bof");
}
//class
var Sport = /** @class */ (function () {
    function Sport(nom, description) {
        this.nom = nom;
        this.description = description;
    }
    Sport.prototype.afficher = function () {
        console.log(this.nom, this.description);
    };
    return Sport;
}());
//héritage
var SportCombat = /** @class */ (function (_super) {
    __extends(SportCombat, _super);
    function SportCombat(nom, description) {
        return _super.call(this, nom, description) || this;
    }
    SportCombat.prototype.afficher = function () {
        _super.prototype.afficher.call(this);
        console.log("c'est un sport un peu spécial !");
    };
    return SportCombat;
}(Sport));
var sport = new SportCombat("Boxe", "est un sport de combat");
sport.afficher();
// Implémentation
var SportJouable = /** @class */ (function (_super) {
    __extends(SportJouable, _super);
    function SportJouable() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SportJouable.prototype.jouer = function (joueur1, joueur2) {
        return "Les deux joueurs jouent !";
    };
    return SportJouable;
}(Sport));
// Implmentation
var jouerAuBadminton = function (joueur1, joueur2) {
    return "";
};
// tableau avec genériques
var genericArray = [5, 6, 7];
// avec une classes qui utilisent des generiques
var Sandwich = /** @class */ (function () {
    // les parametres sont auto déclarer car ils sont public
    function Sandwich(viande, legume) {
        this.viande = viande;
        this.legume = legume;
    }
    Sandwich.prototype.afficher = function () {
        //
    };
    return Sandwich;
}());
//exemple d'utilisation de generique
var Viande = /** @class */ (function () {
    function Viande() {
    }
    return Viande;
}());
;
var Legume = /** @class */ (function () {
    function Legume() {
    }
    return Legume;
}());
;
var Poulet = /** @class */ (function (_super) {
    __extends(Poulet, _super);
    function Poulet() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return Poulet;
}(Viande));
;
var Tomate = /** @class */ (function (_super) {
    __extends(Tomate, _super);
    function Tomate() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return Tomate;
}(Legume));
;
var monSandwitch = new Sandwich(new Poulet(), new Tomate());
//les modules : creation
var BusinessObject;
(function (BusinessObject) {
    var Sandwich = /** @class */ (function () {
        function Sandwich() {
        }
        return Sandwich;
    }());
    BusinessObject.Sandwich = Sandwich;
    ;
    var Viande = /** @class */ (function () {
        function Viande() {
        }
        return Viande;
    }());
    BusinessObject.Viande = Viande;
    ;
    var Tomate = /** @class */ (function () {
        function Tomate() {
        }
        return Tomate;
    }());
    BusinessObject.Tomate = Tomate;
    ;
})(BusinessObject || (BusinessObject = {}));
// utilisation
var sanditchModule;
// ou encore...
var BO = BusinessObject;
var sanditchAliasModule;
