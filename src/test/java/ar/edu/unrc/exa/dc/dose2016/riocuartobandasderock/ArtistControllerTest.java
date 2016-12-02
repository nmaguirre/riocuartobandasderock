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
		artistcon = ArtistController.getInstance();
	 }	

	@Test
	public void notnullgetinstance() {
		assertTrue(ArtistController.getInstance()!=null);
	}
}
