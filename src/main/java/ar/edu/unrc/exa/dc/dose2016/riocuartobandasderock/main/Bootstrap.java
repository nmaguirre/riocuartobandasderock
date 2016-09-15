package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;

public class Bootstrap {
	AlbumController albumController = AlbumController.getInstance();
	
    public static void main(String[] args) {
    	
    	get("/albums", (req, res) -> AlbumController.getAll());
    	get("/albums/:album_id", (req, res) -> AlbumController.getById(req.params(":album_id")));
    	post("/albums", (req, res) -> AlbumController.create(req.queryMap()));
    	put("/albums", (req, res) -> AlbumController.update(req.queryMap()));
    	delete("/albums/:album_id", (req, res) -> AlbumController.delete(req.params(":album_id")));
        get("/hello", (req, res) -> "Hello World");
        
    }
}