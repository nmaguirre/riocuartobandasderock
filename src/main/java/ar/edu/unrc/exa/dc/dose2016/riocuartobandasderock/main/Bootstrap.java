package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;

public class Bootstrap {
	private static ArtistController artistController = new ArtistController(new ArtistDaoImpl());
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        
        get ("/artist", (req,res)->artistController.getAllArtists(req,res));
        
        get("/artist/:id",(req,res)->artistController.getArtistById(req,res));
        
        get("/artist/:name",(req,res)->artistController.getArtistByName(req,res));
        
        post("/artist/",(req,res)->artistController.createArtist(req,res));
        
        put("/artist/:id",(req,res)->artistController.updateArtist(req,res));
        
        delete("/artist/:id",(req,res)->artistController.deleteArtist(req,res));
        
    }
}