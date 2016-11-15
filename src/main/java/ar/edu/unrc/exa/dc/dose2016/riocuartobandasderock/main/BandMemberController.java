package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import spark.Response;

import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *the BandMemberController class treats http requests referred to the Artist model 
 */
public class BandMemberController {
	/**
	 * one implementation BandMemberDao to connect to db
	 */
	private static BandMemberController instance;

	
	/**
	 * Constructor;
	 */
	private BandMemberController(){
	}
	
	public static BandMemberController getInstance() {
		if (instance==null){  
			instance = new BandMemberController();
		}
		return instance;
	}
		
	/**
	 * creates an BandMember 
	 * @param req It contains the attributes of the new BandMerber
	 * @param res
	 * @return a string that describes the result of createBandMember
	 */
	public String createBandMember (Request req,Response res){
		//Not Implemented yet
		/*String user = req.session().attribute("name");
			if ((user==null)||(!(user.equals("admin")))){
			res.status(401);
			return "Forbidden Access";
		}*/
		if ((req.queryParams("artistID").isEmpty())||(req.queryParams("bandID").isEmpty())||(req.queryParams("artistID")==null)||(req.queryParams("bandID")==null)){
			res.status(400);
			return "Request invalid";
		}
		String bandID = req.queryParams("bandID");
		String artistID = req.queryParams("artistID");
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO aDAO = new ArtistDaoImpl(session);
		BandDAO bDAO = new BandDaoImpl(session);
		List <Artist> artists = aDAO.findById(artistID);

		Band band = bDAO.getBand(bandID);
		if (band==null || artists.isEmpty()){
			res.status(400);
			return "Request Invalid";
		}
		Artist artist= artists.get(0);
		if (band==null || artist==null){
			res.status(400);
			return "Request Invalid";
		}
		Transaction transaction = session.beginTransaction();
		BandMemberDAO bmDAO = new BandMemberDAOImpl(session);
		boolean status = (bmDAO.createBandMember(req.queryParams("bandID"),req.queryParams("artistID")));
		transaction.commit();
		session.close();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(409);
		return "Fail";		
	}
		
	/**
	 * delete an BandMember by his idArtist and idBand
	 * @param req it contains idArtist and idBand of the BandMember to search
	 * @param res (Response)
	 * @return a string that describes the result of deleteBandMember
	 */
	public String deleteBandMember(Request req, Response res){
		//Not Implemented yet
		/*String user = req.session().attribute("name");
			if ((user==null)||(!(user.equals("admin")))){
			res.status(401);
			return "Forbidden Access";
		}*/
		if ((req.params(":bandID")==null)||(req.params(":artistID").isEmpty())||(req.params(":artistID").isEmpty())||(req.params(":bandID")==null)){
			res.status(400);
			return "Request invalid";
		}
		Session session = SessionManager.getInstance().openSession();
		BandMemberDAO bmDAO = new BandMemberDAOImpl(session);
		Transaction transaction = session.beginTransaction();
	 	boolean status = bmDAO.deleteBandMember(req.params(":bandID"),req.params(":artistID"));
	 	transaction.commit();
		session.close();
	 	if (status){
	 		res.status(200);
	 		return "Success";
	 	}
	 	res.status(409);
	 	return "Fail";
	}
}