package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;

public class Bootstrap {
	private static ArtistController artistController = new ArtistController(new ArtistDaoImpl());
	private static AlbumController albumController = AlbumController.getInstance();
	
    public static void main(String[] args) {
    	
    	get("/albums", (req, res) -> albumController.getAll());
    	
    	get("/albums/:album_id", (req, res) -> albumController.getById(req.params(":album_id")));
    	
    	post("/albums", (req, res) -> albumController.create(req.queryMap()));
    	
    	put("/albums", (req, res) -> albumController.update(req.queryMap()));
    	
    	delete("/albums/:album_id", (req, res) -> albumController.delete(req.params(":album_id")));
    	        
        get ("/artist", (req,res)->artistController.getAllArtists(req,res));
        
        get("/artist/:id",(req,res)->artistController.getArtistById(req,res));
        
        get("/artist/:name",(req,res)->artistController.getArtistByName(req,res));
        
        post("/artist/",(req,res)->artistController.createArtist(req,res));
        
        put("/artist/:id",(req,res)->artistController.updateArtist(req,res));
        
        delete("/artist/:id",(req,res)->artistController.deleteArtist(req,res));
        
    }
}