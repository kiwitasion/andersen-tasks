package ru.client;

import ru.ws.HelloWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloWebServiceClient {

    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:1986/wss/hello?wsdl");

        QName qname = new QName("http://ws.ru/", "HelloWebServiceImplService");

        Service service = Service.create(url, qname);

        HelloWebService hello = service.getPort(HelloWebService.class);

        System.out.println(hello.getHelloString("Web service"));
    }
}
