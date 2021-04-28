package fr.modji.bo;

public class MedecinSpecialiste extends MedecinGeneraliste{
    private String specialite;

    public MedecinSpecialiste(String nom, String prenom, String numero, Adresse adresse, String specialite, int tarif){
        super(nom,prenom,numero,adresse);
        this.specialite = specialite;
        this.setTarif(tarif);
    }

}
