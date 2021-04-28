package fr.modji.bo;

import java.time.LocalDate;
import java.util.Locale;

public class RendezVous {
    // Atribute
    private Creneau creneau;
    private Patient patient;
    private LocalDate dateRDV;

    // Constructor
    public RendezVous(Creneau creneau, Patient patient, LocalDate dateRDV){
        this.creneau = creneau;
        this.patient = patient;
        this.dateRDV = dateRDV;
    }

    // Method
    public void afficher(){
        System.out.printf("Rendez-vous du %d %s %d ", this.dateRDV.getDayOfMonth(), this.dateRDV.getMonth(), this.dateRDV.getYear());
        this.creneau.afficher();
        System.out.printf("%nAvec le Dr %s %nPour %s %s %n",  this.creneau.getMedecin().getNom(), this.patient.getNom().toUpperCase(), this.patient.getPrenom());
        this.patient.afficher();
        this.creneau.getMedecin().getAdresse().afficher();
    }
}
