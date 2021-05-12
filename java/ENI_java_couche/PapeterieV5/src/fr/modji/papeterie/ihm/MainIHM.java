package fr.modji.papeterie.ihm;

import javax.swing.*;
import java.awt.*;

public class MainIHM extends JFrame {

    private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur;
    private JTextField txtReference, txtDesignation, txtMarque, txtStaock, txtPrix;
    private JRadioButton radType;
    private JCheckBox ckbGrammage;
    private JComboBox cbbCouleur;
    private JButton btnPrec, btnNew, btnSave, btnSupr, btnSuiv;
    private JPanel panBouton;

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
        return txtReference;
    }

    public JTextField getTxtDesignation() {
        return txtDesignation;
    }

    public JTextField getTxtMarque() {
        return txtMarque;
    }

    public JTextField getTxtStaock() {
        return txtStaock;
    }

    public JTextField getTxtPrix() {
        return txtPrix;
    }

    public JRadioButton getRadType() {
        return radType;
    }

    public JCheckBox getCkbGrammage() {
        return ckbGrammage;
    }

    public JComboBox getCbbCouleur() {
        return cbbCouleur;
    }

    public JButton getBtnPrec() {
        return btnPrec;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnSupr() {
        return btnSupr;
    }

    public JButton getBtnSuiv() {
        return btnSuiv;
    }

    public JPanel getPanBouton() {
        return panBouton;
    }
}
