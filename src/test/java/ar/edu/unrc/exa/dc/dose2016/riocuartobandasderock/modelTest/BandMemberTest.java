package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Role;

import java.util.LinkedList;
import java.util.List;

public class BandMemberTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void constructorTestWithRole(){
		Artist artist = new Artist("Ale", "Sergi");
		Band band = new Band();
		band.setName("Miranda");
		List<Role> role = new LinkedList<Role>();
		role.add(Role.vocalist);
		role.add(Role.guitarist);
		BandMember bm = new BandMember(artist,band,role);
		assertTrue(artist.equals(bm.getArtist()));
		assertTrue(band.equals(bm.getBand()));
		assertTrue((bm.getRole()).get(0).equals(Role.vocalist));
		assertTrue((bm.getRole()).get(1).equals(Role.guitarist));
	}
	
	@Test
	public void constructorTestWithoutRole(){
		Artist artist = new Artist("Ale", "Sergi");
		Band band = new Band();
		band.setName("Miranda");
		BandMember bm = new BandMember(artist,band);
		assertTrue(artist.equals(bm.getArtist()));
		assertTrue(band.equals(bm.getBand()));
		assertTrue((bm.getRole()).isEmpty());
	}
	
	@Test
	public void setArtistTest(){
		BandMember bm = new BandMember();
		Artist artist = new Artist("Ale", "Sergi");
		bm.setArtist(artist);
		assertTrue(artist.equals(bm.getArtist()));
	}
	
	@Test
	public void setNullArtistTest(){
		BandMember bm = new BandMember();
		expected.expect(IllegalArgumentException.class);
		bm.setArtist(null);
	}
	
	@Test
	public void setBandTest(){
		BandMember bm = new BandMember();
		Band band = new Band();
		band.setName("Miranda");
		bm.setBand(band);
		assertTrue(band.equals(bm.getBand()));
	}
	
	@Test
	public void setNullBandTest(){
		BandMember bm = new BandMember();
		expected.expect(IllegalArgumentException.class);
		bm.setBand(null);
	}
	
	@Test
	public void setRoleTest(){
		BandMember bm = new BandMember();
		List<Role> role = new LinkedList<Role>();
		role.add(Role.vocalist);
		bm.setRole(role);
		assertTrue((Role.vocalist).equals(bm.getRole().get(0)));
	}
	
	@Test
	public void setNullRoleTest(){
		BandMember bm = new BandMember();
		expected.expect(IllegalArgumentException.class);
		bm.setRole(null);
	}

}