/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miservicioweb;

import com.vogella.logging.MyLogger;
import excepcionws.WSException;
import java.util.logging.Level;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceException;
import java.util.logging.Logger;


/**
 *
 * @author sdist
 */
@WebService(serviceName = "miWS")
public class miWS {
    private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public miWS(){
        System.out.println("Servicio web creado");
        try{
            MyLogger.setup("Servidor");
        }catch(Exception e){
            Logger.getLogger(miWS.class.getName()).log(Level.SEVERE, "Error al configurar el log");
            e.printStackTrace();
        }
        
        LOG.info("Creando web service");
    }
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "suma")
    public int suma(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        //TODO write your implementation code here:
        return a+b;
    }
    
    @WebMethod(operationName = "div")
    public double division(@WebParam(name = "a") double a, @WebParam(name = "b") double b){
        if(b == 0){
            throw new WSException(LOG, "No se puede dividir entre cero", new WebServiceException("##$##División entre cero$$#$$"));
        }
      
        return a/b;
    }
}
