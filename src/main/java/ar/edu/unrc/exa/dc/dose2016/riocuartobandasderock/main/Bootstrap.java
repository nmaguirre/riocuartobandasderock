package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;


public class Bootstrap {
	private static BandController bands;
	private static BandDAO bandDAO;
	
	public Bootstrap(){
		bandDAO = new BandDaoImpl();
		bands = new BandController(bandDAO); 
	}
    
	public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        
        get("/bands",(req, res) -> bands.getBands(req, res));
        get("/band/:name",(req, res) -> bands.getBand(req, res));
        post("/band",(req, res) -> bands.createBand(req, res));
        put("/band",(req, res) -> bands.updateBand(req, res));
        delete("/band/:name",(req, res) -> bands.deleteBand(req, res));
        
    }
}