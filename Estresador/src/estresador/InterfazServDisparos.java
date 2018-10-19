/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estresador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Logger;

/**
 *
 * @author andreamarin
 */
public interface InterfazServDisparos  extends Remote{   
    public long quienSoy() throws RemoteException;
    public long tiempoEspera() throws RemoteException;
    public void acumula(long avg, long std, long n, long max, long min) throws RemoteException;
    public void reset(long time) throws RemoteException;
    public String stats() throws RemoteException;
    public void cambiaLambda(double lambda) throws RemoteException;  
}

