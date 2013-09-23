package ua.taky.dogclinic;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for dogs sample application.
 * Was inspired by legendary PetClinic application in spring examples :)
 * 
 * @author taky
 */
public class DogClinicEntryPoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DogClinicServer.class);
	
	public static void main(String[] args){
		try {
			new DogClinicServer().start();
		} catch (ServletException | LifecycleException e) {
			LOGGER.error("Cannot start server", e);
		} catch (InterruptedException e) {
			LOGGER.error("Server was interrupted", e);
		}
	}

}
