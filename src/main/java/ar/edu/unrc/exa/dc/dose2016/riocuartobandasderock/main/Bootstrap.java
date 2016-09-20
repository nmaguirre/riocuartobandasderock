package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class Bootstrap {
    private static AlbumController albumController = AlbumController.getInstance();
	
    public static void main(String[] args) {
    	
        /* Start album routes */
        get("/albums", (req, res) -> albumController.getAll());
        get("/albums/:album_id", (req, res) -> albumController.getById(req.params(":album_id")));
        post("/albums", (req, res) -> albumController.create(req.queryMap()));
        put("/albums", (req, res) -> albumController.update(req.queryMap()));
        delete("/albums/:album_id", (req, res) -> albumController.delete(req.params(":album_id")));
        /* End album routes */

        get("/hello", (req, res) -> "Hello World");
        
    }
}