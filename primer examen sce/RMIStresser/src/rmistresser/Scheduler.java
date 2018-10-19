/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmistresser;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author mgb
 */
public class Scheduler implements SchedulerInterface{
    long totClients, startTime, totRequests, sum, sqsum, min, max;
    double lam;
    
    public Scheduler(){
        lam = 0;
        totClients = 0;
        startTime = 0;
        totRequests = 0;
        sum = 0;
        sqsum = 0;
        min = Long.parseLong("7FFFFFFFFFFFFFFF",16);
        max = 0;
    }
    
    //Genera numero de cliente
    @Override
    public long clientID() throws RemoteException {
        return ++totClients;
    }

    //Genera tiempo con distribucion exponencial de parametro lambda lambda
    @Override
    public long nanoDelay() throws RemoteException {
        //Genera en milis, luego pasa a nanos
        long expDelay = (long) Math.ceil((-1.0/lam * (Math.log(Math.random())))*1e6);
        //System.out.println("Delay: " + expDelay + " (ns)");
        
        return (startTime-System.currentTimeMillis())*1000000 + expDelay;
    }

    //Acumula estadistcas de cada ciente. sum, sqsum, min y max se sacan del response time de cada cliente en nanos
    @Override
    public void addStats(long numRequests, long sum, long sqsum, long min, long max) throws RemoteException {
        this.totRequests += numRequests;
        this.sum += sum;
        this.sqsum += sqsum;
        this.min = (this.min < min) ? this.min : min;
        this.max = (this.max > max) ? this.max : max;
    }

    //Cambia lambda de la distribucion del tiempo
    @Override
    public void changeLambda(double lam) throws RemoteException {
        //System.out.println("La nueva lambda es: "+ lam);
        this.lam = lam;
        
    }

    //Asigna nuevo tiempo de comienzo
    @Override
    public void reset(long miliStart) throws RemoteException {
        totClients = 0;
        startTime = System.currentTimeMillis()+ miliStart;
        totRequests = 0;
        sum = 0;
        sqsum = 0;
        min = 0;
        max = 0;
    }

    //Estadisticas, estan en nanos (menos avg y std q estan en milis)
    @Override
    public long maxResponse() throws RemoteException {
        return max;
    }

    @Override
    public long minResponse() throws RemoteException {
        return min;
    }

    
    //En milis
    @Override
    public double responseAvg() throws RemoteException {
        return sum/((double) totRequests)*1E-6;
    }

    //En milis
    @Override
    public double responseStdDev() throws RemoteException {
        double avg = responseAvg();
        return Math.sqrt((sqsum*1e-12 - totRequests*avg*avg)/(totRequests-1));
    }
    
    public long getTotRequests() throws RemoteException{
        return totRequests;
    }
    
    /*
    args:
    0: lambda de la distribucion exponencial, por default 5
    1: tiempo de espera, por default 60 segundos = 60 000 milis
    2: host del rmi
    */
    public static void main(String[] args) {
        String host = "localhost";
        
        try{
            Scheduler obj = new Scheduler();
            
            if(args.length == 0){
                
                //Default: empieza en 60 segs en lambda = 5 (5 eventos por segundo)
                obj.lam = 5;
                obj.startTime = System.currentTimeMillis() + 60000;

            }else if(args.length == 1){
                
                //Lee lambda
                obj.lam = Double.parseDouble(args[0]);
                obj.startTime = System.currentTimeMillis() + 60000;

            }else if(args.length == 2){
                
                //Lee lambda, delay
                obj.lam = Double.parseDouble(args[0]);
                obj.startTime = System.currentTimeMillis() + Long.parseLong(args[1])*1000;

            }else{
                
                //Lee lambda, delay, host
                obj.lam = Double.parseDouble(args[0]);
                obj.startTime = System.currentTimeMillis() + Long.parseLong(args[1])*1000;
                host = args[2];

            }
            
            SchedulerInterface stub = (SchedulerInterface) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(host);
            registry.rebind("Scheduler", stub);
            System.out.println("Scheduler listo...");
            
        }catch (Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
