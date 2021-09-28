package com.aayush.gulia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame implements ActionListener {
    JLabel label1, label2;
    JButton button;
    JTextField text, info;
    String path;
    MyFrame(){
        label2 = new JLabel("Error code shown here");
        label2.setBounds(210,25,600,25);
        label1 = new JLabel("Please paste your file path here, do not include quotes");
        label1.setBounds(210,0,600,25);
        info = new JTextField("Status will be shown here");
        info.setBounds(0,25,200,25);
        text = new JTextField("Paste your path here");
        text.setBounds(0,0,200,25);
        button = new JButton("Open");
        button.setBounds(0,50,100,25);
        this.setSize(550,150);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(null);
        button.addActionListener(this);
        this.add(button);
        this.add(text);
        this.add(info);
        this.add(label1);
        this.add(label2);

        ImageIcon icon = new ImageIcon("images/pdf_icon.png");
        this.setIconImage(icon.getImage());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            path = text.getText();
            boolean status = Engine(path);
            if (status){
                info.setText("Success");
            }
            else{
                info.setText("404 - Error Code");
            }
        }
    }

    public static boolean Engine(String path) {
        try {
            File pdfFile = new File(path);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }
            } else {
                System.out.println("File is not exists!");
                return false;
            }
//            System.out.println("Done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
