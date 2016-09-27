package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

import spark.Request;
import spark.Response;

public class BandController {
	/***
	 * This class implements the communication layer between the persistence and frontend. 
	 */

	private BandDAO bandDAO;
	
	/***
	 * 
	 * @param bandPersistence
	 */
	public BandController(BandDAO bandPersistence){
		this.bandDAO = bandPersistence;
	}
	
	/***
	 * Missing implementation
	 * @param req
	 * @param res
	 * @return
	 */
	public List<Band> getBands(Request req ,Response res){
		return new LinkedList<Band>();
	}
	
	/***
	 * Missing implementation
	 * @param req
	 * @param res
	 * @return
	 */
	public Band getBand(Request req,Response res){
		return new Band();
	}
	
	/***
	 * Missing implementation
	 * @param req
	 * @param res
	 * @return
	 */
	public Band createBand(Request req,Response res){
		return new Band();
	}
	
	/***
	 * Missing implementation
	 * @param req
	 * @param res
	 * @return
	 */
	public Band updateBand(Request req,Response res){
		return new Band();
	}
	
	/***
	 * Missing implementation
	 * @param req
	 * @param res
	 * @return
	 */
	public Boolean deleteBand(Request req,Response res){
		return false;
	}
}
