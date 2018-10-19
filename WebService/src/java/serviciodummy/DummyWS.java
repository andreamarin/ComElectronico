/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciodummy;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author andreamarin
 */
@WebService(serviceName = "miWS")
public class DummyWS{
    
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
    public double division(@WebParam(name = "a")double a, @WebParam(name = "b")double b){
        if(b == 0){
            throw new WebServiceException("No se puede dividir entre cero");
        }else{
            return a/b;
        }
    }
    
    
}
