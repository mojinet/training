package fr.modji.TP5_SuivisDesRepas.bo;

public class Aliments {

    private int idRepas;
    private int id;
    private String aliment;

    public Aliments(int idRepas, int id, String aliment) {
        this.idRepas = idRepas;
        this.id = id;
        this.aliment = aliment;
    }

    public Aliments(int idRepas, String aliment) {
        this.idRepas = idRepas;
        this.aliment = aliment;
    }

    public int getIdRepas() {
        return idRepas;
    }

    public int getId() {
        return id;
    }

    public String getAliment() {
        return aliment;
    }
}
