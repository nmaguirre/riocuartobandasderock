package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.UUID;

/* This class represents one person that is member of a band. */
public class Artist {

  /**
  * id represents the object band member into the database
  * name represents the member name
  * surname represents the member surname
  * nickname represents the member nickname
  * role represents the member role in the band
  */
  private String id;
  private String name;
  private String surname;
  private String nickname;
  private List<String> role;
  //private List<Role> role; CREAR ENUMERADO DE ROLES.



  /**
  * The constructor of BandMember class sets the new member without an id.
  */
  private Artist(String id, String name, String surname, String nickname, List<String> role){    
    this.id = id;
	this.name = name;
    this.surname = surname;
    this.nickname = nickname;
    this.role = role;
  }
  
  /**
   * The constructor of BandMember class sets the new member without an id.
   */
  public Artist(String name, String surname, String nickname, List<String> role){    
     this(UUID.randomUUID().toString(), name, surname, nickname, role);
  }
  
  /**
  * @return member id.
  */
  public String getId(){
    return id;
  }
  
  /**
  * @return member name.
  */
  public String getName(){
	  return name;
  }
  
  /**
  * @return member surname.
  */  
  public String getSurname(){
    return surname;
  }

  /**
  * @return member nickname.
  */ 
  public String getNickname(){
    return nickname;
  }
 
  /**
  * @return member role.
  */
  public List<String> getRole(){
    return role;
  } 
  
  /**
  * This method sets the member name.
  * @param bm_name member name.
  */
  public void setName(String bm_name){
	  if (bm_name == null) throw new IllegalArgumentException();
	  name = bm_name;
  }

  /**
  * This method sets the member surname.
  * @param bm_sur member surname.
  */
  public void setSurname(String bm_sur){
    surname = bm_sur;
  }

  /**
  * This method sets the member nickname.
  * @param bm_nick member name.
  */
  public void setNickname(String bm_nick){
    nickname = bm_nick;
  }

  /**
  * This method sets the member role.
  * @param bm_role member role.
  */
  public void setRole(List<String> bm_role){
    role = bm_role;
  }
}