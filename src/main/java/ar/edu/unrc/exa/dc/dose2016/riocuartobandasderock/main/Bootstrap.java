package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.*;

import static ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.JsonUtil.json;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
// import src.main.WebApp.Views;

import spark.Request;
import spark.Response;

import spark.ModelAndView;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;

/**
 *
 * @author Dose
 *
 */
public class Bootstrap {


    private static BandController bands;
    private static LandingBandsIndexDataTable bandDatatable;
    private static ArtistController artistController;
    private static AlbumController albumController;
    private static UserController userController;
    private static SongController songController;
    private static BandMemberController bandMemberController;
    private static String view = "src/main/WebApp/views/";
    private static LandingPageController landingPageController;
    private static DashboardController dashboardController;

    public static void main(String[] args) {

        staticFileLocation("/webapp");
        
        Gson gson = new Gson();

        CommandLineParser parser = new DefaultParser();

        Option dbHost = new Option("dbh","dbHost",true,"use given host as database host");
        Option dbPort = new Option("dbp","dbPort",true,"use given port as database port");
        Option appPort =  new Option("ap","appPort",true,"use given port as application port");
        appPort.setRequired(false);

        Options options = new Options();
        options.addOption(dbHost);
        options.addOption(dbPort);
        options.addOption(appPort);
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );
            if (line.hasOption("dbHost")) {
                    ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions.getInstance().setDbHost(line.getOptionValue("dbHost"));
            }
            if (line.hasOption("dbPort")) {
                ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions.getInstance().setDbPort(line.getOptionValue("dbPort"));
            }

