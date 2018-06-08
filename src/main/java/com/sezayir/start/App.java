package com.sezayir.start;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sezayir.service.BankServiceImpl;


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
        final ResourceConfig rc = new ResourceConfig().packages("com.sezayir");
        

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }


	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		final HttpServer server = startServer();
		System.out.println("Jersey app started and  available at :" + BASE_URI);

		BankServiceImpl service = new BankServiceImpl();
		service.createUsers();
		service.createAccounst();
		System.out.println("HIT ENTER TO STOP IT");

		System.in.read();
		service.cleanDB();
		server.stop();
	}
    

}

