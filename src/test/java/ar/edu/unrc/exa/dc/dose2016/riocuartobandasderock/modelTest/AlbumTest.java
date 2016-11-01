package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class AlbumTest.
 */
public class AlbumTest {

	/**
	 * Non parameter constructor test.
	 */
	@Test
	  public void nonParameterConstructorTest() {
		Album albumTest = new Album();
		albumTest.setTitle("Disraeli Gears");
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
	}
	
	/**
	 * Parameters constructor test.
	 */
	@Test
	public void parametersConstructorTest() {
		String title = "Disraeli Gears";
		Band artist = new Band();
		Album albumTest = new Album(title);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
	}
	
	/**
	 * Full parameters constructor test.
	 */
	@Test
	public void fullParametersConstructorTest() {
		String title = "Disraeli Gears";
		Date releaseDate = new Date(1967,11,2);
		Album albumTest = new Album(title, releaseDate);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
		assertEquals(albumTest.getReleaseDate(),new Date(1967,11,2));
	}
	
	
	/**
	 * Check if an empty Album is valid.
	 */
	@Test
	public void checkRepOk1(){
		Album albumTest = new Album();
		assertFalse(albumTest.repOk());
	}
	
	/**
	 * Check if an Album with title but without songs is valid.
	 */
	@Test
	public void checkRepOk2(){
		Album albumTest = new Album();
		albumTest.setTitle("Disraeli Gears");
		assertFalse(albumTest.repOk());
	}
	
	/**
	 * Check is an Album without title but with songs is valid.
	 */
	@Test
	public void checkRepOk3(){
		Album albumTest = new Album();
		List<Song> songs = new LinkedList<Song>();
		songs.add(new Song("Stranbe Brew", 188));
		albumTest.setSongs(songs);
		assertFalse(albumTest.repOk());
	}
		
}
