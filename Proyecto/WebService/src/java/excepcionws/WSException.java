/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author andreamarin
 */
public class WSException extends WebServiceException{
    private String metodo;
    
    public WSException(Logger LOG, String msg, Exception e){
        super(e.getMessage());
        
        final StackTraceElement[] ste   = e.getStackTrace();
        int depth = 1;
        metodo  = ste[ste.length-1-depth].getMethodName();
        
        LOG.log(Level.INFO, "Error en {0}: {1}", new Object[]{metodo, msg});
    }
    
    public WSException(Logger LOG, String msg){
        super(msg);
        
        LOG.log(Level.INFO, "Error: {1}", msg);
    }
    
}
