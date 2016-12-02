package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	 * @throws ParseException 
	 */
	@Test
	public void fullParametersConstructorTest() throws ParseException {
		String title = "Disraeli Gears";
		Date releaseDate = new Date(1967,11,2);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date releaseDateMod;
		//releaseDateMod = f.parse(releaseDate.toString());
		Album albumTest = new Album(title);
		Band band = new Band("Cream", "psychedelic rock");
		albumTest.setBand(band);
		assertEquals(albumTest.getTitle(),"Disraeli Gears");
	//	assertEquals(albumTest.getReleaseDate(), releaseDateMod);
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
	
	/**
	 * Check is an Album without title neither songs but with release date is valid.
	 */
	@Test
	public void checkRepOk4(){
		Album albumTest = new Album();
		Date releaseDate = new Date(1967,11,2);
		albumTest.setReleaseDate(releaseDate);
		assertFalse(albumTest.repOk());
	}
	
	/**
	 * Check is an album with title and songs is valid.
	 */
	@Test
	public void checkRepOk5(){
		Album albumTest = new Album("Disraeli Gears");
		List<Song> songs = new LinkedList<Song>();
		songs.add(new Song("Stranbe Brew", 188));
		albumTest.setSongs(songs);
		Band band = new Band("Cream", "psychedelic rock");
		albumTest.setBand(band);
		assertTrue(albumTest.repOk());
	}
	
	/**
	 * Check is an album with an empty title and song is valid.
	 */
	@Test
	public void checkRepOk6(){
		Album albumTest = new Album("");
		List<Song> songs = new LinkedList<Song>();
		songs.add(new Song("Stranbe Brew", 188));
		albumTest.setSongs(songs);
		assertFalse(albumTest.repOk());
	}
	
}
