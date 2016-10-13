package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

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
	
	
	
	
	

}
