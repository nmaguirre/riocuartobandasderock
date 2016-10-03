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
	 */
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
		return bmDAO.getAllBandMembers();
	}
	
	
	/**
	 * search a BandMember by his idArtist and idBand
	 * @param req it contains idArtist and idBand of the BandMember to search
	 * @param res
	 * @return one BandMember
	 */
	public BandMember getBandMember (Request req, Response res){
		return bmDAO.findById(req.params("idBand"), req.params("idArtist"));
	}
	
	/**
	 * search BandMembers by his idArtist
	 * @param req it contain idArtist to search
	 * @param res
	 * @return List of BandMembers
	 */
	public List<BandMember> getBandMembersByArtist (Request req, Response res){
		return bmDAO.findByArtist(req.params("idArtist")); 
	}
	
	/**
	 * search BandMembers by his IdBand
	 * @param req it contain idBand of the BandMember to search
	 * @param res
	 * @return List of BandMembers
	 */
	public List<BandMember> getBandMembersByBand (Request req, Response res){
		return bmDAO.findByBand(req.params("idBand")); 
	}
	
	/**
	 * creates an BandMember 
	 * @param req It contains the attributes of the new BandMerber
	 * @param res
	 * @return True when BandMember It was created successfully
	 */
	public boolean createBandMember(Request req,Response res){
		String[] rolesAux=req.queryParamsValues("roles");
		List<Role> roles = new LinkedList<>();
		for (String i:rolesAux){
			roles.add(Role.valueOf(i));
		}
		ArtistDAO aDAO= new ArtistDaoImpl();
		BandDAO bDAO= new BandDaoImpl();
		Band band=bDAO.getBand(req.queryParams("idBand"));
		Artist artist= aDAO.findById(req.queryParams("idArtist"));
		if (band==null || artist==null){
			return false;
		}
		BandMember bandMember = new BandMember(artist,band,roles);
		return bmDAO.createBandMember(bandMember);
	}
	
	/**
	 * update an BandMember 
	 * @param req	It contains the attributes of the BandMember to update
	 * @param res
	 * @return True when BandMember successfully updated
	 */
	public boolean updateBandMember(Request req, Response res){
		BandMember bandMember=bmDAO.findById(req.params("idBand"),req.params("idArtist"));
		if (bandMember==null){
			return false;
		}
		String[] rolesAux=req.queryParamsValues("roles");
		List<Role> roles = bandMember.getRole();
		roles.clear();
		for (String i:rolesAux){
			roles.add(Role.valueOf(i));
		}
		return bmDAO.updateBandMember(bandMember);
	}
		
	/**
	 * delete an BandMember by his idArtist and idBand
	 * @param req it contains idArtist and idBand of the BandMember to search
	 * @param res
	 * @return True when BandMember successfully deleted
	 */
	public boolean deleteBandMember(Request req, Response res){
	 	boolean status = bmDAO.deleteBandMember(req.params(":idBand"),req.params(":idArtist"));
	 	return status;
	}

}
