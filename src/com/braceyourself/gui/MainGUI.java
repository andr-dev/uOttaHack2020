package com.braceyourself.gui;

import com.braceyourself.utility.FileManager;
import com.braceyourself.vision.VideoPanel;
import com.braceyourself.vision.VisionMain;
import org.opencv.core.Core;

import javax.swing.*;

public class MainGUI extends JFrame {

    private JPanel masterPanel;

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 512;

    private static VisionMain visionMain;
    private static FileManager fileManager;
    private static VideoPanel videoPanel;

    public MainGUI (String title) {
        super(title);

        videoPanel = new VideoPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(videoPanel);
        this.pack();
    }

    public static void main (String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        JFrame mainGUI = new MainGUI("Posture App");
        mainGUI.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainGUI.setVisible(true);

        fileManager = new FileManager();
        visionMain = new VisionMain(videoPanel);

    }
}