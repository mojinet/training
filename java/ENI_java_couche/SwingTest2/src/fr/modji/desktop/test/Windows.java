package fr.modji.desktop.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Windows extends JFrame{
    private JPanel mainPanel;
    private JTextField a6TextField;

    public Windows(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
    }

}
