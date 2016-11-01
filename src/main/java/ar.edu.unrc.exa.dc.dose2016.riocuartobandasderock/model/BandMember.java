package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* This class represents the association between a person and a band. */
@Entity
@Table(name = "BandMemberDB")
public class BandMember {

  /**
   * artistID represents the person id in the association class.
  */	
  @Id
  @Column(name = "artistID")
  private String artistID;
  
  /**	
   * bandID represents the band id in the association class.
  */
  @Id
  @Column(name = "bandID")
  private String bandID;
  
  /**
   * The constructor of BandMember class sets the new member.
  */
  public BandMember(String artistID, String bandID){    
    this.artistID = artistID;
    this.bandID =  bandID;
  }
 
  /**
   * This method gains artist id in the association class.
   * @return String artistID.
  */
  public String getArtistID(){
    return artistID;
  }
  
  /**
   * This method gains band id in the association class.
   * @return String bandID.
  */
  public String getBandID(){
	  return bandID;
  }
 
  /**
   * This method sets the artistID.
   * @param bm_artistID is String artistID.
  */
  public void setArtistID(String bm_artistID){
	  if (bm_artistID == "") throw new IllegalArgumentException();
	  artistID = bm_artistID;
  }

  /**
   * This method sets the bandID.
   * @param bm_bandID is String bandID.
  */
  public void setBandID(String bm_bandID){
	  if (bm_bandID == "") throw new IllegalArgumentException();
	  bandID = bm_bandID;
  }
}