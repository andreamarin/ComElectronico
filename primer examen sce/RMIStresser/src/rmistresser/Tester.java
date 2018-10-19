/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmistresser;

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
 * @author Miguel Gonzalez Borja
 */
public class Tester {
    private static final Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    public static void main(String[] args) {
        
        try {
            // TODO code application logic here
            MyLogger.setup("logs/test");
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
        tws.SimpleWebService_Service service = new tws.SimpleWebService_Service();
        tws.SimpleWebService port = service.getSimpleWebServicePort();
        
        long min, t0, t1, responseTime;
        double a, b, response;
        response = 0;
        min = Long.parseLong("7FFFFFFFFFFFFFFF",16);
        
        a = (args.length > 0) ? Double.parseDouble(args[0]) : 1;
        b = (args.length > 1) ? Double.parseDouble(args[1]) : 0;
        
        
        t0 = System.nanoTime();
        try{
            response = port.division(a, b);
        }catch(WebServiceException ex){
            String s = ex.getMessage();
            int start, end;
            start = s.indexOf("@START");
            end = s.indexOf("@END");
            s = s.substring(start+6, end);
            log.log(Level.SEVERE, s);
        }
        
        t1 = System.nanoTime();
        responseTime = t1-t0;
        
        if(responseTime < min){
            min = responseTime;
        }
        
        System.out.println("responseTime: " + responseTime);
        System.out.println("min: " + min);
        System.out.println("response: "+ response);
        
    }
}
