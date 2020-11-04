package com.marck_devs.GitServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public final class Constant {
    public static int LOGGER_PORT = 3402;
    public static int COMMAND_PORT = 1513;
    public static String REPO_HOME = "./";
    public static final String REPO_EXT = ".git";
    public static final String PROPERTIES_FILE = "conf.properties";
    public static final int MAX_LOG_LENGTH = 14;

    private static Properties config;

    static {
        config = new Properties();
        try {
            config.load(new FileInputStream(new File(PROPERTIES_FILE)));
            if(config.containsKey("Logger.Port"))
                LOGGER_PORT = (int) config.get("Logger.Port");
            if(config.containsKey("CommadManager.Port"))
                COMMAND_PORT = (int) config.get("CommandManager.Port");
            if(config.containsKey("RepoManager.HomeFolder")){
                String tmp = (String) config.get("RepoManager.HomeFolder");
                REPO_HOME = tmp.endsWith("/") ? tmp : tmp + "/";
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

/*
get all commites, autors: git for-each-ref --format='%(authorname) %09 %(authoremail)' | short -k5n -k2M -k3n -k4n
pretty log => git log --pretty=format:'{"key":"%h", "author": "%an", "emai":"%am", "time":"%ar", "msg":"$s"}';
 */