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

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.*;
import org.hibernate.Session;

@SuppressWarnings("serial")
public class DashboardArtistsDatatable{

  private String SEARCH;
  private int PAGE_NO;
  private int PAGE_SIZE;
  private int TOTAL_RECORDS;

  public JSONObject init(Request request, Response response){
    JSONObject jsonResult = new JSONObject();
    String[] columnNames = { "name", "surname", "nickname", "actions"};

    PAGE_NO = Integer.parseInt(request.queryParams("start"));
    PAGE_SIZE = Integer.parseInt(request.queryParams("length"));
    SEARCH = request.queryParams("search");

    System.out.println("SEARCH: "+SEARCH);

    response.type("application/json");
    response.header("Cache-Control", "no-store");

    return getArtists(request);
  }

  public JSONObject getArtists(Request request){

    JSONObject result = new JSONObject();
    JSONArray array = new JSONArray();

    List<Artist> artists;
    // create a session to search on DB
    Session session = SessionManager.getInstance().openSession();
    ArtistDAO adao = new ArtistDaoImpl(session);

    if (SEARCH == null || SEARCH.equals(""))
      artists = adao.getAllArtists();
    else
      artists = adao.ilike(SEARCH);

    TOTAL_RECORDS = artists.size();

    // pagination
    artists = subList(PAGE_NO, PAGE_SIZE, artists);

    // make the data Json result for datable
    for (Artist artist : artists) {
      JSONObject row = new JSONObject();
      JSONObject url = new JSONObject();
      row.put("name", artist.getName());
      row.put("surname", artist.getSurname());
      String nickname = artist.getNickname();
      if (nickname == null)
        nickname = "";
      row.put("nickname", nickname);
      row.put("actions", buttons(artist));
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
  public List<Artist> subList(int fromIndex, int step, List<Artist> artists) {
    List<Artist> aux = new LinkedList<Artist>();
    int from = fromIndex;
    int to = step + from;
    while (artists.size() > from && from < to){
      aux.add(artists.get(from));
      from ++;
    }
    return aux;
  }

  public String buttons(Artist artist){
    String buttons="<a href='/artists/"+artist.getId()+"'>";
    buttons += "<i class='material-icons'>remove_red_eye</i></a>";
    buttons += "<a href='/artists/"+artist.getId()+"/edit'>";
    buttons += "<i class='material-icons'>edit</i></a>";
    buttons += "<a class='delete' id='"+artist.getId()+"' href='/artists/"+artist.getId()+"'>";
    buttons += "<i class='material-icons'>close</i></a>";
    return buttons;
  }

 }
