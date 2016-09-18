package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Genere;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BandTest {
  
  @Test
  public void shortConstructorTest(){
    String a_name = "Led Zeppeling";
    String a_description = "Sex,Drugs,Rock&Roll";
    String a_release = "13/09/2016";
    Genere a_genere = new Genere(a_name,a_description);
    LinkedList<BandMember> list_of_members = new LinkedList<BandMember>();
    Band band = new Band(a_name,a_genere,list_of_members,a_release);
    assertEquals(band.getName(),a_name);
    assertEquals(band.getGenere(),a_genere);
    assertEquals(band.getBandMember(),list_of_members);
    assertEquals(band.getRelease(),a_release);
  }
  
  @Test
  public void longConstructorTest(){
    String a_name = "Led Zeppeling";
    String a_description = "Sex,Drugs,Rock&Roll";
    String a_release = "13/09/2016";
    Genere a_genere = new Genere(a_name,a_description);
    LinkedList<BandMember> list_of_members = new LinkedList<BandMember>();
    LinkedList<Album> an_album_list = new LinkedList<Album>();
    Band band = new Band(a_name,a_genere,list_of_members,a_release,an_album_list);
    assertEquals(band.getName(),a_name);
    assertEquals(band.getGenere(),a_genere);
    assertEquals(band.getBandMember(),list_of_members);
    assertEquals(band.getRelease(),a_release);
    assertEquals(band.getAlbum(),an_album_list);
  }

  @Test
  public void setIdTest(){
    String an_id = "1";
    Band band = new Band();
    band.setId(an_id);
    assertEquals(band.getId(),an_id);
  }
  
  @Test
  public void setNameTest(){
    Band band = new Band();
    String name = "led zeppeling";
    band.setName(name);
    assertEquals(band.getName(),name);  
  }
  
  @Test
  public void setReleaseTest(){
    Band band = new Band();
    String release = "13/09/2016";
    band.setRelease(release);
    assertEquals(band.getRelease(),release);  
  }
  
  @Test
  public void setEndTest(){
    Band band = new Band();
    String end = "13/09/2016";
    band.setEnd(end);
    assertEquals(band.getEnd(),end);  
  }
  
  @Test
  public void setAlbunListTest(){
    Band band = new Band();
    LinkedList<Album> an_album_list = new LinkedList<Album>();
    band.setAlbumList(an_album_list);
    assertEquals(band.getAlbum(),an_album_list);  
  }
  
  @Test
  public void setMemberTest(){
    Band band = new Band();
    LinkedList<BandMember> a_member_list = new LinkedList<BandMember>();
    band.setMemberList(a_member_list);
    assertEquals(band.getBandMember(),a_member_list);  
  }
  
  @Test
  public void setGenereTest(){
    Band band = new Band();
    Genere a_genere = new Genere();
    band.setGenere(a_genere);
    assertEquals(band.getGenere(),a_genere);  
  }
}
