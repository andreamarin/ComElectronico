/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmistresser;

/**
 *
 * @author mgb
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface SchedulerInterface extends Remote {
    
    //Para los clientes
    public long clientID() throws RemoteException;
    public long nanoDelay() throws RemoteException;
    public void addStats(long numRequests, long sum, long sqsum, long min, long max) throws RemoteException;
    
    
    //Control global de parametros
    public void changeLambda(double lam) throws RemoteException;
    public void reset(long miliStart)throws RemoteException;
    
    //Estadisticas de todos los intentos
    public long maxResponse() throws RemoteException;
    public long minResponse() throws RemoteException;
    public double responseAvg() throws RemoteException;
    public double responseStdDev() throws RemoteException;
    public long getTotRequests() throws RemoteException;
    
}
