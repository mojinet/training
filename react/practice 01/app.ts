// Créer un élément h1 avec des enfant
// const title = React.createElement('h1',{},'bonjour le monde');
// Ajoute au document dans #root
//ReactDOM.render(title,document.querySelector('#root'));

// Créer un élément h1 avec des enfant en JSX
//const qui = "le monde";
//const title = <h1>Bonjour {qui}</h1>;
// Ajoute au document dans #root
//ReactDOM.render(title,document.querySelector('#root'));

function WelcomeFunc ({name, subname, children}){ 
    return <div>
        <h1>Salut {name + " " + subname} </h1>
        <p>{children}</p>
        </div>
};

function WelcomeFunc2 (props){ 
    return <h1>Salut {props.name + " " + props.subname} </h1>
};




class Welcome extends React.Component{
    constructor(props){ // si on ajoute un constructeur ne pas oublier le super(props)
        super(props);
        this.state = {n: props.start}; // objet qui contient les valeur qui seront modifier
        this.timer = null; // declare le timer qui contiendra une fonction avec interval
    }

    componentDidMount(){
        this.timer = setInterval((this.increment.bind(this)),1000) // bind le this pour ne pas le "perdre"
    }

    componentwillUnmount(){ 
        clearInterval(this.timer)
    }

    increment(){ // si on change un état (state) dépendant d'un autre état : créer une fonction 
        this.setState((state,props) => ( {n: state.n + 1} ))
    }

    render(){
        return  <div>
                    <h1>Salut {this.props.name + " " + this.props.subname} </h1>
                    <p>{this.props.children}</p>
                    <p>{this.state.n}</p>
                </div>
    }
}

ReactDOM.render(<Welcome name="Jean-Baptiste" subname="Cochinard" start={10}>le child</Welcome>, document.querySelector("#root"));
