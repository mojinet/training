class Formulaire{
    constructor(public form) {
        form.addEventListener("submit",(e)=>{
            e.preventDefault();
            this.addMessage();
        })
    }
    public get pseudo():string {
        return this.form.pseudo.value;
    }
    public get message():string {
        return this.form.message.value;
    }
    private addMessage():void{
        let li = document.createElement("li");
        li.innerText = this.pseudo + " : " + this.message;
        document.getElementById("viewZone").append(li);
    }
}

let form:Formulaire = new Formulaire(document.getElementById("mainForm"));