import javax.swing.*;
import java.awt.*;

public class MaFenetre extends JFrame {

    // On déclare nos composant
    private JTextField txtNom;
    private JLabel lblNom;
    private JPanel panBouton;
    private JButton btnAjouter, btnAnnuler;

    // Création du conteneur principal
    public MaFenetre() throws HeadlessException {
        this.setTitle("Ma premiere fenetre");                   // on modifie le titre
        this.setSize(new Dimension(500,250));      // On définie une taille
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Choisie le comportement en cliquan sur X
        initIHM();
    }

    // Création du conteneur de composant
    private void initIHM() {
        // On parametre un conteneur Jpanel
        JPanel panel = new JPanel();          // notre conteneur de composant
        panel.setLayout(new GridBagLayout()); // on définie le type de layout
        GridBagConstraints gbc = new GridBagConstraints(); // Permet de gerer la disposition

        // On ajoute les composants
        gbc.gridy = 0;                              // définie les coordonnées
        gbc.gridx = 0;
        panel.add(getLblNom(), gbc);  // On ajoute un composant JLabel au coordonnée définie par gbc
        gbc.gridx = 1;                             // On repositionne le curseur du layout
        panel.add(getTxtNom(),gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(getPanBouton(),gbc);
        // Ont ajoute à notre conteneur principal notre conteneur de compostant
        this.setContentPane(panel);
    }

    // Design pattern Singleton pour recuperer nos composants
    public JTextField getTxtNom() {
        if(this.txtNom == null){
            txtNom = new JTextField(30);
        }
        return txtNom;
    }
    public JLabel getLblNom() {
        if(this.txtNom == null){
            lblNom = new JLabel("Nom :");
        }
        return lblNom;
    }

    public JPanel getPanBouton() {
        if(this.panBouton== null){
            panBouton = new JPanel();
            panBouton.add(getBtnAjouter());
            panBouton.add(getBtnAnnuler());
        }
        return panBouton;
    }

    public JButton getBtnAjouter() {
        if (btnAjouter == null){
            btnAjouter = new JButton("Ajouter");
        }
        return btnAjouter;
    }

    public JButton getBtnAnnuler() {
        if (btnAnnuler == null){
            btnAnnuler = new JButton("Annuler");
        }
        return btnAnnuler;
    }

}