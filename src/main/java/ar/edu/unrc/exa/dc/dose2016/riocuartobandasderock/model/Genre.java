package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.UUID;

public class Genre {
  private String id;
  private TGenre name;
  private String description;
  private String release;
  private List<String> initiatedBy;
  private List<Genre> influence;
  
  /**
   * constructor of Genre
   */
  public Genre(){
  }
  
  /**
   * constructor of Genre
   * @param a_name - name of the genre
   * @param a_description - description of the genre
   */
  public Genre(TGenre a_name, String a_description){
    id = UUID.randomUUID().toString();
    name = a_name;
    description = a_description;
  }
  
  /**
   * constructor of Genre
   * @param a_name - name of the genre
   * @param a_description - description of the genre
   * @param a_release - release date of the genre
   */
  public Genre(TGenre a_name, String a_description, String a_release){
    id = UUID.randomUUID().toString();
    name = a_name;
    description = a_description;
    release = a_release;
  }
  
  /**
   * get an id of the Genre
   * @return Genere.id
   */
  public String getId(){
    return id;
  }
  
  /**
   * get a name of the Genre
   * @return Genere.name
   */
  public TGenre getName(){
    return name;
  }
  
  /**
   * get a description of the Genre
   * @return Genere.description
   */
  public String getDescription(){
    return description;
  }
  
  /**
   * get a release of the Genre
   * @return Genere.release
   */
  public String getRelease(){
    return release;
  }
  
  /**
   * get a list of initiators of the Genre
   * @return Genere.initiatedBy
   */
  public List<String> getInitiators(){
    return initiatedBy;
  }
  
  /**
   * get a list of influences of the Genre
   * @return Genere.influence
   */
  public List<Genre> getInfluence(){
    return influence;
  }
  
  /**
   * set some id to Genere.id
   * @param an_id
   */
  public void setId(String an_id){
    id = an_id;
  }
  
  /**
   * set some name to Genere.name
   * @param a_name
   */
  public void setName(TGenre a_name){
    name = a_name;
  }
  
  /**
   * set some description to Genere.description
   * @param a_description
   */
  public void setDescription(String a_description){
    description = a_description;
  }
  
  /**
   * set some release date to Genere.release
   * @param a_release
   */
  public void setRelease(String a_release){
    release = a_release;
  }
  
  /**
   * set a list of founders to Genere.initiatedBy
   * @param an_initiatedBy
   */
  public void setInitiatedBy(List<String> an_initiatedBy){
    initiatedBy = an_initiatedBy;
  }
  
  /**
   * set a list of genres they influenced the Genero to Genere.influence
   * @param an_influence
   */
  public void setInfluence(List<Genre> an_influence){
    influence = an_influence;
  }  
}