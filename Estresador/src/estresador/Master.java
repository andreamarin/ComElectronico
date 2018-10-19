package estresador;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreamarin
 */
public class Master {
    
    static InterfazServDisparos servidor;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ans="";
        String res = "";
        Scanner sc = new Scanner(System.in);
        
        System.out.println("----------------------------------------------------------------");
        System.out.println("Master empezando con "+args.length+" argumento(s).");

        String host = (args.length < 1) ? "localhost"  : args[0];
        System.out.println("\t * host: " +host);

        try{
            Registry registry = LocateRegistry.getRegistry(host);
            servidor = (InterfazServDisparos) registry.lookup("ServDisparo");
            
            do{
                System.out.println("¿Qué quieres hacer? \n"
                        + "\t 1. Resetar servidor. \n"
                        + "\t 2. Cambiar el valor de lambda \n"
                        + "\t 3. Obtener estadísticas \n"
                        + "\t 4. Salir \n");
                
                ans = sc.next();
                ans = ans.replace(".", "");
                
                switch(ans){
                    case "1":
                        System.out.println("¿Cuántos segundos quieres que espere?\n");
                        res = sc.next();
                        
                        servidor.reset(Long.parseLong(res));
                        break;
                        
                    case "2":
                        System.out.println("¿Qué valor de lambda quieres?");
                        res = sc.next();
                        servidor.cambiaLambda(Double.parseDouble(res));
                        break;
                        
                    case "3":
                        res = servidor.stats();
                        System.out.println(res);
                        break;
                        
                    case "4":
                        break;
                        
                    default:
                        System.out.println("Opción inválida");
                }
            }while(!ans.equals("4"));
            
        }catch(Exception e){
            
        }
    }
    
}
