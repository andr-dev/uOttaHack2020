package com.braceyourself.utility;

import java.io.File;

public class FileManager {

    private final String path_root = System.getProperty("user.home");
    private final String path_extension = "\\AppData\\Local\\";
    private final String path_appName = "postureapp\\";
    private static String path;

    public FileManager() {
        path = path_root + path_extension + path_appName;

        if(!new File(path).exists()){
            new File(path).mkdir();
        }
    }

    public boolean saveFile() {
        //public static final File savedir = new File(new File(System.getProperty("user.home")), ".yourgame");
        return false;

    }

    public File getFile(String subPath) {
        File file = new File(path + subPath);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static String getPath() {
        return path;
    }
}