            if (line.hasOption("appPort")) {
                ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions.getInstance().setAppPort(line.getOptionValue("appPort"));
            }
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }

        /* TABLE CODE  RESPONSE HTTP
         * ===============================================================
         * WHEN ACTION PASS INVALID ARGUMENT RETURN                      CODE 400
         * INSERT ONE NEW REGISTER IS OK                                 CODE 201
         * INSERT ONE NEW REGISTER IS DATABASE ERROR                     CODE 409
         *
         * SEARCH REGISTER ALL OR FOR ATRIBUTTE AND RETURN NO EMPTY LIST CODE 200
         * SEARCH REGISTER ALL OR FOR ATRIBUTTE AND RETURN EMPTY  LIST   CODE 204
         *
         */

        // List of controller
        bandDatatable = new LandingBandsIndexDataTable();
        albumController  = AlbumController.getInstance();
        artistController = ArtistController.getInstance();
        bandMemberController = BandMemberController.getInstance();
        bands = BandController.getInstance();
        songController = SongController.getInstance();
        userController = UserController.getInstance();
        landingPageController = LandingPageController.getInstance();
        dashboardController = DashboardController.getInstance();
        port(Integer.parseInt(ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions.getInstance().getAppPort()));

        before("/dashboard", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });

        before("/albums", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });
        
        before("/albums/*", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });
        
        before("/artists", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });
        
        before("/artists/*", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });

        before("/bands", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });

        before("/bands/*", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });

        before("/songs", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });

        before("/songs/*", (req, res) -> {
            if (!userController.authenticated(req, res))
                res.redirect("/login");
        });

        /**
        * HELLO WORLD PAGE
        **/
        get("/hello", (req, res) -> "Hello World");

        /**
        * LANDING PAGE
        **/
        get("/", (req, res) -> landingPageController.index(req,res), new VelocityTemplateEngine());
        
        post("/landing/bands/datatable",(req, res) -> landingPageController.datatable(req, res), gson::toJson);

        get("/landing/band/show",(req, res) -> landingPageController.bandID(req, res), gson::toJson);

        get("/landing/artist/show",(req, res) -> landingPageController.artistID(req, res), gson::toJson);

        get("/landing/album/show",(req, res) -> landingPageController.albumID(req, res), gson::toJson);

        get("/landing/song/show",(req, res) -> landingPageController.songID(req, res), gson::toJson);

        /**
        * DASHBOARD
        **/
        get("/dashboard", (req, res) -> dashboardController.index(req,res), new VelocityTemplateEngine());

        /**
        * ALBUM
        **/
        get("/albums", (req, res) -> albumController.getAll(req, res), new VelocityTemplateEngine());

        get("/albums/new",(req, res) -> albumController.newAlbum(req, res), new VelocityTemplateEngine());

        get("/albums/:id/edit",(req, res) -> albumController.editAlbum(req, res), new VelocityTemplateEngine());

        get("/albums/:id",(req, res) -> albumController.showAlbum(req, res), new VelocityTemplateEngine());

        // delete("/albums/:id",(req, res) -> albumController.delete(req, res), new VelocityTemplateEngine());

        // put("/albums/:id",(req, res) -> albumController.update(req, res), new VelocityTemplateEngine());

        get("/albums/findByReleaseDate/:release_date", (req, res) -> albumController.findByReleaseDate(req, res),json());

        post("/albums", (req, res) -> albumController.create(req, res), new VelocityTemplateEngine());

        /**
        * BAND
        **/

        
        get("/bands",(req, res) -> bands.getBands(req, res), new VelocityTemplateEngine());

        get("/bands/new",(req, res) -> bands.newBand(req, res), new VelocityTemplateEngine());

        get("/bands/:id/edit",(req, res) -> bands.editBand(req, res), new VelocityTemplateEngine());

        get("/bands/:id",(req, res) -> bands.showBand(req, res), new VelocityTemplateEngine());

        delete("/bands/:id",(req, res) -> bands.showBand(req, res), new VelocityTemplateEngine());

        post("/bands",(req, res) -> bands.createBand(req, res), new VelocityTemplateEngine());

        put("/bands",(req, res) -> bands.updateBand(req, res));

        delete("/bands/:id",(req, res) -> bands.deleteBand(req, res));



        /**
        * ARTIST
        **/
        get("/artists",(req,res)->artistController.getAllArtists(req,res), new VelocityTemplateEngine());

        get("/artists/new",(req, res) -> artistController.newArtist(req, res), new VelocityTemplateEngine());

        get("/artists/:id/edit",(req, res) -> artistController.editArtist(req, res), new VelocityTemplateEngine());

        get("/artists/:id",(req, res) -> artistController.showArtist(req, res), new VelocityTemplateEngine());

        
        /** returns an artist whose id = :id the output is json format
         * example:   Request: GET /artist/10
         *            Output : {name: Matias, surname: Cerra, nickname: }
         * 
         * **/    
        
        get("/artists/findbyallattributes/",(req,res)->artistController.getOneArtist(req,res),json());

        get("/artists/:id",(req,res)->artistController.getArtistById(req,res));

        get("/artists/findbyname/:name",(req,res)->artistController.getArtistByName(req,res),json());

        get("/artists/findbynickname/:nickname",(req,res)->artistController.getArtistByNickname(req,res),json());

        get("/artists/findbysurname/:surname",(req,res)->artistController.getArtistBySurname(req,res),json());

        get("/artists/getbands/",(req,res)->artistController.getBandMembersByArtist(req, res),json());

        get("/artists/getbandsbyId/:artistID",(req,res)->artistController.getBandMembersByArtistId(req, res),json());

        post("/artists",(req,res)->artistController.createArtist(req,res), new VelocityTemplateEngine());

        put("/artists/:id",(req,res)->artistController.updateArtist(req,res));

        delete("/artists/:id",(req,res)->artistController.deleteArtist(req,res));

        /**
         * BAND MEMEBER
         **/
        post("/bandmembers",(req,res)->bandMemberController.createBandMember(req, res));
        
        delete("/bandmembers/:artistID/:bandID",(req,res)->bandMemberController.deleteBandMember(req, res));
        
        /**
         * USER
         **/
        post("/users", (req, res) -> userController.create(req, res));

        put("/users/:name", (req, res) -> userController.update(req, res));

        delete("/users/:name", (req, res) -> userController.delete(req, res));

        get("/login", (req, res)  -> userController.getLogin(req, res), new VelocityTemplateEngine());

        post("/login", (req, res) -> userController.login(req, res));

        get("/logout", (req, res) -> userController.logout(req, res));

        /**
        * SONG
        **/
        post("/songs",(req,res)-> songController.create(req, res));
//        post("/songs/",(req,res)->songController.create(req, res));

        get("/songs/findById/:id",(req,res)-> songController.getById(req,res), json());

        get("/songs/findbyname/:name",(req,res)-> songController.getByName(req,res), json());

        get("/songs/findbyduration/:duration",(req,res)-> songController.getByDuration(req,res), json());

        get("/songs",(req, res) -> songController.getAll(req, res), new VelocityTemplateEngine());

        get("/songs/new",(req, res) -> songController.newSong(req, res), new VelocityTemplateEngine());

        get("/songs/:id/edit",(req, res) -> songController.editSong(req, res), new VelocityTemplateEngine());

        get("/songs/:id",(req, res) -> songController.showSong(req, res), new VelocityTemplateEngine());

        get("/songs",(req, res) -> songController.create(req, res));

        put("/songs/:id",(req, res) -> songController.update(req, res));

        delete("/songs/:id",(req, res) -> songController.remove(req, res));
    
    }
}
