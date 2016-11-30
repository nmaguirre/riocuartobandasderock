package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.List;

import org.hibernate.Session;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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
  * * * * * * * * *
  * INDEX METHOD  *
  * * * * * * * * *
  */

  public ModelAndView index(Request req ,Response res){
    Session session = SessionManager.getInstance().openSession();
    BandDAO bandDAO = new BandDaoImpl(session);
    Map<String, Object> attributes = new HashMap<>();
    attributes.put("template", Routes.landing_page());
    return new ModelAndView(attributes, Routes.layout_landing_page());
  }

  /***
  * * * * * * * * *
  * BAND METHODS  *
  * * * * * * * * *
  */

  public JSONObject datatable(Request req,Response res){
    LandingBandsIndexDataTable bandDatatable = new LandingBandsIndexDataTable();
    return bandDatatable.landing(req, res);
  }

  public JSONObject bandID(Request req, Response res){
    Band band;
    JSONObject result = new JSONObject();

    Session session = SessionManager.getInstance().openSession();
    BandDAO bdao = new BandDaoImpl(session);
    System.out.println("ID: "+req.queryParams("id"));
    band = bdao.getBand(req.queryParams("id"));

    if(band != null){
      result.put("name", band.getName());
      result.put("genre", band.getGenre());
    }

    res.type("application/json");
    res.header("Cache-Control", "no-store");
    return result;
  }


  /***
  * * * * * * * * * *
  * ARTIST METHODS  *
  * * * * * * * * * *
  */


  /***
  * * * * * * * * *
  * ALBUM METHODS *
  * * * * * * * * *
  */


  /***
  * * * * * * * * *
  * SONG METHODS  *
  * * * * * * * * *
  */


}
