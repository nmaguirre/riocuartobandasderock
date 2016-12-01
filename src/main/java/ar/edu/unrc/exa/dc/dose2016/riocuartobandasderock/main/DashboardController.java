package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.*;

import java.util.List;

import org.hibernate.Session;


import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;


/***
 *
 * @author DOSE
 * This class implements the communication layer between the persistence and frontend
 * following the singleton patter.
 */

public class DashboardController {
  /*
   * check that have only one instance of class
   */
  private static DashboardController instance = null;

  /***
   * Constructor of class DashboardController
   * Implement the singleton pattern.
   */
  public static DashboardController getInstance() {
      if(instance == null) {
         instance = new DashboardController();
      }
      return instance;
  }

  /***
   * This method returns a index view
   * @param req
   * @param res
   */
  public ModelAndView index(Request req ,Response res){
    Map<String, Object> attributes = new HashMap<>();
    Session session = SessionManager.getInstance().openSession();
    BandDAO bandDAO = new BandDaoImpl(session);
    AlbumDaoImpl albumDAO = new AlbumDaoImpl(session);
    SongDAO songDAO = new SongDaoImpl(session);
    ArtistDAO artistDAO = new ArtistDaoImpl(session);
    List<Band> bands= bandDAO.getAllBands();
    int cantidadAlbumes = albumDAO.cantAlbums();
    int cantidadCanciones = songDAO.cantSongs();
    int cantidadArtistas = artistDAO.cantArtists();
    int cantidadBandas = bandDAO.cantBands();
    attributes.put("cantidadBandas", cantidadBandas);
    attributes.put("cantidadAlbumes", cantidadAlbumes);
    attributes.put("cantidadArtistas", cantidadArtistas);
    attributes.put("cantidadCanciones", cantidadCanciones);
    attributes.put("template", Routes.dashboard_index());
    attributes.put("title", Panel de Administrador);
    return new ModelAndView(attributes, Routes.layout_dashboard());
  }
}
