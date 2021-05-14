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

    private final int sizeTxt = 15;

    public MainIHM(){
        this.setTitle("Gestion de catalogue");
        this.setSize(new Dimension(400,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initIHM();
        this.setVisible(true);
        this.setResizable(false);
    }

    private void initIHM(){
        // constuction du JPanel
        JPanel panel = (JPanel) this.getContentPane();
        var gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        //Positionnement de nos elements
        // Reference
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(getLblReference(),gbc);
        gbc.weightx = 0.1;
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
        panel.add(getLblGrammage(),gbc);
        gbc.gridx = 1;
        panel.add(getCkbGrammage(),gbc);

        // couleur
        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(getLblCouleur(),gbc);
        gbc.gridx = 1;
        panel.add(getCbbCouleur(),gbc);

        // section btn todo n'affiche plus rien
        gbc.gridy = 8;
        gbc.gridwidth = 10;
        gbc.gridx = 0;
        panel.add(getPanBouton(),gbc);


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
            lblGrammage = new JLabel("Grammage");
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
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/back24.gif"));
            btnPrec = new JButton(icon);
        }
        return btnPrec;
    }

    public JButton getBtnNew() {
        if (btnNew == null){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/new24.gif"));
            btnNew = new JButton(icon);
        }
        return btnNew;
    }

    public JButton getBtnSave() {
        if (btnSave == null){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/save24.gif"));
            btnSave = new JButton(icon);
        }
        return btnSave;
    }

    public JButton getBtnSupr() {
        if (btnSupr == null){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/delete24.gif"));
            btnSupr = new JButton(icon);
        }
        return btnSupr;
    }

    public JButton getBtnSuiv() {
        if (btnSuiv == null){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/Forward24.gif"));
            btnSuiv = new JButton(icon);
        }
        return btnSuiv;
    }

    public JPanel getPanBouton() {
        if (panBouton == null){
            // init JPanel
            panBouton = new JPanel();
            panBouton.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            // positionnement
            gbc.gridx = 0;
            gbc.gridy = 0;
            panBouton.add(getBtnPrec(),gbc);
            gbc.gridx = 1;
            panBouton.add(getBtnNew(),gbc);
            gbc.gridx = 2;
            panBouton.add(getBtnSave(),gbc);
            gbc.gridx = 3;
            panBouton.add(getBtnSupr(),gbc);
            gbc.gridx = 4;
            panBouton.add(getBtnSuiv(),gbc);

            this.setContentPane(panBouton);
        }
        return panBouton;
    }
}
