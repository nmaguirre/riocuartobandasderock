package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.TGenre;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AlbumTest {

	@Test
	  public void nonParameterConstructorTest(){
		Album albumTest = new Album();
		albumTest.setTitle("Disraeli Gears");
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
	}
	
	@Test
	public void parametersConstructorTest(){
		String title = "Disraeli Gears";
		Band artist = new Band();
		artist.setName("Cream");
		Album albumTest = new Album(title,artist);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
		assertEquals(albumTest.getArtist().getName(),"Cream");
	}
	
	@Test
	public void fullParametersConstructorTest(){
		String title = "Disraeli Gears";
		Band artist = new Band();
		artist.setName("Cream");
		Date releaseDate = new Date(1967,11,2);
		int duration = 2019;
		TGenre genre = TGenre.rock;
		Date recordingDate = new Date(1967,5,0); //cero indica que el dia es desconocido
		String discography = "Reaction";
	    int ISRC = 827361683;
		int UPC = 2874103;
		
		Album albumTest = new Album(title,artist,releaseDate,duration,genre,recordingDate,discography,ISRC,UPC);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
		assertEquals(albumTest.getArtist().getName(),"Cream");
		assertEquals(albumTest.getReleaseDate(),new Date(1967,11,2));
		assertEquals(albumTest.getDuration(),2019);
		assertEquals(albumTest.getGenre(),TGenre.rock);
		assertEquals(albumTest.getRecordingDate(),new Date(1967,5,0));
		assertEquals(albumTest.getDiscography(),"Reaction");
		assertEquals(albumTest.getISRC(),827361683);
		assertEquals(albumTest.getUPC(),2874103);
	}
	
}
