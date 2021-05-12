package fr.modji.companieAerienne.bo;

public class Reservation {
    private String nomClient;
    private String prenomClient;
    private Vol vol;
    private boolean confirmation;

    public Reservation(String nomClient, String prenomClient, Vol vol, boolean confirmation) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.vol = vol;
        this.confirmation = confirmation;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                ", vol=" + vol +
                ", confirmation=" + confirmation +
                '}';
    }
}
