package com.braceyourself.gui;

import com.braceyourself.utility.FileManager;
import com.braceyourself.vision.VideoPanel;
import com.braceyourself.vision.VisionMain;
import org.opencv.core.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private JPanel masterPanel;

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 512;

    private static VisionMain visionMain;
    private static FileManager fileManager;
    private static VideoPanel videoPanel;
    private static MainTaskbar mainTaskbar;

    public MainGUI (String title) {
        super(title);

        mainTaskbar = new MainTaskbar(this);

        masterPanel = new JPanel();
        masterPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        masterPanel.setLayout(null);
        masterPanel.setBackground(new Color(200, 200, 200));

        videoPanel = new VideoPanel();
        videoPanel.setSize(720, 512);

        masterPanel.add(videoPanel);

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
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

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