package com.braceyourself.vision;

import com.braceyourself.utility.MatConverter;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class VideoPanelManager implements Runnable {

    private VideoCapture videoCapture;
    private VideoPanel videoPanel;
    private final int REFRESH_RATE = 1000/60; // Milliseconds per refresh
    private boolean isRunning = false;
    private MatConverter matConverter;

    public VideoPanelManager(VideoCapture vc, VideoPanel vp) {
        this.videoCapture = vc;
        this.videoPanel = vp;
    }

    public void run(){
        this.isRunning = true;
        this.videoPanel.setVideoStreamState(true);
        System.out.println("VideoPanelManager is running!");

        if (videoCapture.isOpened()) {
            while (isRunning) {
                try {
                    Thread.sleep(REFRESH_RATE);

                    Mat n = new Mat();

                    videoCapture.read(n);

                    videoPanel.setFrame(MatConverter.getBufferedImage(n));
                    videoPanel.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } else {
            return; // Should be enough to stop the thread :)))
        }
    }

    public void stop() {
        this.isRunning = false;
        this.videoPanel.setVideoStreamState(false);
    }
}