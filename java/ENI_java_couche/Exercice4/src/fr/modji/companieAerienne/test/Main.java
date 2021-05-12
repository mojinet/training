package fr.modji.companieAerienne.test;

import fr.modji.companieAerienne.bo.*;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // on créer les compagnies
        var compagnie1 = new Compagnie("Compagnie A");
        var compagnie2 = new Compagnie("Compagnie B");
        var compagnie3 = new Compagnie("Compagnie C");

        // on créer les aéroports
        var aeroport1 = new Aeroport("CharleDeGaule","Paris");
        var aeroport2 = new Aeroport("Marseille-Provence","Marseille");
        var aeroport3 = new Aeroport("Bastia-Poretta","Bastia");
        var aeroport4 = new Aeroport("Toulouse-Blagnac","Toulouse");
        var aeroport5 = new Aeroport("Bâle-Mulhouse-Fribourg","Mulhouse");
        var aeroport6 = new Aeroport("Bordeaux-Mérignac","Bordeaux");

        //on créer une liste de compagnies
        var compaList = new ArrayList<Compagnie>();
        compaList.add(compagnie1);
        compaList.add(compagnie2);
        compaList.add(compagnie3);

        // on ajoute les aeroport dans les compagnie
        compagnie1.addAeroport(aeroport1);
        compagnie1.addAeroport(aeroport2);
        compagnie2.addAeroport(aeroport3);
        compagnie2.addAeroport(aeroport4);
        compagnie3.addAeroport(aeroport5);
        compagnie3.addAeroport(aeroport6);

        // date juste pour en exemple
        long millis = System.currentTimeMillis(); // recupere currentTime
        Date Depart = new Date(millis - 10000);
        Date Arrive = new Date(millis - 50000);

        // on créer 10 vols pour chaques compagnies
        for (Compagnie c : compaList) {
            for (int i = 0; i < 10; i++) {
                c.addVol(new Vol(c.getAeroport(0),c.getAeroport(1),Depart,Arrive));
            }
        }

        // on créer les clients
        var cli1 = new Client("Henrie","SaladeDor","rue de la musique");
        var cli2 = new Client("Bob", "Harley","rue de la plante");
        var cli3 = new Client("Gerard","Deuxpardeux","rue de la fete");

        // Les client reserve (Le client qui fait la reservation, le client pour qui c'est reservé, le vol de la reservation)
        var reservation1 = new Reservation(cli1, cli1, compagnie1.getVol(0));
        var reservation2 = new Reservation(cli2, cli2, compagnie2.getVol(5));
        var reservation3 = new Reservation(cli3, cli2, compagnie3.getVol(7));

        // On affiche ça
        System.out.println(reservation1);
        System.out.println(reservation2);
        System.out.println(reservation3);
    }
}
