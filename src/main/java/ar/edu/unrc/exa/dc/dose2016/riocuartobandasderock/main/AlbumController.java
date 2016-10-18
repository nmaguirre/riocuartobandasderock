package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.Date;
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

    public Boolean create(Request req, Response res) {
    	if (req.params("name") == null && req.params("release_date") == null){
    		res.status(400);
    		return false;
    	}
    	Date release_date = new Date(req.params("release_date"));
    	boolean result = dao.createAlbum(req.params("name"), release_date);
    	//boolean result = dao.createAlbum(new Album());
    	int http_status = result ? 201 : 409; 
    	res.status(http_status);
    	if (!result) res.body("Duplicate album");
    	return result;
    }

}
