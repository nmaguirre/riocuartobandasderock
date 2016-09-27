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
        return dao.getAllAlbums();
    }

    public Album getById(Request req, Response res) {
        return dao.findById(req.params("id"));
    }

    public Boolean create(Request req, Response res) {
        return false;
    }

    public Boolean update(Request req, Response res) {
        dao.updateAlbum(dao.findById(req.params("id")));
        return false;
    }

    public Boolean delete(Request req, Response res) {
        dao.deleteAlbum(req.params("id"));
        return false;
    }
}
