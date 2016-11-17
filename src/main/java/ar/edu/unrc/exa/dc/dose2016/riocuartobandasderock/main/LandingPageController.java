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
    Map<String, Object> attributes = new HashMap<>();
    return new ModelAndView(attributes, "views/landing_page/index.vm");
  }
}
