/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmistresser;


import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.xml.ws.WebServiceException;
import com.vogella.logger.MyHTMLFormatter;
import com.vogella.logger.MyLogger;


/**
 *
 * @author mgb
 */
public class Client {
    private static final Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    /*
    args:
    0: num solicitudes, default 10
    1: rmihost, defaul localhost
    */
    public static void main(String[] args) {
        try {
            MyLogger.setup("logs/clientLogs");
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
        
        String rmihost = "localhost";
        long numRequests = 10;
        long errors = 0;
        long id, delay, milis, nanos, sum, sqsum, min, max, t0, t1, responseTime;
        sum = 0;
        sqsum = 0;
        min = Long.parseLong("7FFFFFFFFFFFFFFF",16);
        System.out.println(min);
        max = -1;
        
        //Lee argumentos
        if(args.length == 1){
            
            numRequests = Long.parseLong(args[0]);
            
        }else if (args.length > 1){
            
            numRequests = Long.parseLong(args[0]);
            rmihost = args[1];
            
        }
        
        try{
            //Consigue el scheduler desde el rmi en rmihost
            Registry registry = LocateRegistry.getRegistry(rmihost);
            SchedulerInterface sch = (SchedulerInterface) registry.lookup("Scheduler");
            
            //Prepara variables para peticiones
            double a, b, response;
            Random rng = new Random();
            
            //Crea los objetos para la llamada al webservice
            tws.SimpleWebService_Service service = new tws.SimpleWebService_Service();
            tws.SimpleWebService port = service.getSimpleWebServicePort();
            
            //Consigue tu id + tiempo de espera para mandar solicitudes
            id = sch.clientID();
            delay = sch.nanoDelay();
            milis =(long) Math.floor(delay * 1e-6);
            nanos = (long) (delay-milis*1e6);
            System.out.println("Cliente numero: " + id);
            System.out.println("Delay de: "+milis+" ms, "+nanos+" ns");
            
            Thread.sleep(milis, (int) nanos);
            
            
            
            
            //Manda numRequests solicitudes
            for(int i = 0; i < numRequests; ++i){
                a = rng.nextDouble()*100;
                b = rng.nextDouble()*100;
                
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
                    log.log(Level.SEVERE, s);
                    ++errors;
                    continue;
                }
                
                t1 = System.nanoTime();
                
                responseTime = t1-t0;
                
                if(responseTime > max){
                    max = responseTime;
                }else if(responseTime < min){
                    min = responseTime;
                }
                
                sum += responseTime;
                sqsum += responseTime*responseTime;
                
                if(i % 100 == 0){
                    System.out.println("Solicitud "+i+": " + a + " + " + b + " = " + response);
                }
            }
            
            
            sch.addStats(numRequests - errors, sum, sqsum, min, max);
            
            
        }catch(Exception e){
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
