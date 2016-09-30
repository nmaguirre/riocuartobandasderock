package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;


public class Bootstrap {

	
	private static BandController bands = new BandController(new BandDaoImpl());
	private static ArtistController artistController = new ArtistController();
	private static BandMemberController bandMemberController = new BandMemberController();    

    public static void main(String[] args) {

        get("/hello", (req, res) -> "Hello World");
        
        get("/bands",(req, res) -> bands.getBands(req, res));

        get("/band/:name",(req, res) -> bands.getBand(req, res));
        
        post("/band",(req, res) -> bands.createBand(req, res));
        
        put("/band",(req, res) -> bands.updateBand(req, res));
        
        delete("/band/:name",(req, res) -> bands.deleteBand(req, res));

        get ("/artist", (req,res)->artistController.getAllArtists(req,res));
        
        get("/artist/:id",(req,res)->artistController.getArtistById(req,res));
        
        get("/artist/:name",(req,res)->artistController.getArtistByName(req,res));
        
        post("/artist/",(req,res)->artistController.createArtist(req,res));
        
        put("/artist/:id",(req,res)->artistController.updateArtist(req,res));
        
        delete("/artist/:id",(req,res)->artistController.deleteArtist(req,res));
        
        get ("/bandMember/:idArtist/:idBand", (req,res)->bandMemberController.getBandMember(req,res));
        
        get("/bandMember/:idBand",(req,res)->bandMemberController.getBandMembersByBand(req,res));
        
        get("/bandMember/:idArtist",(req,res)->bandMemberController.getBandMembersByArtist(req,res));
        
        post("/bandMember/",(req,res)->bandMemberController.createBandMember(req,res));
        
        put("/bandMember/:idArtist/:idBand",(req,res)->bandMemberController.updateBandMember(req,res));
        
        delete("/bandMember/:idArtist/:idBand",(req,res)->bandMemberController.deleteBandMember(req,res));
        
    }
}