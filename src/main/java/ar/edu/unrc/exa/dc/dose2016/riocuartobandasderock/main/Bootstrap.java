package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;


public class Bootstrap {

	
	private static BandController bands = new BandController(new BandDaoImpl());
	private static ArtistController artistController;
	private static AlbumController albumController = AlbumController.getInstance();
	private static SongController songController;

    public static void main(String[] args) {
    	
    	CommandLineParser parser = new DefaultParser();
    	Option dbHost = OptionBuilder.withArgName( "host" )
    			.hasArg()
                .withDescription("use given host as database host")
                .create("dbHost");
    	Option dbPort = OptionBuilder.withArgName( "port" )
                .hasArg()
                .withDescription("use given port as database port")
                .create("dbPort");
    	Options options = new Options();
    	options.addOption(dbHost);
    	options.addOption(dbPort);
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );
            if (line.hasOption("dbHost")) { 
            		ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions.getInstance().setDbHost(line.getOptionValue("dbHost"));
            }
            if (line.hasOption("dbPort")) { 
            	ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions.getInstance().setDbPort(line.getOptionValue("dbPort"));            
            }
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
        
        artistController = new ArtistController();
        songController = new SongController();
             

        post("/albums", (req, res) -> albumController.create(req, res));

        get("/hello", (req, res) -> "Hello World");

        get("/bands",(req, res) -> bands.getBands(req, res));

        get("/band/:name",(req, res) -> bands.getBand(req, res));

        post("/band",(req, res) -> bands.createBand(req, res));

        put("/band",(req, res) -> bands.updateBand(req, res));

        delete("/band/:name",(req, res) -> bands.deleteBand(req, res));

        get ("/artist", (req,res)->artistController.getAllArtists(req,res));

        get("/artist/findbyname/:name",(req,res)->artistController.getArtistByName(req,res));
        
        get("/artist/findbynickname/:nickname",(req,res)->artistController.getArtistByNickname(req,res));

        get("/artist/findbysurname/:surname",(req,res)->artistController.getArtistBySurname(req,res));

        post("/artist/",(req,res)->artistController.createArtist(req,res));
        
        post("/song/",(req,res)->songController.createSong(req,res));

    }
}
