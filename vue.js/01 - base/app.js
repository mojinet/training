// on déclare une constante
const vm = Vue.createApp({
    // contiendra nos states (données qui seront modifier)
    data(){
        return{
            message: 'hello world',
            todos: [
                'faire un premier truc',
                'Faire un deuxieme truc',
                'Faire un dernier truc'
            ],
            listeObjet: [
                {id:0, content: 'premier element'},
                {id:1, content: 'deuxieme element'}
            ],
            input: ''
        }
    },
    // contiendra nos méthodes
    methods:{
        inverser(){
            this.message = this.message.split('').reverse().join('')
        },
        ajouter(){
            this.todos.push(this.input);
            this.input = ''
        }
    }
})

//créer un composant
vm.component('test',{
    props: {text: String},
    template: `{{text}}`
})

// monte le template sur #root
vm.mount('#root')
