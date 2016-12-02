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

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.*;
import org.hibernate.Session;
 
@SuppressWarnings("serial")
public class DashboardSongsDatatable{
 
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

    return getSongs(request);
  }

  public JSONObject getSongs(Request request){
 
    JSONObject result = new JSONObject();
    JSONArray array = new JSONArray();

    List<Song> songs;
    // create a session to search on DB
    Session session = SessionManager.getInstance().openSession();
    SongDAO sdao = new SongDaoImpl(session);

    if (SEARCH == null || SEARCH.equals(""))
      songs = sdao.getAllSongs();
    else
      songs = sdao.ilike(SEARCH);

    TOTAL_RECORDS = songs.size();
    
    // pagination
    songs = subList(PAGE_NO, PAGE_SIZE, songs);

    // make the data Json result for datable
    for (Song song : songs) {
      JSONObject row = new JSONObject();
      JSONObject url = new JSONObject();
      row.put("name", song.getName());
      row.put("duration", song.getDurationAsString());
      row.put("actions", buttons(song));
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
  public List<Song> subList(int fromIndex, int step, List<Song> songs) {
    List<Song> aux = new LinkedList<Song>();
    int from = fromIndex;
    int to = step + from;
    while (songs.size() > from && from < to){
      aux.add(songs.get(from));
      from ++;
    }
    return aux;
  }

  public String buttons(Song song){
    String buttons="<a href='/songs/"+song.getId()+"'>";
    buttons += "<i class='material-icons'>remove_red_eye</i></a>";
    buttons += "<a href='/songs/"+song.getId()+"/edit'>";
    buttons += "<i class='material-icons'>edit</i></a>";
    buttons += "<a class='delete' id='"+song.getId()+"' href='/songs/"+song.getId()+"'>";
    buttons += "<i class='material-icons'>close</i></a>";
    return buttons;
  }
 
 }