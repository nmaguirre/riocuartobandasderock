package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;


import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class SongTest {

	@Test
	public void idTest(){
		Song song = new Song();
		song.setId("1");
		assertEquals("1" , song.getId());
	}

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
}
