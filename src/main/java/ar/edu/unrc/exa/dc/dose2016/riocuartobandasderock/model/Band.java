package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.UUID;

public class Band {
  private String id;
  private List<Album> albumList;
  private List<Artist> artistList;
  private String name;
  private String release;
  private String end;
  private Genere genere;
  
  public Band(){    
  }

  public Band(String a_name, Genere a_genere, List<Artist> list_of_artist, String a_release){
    id = UUID.randomUUID().toString();
    name = a_name;
    genere = a_genere;
    artistList = list_of_artist;
    release = a_release;
  }

  public Band(String a_name, Genere a_genere, List<Artist> list_of_artist, String a_release, List<Album> list_of_albums){
    id = UUID.randomUUID().toString();
    name = a_name;
    genere = a_genere;
    artistList = list_of_artist;
    albumList = list_of_albums;
    release = a_release;
  }

  
  public String getId(){
    return id;
  }
  
  public List<Album> getAlbum(){
    return albumList;
  }
  
  public List<Artist> getArtistList(){
    return artistList;
  }
  
  public String getName(){
    return name;
  }
  
  public String getRelease(){
    return release;
  }
  
  public String getEnd(){
    return end;
  }
  
  public Genere getGenere(){
    return genere;
  }
    
  public void setId(String an_id){
    id = an_id;
  }
  
  public void setAlbumList(List<Album> an_album_list){
    albumList = an_album_list;
  }
  
  public void setArtistList(List<Artist> an_artist_list){
	  artistList = an_artist_list;
  }
  
  public void setName(String a_name){
    name = a_name;
  }
  
  public void setRelease(String a_release){
    release = a_release;
  }
  
  public void setEnd(String an_end){
    end = an_end;
  }
  
  public void setGenere(Genere a_genere){
    genere = a_genere;
  } 
}