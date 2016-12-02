package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;
 
import java.io.*;
import java.sql.*;
// import org.json.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import spark.Request;
import spark.Response;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.*;
import org.hibernate.Session;
 
@SuppressWarnings("serial")
public class LandingBandsIndexDataTable{
 
  private String SEARCH;
  private int PAGE_NO;
  private int PAGE_SIZE;
  private int TOTAL_RECORDS;

  public JSONObject landing(Request request, Response response){
    JSONObject jsonResult = new JSONObject();
    String[] columnNames = { "name", "genre"};

    PAGE_NO = Integer.parseInt(request.queryParams("start"));
    PAGE_SIZE = Integer.parseInt(request.queryParams("length"));
    SEARCH = request.queryParams("search");

    System.out.println("SEARCH: "+SEARCH);

    response.type("application/json");
    response.header("Cache-Control", "no-store");

    return getBands(request);
  }

  public JSONObject getBands(Request request){
 
    JSONObject result = new JSONObject();
    JSONArray array = new JSONArray();

    List<Band> bands;
    // create a session to search on DB
    Session session = SessionManager.getInstance().openSession();
    BandDAO bdao = new BandDaoImpl(session);

    if (SEARCH == null || SEARCH.equals(""))
      bands = bdao.getAllBands();
    else
      bands = bdao.ilike(SEARCH);

    TOTAL_RECORDS = bands.size();
    
    // pagination
    bands = subList(PAGE_NO, PAGE_SIZE, bands);
    String todo = "";
    // make the data Json result for datable
      JSONObject row = new JSONObject();
      int i = 0;
    for (Band band : bands) {
      i+=1;
      JSONObject url = new JSONObject();
      todo+= link(band.getName(),band.getGenre(), band.getId(),i)+"<br>";
      // row.put("genre", band.getGenre());
      // row.put("DT_RowClass", "dt-link");
      // url.put("url", "href='/bands/"+band.getId()+"'");
      // row.put("DT_RowData", url);
    }
      row.put("name", todo);
      array.add(row); 

    // json resul for datatable
    try {
     result.put("iTotalRecords", TOTAL_RECORDS);
     result.put("iTotalDisplayRecords", TOTAL_RECORDS);
     result.put("data", array);
    } catch (Exception e) {}
    return result;
  }

  // necessary for pagination
  public List<Band> subList(int fromIndex, int step, List<Band> bands) {
    List<Band> aux = new LinkedList<Band>();
    int from = fromIndex;
    int to = step + from;
    while (bands.size() > from && from < to){
      aux.add(bands.get(from));
      from ++;
    }
    return aux;
  }

  public String link_to(String name, String id){
    return "<a class='bands-item' id='"+id+"' href=''>"+name+"</a>";
  }

  public String link(String name,String genre, String id,int i){
    String band = "<div class='track movee'>";
    band += "<div class='track__number'>"+(i)+"</div>";
    band += "<div class='track__added'>";
    band += "<i class='ion-checkmark-round added'></i></div>";
    band += "<div class='track__title'>"+link_to(name,id)+"</div>";
    band += "<div class='track__explicit'>"+genre+"</div>";
    band += "<div class='track__popularity'>";
    band += "<i class='ion-arrow-graph-up-right'></i>";
    band += "</div></div>";

    return band;
  }

 
 }