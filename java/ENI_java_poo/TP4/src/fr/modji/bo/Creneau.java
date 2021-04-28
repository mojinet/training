package fr.modji.bo;

import java.time.LocalTime;

public class Creneau {

    // Atribute
    private MedecinGeneraliste medecin;
    private LocalTime hour;
    private final int TIME;

    // Constructor
    public Creneau(LocalTime hour, int time, MedecinGeneraliste medecin){
        this.medecin = medecin;
        this.hour = hour;
        this.TIME = time;
        medecin.ajouterCreneau(this); // Association with MedecinGeneraliste
    }

    // Method
    public void afficher(){
        LocalTime hour = this.hour;
        LocalTime hour2 = this.hour.plusMinutes(this.TIME);
        System.out.printf("%02d:%02d - %02d:%02d (%d minutes)",
                hour.getHour(),hour.getMinute(),hour2.getHour(), hour2.getMinute(), this.TIME);
    }
    public MedecinGeneraliste getMedecin(){
        return this.medecin;
    }
}
