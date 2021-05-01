package fr.modji.ElevageVolailles;

public abstract class Volaille {
    // Atribute
    protected int identite;
    protected double poids;

    // Constructor
    public Volaille(double poids, int numero){
        this.poids = poids;
        this.identite = numero;
    }

    // Redefine toString
    @Override
    public String toString(){
        String response = String.format("%s %d, poids : %.3fKg, prix : %.2f€", this.getName(), this.getIdentite(), this.getPoids(), this.prix());
        return response;
    }

    // Abstract method
    protected abstract double prix();
    public abstract boolean assezGrosse();

    // Getter
    public double getPoids() {
        return poids;
    }
    public int getIdentite() {
        return identite;
    }

    // Setter
    public void setIdentite(int identite) {
        this.identite = identite;
    }
    public void setPoids(double poids) {
        this.poids = poids;
    }

    /**
     * @return name of class
     */
    private String getName() {
        String str = this.getClass().toString();
        int index = str.lastIndexOf(".");
        str = str.substring(index+1);
        return str;
    }
}
