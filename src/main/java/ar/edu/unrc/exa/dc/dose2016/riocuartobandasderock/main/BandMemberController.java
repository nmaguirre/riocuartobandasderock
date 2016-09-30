package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import java.util.List;

public class BandMemberController {
	private BandMemberDAO bmDAO;

	public BandMemberController(){
		bmDAO = new BandMemberDAOImpl();
	}
	
	public List<BandMember> getAllBandMembers (Request req, Response res){
		return bmDAO.getAllBandMembers();
	}
	
	public BandMember getBandMember (Request req, Response res){
		return bmDAO.findById(req.params("idBand"), req.params("idArtist"));
	}

	public List<BandMember> getBandMembersByArtist (Request req, Response res){
		return bmDAO.findByArtist(req.params("idArtist")); 
	}
	
	public List<BandMember> getBandMembersByBand (Request req, Response res){
		return bmDAO.findByBand(req.params("idBand")); 
	}
		
	public boolean createBandMember(Request req,Response res){
		return false;
	}
	
	public boolean updateBandMember(Request req, Response res){
		return false;
	}
	
	public boolean deleteBandMember(Request req, Response res){
	 	boolean status = bmDAO.deleteBandMember(req.params(":idBand"),req.params(":idArtist"));
	 	return status;
	}

}
