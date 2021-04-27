package fr.modji.bo;

import java.time.LocalDate;
import java.util.Locale;

public class Patient {

    // Attribute
    private String nom, prenom, numero, commentaire;
    private char sexe;
    private long numSecu;
    private LocalDate dateNaissance;

    // Constructor
    public Patient(String nom, String prenom, String numero, char sexe, long numSecu, LocalDate dateNaissance, String commentaire ){
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.sexe = sexe;
        this.numSecu = numSecu;
        this.dateNaissance = dateNaissance;
        this.commentaire = (commentaire == null) ? "Aucun commentaire" : commentaire;
    }

    // Setter
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getNumero() { return numero; }
    public String getCommentaire() { return commentaire; }
    public char getSexe() { return sexe; }
    public long getNumSecu() { return numSecu; }
    public LocalDate getDateNaissance() { return dateNaissance; }

    // Method
    public void afficher(){
        System.out.printf( "%s %s %n Téléphone : %s %n Sexe : %s %n Numéro de sécurité sociale : %d %n Date de naissace : %s %n Commentaire : %s %n",
                getNom().toUpperCase(), getPrenom(),getNumero(), getSexe(), getNumSecu(), getDateNaissance(), getCommentaire() );
    }
}
