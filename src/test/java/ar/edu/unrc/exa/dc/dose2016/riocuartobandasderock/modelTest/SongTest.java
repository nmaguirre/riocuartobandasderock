package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;


import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;
/**
 * 
 * In this class are hosted the test corresponding to the class Song
 */
public class SongTest {
            
            /**
             * test nameTest
             * Description: This test confirms that the setName method define the name waited for a song
             */
	@Test
	public void nameTest(){
		Song song = new Song();
		song.setName("thebeatles");
		assertEquals("thebeatles", song.getName());
	}
	
        /**
         * test durationTest
         * Description: Thist test confirms that the method setDuration define the duration waited for a song
         */
	@Test
	public void durationTest(){
		Song song = new Song();
		song.setDuration(3);
		assertEquals(3, song.getDuration());
	}
	
        /**
         * test constructorTest
         * Description: This test confirms that the constructor of the class Song will generate a song expected
         */
	@Test
	public void constructorTest(){
		Song song = new Song("pinguinos en la cama", 300);
		boolean res = (song.getName() == "pinguinos en la cama") && (song.getDuration() == 300);
		assertTrue(res);
	}
	
        /**
         * test getDurationInStringTest
         * Description: This test confirms that the method getDurationAsString generated the string corresponding to the duration of the song
         */
	@Test
	public void getDurationInStringTest(){
		Song song = new Song();
		int a = 129;
		song.setDuration(a);
		assertEquals("2:9",song.getDurationAsString());
	}
	
        /**
         * test albumTest
         * Description: This test confirms that the method setAlbum generated the album corresponding in the album field of song
         */
 	@Test
 	public void albumTest(){
 		Album alb = new Album();
 		Song song = new Song();
 		song.setAlbum(alb);
 	    assertEquals(alb, song.getAlbum());
 	}

 
}
