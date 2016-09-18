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
  
  public Genere(){
  }
  
  public Genere(String a_name, String a_description){
    id = UUID.randomUUID().toString();
    name = a_name;
    description = a_description;
  }
  
  public Genere(String a_name, String a_description, String a_release){
    id = UUID.randomUUID().toString();
    name = a_name;
    description = a_description;
    release = a_release;
  }
  
  public String getId(){
    return id;
  }
  
  public String getName(){
    return name;
  }
  
  public String getDescription(){
    return description;
  }
  
  public String getRelease(){
    return release;
  }
  
  public List<String> getinitiators(){
    return initiatedBy;
  }
  
  public List<Genere> getInfluence(){
    return influence;
  }
  
  public void setId(String an_id){
    id = an_id;
  }
  
  public void setName(String a_name){
    name = a_name;
  }
  
  public void setDescription(String a_description){
    description = a_description;
  }
  
  public void setRelease(String a_release){
    release = a_release;
  }
  
  public void setInitiatedBy(List<String> an_initiatedBy){
    initiatedBy = an_initiatedBy;
  }
  
  public void setInfluence(List<Genere> an_influence){
    influence = an_influence;
  }  
}