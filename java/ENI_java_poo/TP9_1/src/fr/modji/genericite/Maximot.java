package fr.modji.genericite;

public class Maximot {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        // on initialise notre dictionnaire
        Dictionnaire dico = new Dictionnaire("dictionnaire.txt");
        // on procede au tirage d'un des mot du dico
        Tirage tirage = new Tirage(dico);

        System.out.println("Bienvenue au jeu du MaximotV2 ! ");
        System.out.println("Voici votre tirage : " + tirage.getMotMelanger());
    }
}
