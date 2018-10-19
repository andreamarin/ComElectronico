
package miservicioweb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the miservicioweb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hello_QNAME = new QName("http://miservicioweb/", "hello");
    private final static QName _DivResponse_QNAME = new QName("http://miservicioweb/", "divResponse");
    private final static QName _Div_QNAME = new QName("http://miservicioweb/", "div");
    private final static QName _Suma_QNAME = new QName("http://miservicioweb/", "suma");
    private final static QName _SumaResponse_QNAME = new QName("http://miservicioweb/", "sumaResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://miservicioweb/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: miservicioweb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Div }
     * 
     */
    public Div createDiv() {
        return new Div();
    }

    /**
     * Create an instance of {@link Suma }
     * 
     */
    public Suma createSuma() {
        return new Suma();
    }

    /**
     * Create an instance of {@link SumaResponse }
     * 
     */
    public SumaResponse createSumaResponse() {
        return new SumaResponse();
    }

    /**
     * Create an instance of {@link DivResponse }
     * 
     */
    public DivResponse createDivResponse() {
        return new DivResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://miservicioweb/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://miservicioweb/", name = "divResponse")
    public JAXBElement<DivResponse> createDivResponse(DivResponse value) {
        return new JAXBElement<DivResponse>(_DivResponse_QNAME, DivResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Div }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://miservicioweb/", name = "div")
    public JAXBElement<Div> createDiv(Div value) {
        return new JAXBElement<Div>(_Div_QNAME, Div.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Suma }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://miservicioweb/", name = "suma")
    public JAXBElement<Suma> createSuma(Suma value) {
        return new JAXBElement<Suma>(_Suma_QNAME, Suma.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://miservicioweb/", name = "sumaResponse")
    public JAXBElement<SumaResponse> createSumaResponse(SumaResponse value) {
        return new JAXBElement<SumaResponse>(_SumaResponse_QNAME, SumaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://miservicioweb/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
