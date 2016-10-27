package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import spark.Request;
import spark.Response;

/***
 *
 * @author DOSE
 * This class implements the communication layer between the persistence and frontend
 * following the singleton patter.
 */

public class BandController {
	/*
	 * check that have only one instance of class
	 */
	private static BandController instance = null;
	private BandDAO bandDAO;

	/*
	 * Constructor of class BandController
	 * Implement the singleton pattern.
	 */
	public static BandController getInstance() {
      if(instance == null) {
         instance = new BandController();
      }
      return instance;
  }

  private BandController(){
  	bandDAO = new BandDaoImpl();
  }

	/***
	 * This method returns all bands
	 * @param req
	 * @param res
	 * @return A list of all bands
	 */
	public List<Band> getBands(Request req ,Response res){
		bandDAO.openCurrentSession();
		List<Band> bands= bandDAO.getAllBands();
		bandDAO.closeCurrentSession();
		int status = (bands.size()>0)? 200:204;
		res.status(status);
		return bands;
	}

	/***
	 * This method takes a band name, and returns a list of bands with this name
	 * @param req
	 * @param res
	 * @return a list of bands with the name of the request
	 */
	public List<Band> getBandByName(Request req,Response res){
		if (req.params(":name")==""){
			res.status(400);
			return null;
		}
		bandDAO.openCurrentSession();
		List<Band> bands = bandDAO.findBandByName(req.params(":name"));
		bandDAO.closeCurrentSession();
		int status = (bands.size()!=0)? 200:204;
		res.status(status);
		return bands;
	}

	/***
	 * This method takes a band genre and return a list of bands with this genre
	 * @param req
	 * @param res
	 * @return the data of a band, encapsulated in an object.
	 */
	public List<Band> getBandByGenre(Request req,Response res){
		if (req.params(":genre")==""){
			res.status(400);
		}
		bandDAO.openCurrentSession();
		List<Band> bands = bandDAO.findBandByName(req.params(":genre"));
		bandDAO.closeCurrentSession();
		int status = (bands.size()!=0)? 200:204;
		res.status(status);
		return bands;
	}

	/***
	 * This method takes the data of a band from the frontend, and creates a band in database
	 * @param req
	 * @param res
	 * @return the object of the band created.
	 */
	public String createBand(Request req,Response res){
		if((req.queryParams("name")=="") && (req.queryParams("genre")=="")){
			res.status(400);
			return "Request invalid";
		}
		bandDAO.openCurrentSessionwithTransaction();
		boolean status = bandDAO.createBand(req.queryParams("name"),req.queryParams("genre"));
		bandDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(409);
		return "Fail";
	}

	/***
	 * This method takes the data of a band from the frontend, and updates a band in database
	 * @param req
	 * @param res
	 * @return a String that describes the result of update a band.
	 */
	public String updateBand(Request req,Response res){
		/*if((req.queryParams("name")=="") && (req.queryParams("genre")=="")){
			res.status(400);
			return "Request invalid";
		}
		bandDAO.openCurrentSession();
		Band band = bandDAO.findById(req.params(":id"));
		bandDAO.closeCurrentSession();
		if (band==null){
			res.status(400);
			return "Request invalid";
		}
		band.setName(req.queryParams("name"));
		band.setGenre(req.queryParams("genre"));
		bandDAO.openCurrentSessionwithTransaction();
		boolean status = bandDAO.updateBand(band);
		bandDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(200);
			return "Success";
		}*/
		res.status(409);
		return "Fail";
	}

	/***
	 * This method takes the id of a band from the frontend, and delete this band in database
	 * @param req
	 * @param res
	 * @return true if the the band was created. Otherwise, false.
	 */
	public String deleteBand(Request req,Response res){
		if ((req.params(":id"))==""){
			res.status();
			return "Request invalid";
		}
		bandDAO.openCurrentSessionwithTransaction();
	 	boolean status = bandDAO.deleteBand(req.params(":id"));
		bandDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(200);
			return "Success";
		}
		res.status(409);
		return "Fail";

	}
}
