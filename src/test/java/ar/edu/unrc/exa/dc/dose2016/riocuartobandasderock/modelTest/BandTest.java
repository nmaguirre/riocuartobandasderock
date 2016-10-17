package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

import java.util.LinkedList;
import java.util.UUID;

public class BandTest {
  
	/**
	 * in this test, initily we create a band, then we check 
	 * with getters all attributes
	 * are the same are the same as the inserted
	 */
	/*
  @Test
  public void shortConstructorTest(){
    String a_name = "Led Zeppeling";
    String a_description = "Sex,Drugs,Rock&Roll";
    String a_release = "13/09/2016";
    Genre a_genere = new Genre(a_name,a_description);
    LinkedList<Artist> an_artist_list = new LinkedList<Artist>();
    Band band = new Band(a_name,a_genere,an_artist_list,a_release);
    assertEquals(band.getName(),a_name);
    assertEquals(band.getGenere(),a_genere);
    assertEquals(band.getArtistList(),an_artist_list);
    assertEquals(band.getRelease(),a_release);
  }*/
  
  /**
	 * in this test, initily we create a band, then we check 
	 * with getters all attributes
	 * are the same are the same as the inserted
	 */
/*
  @Test
  public void longConstructorTest(){
    String a_name = "Led Zeppeling";
    String a_description = "Sex,Drugs,Rock&Roll";
    String a_release = "13/09/2016";
    Genre a_genere = new Genre(a_name,a_description);
    LinkedList<Artist> an_artist_list = new LinkedList<Artist>();
    LinkedList<Album> an_album_list = new LinkedList<Album>();
    Band band = new Band(a_name,a_genere,an_artist_list,a_release,an_album_list);
    assertEquals(band.getName(),a_name);
    assertEquals(band.getGenere(),a_genere);
    assertEquals(band.getArtistList(),an_artist_list);
    assertEquals(band.getRelease(),a_release);
    assertEquals(band.getAlbum(),an_album_list);
  }
*/
  /**
   * in this test we set an id, and then we check
   * that match with the id created
   */
  @Test
  public void setIdTest(){
    String an_id = "1";
    Band band = new Band();
    band.setId(an_id);
    assertEquals(band.getId(),an_id);
  }
  
  /**
   * in this test we set a name, and then we check
   * that match with the name created
   */
  @Test
  public void setNameTest(){
    Band band = new Band();
    String name = "led zeppeling";
    band.setName(name);
    assertEquals(band.getName(),name);  
  }
  


}
