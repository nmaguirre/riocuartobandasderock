package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static spark.Spark.*;
// import static spark.Spark.staticFileLocation;

import spark.ModelAndView;

import spark.Request;
import spark.Response;

import spark.ModelAndView;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.*;
/***
 *
 * @author DOSE
 * This class implements the communication layer between the persistence and frontend
 * following the singleton patter.
 */

public class LandingPageController {
  /*
   * check that have only one instance of class
   */
  private static LandingPageController instance = null;

  /***
   * Constructor of class LandingPageController
   * Implement the singleton pattern.
   */
  public static LandingPageController getInstance() {
      if(instance == null) {
         instance = new LandingPageController();
      }
      return instance;
  }

  /***
   * This method returns a index view
   * @param req
   * @param res
   */
  public ModelAndView index(Request req ,Response res){
    Session session = SessionManager.getInstance().openSession();
    BandDAO bdao = new BandDaoImpl(session);
    AlbumDaoImpl adao = new AlbumDaoImpl(session);
    SongDAO sdao = new SongDaoImpl(session);
    ArtistDAO artistDAO = new ArtistDaoImpl(session);
    Map<String, Object> attributes = new HashMap<>();
    List<Band> bands= bdao.getAllBands();
    List<Artist> artists = artistDAO.getAllArtists();
    List<Song> songs = sdao.getAllSongs();
    List<Album> albums = adao.getAll();
    attributes.put("template", Routes.landing_page());
    attributes.put("bands", bands);
    attributes.put("artists", artists);
    attributes.put("songs",songs);
    attributes.put("albumns", albums);
    return new ModelAndView(attributes, Routes.layout_landing_page());
  }
}
