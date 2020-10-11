
package com.wonder4work.webserviceclient.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wonder4work.webserviceclient.client package. 
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

    private final static QName _ListAllCityWeather_QNAME = new QName("http://service.wonder4work.com", "listAllCityWeather");
    private final static QName _ListAllCityWeatherResponse_QNAME = new QName("http://service.wonder4work.com", "listAllCityWeatherResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wonder4work.webserviceclient.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListAllCityWeather }
     * 
     */
    public ListAllCityWeather createListAllCityWeather() {
        return new ListAllCityWeather();
    }

    /**
     * Create an instance of {@link ListAllCityWeatherResponse }
     * 
     */
    public ListAllCityWeatherResponse createListAllCityWeatherResponse() {
        return new ListAllCityWeatherResponse();
    }

    /**
     * Create an instance of {@link Weather }
     * 
     */
    public Weather createWeather() {
        return new Weather();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllCityWeather }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wonder4work.com", name = "listAllCityWeather")
    public JAXBElement<ListAllCityWeather> createListAllCityWeather(ListAllCityWeather value) {
        return new JAXBElement<ListAllCityWeather>(_ListAllCityWeather_QNAME, ListAllCityWeather.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllCityWeatherResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wonder4work.com", name = "listAllCityWeatherResponse")
    public JAXBElement<ListAllCityWeatherResponse> createListAllCityWeatherResponse(ListAllCityWeatherResponse value) {
        return new JAXBElement<ListAllCityWeatherResponse>(_ListAllCityWeatherResponse_QNAME, ListAllCityWeatherResponse.class, null, value);
    }

}
