package estresador;

import java.lang.reflect.Method;

/**
 * Clase que se encarga de redireccionar el programa a la clase que se necesita
 * @author andreamarin
 */
public class Distribuidor {

    /**
     * @param args el primer argumento debe ser el nombre de la clase a la que se quiere llamar. 
     *  Después se ponen los argumentos pertinentes a la clase que se llamó.
     */
    public static void main(String[] args) {
        int n = args.length;
        System.out.println("Distribuidor empezando con "+n+" argumento(s).");
        String nomClase;
        String[] args2 = new String[n-1];;
        
        if(n > 1){
            for (int i = 1; i < n; i++) {
                args2[i-1] = args[i]; 
            }
        }
        
        nomClase = args[0];
        Class[] cArg = new Class[1];
        cArg[0] = String[].class;
        System.out.println("Redirigiendo a "+nomClase);
        try{
            //Ejecución dinámica de clases
            Class cl = Class.forName(nomClase); // Se crea una instancia de la clase requerida
            Method m = cl.getMethod("main", cArg);  // Se obtiene el método "main" de la clase
            m.invoke(cl, (Object) args2);   // invocamos el método con los argumentos de la clase
            
        }catch(Exception e){
            System.out.println("Error "+e.toString());
        }
        
//        if(nomClase.compareToIgnoreCase("Cliente") == 0)
//            Cliente.main(args2);
//        else if (nomClase.compareToIgnoreCase("Master") == 0)
//            Master.main(args2);
//        else if (nomClase.compareToIgnoreCase("ServidorDisparos") == 0)
//            ServidorDisparos.main(args2);
        
    }
    
}
