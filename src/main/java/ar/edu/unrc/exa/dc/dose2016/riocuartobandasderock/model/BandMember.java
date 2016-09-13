package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

/* This class represents one person that is member of a band. */
public class BandMember {

  /**
  * id represents the object band member into the database
  * name represents the member name
  * surname represents the member surname
  * nickname represents the member nickname
  * role represents the member role in the band
  */
  private Integer id;
  private String name;
  private String surname;
  private String nickname;
  private List<String> role; 



  /**
  * The constructor of BandMember class sets the new member
  */
  public BandMember(int id, String name, String surname, String nickname, List<String> role){    
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.nickname = nickname;
    this.role = role;
  }
  
  /**
  * @return member id.
  */
  public Integer getId(){
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
  * This method sets the id of the member.
  * @param bm_id member id.
  */
  public void setId(Integer bm_id){
    id = bm_id;
  }
  
  /**
  * This method sets the name of the member.
  * @param bm_name member name.
  */
  public void setName(String bm_name){
    name = bm_name;
  }

  /**
  * This method sets the surname of the member.
  * @param bm_sur member surname.
  */
  public void setSurname(String bm_sur){
    surname = bm_sur;
  }

  /**
  * This method sets the nickname of the member.
  * @param bm_nick member name.
  */
  public void setNickname(String bm_nick){
    nickname = bm_nick;
  }

  /**
  * This method sets the role of the member.
  * @param bm_role member role.
  */
  public void setRole(List<String> bm_role){
    role = bm_role;
  }
}