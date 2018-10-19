/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmistresser;

import java.lang.reflect.Method;

/**
 *
 * @author sdist
 */
public class Dispatcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = args.length;
        
        if(n == 0){
            System.out.println("TODO: Agregar informacion sobre argumentos par cada clase");
        }else{
            String exec = args[0];
            String[] args2;
            
            //Preparar args para la clase llamada
            if(n == 1){
                args2 = new String[0];
            }else{
                args2 = new String[n-1];
                for(int i=1; i<n; i++){
                    args2[i-1] = args[i];
                }
            }
            
            /*
            //Llamada a main correspondiente
            if(exec.compareToIgnoreCase("client")==0){
                Client.main(args2);
            }else if(exec.compareToIgnoreCase("scheduler")==0){
                Scheduler.main(args2);
            }else if(exec.compareToIgnoreCase("admin")==0){
                Admin.main(args2);
            }*/
            
            
            Class[] cArg = new Class[1];
            cArg[0] = String[].class;
            System.out.println("Redirigiendo a: "+exec);
            try{
                Class cl = Class.forName(exec);
                Method m = cl.getMethod("main", cArg);
                m.invoke(cl, (Object) args2);
            }catch (Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
}
