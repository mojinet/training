package fr.modji.companieAerienne.bo;

import java.util.Date;

public class Escale {
    private Aeroport aeroport;
    private Date heureArrive;
    private Date heureDepart;

    public Escale(Aeroport aeroport, Date heureArrive, Date heureDepart) {
        this.aeroport = aeroport;
        this.heureArrive = heureArrive;
        this.heureDepart = heureDepart;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }

    public Date getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(Date heureArrive) {
        this.heureArrive = heureArrive;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    @Override
    public String toString() {
        return "Escale{" +
                "aeroport=" + aeroport +
                ", heureArrive=" + heureArrive +
                ", heureDepart=" + heureDepart +
                '}';
    }
}
