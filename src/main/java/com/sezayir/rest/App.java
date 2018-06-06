package com.sezayir.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sezayir.dao.DerbDbRepsository;
import com.sezayir.service.BankServiceImpl;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;


/**
 * Main Application
 *
 */
public class App {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages("com.sezayir.rest");
        

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException    {
        final HttpServer server = startServer();
        System.out.println("ersey app started with WADL available at :"+ BASE_URI);
        System.out.println("Hit enter to stop it");
        
        BankServiceImpl bs=  new    BankServiceImpl();
        bs.createUsers();
        bs.createAccounst();
        
        System.in.read();
        server.stop();
    }
    

}

