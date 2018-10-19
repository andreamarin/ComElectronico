/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estresador;

import com.vogella.logger.MyLogger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author andreamarin
 */
public class Cliente{
    private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        long responseTime = 0L;
        long sum = 0;   // suma de los tiempos
        long sum2 = 0;  // suma de los cuadrados de los tiempos
        long tiempoEspera = 0;
        long max  = 0;
        long min = 10000;
        long miId;
        
        int x = 0;
        int y = 0;
        int res = 0;
        double div_res;
        
        int n = (args.length < 1) ? 15 : Integer.parseInt(args[1]);
        String host = (args.length < 2) ? "localhost" : args[0];
        
        try{
            MyLogger.setup("Cliente");            
            
            // obtiene el RMI
            Registry registry = LocateRegistry.getRegistry(host);
            
            // obtengo el servidor de disparos que estoy usando
            InterfazServDisparos servidor = (InterfazServDisparos) registry.lookup("ServDisparo");
            miId = servidor.quienSoy();
            tiempoEspera = servidor.tiempoEspera();
                        
            System.out.println("----------------------------------------------------------------");
            System.out.println("Cliente "+miId+" empezando con "+args.length+" argumento(s).");
            System.out.println("\t * Num solicitudes: " +n);
            System.out.println("\t * host: " +host);
            System.out.println("Faltan " + tiempoEspera*1e-6 + " ms");
            
            LOG.log(Level.INFO, "Cliente {0} listo para procesar {1} solicitudes", new Object[] {miId,n});
            
            
            long milis = (long) Math.floor(tiempoEspera * 1e-6);
            long nanos = (long) (tiempoEspera - milis*1e6);
            
            // port del servicio web para no tener que crearlo cada vez
            miservicioweb.MiWS_Service service = new miservicioweb.MiWS_Service();
            miservicioweb.MiWS port = service.getMiWSPort();
            
            // cliente se duerme _tiempoEspera_ segundos
            Thread.currentThread().sleep(milis, (int)nanos);
            
            for (int i = 0; i < n; i++){
                System.out.println("\n*****************************");
                System.out.println("Proporcionando solicitud "+(i+1));
                
                x = (int) (Math.round(Math.random()*8000)/100);
                y =  (int) (Math.round(Math.random()*8000)/100);
                
                
                responseTime = System.nanoTime();
                
                double p = Math.random();
                try{
                    if(p < 0.4)
                        div_res = port.div(x, 0);
                    else
                        div_res = port.div(x, y);
                    
                    System.out.println(x+" entre "+y+" es "+div_res);
                    
                }catch(WebServiceException e){
                    String ex =  e.toString();
                    int start_index = ex.indexOf("##$##");
                    int end_index = ex.indexOf("$$#$$");
                    
                    String msg = ex.substring(start_index+5, end_index);
     
                    LOG.log(Level.WARNING, "Solicitud {0}: {1}", new Object[]{i, msg});
                    
                    System.out.println(ex);
                }
                
                res = port.suma(x, y);
                
                responseTime = System.nanoTime() - responseTime;
                
                sum += responseTime;
                sum2 += Math.pow(responseTime, 2);
                
                if(i == 0){
                    min = responseTime;
                    max = responseTime;
                }else{
                    if(responseTime < min)
                        min = responseTime;
                
                    if(responseTime > max)
                        max = responseTime;
                }
                
                //LOG.log(Level.INFO, "Solicitud {0} exitosa",i);
                
                
                System.out.println("La suma de "+x+" + "+y+" es "+res+".");
                System.out.println("Tiempo de espera: "+(responseTime*1e-9)+"s.");
                
                System.out.println("*****************************");

            }
            // acumula los datos del cliente actual en el Servidor de Disparos
            servidor.acumula(sum, sum2, n, min, max);
            System.out.println("----------------------------------------------------------------");

        }catch(Exception e){
            System.out.println("Error en el cliente "+e.toString());
        }
    }
}
