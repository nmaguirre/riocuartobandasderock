package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;


public class ArtistTets {
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
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
	public void setNullNameTest(){
		String a_name = "Pablo";
	    String a_surname = "Beilinson";
	    Artist artist = new Artist(a_name, a_surname);
		expected.expect(IllegalArgumentException.class);
		artist.setName(null);
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
	public void setNullSurnameTest(){
		String a_name = "Gustavo";
	    String a_surname = "Cerati";
	    Artist artist = new Artist(a_name, a_surname);
		expected.expect(IllegalArgumentException.class);
		artist.setSurname(null);
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
	
	@Test
	public void setNullNicknameTest(){
      String a_name = "Juan Sebastián";
      String a_surname = "Gutiérrez";
      String a_nickname = "Juanse";
      Artist artist = new Artist(a_name, a_surname, a_nickname);
      expected.expect(IllegalArgumentException.class);
      artist.setNickname(null);
	}
}

