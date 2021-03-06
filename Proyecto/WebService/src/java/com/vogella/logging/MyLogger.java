/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vogella.logging;

/**
 *
 * @author andreamarin
 */


import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static private String path;
    
    static public void setup(String name) throws IOException {

        // get the global logger to configure it
        Logger logger;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        
        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
        
        path = "/Users/andreamarin/Desktop/ITAM/7semestre/ComElectronico/Proyecto/log/"; //../log/";//~/Desktop/ITAM/7semestre/ComElectronico/Proyecto/log/";
        logger.setLevel(Level.ALL);
        fileTxt = new FileHandler(path+"Log_"+name+".txt", true);
        fileHTML = new FileHandler(path+"Log_"+name+".html",true);

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // create an HTML formatter
        formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}
