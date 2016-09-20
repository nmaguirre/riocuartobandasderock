package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import spark.QueryParamsMap;

public class AlbumController {
    protected static AlbumController unique_instance;
    protected AlbumDAO dao;

    protected AlbumController() {
        unique_instance = new AlbumController();
    }

    public static AlbumController getInstance() {
        if (unique_instance == null)
            unique_instance = new AlbumController();
        return unique_instance;
    }

    public List<Album> getAll() {
        return dao.getAllAlbums();
    }

    public Album getById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    public Boolean create(QueryParamsMap params) {
        // Maybe we need to sanitize params
        // Album album = new Album(params);
        // return album.save
        return false;
    }

    public Boolean update(QueryParamsMap params) {
        int id = params.get("id").integerValue();
        // Maybe we need to sanitize params
        // Album album = dao.findById(id);
        // return dao.updateAlbum(album, params);
        return false;
    }

    public Boolean delete(String id) {
        dao.deleteAlbum(dao.findById(Integer.parseInt(id)));
        return false;
    }
}
