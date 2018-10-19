package estresador;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.vogella.logger.MyLogger;
import java.io.IOException;
/**
 * Clase que se encarga de gestionar los clientes
 * @author andreamarin
 * 
 * Determina cuánto tiempo debe de esperar cada cliente y acumula las estadísticas
 */

public class ServidorDisparos implements InterfazServDisparos {
    private long n; // número de clientes
    private long max; // tiempo de espera máximo
    private long min; // tiempo de espera mínimo
    private long avg; // promedio del tiempo
    private long std; // desviación estándar
    private static int id = 0;
    private double lambda = 5; // parámetro de la dist. exponencial
    private long empieza; // En cuánto tiempo va a comenzar a funcionar el servidor (ms)
    
    public ServidorDisparos(){
        n = 0; 
        std = 0;
        avg = 0;
        max = 0;
        min = 0;
        //tiempoEspera = (long) (System.currentTimeMillis() + (60*1e3));  // por default se espera 60s
        empieza = 0;   
    }
    
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    @Override
    public long quienSoy() throws RemoteException {
        return ++id;
    }

    /**
     * 
     * @return
     * @throws RemoteException 
     */
    @Override
    public long tiempoEspera() throws RemoteException {
        long delay;
        
        //genero el delay en nano segundos
        delay = (long) ((-(1/lambda)*Math.log(Math.random()))*(1e6));
        System.out.println("Delay "+delay+" ns");
        
        return (long) ((empieza - System.currentTimeMillis())*(1e6)+delay);
    }
    
    /**
     * 
     * @param lambda
     * @throws RemoteException 
     */
    @Override
    public void cambiaLambda(double lambda) throws RemoteException{
        this.lambda = lambda;
    }
    
    /**
     * 
     * @param avg
     * @param std
     * @param n
     * @param max
     * @param min
     * @throws RemoteException 
     */
    @Override
    public void acumula(long avg, long std, long n, long max, long min) throws RemoteException {
        this.avg += avg;
        this.std += std;
        this.n += n;
        
        if(n == 0){
            this.max = max;
            this.min = min;
        }else{
            if(this.max < max)
                this.max = max;

            if(this.min > min)
                this.min = min;
        }
    }

    /**
     * Resetea los valores del servidor
     * @param time Cuántos segundos quiere que se espere para comenzar
     * @throws RemoteException
     */
    @Override
    public void reset(long time) throws RemoteException {
        n = 0;
        std = 0;
        avg = 0;
        max = 0;
        min = 0;
        id = 0;
        
        empieza = (long) (System.currentTimeMillis() + time*1e3);
        
        System.out.println("La prueba comienza en " + time + "s");
    }

    
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    @Override
    public String stats() throws RemoteException {
        String res = "";
        avg = avg/n;
        std = (long) (std - n*Math.pow(avg,2));
        
        res = "Se hicieron "+n+" solocitudes: \n"+
                "\t Tiempo promedio: "+avg+" s \n"+
                "\t Desviación estándar "+std+ "s \n"+
                "\t Tiempo de respuesta mínimo: "+ min +"s \n"+
                "\t Tiempo de respuesta máximo: "+ max + "s \n";
        
        return res;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Servidor empezando con "+args.length+" argumento(s).");
        
        InterfazServDisparos servidor;
        servidor = new ServidorDisparos();
        String host = (args.length < 1) ? "localhost" : args[0];
        System.out.println("\t * host: " +host);
        
        
        
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            InterfazServDisparos stub = (InterfazServDisparos) UnicastRemoteObject.exportObject(servidor,0);
            registry.rebind("ServDisparo", stub); //guardo mi servidor en el RMI
            
            servidor.reset(60);
            
            System.out.println("Servidor de disparo listo...");
        }catch(Exception  e){
            System.out.println("Server exception "+e.toString());
        }
    }
}
