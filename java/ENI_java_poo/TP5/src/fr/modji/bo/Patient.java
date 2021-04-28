package fr.modji.bo;

import java.time.LocalDate;

public class Patient extends Personne {

    // Attribute
    private String commentaire;
    private char sexe;
    private long numSecu;
    private LocalDate dateNaissance;

    // Constructor
    public Patient(String nom, String prenom, String numero, char sexe, long numSecu, LocalDate dateNaissance, String commentaire, Adresse adresse ){
        super(nom,prenom,numero,adresse);
        this.sexe = sexe;
        this.numSecu = numSecu;
        this.dateNaissance = dateNaissance;
        this.commentaire = (commentaire == null) ? "Aucun commentaire" : commentaire;

    }
    public Patient(String nom, String prenom, String telephone, char sexe, long numSecu, LocalDate dateNaissance, String commentaire ){
        this(nom,prenom,telephone,sexe,numSecu,dateNaissance,commentaire,null);
    }

    // Getter
    public String getCommentaire() { return commentaire; }
    public char getSexe() { return sexe; }
    public long getNumSecu() { return numSecu; }
    public LocalDate getDateNaissance() { return dateNaissance; }

    // Method
    public void afficher(){
        System.out.printf( "%s %s %nTéléphone : %s %nSexe : %s %nNuméro de sécurité sociale : %d %nDate de naissace : %s %nCommentaire : %s %n",
                getNom().toUpperCase(), getPrenom(),getNumeroDeTelephone(), getSexe(), getNumSecu(), getDateNaissance(), getCommentaire() );
        super.afficher();
    }
}
