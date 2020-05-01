package com.braceyourself.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTaskbar {

    public static TrayIcon trayIcon;

    public MainTaskbar () {
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
        MenuItem menuExit = new MenuItem("Exit");

        menuToggle.addActionListener(new ActionListener() {
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
        menu.addSeparator();
        menu.add(menuExit);

        try {
            tray.add(trayIcon);
        } catch (Exception e) {

        }
    }

    protected static Image createIcon(String path, String description) {
        return (new ImageIcon("/hello", description)).getImage();
    }
}
