/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tws;

import exceptions.ExcepcionAplicativa;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sdist
 */
@WebService(serviceName = "SimpleWebService")
public class SimpleWebService {

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
    @WebMethod(operationName = "division")
    public double division(@WebParam(name = "a") double a, @WebParam(name = "b") double b) throws ExcepcionAplicativa{
        if(b==0) throw new ExcepcionAplicativa("@STARTDivision entre 0@END", 1);
        return a/b;
    }
}
