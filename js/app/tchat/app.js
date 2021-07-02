var Formulaire = /** @class */ (function () {
    function Formulaire(form) {
        var _this = this;
        this.form = form;
        form.addEventListener("submit", function (e) {
            e.preventDefault();
            _this.addMessage();
        });
    }
    Object.defineProperty(Formulaire.prototype, "pseudo", {
        get: function () {
            return this.form.pseudo.value;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(Formulaire.prototype, "message", {
        get: function () {
            return this.form.message.value;
        },
        enumerable: false,
        configurable: true
    });
    Formulaire.prototype.addMessage = function () {
        var li = document.createElement("li");
        li.innerText = this.pseudo + " : " + this.message;
        document.getElementById("viewZone").append(li);
    };
    return Formulaire;
}());
var form = new Formulaire(document.getElementById("mainForm"));
