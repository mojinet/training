package fr.modji.papeterie.ihm;

import javax.swing.*;
import java.awt.*;

public class MainIHM extends JFrame {

    private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur;
    private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
    private JRadioButton radType;
    private JCheckBox ckbGrammage;
    private JComboBox cbbCouleur;
    private JButton btnPrec, btnNew, btnSave, btnSupr, btnSuiv;
    private JPanel panBouton;

    private final int sizeTxt = 10;

    public MainIHM(){
        this.setTitle("Gestion de catalogue");
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initIHM();
    }

    private void initIHM(){
        // constuction du JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Positionnement de nos elements
        // Reference
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(getLblReference(),gbc);
        gbc.gridx = 1;
        panel.add(getTxtReference(),gbc);

        // designation
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(getLblDesignation(),gbc);
        gbc.gridx = 1;
        panel.add(getTxtDesignation(),gbc);

        // marque
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(getLblMarque(),gbc);
        gbc.gridx = 1;
        panel.add(getTxtMarque(),gbc);

        // stock
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(getLblStock(),gbc);
        gbc.gridx = 1;
        panel.add(getTxtStock(),gbc);

        // prix
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(getLblPrix(),gbc);
        gbc.gridx = 1;
        panel.add(getTxtPrix(),gbc);

        // type
        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(getLblType(),gbc);
        gbc.gridx = 1;
        panel.add(getRadType(),gbc);

        // grammage
        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(getLblCouleur(),gbc);
        gbc.gridx = 1;
        panel.add(getCbbCouleur(),gbc);

        // couleur
        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(getLblCouleur(),gbc);
        gbc.gridx = 1;
        panel.add(getCbbCouleur(),gbc);

        // section btn todo n'affiche plus rien
        /*
        gbc.gridy = 8;
        gbc.gridx = 0;
        panel.add(getPanBouton(),gbc);
        */

        this.setContentPane(panel);
    }

    // Singleton sur tout les composants
    public JLabel getLblReference() {
        if (lblReference == null){
            lblReference = new JLabel("Reference");
        }
        return lblReference;
    }

    public JLabel getLblDesignation() {
        if (lblDesignation == null){
            lblDesignation = new JLabel("Designation");
        }
        return lblDesignation;
    }

    public JLabel getLblMarque() {
        if (lblMarque == null){
            lblMarque = new JLabel("Marque");
        }
        return lblMarque;
    }

    public JLabel getLblStock() {
        if (lblStock == null){
            lblStock = new JLabel("Stock");
        }
        return lblStock;
    }

    public JLabel getLblPrix() {
        if (lblPrix == null){
            lblPrix = new JLabel("Prix");
        }
        return lblPrix;
    }

    public JLabel getLblType() {
        if (lblType == null){
            lblType = new JLabel("Type");
        }
        return lblType;
    }

    public JLabel getLblGrammage() {
        if (lblGrammage == null){
            lblGrammage = new JLabel("Reference");
        }
        return lblGrammage;
    }

    public JLabel getLblCouleur() {
        if (lblCouleur == null){
            lblCouleur = new JLabel("Couleur");
        }
        return lblCouleur;
    }

    public JTextField getTxtReference() {
        if (txtReference == null){
            txtReference = new JTextField(sizeTxt);
        }
        return txtReference;
    }

    public JTextField getTxtDesignation() {
        if (txtDesignation == null){
            txtDesignation = new JTextField(sizeTxt);
        }
        return txtDesignation;
    }

    public JTextField getTxtMarque() {
        if (txtMarque == null){
            txtMarque = new JTextField(sizeTxt);
        }
        return txtMarque;
    }

    public JTextField getTxtStock() {
        if (txtStock == null){
            txtStock = new JTextField(sizeTxt);
        }
        return txtStock;
    }

    public JTextField getTxtPrix() {
        if (txtPrix == null){
            txtPrix = new JTextField(sizeTxt);
        }
        return txtPrix;
    }
    // todo radio component
    public JRadioButton getRadType() {
        if (radType == null){
            radType = new JRadioButton();
        }
        return radType;
    }
    // todo checkbox component
    public JCheckBox getCkbGrammage() {
        if (ckbGrammage == null){
            ckbGrammage = new JCheckBox();
        }
        return ckbGrammage;
    }
    // todo checkbox component
    public JComboBox getCbbCouleur() {
        if (cbbCouleur == null){
            cbbCouleur = new JComboBox();
        }
        return cbbCouleur;
    }
    // todo listener & image
    public JButton getBtnPrec() {
        if (btnPrec == null){
            btnPrec = new JButton("precedent");
        }
        return btnPrec;
    }

    public JButton getBtnNew() {
        if (btnNew == null){
            btnNew = new JButton("nouveau");
        }
        return btnNew;
    }

    public JButton getBtnSave() {
        if (btnSave == null){
            btnSave = new JButton("Sauvegarde");
        }
        return btnSave;
    }

    public JButton getBtnSupr() {
        if (btnSupr == null){
            btnSupr = new JButton("Suprimmer");
        }
        return btnSupr;
    }

    public JButton getBtnSuiv() {
        if (btnSuiv == null){
            btnSuiv = new JButton("Suivant");
        }
        return btnSuiv;
    }

    public JPanel getPanBouton() {
        if (panBouton == null){
            panBouton = new JPanel();
            panBouton.add(btnPrec);
            panBouton.add(btnNew);
            panBouton.add(btnSave);
            panBouton.add(btnSupr);
            panBouton.add(btnSuiv);
        }
        return panBouton;
    }
}
