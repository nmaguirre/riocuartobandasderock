package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

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

    attributes.put("template", Routes.dashboard_index());
    return new ModelAndView(attributes, Routes.layout_dashboard());
  }
}
