package com.braceyourself.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {

    private JPanel masterPanel;

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 512;

    private static MainTaskbar mainTaskbar;

    public MainGUI (String title) {
        super(title);

        mainTaskbar = new MainTaskbar();

        JPanel video = new JPanel();
        video.setSize(720, 512);
        video.setBackground(Color.GREEN);

        JButton sync = new JButton();
        sync.setBounds(784, 120, 176, 40);
        sync.setBackground(Color.RED);
        sync.setText("Synchronize");

        JButton power = new JButton();
        power.setBounds(822, 220, 100, 100);
        sync.setBackground(Color.RED);
        power.setText("On/Off");

        masterPanel = new JPanel();
        masterPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        masterPanel.setLayout(null);
        masterPanel.setBackground(new Color(200, 200, 200));



        masterPanel.add(sync);
        masterPanel.add(power);
        masterPanel.add(video);

        this.setContentPane(masterPanel);
        this.setResizable(false);
        this.pack();
        this.setState(Frame.ICONIFIED);
    }

    public static void main (String[] args) {

        JFrame mainGUI = new MainGUI("Posture App");
        mainGUI.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainGUI.setVisible(true);

    }
}