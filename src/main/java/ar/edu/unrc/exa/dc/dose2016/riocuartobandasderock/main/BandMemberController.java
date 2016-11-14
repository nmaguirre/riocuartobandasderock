package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
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
		if ((req.queryParams("artistID").isEmpty())||(req.queryParams("bandID").isEmpty())||(req.queryParams("artistID")==null)||(req.queryParams("bandID")==null)){
			res.status(400);
			return "Request invalid";
		}
		Session session = SessionManager.getInstance().openSession();
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
	 * @param res
	 * @return a string that describes the result of deleteBandMember
	 */
	public String deleteBandMember(Request req, Response res){
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
	
	/**
	 * search Artists of a Band by his name
	 * @param req it contain name of the Band to search Artist
	 * @param res
	 * @return List of BandMembers
	 *//*
	public List<Artist> getBandMembersByBand(Request req, Response res){
		String bName = req.queryParams("bandNickname");
		if((bName=="")||(bName==null)){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();//.openSession();
		BandMemberDAO bmDAO=new BandMemberDAOImpl(session);//new BandMemberDAOImpl(session);
		List<Artist> bandMembers = bmDAO.findByBandByAttributes(req.params(bName));
		session.close();
		int status = (bandMembers.size()>0)? 200:204;
		res.status(status);
		return bandMembers;
	}*/
	
	/**
	 * search Artists of a Band by his Id
	 * @param req it contain name of the Band to search Artist
	 * @param res
	 * @return List of BandMembers
	 *//*
	public List<Artist> getBandMembersByBandId(Request req, Response res){
		String bandID = req.params(":idBand");
		if((bandID=="")||(bandID==null)){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();
		BandMemberDAO bmDAO=new BandMemberDAOImpl(session);		
		List<Artist> bandMembers = bmDAO.findByBand(req.params(bandID));
		session.close();
		int status = (bandMembers.size()>0)? 200:204;
		res.status(status);
		return bandMembers;
	}*/
}