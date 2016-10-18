	package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AlbumTest {

	@Test
	  public void nonParameterConstructorTest() {
		Album albumTest = new Album();
		albumTest.setTitle("Disraeli Gears");
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
	}
	
	@Test
	public void parametersConstructorTest() {
		String title = "Disraeli Gears";
		Band artist = new Band();
		Album albumTest = new Album(title);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
	}
	
	@Test
	public void fullParametersConstructorTest() {
		String title = "Disraeli Gears";
		Date releaseDate = new Date(1967,11,2);
		Album albumTest = new Album(title, releaseDate);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
		assertEquals(albumTest.getReleaseDate(),new Date(1967,11,2));

	}
	
}
