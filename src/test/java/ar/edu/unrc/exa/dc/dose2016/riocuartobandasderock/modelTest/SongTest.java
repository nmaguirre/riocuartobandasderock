package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Genere;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class SongTest {

	@Test
	public void idTest(){
		Song song = new Song();
		song.setId(1);
		assertEquals(1 , song.getId());
	}

	@Test
	public void nameTest(){
		Song song = new Song();
		song.setName("thebeatles");
		assertEquals("thebeatles", song.getName());
	}
	
	@Test
	public void nameAuthor(){
		Song song = new Song();
		song.setAuthor("arjona");
		assertEquals("arjona", song.getAuthor());
	}
	
	@Test
	public void durationTest(){
		Song song = new Song();
		song.setDuration(3);
		assertEquals(3, song.getDuration());
	}
	
	@Test
	public void albumTest(){
		Album alb = new Album();
		Song song = new Song();
		song.setAlbum(alb);
	    assertEquals(alb, song.getAlbum());
	}
	
	@Test
	public void genereTest(){
		/*Genere gen = new Genere(1 ,"rock","heavy");
		Song song = new Song();
		song.setGenere(gen);
		assertEquals(gen, song.getGenere());*/
	}
	
	@Test
	public void bandTest(){
		List<Band> band = new ArrayList<Band>();
		Song song = new Song();
		song.setBands(band);
		assertEquals(band, song.getBands());
	}
	
	@Test
	public void constructorTest(){
		Album alb = new Album();
		Genere gen = new Genere();
		List<Band> band = new ArrayList<Band>();
		Song song = new Song(1, "pinguinos en la cama", gen, band, 300 , "arjona", alb);
		boolean res = ((song.getId() == 1) && (song.getName() == "pinguinos en la cama") &&(song.getGenere() == gen) 
		&& (song.getBands() == band) && (song.getAlbum() == alb) && (song.getDuration() == 300) 
		&& (song.getAuthor() == "arjona"));
		assertTrue(res);
			
	}
}
