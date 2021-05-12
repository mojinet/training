package fr.modji.companieAerienne.bo;

public class Reservation {
    private String nomClientReserve;
    private String prenomClientReserve;
    private String nomClient;
    private String prenomClient;
    private Vol vol;
    private boolean confirmation;

    public Reservation(Client clientReserve, Client client, Vol vol) {
        this.nomClientReserve = client.getNom();
        this.prenomClientReserve = client.getPrenom();
        this.nomClient = client.getNom();
        this.prenomClient = client.getPrenom();
        this.vol = vol;
        this.confirmation = true;
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
    public String getNomClientReserve() {
        return nomClientReserve;
    }

    public void setNomClientReserve(String nomClientReserve) {
        this.nomClientReserve = nomClientReserve;
    }

    public String getPrenomClientReserve() {
        return prenomClientReserve;
    }

    public void setPrenomClientReserve(String prenomClientReserve) {
        this.prenomClientReserve = prenomClientReserve;
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
