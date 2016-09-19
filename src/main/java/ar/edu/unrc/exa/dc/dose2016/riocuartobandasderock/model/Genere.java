package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.UUID;

public class Genere {
  private String id;
  private String name;
  private String description;
  private String release;
  private List<String> initiatedBy;
  private List<Genere> influence;
  
  /**
   * constructor of Genere
   */
  public Genere(){
  }
  
  /**
   * constructor of Genere
   * @param a_name - name of the genere
   * @param a_description - description of the genere
   */
  public Genere(String a_name, String a_description){
    id = UUID.randomUUID().toString();
    name = a_name;
    description = a_description;
  }
  
  /**
   * constructor of Genere
   * @param a_name - name of the genere
   * @param a_description - description of the genere
   * @param a_release - release date of the genere
   */
  public Genere(String a_name, String a_description, String a_release){
    id = UUID.randomUUID().toString();
    name = a_name;
    description = a_description;
    release = a_release;
  }
  
  /**
   * get an id of the Genere
   * @return Genere.id
   */
  public String getId(){
    return id;
  }
  
  /**
   * get a name of the Genere
   * @return Genere.name
   */
  public String getName(){
    return name;
  }
  
  /**
   * get a description of the Genere
   * @return Genere.description
   */
  public String getDescription(){
    return description;
  }
  
  /**
   * get a release of the Genere
   * @return Genere.release
   */
  public String getRelease(){
    return release;
  }
  
  /**
   * get a list of initiators of the Genere
   * @return Genere.initiatedBy
   */
  public List<String> getInitiators(){
    return initiatedBy;
  }
  
  /**
   * get a list of influences of the Genere
   * @return Genere.influence
   */
  public List<Genere> getInfluence(){
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
  public void setName(String a_name){
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
   * set a list of generes they influenced the Genero to Genere.influence
   * @param an_influence
   */
  public void setInfluence(List<Genere> an_influence){
    influence = an_influence;
  }  
}