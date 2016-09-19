package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import java.util.List;

public class ArtistController {	
	
	private ArtistDAO artistDAO;
	
	public ArtistDAO getArtistDAO() {
		return artistDAO;
	}

	public ArtistController(ArtistDAO artistDAO_a){
		if (artistDAO_a==null) throw new IllegalArgumentException("Argument is null");
		artistDAO = artistDAO_a;
	}

	public List<Artist> getAllArtists(Request req, Response res){
		return artistDAO.getAllArtists(); 
	}
	
	public Artist getArtistById (Request req, Response res){
		return artistDAO.findById(req.params(":id")); 
	}
	
	public List<Artist> getArtistByName (Request req, Response res){
		return artistDAO.findByName(req.params("name"));
	}
		
	public boolean createArtist(Request req,Response res){
		Artist artist = new Artist(req.queryParams("name"),req.queryParams("surname"),req.queryParams("nickname"));
		boolean status = artistDAO.createArtist(artist);
		return status;
	}
	
	public boolean updateArtist(Request req, Response res){
		Artist artist = artistDAO.findById(req.params(":id"));
		if (artist==null){
			return false;
		}
		artist.setName(req.queryParams("name"));
		artist.setNickname(req.queryParams("nickName"));
		artist.setSurname(req.queryParams("surName"));
		boolean status = artistDAO.updateArtist(artist);
		return status;
	}
	
	public boolean deleteArtist(Request req, Response res){
	 	boolean status = artistDAO.deleteArtist(req.params(":id"));
	 	return status;
	}

}
