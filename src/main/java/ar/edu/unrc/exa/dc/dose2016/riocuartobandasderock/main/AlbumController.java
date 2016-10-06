package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import spark.Request;
import spark.Response;

public class AlbumController {
    protected static AlbumController unique_instance = null;
    protected AlbumDAO dao;

    public AlbumController(AlbumDaoImpl albumDaoImpl) {
    	dao = albumDaoImpl;
	}

	public static AlbumController getInstance() {
        if (unique_instance == null)
            unique_instance = new AlbumController(new AlbumDaoImpl());
        return unique_instance;
    }

    public List<Album> getAll(Request req, Response res) {
     	List<Album> albums = dao.getAllAlbums();
     	int http_status = albums == null ? 200 : 400;
     	res.status(http_status);
    	return albums;
    }

    public Album getById(Request req, Response res) {
        Album album = dao.findById(req.params("id"));
        int http_status = album == null ? 200 : 400;
        res.status(http_status);
        return album;
    }

    public Boolean create(Request req, Response res) {
    	boolean result = dao.createAlbum(new Album());
    	int http_status = result ? 200 : 400; 
    	res.status(http_status);
    	return result;
    }

    public Boolean update(Request req, Response res) {
    	boolean result = dao.updateAlbum(dao.findById(req.params("id"))); 
    	int http_status = result ? 200 : 400; 
    	res.status(http_status);
    	return result;
    }

    public Boolean delete(Request req, Response res) {
    	boolean result = dao.deleteAlbum(req.params("id"));
    	int http_status = result ? 200 : 400; 
    	res.status(http_status);
    	return result;
    }
}
