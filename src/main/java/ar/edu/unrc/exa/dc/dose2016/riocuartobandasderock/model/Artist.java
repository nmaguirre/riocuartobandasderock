package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.UUID;

/* This class represents one person that is artist of a band. */
public class Artist {

  /**
  * id represents the object band artist into the database
  * name represents the artist name
  * surname represents the artist surname
  * nickname represents the artist nickname
  */
  private String id;
  private String name;
  private String surname;
  private String nickname;


  /**
   * The constructor of Artist class sets the new artist
   * @param id: Artist id.
   * @param name: Artist name.
   * @param surname: Artist surname.
   * @param nickname: Artist nickname.
   */
  private Artist(String id, String name, String surname, String nickname){    
    this.id = id;
	this.name = name;
    this.surname = surname;
    this.nickname = nickname;
  }
  
  /**
   * The constructor of Artist class sets the new artist.
   * @param name: Artist name.
   * @param surname: Artist surname.
   * @param nickname: Artist nickname.
   */
  public Artist(String name, String surname, String nickname){    
     this(UUID.randomUUID().toString(), name, surname, nickname);
  }
  
  /**
   * The constructor of Artist class sets a new artist with a name and surname.
   * @param name: Artist name.
   * @param surname: Artist surname.
   */
  public Artist(String name, String surname){
    this(UUID.randomUUID().toString(), name, surname, "");
  }
  
  /**
  * @return artist id.
  */
  public String getId(){
    return id;
  }
  
  /**
  * @return artist name.
  */
  public String getName(){
	  return name;
  }
  
  /**
  * @return artist surname.
  */  
  public String getSurname(){
    return surname;
  }

  /**
  * @return artist nickname.
  */ 
  public String getNickname(){
    return nickname;
  }
  
  /**
  * This method sets the artist name.
  * @param art_name: Artist name.
  * @throws IllegalArgumentException if name is null.
  */
  public void setName(String art_name){
	  if (art_name == null) throw new IllegalArgumentException("Name can't be null");
	  name = art_name;
  }

  /**
  * This method sets the artist surname.
  * @param art_sur member surname.
  * @throws IllegalArgumentException if surname is null.
  */
  public void setSurname(String art_sur){
    if (art_sur == null) throw new IllegalArgumentException("Surname can't be null");
    surname = art_sur;
  }

  /**
  * This method sets the artist nickname.
  * @param bm_nick member name.
  * @throws IllegalArgumentException if nickname is null.
  */
  public void setNickname(String art_nick){
    if (art_nick == null) throw new IllegalArgumentException("Nickname can't be null");
    nickname = art_nick;
  }
}