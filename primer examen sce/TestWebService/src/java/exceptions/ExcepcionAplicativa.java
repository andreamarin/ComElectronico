/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.vogella.logger.MyHTMLFormatter;
import com.vogella.logger.MyLogger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author sdist
 */
public class ExcepcionAplicativa extends WebServiceException{
    private static final Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    private static boolean log_init = false;
    private long ERROR_CODE;
    private String summary;
    
    
    public ExcepcionAplicativa(String message, long code) {
        super(message);
        
        if(!log_init){
                try {
                // TODO code application logic here
                MyLogger.setup("C:/155766/wslogs/SimpleWSlogs");
                log_init = true;
                } catch (IOException ex) {
                    log.log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    log.log(Level.SEVERE, null, ex);
                }
        }
        
        log.log(Level.WARNING, "Error Code: " + code);
        
        summary = message;
        ERROR_CODE = code;
    }

    public long getERROR_CODE() {
        return ERROR_CODE;
    }

    public String getSummary() {
        return summary;
    }
    
    
}
