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

        //on créer une liste de compagnies
        var compaList = new ArrayList<Compagnie>();
        compaList.add(compagnie1);
        compaList.add(compagnie1);
        compaList.add(compagnie1);

        // on créer les aéroports
        var aeroport1 = new Aeroport("CharleDeGaule","Paris");
        var aeroport2 = new Aeroport("Marseille-Provence","Marseille");
        var aeroport3 = new Aeroport("Bastia-Poretta","Bastia");
        var aeroport4 = new Aeroport("Toulouse-Blagnac","Toulouse");
        var aeroport5 = new Aeroport("Bâle-Mulhouse-Fribourg","Mulhouse");
        var aeroport6 = new Aeroport("Bordeaux-Mérignac","Bordeaux");

        // on ajoute les aeroport dans les compagnie
        compagnie1.addAeroport(aeroport1);
        compagnie1.addAeroport(aeroport2);
        compagnie2.addAeroport(aeroport3);
        compagnie2.addAeroport(aeroport4);
        compagnie3.addAeroport(aeroport5);
        compagnie3.addAeroport(aeroport6);

        long millis = System.currentTimeMillis();
        // on créer 10 vols pour chaques compagnies
        for (Compagnie c : compaList) {
            for (int i = 0; i < 10; i++) {
                c.addVol(new Vol(
                        true,
                        c.getAeroport(0),
                        c.getAeroport(1),
                        new Date(millis - 10000),
                        new Date(millis - 50000)));
            }
        }

        // on créer les clients
        var cli1 = new Client("Henrie","SaladeDor","rue de la musique");
        var cli2 = new Client("Bob", "Harley","rue de la plante");
        var cli3 = new Client("Gerard","Deuxpardeux","rue de la fete");

        // Les client reserve
        var reservation1 = new Reservation(cli1.getNom(),cli1.getPrenom(),compaList.get(0).getListeVol().get(0),true);
        var reservation2 = new Reservation(cli2.getNom(),cli2.getPrenom(),compaList.get(1).getListeVol().get(1),true);
        var reservation3 = new Reservation(cli3.getNom(),cli3.getPrenom(),compaList.get(2).getListeVol().get(0),true);

        // On affiche ça
        System.out.println(reservation1);
        System.out.println(reservation2);
        System.out.println(reservation3);
    }
}
