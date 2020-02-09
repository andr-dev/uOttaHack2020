package com.braceyourself.gui;

import com.braceyourself.utility.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTaskbar {

    public static TrayIcon trayIcon;
    public static MainGUI mainGUI;

    public MainTaskbar (MainGUI gui) {
        this.mainGUI = gui;

        show();
    }

    public static void show () {
        if (!SystemTray.isSupported()) {
            System.exit(0);
        }
        trayIcon = new TrayIcon(createIcon("resources\\icon.png", "Icon"));
        trayIcon.setToolTip("Ouch! DeSlouch");

        final SystemTray tray  = SystemTray.getSystemTray();
        final PopupMenu menu = new PopupMenu();

        MenuItem menuToggle = new MenuItem("Enable / Disable");
        MenuItem menuOpen = new MenuItem("Open");
        MenuItem menuSync = new MenuItem("Quick Sync");
        MenuItem menuExit = new MenuItem("Exit");

        menuToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        menuSync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        trayIcon.setPopupMenu(menu);

        menu.add(menuToggle);
        menu.add(menuOpen);
        menu.add(menuSync);
        menu.addSeparator();
        menu.add(menuExit);

        try {
            tray.add(trayIcon);
        } catch (Exception e) {

        }
    }

    protected static Image createIcon(String path, String description) {
        System.out.println(FileManager.getPath() + path);
        return (new ImageIcon(FileManager.getPath() + path, description)).getImage();
    }
}
