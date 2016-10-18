package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.UUID;

public class Band {
  private String id;
  private String name;
  private String genre;

  
  
  /**
   * this is a constructor for an empty band
   */
  public Band(){
  }
 
  /**
   * this create a initial band with some params
   * @param a_name represent the name of the band
   * @param a_genre is the genre of the band
   */
  public Band(String a_name, String a_genre){
    id = UUID.randomUUID().toString();
    name = a_name;
    genre = a_genre;
  }


  /**
   * this method return the id of the band
   * @return id (String)
   */
  public String getId(){
    return id;
  }

  /**
   * return a string with the name of the band
   * @return name (String)
   */
  public String getName(){
    return name;
  }
  

  /**
   * @return the genre of the band 
   */
  public String getGenere(){
    return genre;

  }
    
  /**
   * this method set a different id for the band
   * @param an_id (String)
   */
  public void setId(String an_id){
    id = an_id;
  }

  /**
   * this method set a different name to the band
   * @param a_name (String)
   */
  public void setName(String a_name){
    name = a_name;
  }
  

  /**
   * this method set the principal genre of the band
   * @param a_genre (Genere)
   */

  public void setGenere(String a_genre){
    genre = a_genre;
  } 
}