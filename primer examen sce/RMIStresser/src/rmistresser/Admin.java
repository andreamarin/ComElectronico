/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmistresser;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author MGONZALEBOR
 */
public class Admin {
    public static void main(String[] args) {
        try{
            
            String rmihost = (args.length == 0) ? "localhost" : args[0] ;
            
            //Consigue el scheduler desde el rmi en rmihost
            Registry registry = LocateRegistry.getRegistry(rmihost);
            SchedulerInterface sch = (SchedulerInterface) registry.lookup("Scheduler");
            
            String command = "";
            Scanner in = new Scanner(System.in);
            
            while(!command.equals("exit")){
                System.out.println("Que desea hacer?");
                System.out.println("\t(1) Resetear el Scheduler");
                System.out.println("\t(2) Cambiar el parametro de la distribucion de los tiempos");
                System.out.println("\t(3) Obtener estadisticas de prueba de estresamiento");
                System.out.println("\t(exit) Cerrar administrador");
                command = in.nextLine();
                switch(command){
                    case "1":
                        System.out.println("En cuanto tiempo (segundos) empieza la prueba?");
                        command = in.nextLine();
                        sch.reset(Long.parseLong(command)*1000);
                        break;
                    case "2":
                        System.out.println("Cual es el nuevo parametro lambda?");
                        command = in.nextLine();
                        sch.changeLambda(Double.parseDouble(command));
                        break;
                    case "3":
                        System.out.println("============================");
                        System.out.println("Total de solicitudes: " + sch.getTotRequests());
                        System.out.println("Promedio (ms): " + sch.responseAvg());
                        System.out.println("Desviacion Estandar (ms): " + sch.responseStdDev());
                        System.out.println("Maximo tiempo de respuesta (ms): " + sch.maxResponse()*1e-6);
                        System.out.println("Minimo tiempo de respuesta (ms): " + sch.minResponse()*1e-6);
                        System.out.println("============================"); 
                        break;
                    default:
                        break;
                }
                
            }
            
        }catch(Exception e){
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
