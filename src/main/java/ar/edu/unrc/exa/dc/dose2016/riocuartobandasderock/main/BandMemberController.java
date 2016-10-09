package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Role;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import java.util.LinkedList;
import java.util.List;

/**
 *the BandMemberController class treats http requests referred to the Artist model 
 */
public class BandMemberController {
	/**
	 * one implementation BandMemberDao to connect to db
	 */
	private BandMemberDAO bmDAO;
	
	/**
	 * Constructor;
	 *
	
	public BandMemberController(){
		bmDAO = new BandMemberDAOImpl();
	}
	
	/**
	 * get all BandMember 
	 * @param req (Request)
	 * @param res (Response)
	 * @return  List of BandMembers
	 */
	public List<BandMember> getAllBandMembers (Request req, Response res){
		//bmDAO.openCurrentSession();
		List <BandMember> bandMembers = bmDAO.getAllBandMembers();
		//bmDAO.closeCurrentSession();
		int status = (bandMembers.size()>0)? 200:404;
		res.status(status);
		return bandMembers;
	}
	
	
	/**
	 * search a BandMember by his idArtist and idBand
	 * @param req it contains idArtist and idBand of the BandMember to search
	 * @param res
	 * @return one BandMember
	 */
	public BandMember getBandMember (Request req, Response res){
		if ((req.params(":idArtist")=="") || (req.params(":idBand")=="")){
			res.status(400);
			return null;
		}
		//bmDAO.openCurrentSession();
		BandMember bandMember = bmDAO.findById(req.params(":idBand"), req.params(":idArtist"));
		//bmDAO.closeCurrentSession();
		int status = (bandMember!=null)? 200:404;
		res.status(status);
		return bandMember;
	}
	
	/**
	 * search BandMembers by his idArtist
	 * @param req it contain idArtist to search
	 * @param res
	 * @return List of BandMembers
	 */
	public List<BandMember> getBandMembersByArtist (Request req, Response res){
		if (req.params(":idArtist")==""){
			res.status(400);
			return null;
		}
		//bmDAO.openCurrentSession();
		List<BandMember> bandMembers = bmDAO.findByArtist(req.params(":idArtist"));
		//bmDAO.closeCurrentSession();
		int status = (bandMembers.size()>0)? 200:404;
		res.status(status);
		return bandMembers;
	}
	
	/**
	 * search BandMembers by his IdBand
	 * @param req it contain idBand of the BandMember to search
	 * @param res
	 * @return List of BandMembers
	 */
	public List<BandMember> getBandMembersByBand (Request req, Response res){
		if (req.params(":idBand")==""){
			res.status(400);
			return null;
		}
		//bmDAO.openCurrentSession();
		List<BandMember> bandMembers = bmDAO.findByBand(req.params(":idBand"));
		//bmDAO.closeCurrentSession();
		int status = (bandMembers.size()>0)? 200:404;
		res.status(status);
		return bandMembers;
	}
	
	/**
	 * creates an BandMember 
	 * @param req It contains the attributes of the new BandMerber
	 * @param res
	 * @return a string that describes the result of createBandMember
	 */
	public String createBandMember(Request req,Response res){
		String[] rolesAux=req.queryParamsValues("roles");
		List<Role> roles = new LinkedList<>();
		for (String rol:rolesAux){
			roles.add(Role.valueOf(rol));
		}
		ArtistDAO aDAO= new ArtistDaoImpl();
		BandDAO bDAO= new BandDaoImpl();
		//bDAO.openCurrentSession();
		Band band=bDAO.getBand(req.queryParams("idBand"));
		//bDAO.closeCurrentSession();
		//aDAO.openCurrentSession();
		Artist artist= aDAO.findById(req.queryParams("idArtist"));
		//aDAO.closeCurrentSession();
		if (band==null || artist==null){
			res.status(400);
			return "Request Invalid";
		}
		BandMember bandMember = new BandMember(artist,band,roles);
		//bmDAO.openCurrentSessionWithTransaction();
		boolean status = (bmDAO.createBandMember(bandMember));
		//bmDAO.closeCurrentSessionWithTransaction();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(500);
		return "Fail";		
	}
	
	/**
	 * update an BandMember 
	 * @param req	It contains the attributes of the BandMember to update
	 * @param res
	 * @return a string that describes the result of UpdateBandMember
	 */
	public String updateBandMember(Request req, Response res){
		if (req.params(":idBand")==""||req.params(":idArtist")==""){
			res.status(400);
			return "Request invalid";
		}
		//bmDAO.openCurrentSession();
		BandMember bandMember=bmDAO.findById(req.params(":idBand"),req.params(":idArtist"));
		//bmDAO.closeCurrentSession();
		if (bandMember==null){
			res.status(404);
			return "Not Found";
		}
		String[] rolesAux=req.queryParamsValues("roles");
		List<Role> roles = bandMember.getRole();
		roles.clear();
		for (String rol:rolesAux){
			roles.add(Role.valueOf(rol));
		}
		//bmDAO.openCurrentSessionWithTransaction();
		boolean status= bmDAO.updateBandMember(bandMember);
		//bmDAO.closeCurrentSessionWithTransaction();
		if (status){
			res.status(200);
			return "Success";
		}
		res.status(500);
		return "Fail"; 
	}
		
	/**
	 * delete an BandMember by his idArtist and idBand
	 * @param req it contains idArtist and idBand of the BandMember to search
	 * @param res
	 * @return a string that describes the result of deleteBandMember
	 */
	public String deleteBandMember(Request req, Response res){
		if ((req.params(":idBand")=="")||(req.params(":idArtist")=="")){
			res.status(400);
			return "Request invalid";
		}
		//bmDAO.openCurrentSessionWithTransaction();
	 	boolean status = bmDAO.deleteBandMember(req.params(":idBand"),req.params(":idArtist"));
		//bmDAO.closeCurrentSessionWithTransaction();
	 	if (status){
	 		res.status(200);
	 		return "Success";
	 	}
	 	res.status(404);
	 	return "Not Found";
	}
}
