package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;


import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class SongTest {

	@Test
	public void nameTest(){
		Song song = new Song();
		song.setName("thebeatles");
		assertEquals("thebeatles", song.getName());
	}
	

	@Test
	public void durationTest(){
		Song song = new Song();
		song.setDuration(3);
		assertEquals(3, song.getDuration());
	}
	
	@Test
	public void constructorTest(){
		Song song = new Song("pinguinos en la cama", 300);
		boolean res = (song.getName() == "pinguinos en la cama") && (song.getDuration() == 300);
		assertTrue(res);
	}
	
	@Test
	public void getDurationInStringTest(){
		Song song = new Song();
		int a = 129;
		song.setDuration(a);
		assertEquals("2:9",song.getDurationAsString());
	}

	@Test
 	public void albumTest(){
 		Album alb = new Album();
 		Song song = new Song();
 		song.setAlbum(alb);
 	    assertEquals(alb, song.getAlbum());
	}    
}
