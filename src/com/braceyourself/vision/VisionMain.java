package com.braceyourself.vision;

import com.braceyourself.utility.FileManager;
import org.opencv.core.Mat;
import org.opencv.dnn.Net;
import org.opencv.videoio.VideoCapture;

import static org.opencv.dnn.Dnn.readNetFromCaffe;

public class VisionMain {

    public final String protoFile = "models\\pose_deploy_linevec.prototxt";
    public final String weightsFile = "models\\pose_iter_440000.caffemodel";

    public VideoCapture videoCapture;
    public VideoPanel videoPanel;
    public VideoPanelManager vPM;

    public Net net;

    public VisionMain(VideoPanel vp) {
        this.videoPanel = vp;
        System.out.println("Reading proto and weights file . . .");
        net = readNetFromCaffe(FileManager.getPath() + protoFile, FileManager.getPath() + weightsFile);
        System.out.println("Proto and Weights file loaded successfully!");

        System.out.println("Setting up VideoCapture webcam stream . . .");
        videoCapture = new VideoCapture(0);
        if (!videoCapture.isOpened()) {
            System.out.println("FATAL: Failed to setup webcam stream!");
        } else {
            System.out.println("Found webcam!");

            startVideoPanelManager();
        }
    }

    public void ProcessFrame(Mat frame) { // Frame must be imported through blobFromImage(frame, 1.0 / 255, Size(inWidth, inHeight), Scalar(0, 0, 0), false, false);
        net.setInput(frame);
        Mat output = net.forward();
    }

    public void startVideoPanelManager() {
        vPM = new VideoPanelManager(this.videoCapture, this.videoPanel, this.net);
        new Thread(vPM).start();

    }
}