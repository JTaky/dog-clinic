package ua.taky.dogclinic;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

import ua.taky.dogclinic.web.DogsServlet;

/**
 * Server for dog clinic
 * 
 * @author taky
 */
public class DogClinicServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DogClinicServer.class);
	
	private static final String WEBAPP_DIR_LOCATION = "src/main/webapp/";
	private static final int WEB_PORT = 8080;
	
	private CountDownLatch awaitSignal = new CountDownLatch(1);

	/**
	 * Strarts and intialize internal state for the server.
	 */
	public void start() throws ServletException, LifecycleException, InterruptedException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(WEB_PORT);
		
		Context appContext = tomcat.addWebapp("/", new File(WEBAPP_DIR_LOCATION).getAbsolutePath());
		
		Tomcat.addServlet(appContext, "resourceServlet", new DefaultServlet());
		appContext.addServletMapping("/", "resourceServlet");
		
		Tomcat.addServlet(appContext, "dogServlet", new DogsServlet());
		appContext.addServletMapping("/dog", "dogServlet");
		
		tomcat.start();
		LOGGER.info("Web server started on port - {}", WEB_PORT);
		awaitSignal.await(); //server is run while not java killed (I know it is a scratch)
	}

}
