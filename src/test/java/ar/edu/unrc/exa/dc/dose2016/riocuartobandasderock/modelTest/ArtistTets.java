package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public class ArtistTets {
	
	@Test
	public void constructorTest(){
		String a_name = "Carlos Alberto";
		String a_surname = "Solari";
		String a_nick = "Indio";
		Artist artist = new Artist(a_name, a_surname, a_nick);
		assertTrue(artist.getName().equals(a_name));
		assertTrue(artist.getSurname().equals(a_surname));
		assertTrue(artist.getNickname().equals(a_nick));
	}	
	
	@Test
	public void constructorTestWithoutNickname(){
		String a_name = "Gustavo";
		String a_surname = "Napoli";
		Artist artist = new Artist(a_name, a_surname);
		assertTrue(artist.getName().equals(a_name));
		assertTrue(artist.getSurname().equals(a_surname));
	}
	
	@Test
	public void setNameTest(){
	    String a_name = "Pablo";
	    String a_surname = "Beilinson";
	    Artist artist = new Artist(a_name, a_surname);
	    String new_name = "Eduardo";
	    artist.setName(new_name);
	    assertEquals(artist.getName(),new_name);  
	}
	
	@Test
	public void setSurnameTest(){
	    String a_name = "Gustavo";
	    String a_surname = "González";
	    Artist artist = new Artist(a_name, a_surname);
	    String new_surname = "Cerati";
	    artist.setSurname(new_surname);
	    assertEquals(artist.getSurname(),new_surname);  
	}
	
	@Test
	public void setNicknameTest(){
	    String a_name = "Juan Sebastián";
	    String a_surname = "Gutiérrez";
	    Artist artist = new Artist(a_name, a_surname);
	    String nickname = "Juanse";
	    artist.setNickname(nickname);
	    assertEquals(artist.getNickname(),nickname);  
	}
}

