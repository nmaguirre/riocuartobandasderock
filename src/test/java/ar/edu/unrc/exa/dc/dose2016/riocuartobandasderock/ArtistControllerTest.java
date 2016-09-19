package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ArtistController;

public class ArtistControllerTest {

	
	@Test(expected=IllegalArgumentException.class)
	 public void testArtistControllernull() {
	  ArtistDAO artistdao=null;
	  ArtistController artistcon = new ArtistController(artistdao);
	 }
	
	@Test
	 public void testArtistController() {
	  ArtistDaoImpl artistdao=new ArtistDaoImpl();
	  ArtistController artistcon = new ArtistController(artistdao);
	  assertTrue( artistcon.getArtistDAO()!=null);
	 }
	

}
