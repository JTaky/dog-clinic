package ua.taky.dogclinic.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.taky.dogclinic.service.DogsService;
import ua.taky.dogclinic.service.domain.Dog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * Servlet implement REST api for dog object.
 * 
 * @author taky
 */
@SuppressWarnings("serial") //hope nobody serialize servlet objects :)
public class DogsServlet extends HttpServlet {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DogsServlet.class);
	
	private static final Gson GSON = new GsonBuilder().create();
	
	private static final DogsService dogsService = new DogsService();
	
	/**
	 * Return JSON formatted list of dogs in the clinic
	 * @throws IOException if some i/o problem
	 * @throws ServletException 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter outWriter = new PrintWriter(resp.getOutputStream())){
			JsonArray dogsJsonArray = new JsonArray();
			for(Dog curDog : dogsService.getDogs()){
				dogsJsonArray.add(GSON.toJsonTree(curDog));
			}
			outWriter.print(GSON.toJson(dogsJsonArray));
			outWriter.close();
		} catch (IOException e) {
			LOGGER.warn("Cannot write answer", e);
		}
	}
	
	/**
	 * Put dog to the storage. 
	 * In case of the problem notify client via http status code.
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		try {
			Reader reader = new InputStreamReader(req.getInputStream(), "UTF-8");
			//if we use some REST(or WEB) framework we will use built in validation technique
			dogsService.addDog(GSON.fromJson(reader, Dog.class));
			try (PrintWriter outWriter = new PrintWriter(resp.getOutputStream())){
				outWriter.print("{status: OK}");
			}
		} catch(RuntimeException e){ //e.g. validation error
			LOGGER.warn("Cannot store dog", e);
			sendError("{err: Cannot store dog}", resp);
		}
	}

	private void sendError(String errMsg, HttpServletResponse resp) {
		try {
			resp.sendError(400, errMsg);
		} catch (IOException e) {
			LOGGER.warn("Cannot send expection", e);
		}
	}

}