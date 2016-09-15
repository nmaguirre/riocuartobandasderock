package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

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
	public void durationTest(){
		Song song = new Song();
		song.setDuration(3);
		assertEquals(3, song.getDuration());
	}
	/*
	@Test
	public void albumTest(){
		Album alb = new Album();
		Song song = new Song();
		song.setAlbum(alb);
	//	assertEquals("Arjona", song.getAuthor());
	}
	
	@Test
	public void genereTest(){
		Genere gen = new Genere(1,"rock","heavy");
		Song song = new Song();
		song.setGenere(gen);
		boolean id = song.getGenere().getId() == 1;
		boolean name = song.getGenere().getName() == "rock";
		boolean desc = song.getGenere().getDescription() == "heavy";
		assertTrue(id && name && desc);
	}*/
}
