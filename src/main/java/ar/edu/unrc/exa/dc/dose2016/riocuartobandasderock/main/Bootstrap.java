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
	private static BandMemberController bandMemberController = new BandMemberController();
	private static AlbumController albumController = AlbumController.getInstance();

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

        get("/albums", (req, res) -> albumController.getAll(req, res));

        get("/albums/:album_id", (req, res) -> albumController.getById(req, res));

        post("/albums", (req, res) -> albumController.create(req, res));

        put("/albums", (req, res) -> albumController.update(req, res));

        delete("/albums/:album_id", (req, res) -> albumController.delete(req, res));

        get("/hello", (req, res) -> "Hello World");

        get("/bands",(req, res) -> bands.getBands(req, res));

        get("/band/:name",(req, res) -> bands.getBand(req, res));

        post("/band/",(req, res) -> bands.createBand(req, res));

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
