package ru.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.ws.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {

    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "!";
    }
}
