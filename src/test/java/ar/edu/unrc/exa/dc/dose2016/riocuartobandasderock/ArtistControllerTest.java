package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

import spark.Request;
import spark.Response;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ArtistController;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public class ArtistControllerTest {

	@Mocked ArtistDAO artistdao;
	ArtistController artistcon=null;

	
	
	@Before
	 public void beforetest() {
		artistcon = new ArtistController();
	
	 }
	
	@Test(expected=IllegalArgumentException.class)
	 public void testArtistControllernull() {
	  artistdao=null;
	  ArtistController artistcon = new ArtistController();
	 }
	
	
	@Test
	 public void testgetAllArtists() {
		 ArtistController artistcon = new ArtistController();
		 Request req=null;
		 Response res=null;
		 
		 new Expectations() {
			    // variables declared here are mocked by default
			   {
				artistdao.getAllArtists();
			    returns(new LinkedList<Artist>());
			   }
			  };
		
		assertTrue(artistcon.getAllArtists(req,res).size()==0);
	 }


}
