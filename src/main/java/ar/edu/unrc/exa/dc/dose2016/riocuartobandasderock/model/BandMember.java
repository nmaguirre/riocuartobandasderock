package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.LinkedList;
import java.util.List;

/* This class represents the association between a person and a band. */
public class BandMember {

  /**
  * Artist represents the person in the band.
  * Band represents the band.
  * Role represents the artist's roles in the band.
  */
  private Artist artist;
  private Band band;
  private List<Role> role;
  private static List<Role> emptyRole = new LinkedList<Role>();

  
  /**
  * The constructor of BandMember class sets the new member.
  */
  public BandMember(Artist artist, Band band , List<Role> role){    
    this.artist = artist;
    this.band =  band;
    this.role = role;
  }
  
  /**
   * The constructor of BandMember class sets the new member without 
   * an list of roles.
   */
  public BandMember(Artist artist, Band band){   
	  this.artist = artist;
	  this.band =  band;
	  this.role = emptyRole;
  }

  
  /**
  * @return Object Artist.
  */
  public Artist getArtist(){
    return artist;
  }
  
  /**
  * @return Object Band.
  */
  public Band getBand(){
	  return band;
  }
 
  /**
  * @return member role.
  */
  public List<Role> getRole(){
    return role;
  } 
  
  /**
  * This method sets the artist.
  * @param bm_artist is object artist.
  */
  public void setArtist(Artist bm_artist){
	  if (bm_artist == null) throw new IllegalArgumentException();
	  artist = bm_artist;
  }

  /**
  * This method sets the band.
  * @param bm_band is object band.
  */
  public void setBand(Band bm_band){
	  if (bm_band == null) throw new IllegalArgumentException();
	  band = bm_band;
  }

  /**
  * This method sets the member role.
  * @param bm_role member role.
  */
  public void setRole(List<Role> bm_role){
	  if (bm_role == null) throw new IllegalArgumentException();
	  role = bm_role;
  }
}