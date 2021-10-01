package com.aayush.gulia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class MyFrame extends JFrame implements ActionListener {
    JLabel label1, label2;
    JButton btnOpen ,BrowseFile, Close;
    JTextField text, info;
    String path;
    File fileShowOpenDialogue;
    MyFrame(){
        UI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnOpen){
            path = text.getText();
            boolean status = Engine(path);
            if (status){
                info.setText("Success");
            }
            else{
                info.setText("404 - Error Code");
            }
        }
        if (e.getSource()==BrowseFile){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
            fileShowOpenDialogue = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(fileShowOpenDialogue);}
            text.setText(String.valueOf(fileShowOpenDialogue));
        }
        if (e.getSource()==Close){
            System.out.println("Close btn pressed");
        }
    }

    public static boolean Engine(String path) {
        try {
            File pdfFile = new File(path);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("System viewer does not support this application");
                }
            } else {
                System.out.println("File does not exists");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    void UI(){
        Close = new JButton("Close");
        Close.setBounds(300,50,100,25);
        label2 = new JLabel("Error code shown here");
        label2.setBounds(210,25,600,25);
        label1 = new JLabel("Please paste your file path here, do not include quotes");
        label1.setBounds(210,0,600,25);
        info = new JTextField("Status will be shown here");
        info.setBounds(0,25,200,25);
        info.setEditable(false);
        text = new JTextField("Paste your path here");
        text.setBounds(0,0,200,25);
        btnOpen = new JButton("Open");
        btnOpen.setBounds(0,50,100,25);
        BrowseFile = new JButton("Browse");
        BrowseFile.setBounds(150,50,100,25);
        BrowseFile.addActionListener(this);
        Close.addActionListener(this);
        this.setSize(550,150);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        btnOpen.addActionListener(this);
        this.add(BrowseFile);
        this.add(btnOpen);
        this.add(text);
        this.add(info);
        this.add(label1);
        this.add(label2);
        this.add(Close);

        ImageIcon icon = new ImageIcon("images/pdf_icon.png");
        this.setIconImage(icon.getImage());
    }
}
