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

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.*;
import org.hibernate.Session;
 
@SuppressWarnings("serial")
public class DashboardAlbumsDatatable{
 
  private String SEARCH;
  private int PAGE_NO;
  private int PAGE_SIZE;
  private int TOTAL_RECORDS;

  public JSONObject init(Request request, Response response){
    JSONObject jsonResult = new JSONObject();
    String[] columnNames = { "name", "duration", "actions"};

    PAGE_NO = Integer.parseInt(request.queryParams("start"));
    PAGE_SIZE = Integer.parseInt(request.queryParams("length"));
    SEARCH = request.queryParams("search");

    System.out.println("SEARCH: "+SEARCH);

    response.type("application/json");
    response.header("Cache-Control", "no-store");

    return getAlbums(request);
  }

  public JSONObject getAlbums(Request request){
 
    JSONObject result = new JSONObject();
    JSONArray array = new JSONArray();

    List<Album> albums;
    // create a session to search on DB
    Session session = SessionManager.getInstance().openSession();
    AlbumDAO adao = new AlbumDaoImpl(session);

    if (SEARCH == null || SEARCH.equals(""))
      albums = adao.getAll();
    else
      albums = adao.ilike(SEARCH);

    TOTAL_RECORDS = albums.size();
    
    // pagination
    albums = subList(PAGE_NO, PAGE_SIZE, albums);

    // make the data Json result for datable
    for (Album album : albums) {
      JSONObject row = new JSONObject();
      JSONObject url = new JSONObject();
      row.put("title", album.getTitle());
      try{
        row.put("release", album.getReleaseDate());
      }catch(Exception e){}
      row.put("actions", buttons(album));
      array.add(row); 
    }

    // json resul for datatable
    try {
     result.put("iTotalRecords", TOTAL_RECORDS);
     result.put("iTotalDisplayRecords", TOTAL_RECORDS);
     result.put("data", array);
    } catch (Exception e) {}
    return result;
  }

  // necessary for pagination
  public List<Album> subList(int fromIndex, int step, List<Album> albums) {
    List<Album> aux = new LinkedList<Album>();
    int from = fromIndex;
    int to = step + from;
    while (albums.size() > from && from < to){
      aux.add(albums.get(from));
      from ++;
    }
    return aux;
  }

  public String buttons(Album album){
    String buttons="<a href='/albums/"+album.getId()+"'>";
    buttons += "<i class='material-icons'>remove_red_eye</i></a>";
    buttons += "<a href='/albums/"+album.getId()+"/edit'>";
    buttons += "<i class='material-icons'>edit</i></a>";
    buttons += "<a href='/albums/"+album.getId()+"'>";
    buttons += "<i class='material-icons'>close</i></a>";
    return buttons;
  }
 
 }