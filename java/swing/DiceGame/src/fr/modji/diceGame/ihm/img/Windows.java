package fr.modji.diceGame.ihm.img;

import fr.modji.diceGame.bo.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows extends JFrame {

    private JPanel mainPanel;
    private JPanel headPanel;
    private JPanel footPanel;
    private JPanel contentPanel;
    private JTextField diceMax;
    private JButton btnLancer;
    private JTextField txtField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    private int[] diceValue;

    public Windows(String title){
        super(title);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(500,500));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //test
        Dice dice = new Dice();
        diceValue = dice.getDices(6);
        btnLancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = Integer.parseInt(diceMax.getText());
                txtField1.setText((int) (Math.random() * max)+1 + "");
                textField2.setText((int) (Math.random() * max)+1 + "");
                textField3.setText((int) (Math.random() * max)+1 + "");
                textField4.setText((int) (Math.random() * max)+1 + "");
                textField5.setText((int) (Math.random() * max)+1 + "");
            }
        });
    }

}
