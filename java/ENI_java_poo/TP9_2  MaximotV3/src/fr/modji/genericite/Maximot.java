package fr.modji.genericite;

import java.util.Locale;
import java.util.Scanner;

public class Maximot {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Scanner scan = new Scanner(System.in);
        String response;
        // on initialise notre dictionnaire
        Dictionnaire dico = new Dictionnaire("dictionnaire.txt");
        // on procede au tirage d'un des mot du dico
        Tirage tirage = new Tirage(dico);

        System.out.println("Bienvenue au jeu du MaximotV2 ! ");
        System.out.println("Voici votre tirage : " + tirage.getMot());
        System.out.print("Votre proposition s'il vous plais : ");
        response = scan.nextLine();
        if( tirage.compare(response.toUpperCase()) > 0 ){
            System.out.println("Bonne réponse !");
            System.out.println("Score : " + tirage.compare(response.toUpperCase()) + " Points sur " + tirage.getMot().length());
        }else{
            System.out.print("Vous avez perdu !");
        }
        tirage.DisplayListeReponse();
    }
}