package fr.modji.companieAerienne.bo;

import java.util.Date;
import java.util.List;

public class Vol {
    private boolean reservationOuverte;
    private Aeroport aeroDepart;
    private Aeroport aeroArrive;
    private Date dateDepart;
    private Date dateArrive;
    private List<Escale> listEscal;

    public Vol(Aeroport aeroDepart, Aeroport aeroArrive, Date dateDepart, Date dateArrive) {
        this.reservationOuverte = true;
        this.aeroDepart = aeroDepart;
        this.aeroArrive = aeroArrive;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
    }

    public boolean isReservationOuverte() {
        return reservationOuverte;
    }

    public void setReservationOuverte(boolean reservationOuverte) {
        this.reservationOuverte = reservationOuverte;
    }

    public Aeroport getAeroDepart() {
        return aeroDepart;
    }

    public void setAeroDepart(Aeroport aeroDepart) {
        this.aeroDepart = aeroDepart;
    }

    public Aeroport getAeroArrive() {
        return aeroArrive;
    }

    public void setAeroArrive(Aeroport aeroArrive) {
        this.aeroArrive = aeroArrive;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public List<Escale> getListEscal() {
        return listEscal;
    }

    public void setListEscal(List<Escale> listEscal) {
        this.listEscal = listEscal;
    }

    @Override
    public String toString() {
        StringBuilder rc2 = new StringBuilder();
        rc2.append("%n");

        return "Vol{" +
                "reservationOuverte=" + reservationOuverte +
                ", aeroDepart=" + aeroDepart +
                ", aeroArrive=" + aeroArrive +
                ", dateDepart=" + dateDepart +
                ", dateArrive=" + dateArrive +
                ", listEscal=" + listEscal +
                '}';
    }
}
