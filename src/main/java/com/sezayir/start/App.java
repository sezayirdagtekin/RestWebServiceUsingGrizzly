package com.sezayir.start;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sezayir.service.AcccountService;
import com.sezayir.service.AccountServiceImpl;
import com.sezayir.service.CustomerService;
import com.sezayir.service.CustomerServiceImpl;
import com.sezayir.util.Util;


/**
 * Main Application
 *
 */
public class App {

	final static Logger log= Logger.getLogger(App.class);
	
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";
	private static String DERBY_PATH = "codejava";
	
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig rc = new ResourceConfig().packages("com.sezayir");
        

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }


	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		init();
		final HttpServer server = startServer();
		log.info("Jersey app started");
		log.info("Accounts Url:"+BASE_URI+"bank/accounts");
		log.info("Customers Url:"+BASE_URI+"bank/customers");
		log.info("Money transfer example:"+BASE_URI+"bank/user1/transfer/user2/300");
		//Clean old derby folders
		Util.deleteDirectoryStream(DERBY_PATH);
		
		log.info("Database is being configured. Please wait...");
		CustomerService customerService = new CustomerServiceImpl();
		customerService.createCustomers();
		AcccountService accountService= new AccountServiceImpl();
		accountService.createAccount();
		log.info("HIT ENTER TO STOP IT");

		System.in.read();
		customerService.dropCustomerTable();
		accountService.dropAccountTable();
		server.stop();
	}
	//Solution for standalone java application
	//WARN No appenders could be found for logger, Please initialize the log4j system properly
	public static void init() {
		Properties log4jProperties = new Properties();
		log4jProperties.setProperty("log4j.rootLogger", "DEBUG, A1");
		log4jProperties.setProperty("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
		log4jProperties.setProperty("log4j.appender.A1.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.setProperty("log4j.appender.A1.layout.ConversionPattern", "%-4r [%t] %-5p %c %x - %m%n");
		PropertyConfigurator.configure(log4jProperties);
	}

    

}

