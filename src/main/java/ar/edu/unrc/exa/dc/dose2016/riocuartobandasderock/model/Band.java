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
  private Genre genere;
  
  
  /**
   * this is a constructor for an empty band
   */
  public Band(){
  }

  /**
   * Is a constructor for a simple band
   * @param a_name represent the name of the band
   * @param a_genere is the genere of the band
   * @param list_of_artist is a list of integrant
   * @param a_release this is the date when it was founded
   */
  public Band(String a_name, Genre a_genere, List<Artist> list_of_artist, String a_release){
    id = UUID.randomUUID().toString();
    name = a_name;
    genere = a_genere;
    artistList = list_of_artist;
    release = a_release;
  }

 
  /**
   * this create a initial band with some params
   * @param a_name represent the name of the band
   * @param a_genere is the genere of the band
   * @param list_of_artist is a list of integrant
   * @param a_release this is the date when it was founded
   * @param list_of_albums is list with all albumns of the band
   */
  public Band(String a_name, Genre a_genere, List<Artist> list_of_artist, String a_release, List<Album> list_of_albums){
    id = UUID.randomUUID().toString();
    name = a_name;
    genere = a_genere;
    artistList = list_of_artist;
    albumList = list_of_albums;
    release = a_release;
  }

  /**
   * this method return the id of the band
   * @return id (String)
   */
  public String getId(){
    return id;
  }
  
  /**
   * this method return the list of albumns of the band
   * @return albumList (List<Album>)
   */
  public List<Album> getAlbum(){
    return albumList;
  }
  
  /**
   * return a list with all members of the band
   * @return artistList (List<Artist>)
   */
  public List<Artist> getArtistList(){
    return artistList;
  }
  
  /**
   * return a string with the name of the band
   * @return name (String)
   */
  public String getName(){
    return name;
  }
  
  /**
   * return the release when the band was founded
   * @return release (String)
   */
  public String getRelease(){
    return release;
  }
  
  /**
   * if the band disappeared or separated return that date
   * @return end (String)
   */
  public String getEnd(){
    return end;
  }
  
  /**
   * return the genre of the band
   * @return Genre (Genere) 
   */
  public Genre getGenere(){
    return genere;
  }
    
  /**
   * this method set a different id for the band
   * @param an_id (String)
   */
  public void setId(String an_id){
    id = an_id;
  }
  
  /**
   * this method set the list of the albums of the band
   * @param an_album_list (List<Album>)
   */
  public void setAlbumList(List<Album> an_album_list){
    albumList = an_album_list;
  }
  
  /**
   * this method set the all members to the band
   * @param an_artist_list (List<Artist>)
   */
  public void setArtistList(List<Artist> an_artist_list){
	  artistList = an_artist_list;
  }
  
  /**
   * this method set a different name to the band
   * @param a_name (String)
   */
  public void setName(String a_name){
    name = a_name;
  }
  
  /**
   * this method set the release of the band
   * @param a_release (String)
   */
  public void setRelease(String a_release){
    release = a_release;
  }
  
  /**
   * this method set the end date when the band disappeared
   * @param an_end (String)
   */
  public void setEnd(String an_end){
    end = an_end;
  }
  
  /**
   * this method set the principal genre of the band
   * @param a_genere (Genere)
   */
  public void setGenere(Genre a_genere){
    genere = a_genere;
  } 
}