package com.braceyourself.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private JPanel masterPanel;

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 512;

    private static MainTaskbar mainTaskbar;

    public MainGUI (String title) {
        super(title);

        mainTaskbar = new MainTaskbar(this);

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

        JButton test = new JButton();
        test.setBounds(768, 32, 192, 32);
        test.setText("Sync");
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sync();
            }
        });

        masterPanel.add(test);

        this.setContentPane(masterPanel);
        this.setResizable(false);
        this.pack();
        this.setState(Frame.ICONIFIED);
    }

    public static void main (String[] args) {

        fileManager = new FileManager();

        JFrame mainGUI = new MainGUI("Posture App");
        mainGUI.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        visionMain = new VisionMain(videoPanel);
    }

    public void sync() {
        VisionMain.vPM.sync();
    }

    public void toggle() {
        VisionMain.vPM.toggle();
    }
}