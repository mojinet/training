package fr.modji.bo;

import java.time.LocalDate;

public class Patient {

    // Attribute
    private String nom, prenom, numero, commentaire;
    private char sexe;
    private long numSecu;
    private LocalDate dateNaissance;
    private Adresse adresse;

    // Constructor
    public Patient(String nom, String prenom, String numero, char sexe, long numSecu, LocalDate dateNaissance, String commentaire, Adresse adresse ){
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.sexe = sexe;
        this.numSecu = numSecu;
        this.dateNaissance = dateNaissance;
        this.commentaire = (commentaire == null) ? "Aucun commentaire" : commentaire;
        this.adresse = adresse;
    }
    public Patient(String nom, String prenom, String numero, char sexe, long numSecu, LocalDate dateNaissance, String commentaire ){
        this(nom,prenom,numero,sexe,numSecu,dateNaissance,commentaire,null);
    }

    // Getter
    public Adresse getAdresse() { return this.adresse; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getNumero() { return numero; }
    public String getCommentaire() { return commentaire; }
    public char getSexe() { return sexe; }
    public long getNumSecu() { return numSecu; }
    public LocalDate getDateNaissance() { return dateNaissance; }

    // Method
    public void afficher(){
        if ( this.getAdresse() != null ){
            this.getAdresse().afficher();
        }
        System.out.printf( "%s %s %nTéléphone : %s %nSexe : %s %nNuméro de sécurité sociale : %d %nDate de naissace : %s %nCommentaire : %s %n",
                getNom().toUpperCase(), getPrenom(),getNumero(), getSexe(), getNumSecu(), getDateNaissance(), getCommentaire() );
    }
}
