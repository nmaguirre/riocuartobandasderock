package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Title:   riocuartobandasderock.model.Band<p>
 * Description: class which defines a band of rock with the basic elements
 *              that are required for a band like a genre and artist.<p>
 * @author Ezequiel Depetris&Gaston Massimino
 */
@Entity
@Table(name = "bandDB")
public class Band {

  /** The Band id */
  @Id
  @Column(name = "bandID")
  private String id;

  /** The Band name */
  @Column(name = "name")
  private String name;

  /** The Band genre */
  @Column(name = "genre")
  private String genre;

  /** The band menbers has set */
//  @ManyToMany(cascade = {CascadeType.ALL})
//  @JoinTable(name="BandMemberDB", joinColumns={@JoinColumn(name="bandID")}, inverseJoinColumns={@JoinColumn(name="artistID")})
//  private Set<Artist> members = new HashSet();

  /** The Band albums as set */
  @OneToMany(cascade= CascadeType.ALL)
  private Set<Album> albums = new HashSet<Album>();

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
   * return the genre of the band
   * @return String (Genre)
   */
  public String getGenre(){
    return genre;
  }

  /**
  *   This method returns the member of the bands has set
  *   @return Set<Artist> the artist that play on the band
  */
//  public Set<Artist> getMembers(){
//    return this.members;
//  }

  /**
  *   This methods returns the Albums of the band
  *   @return Set<Album> The Albums of the band
  */
  public Set<Album> getAlbums(){
    return this.albums;
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
   * @param a_genre (Genre)
   */
  public void setGenre(String a_genre){
    genre = a_genre;
  }

  /**
  *   This method set the members attributte
  */
//  public void setMembers(Set<Artist> member){
//    this.members = member;
//  }

  /**
  *   This methods set the album attributte
  */
  public void setAlbums(Set<Album> album){
    this.albums = album;
  }  

  /**
   * this method check that the instance was create with valid
   * atributes (name != null and genre != null)
   */
  public boolean repOK(){
    return (name != null && genre != null);
  }
}