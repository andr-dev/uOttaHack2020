package com.braceyourself.vision;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoPanel extends JPanel {
    private BufferedImage frame;
    private int frameCounter = 0;
    private boolean videoStreamState = false;

    public VideoPanel () {
        super();
    }

    public void setFrame(BufferedImage img) {
        this.frame = img;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.videoStreamState) {
            if (this.frame == null) {
                System.out.println("FATAL: Frame is null!");
            }

            g.drawImage(this.frame, 10, 10, this.frame.getWidth(), this.frame.getHeight(), null);
            g.setFont(new Font("arial", 2, 24));
            g.setColor(Color.WHITE);
            g.drawString("Frame #" + frameCounter, 50, 50);

            frameCounter++;
        }
    }

    public void setVideoStreamState(boolean state) {
        this.videoStreamState = state;
    }
}
