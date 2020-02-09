package com.braceyourself.vision;

import com.braceyourself.utility.FileManager;
import com.braceyourself.utility.MatConverter;
import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.CvType.CV_32F;
import static org.opencv.dnn.Dnn.blobFromImage;
import static org.opencv.imgproc.Imgproc.*;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH;

public class VideoPanelManager implements Runnable {

    private VideoCapture videoCapture;
    private VideoPanel videoPanel;
    private Net net;
    private final int REFRESH_RATE = 1000/30; // Milliseconds per refresh
    private boolean isRunning = false;
    private MatConverter matConverter;

    public VideoPanelManager(VideoCapture vc, VideoPanel vp, Net net) {
        this.videoCapture = vc;
        this.videoPanel = vp;
        this.net = net;
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

//                    Mat blob = blobFromImage(n);
//                    net.setInput(blob);
//
//                    Mat o = net.forward();
//
//                    int h = o.size(2);
//                    int w = o.size(3);
//
//                    System.out.println("h: " + h + ", w: " + w);
//
//                    ArrayList<Point> points = new ArrayList<Point>();
//
//                    Mat m = n.clone();
//
//                    for (int a = 0; a < 15; a++) {
//                        Point p = new Point(-1, -1);
//                        p.x *= (float) videoCapture.get(CAP_PROP_FRAME_WIDTH) / w;
//                        p.y *= (float) videoCapture.get(CAP_PROP_FRAME_HEIGHT) / h;
//
//                        circle(m, new Point( (int) p.x, (int) p.y), 16, new Scalar(0,255,255), -1);
//
//                        points.add(p);
//                    }
//
//                    for (int a = 0; a < 7; a++)
//                    {
//                        // lookup 2 connected body/hand parts
//                        Point partA = points.get(POSE_PAIRS[a][0]);
//                        Point partB = points.get(POSE_PAIRS[a][1]);
//
//                        if (partA.x<=0 || partA.y<=0 || partB.x<=0 || partB.y<=0)
//                            continue;
//
//                        line(n, partA, partB, new Scalar(0,255,255), 8);
//                        circle(n, partA, 8, new Scalar(0,0,255), -1);
//                        circle(n, partB, 8, new Scalar(0,0,255), -1);
//                    }



                    CascadeClassifier face = new CascadeClassifier(FileManager.getPath() + "models/haarcascade_frontalface_default.xml");
                    //CascadeClassifier upperBody = new CascadeClassifier(FileManager.getPath() + "models/haarcascade_upperbody.xml");

                    Mat gray = new Mat();
                    cvtColor(n, gray, COLOR_BGR2GRAY);

                    Mat imager = new Mat();
                    Mat grayr = new Mat();

                    Size scale = new Size(720,480);

                    Imgproc.resize(n, imager, scale);
                    Imgproc.resize(gray, grayr, scale);

                    MatOfRect faceDetections = new MatOfRect();
                    MatOfRect upperBodyDetections = new MatOfRect();

                    face.detectMultiScale(grayr, faceDetections);
                    //upperBody.detectMultiScale(grayr, upperBodyDetections);

                    //System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
                    //System.out.println(String.format("Detected %s upperBodies", upperBodyDetections.toArray().length));

                    for (Rect rect : faceDetections.toArray()) {
                        rectangle(imager, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                    }

//                    for (Rect rect : upperBodyDetections.toArray()) {
//                        rectangle(imager, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
//                    }

//                    double ratio = 0;
//                    if (upperBodyDetections.toArray().length != 0) {
//                        if (faceDetections.toArray().length != 0) {
//                            ratio = ((double) faceDetections.toArray()[0].width) / ((double) upperBodyDetections.toArray()[0].width);
//                            System.out.println(ratio);
//                        }
//                    }



                    videoPanel.setFrame(MatConverter.getBufferedImage(imager));
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