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
	
	private Artist artist1 = new Artist("Ale","Sergi");
	private Band band1 = new Band();
	private Artist artist2 = new Artist("Taylor","Swift");
	private Band band2 = new Band();
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void constructorTestWithRole(){
		band1.setName("Miranda");
		List<Role> role = new LinkedList<Role>();
		role.add(Role.vocalist);
		role.add(Role.guitarist);
		BandMember bm = new BandMember(artist1,band1,role);
		assertTrue(artist1.equals(bm.getArtist()));
		assertTrue(band1.equals(bm.getBand()));
		assertTrue((bm.getRole()).get(0).equals(Role.vocalist));
		assertTrue((bm.getRole()).get(1).equals(Role.guitarist));
	}
	
	@Test
	public void constructorTestWithoutRole(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		assertTrue(artist1.equals(bm.getArtist()));
		assertTrue(band1.equals(bm.getBand()));
		assertTrue((bm.getRole()).isEmpty());
	}
	
	@Test
	public void setArtistTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		bm.setArtist(artist2);
		assertTrue(artist2.equals(bm.getArtist()));
	}
	
	@Test
	public void setNullArtistTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		expected.expect(IllegalArgumentException.class);
		bm.setArtist(null);
	}
	
	@Test
	public void setBandTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		band2.setName("TS's Band");
		bm.setBand(band2);
		assertTrue(band2.equals(bm.getBand()));
	}
	
	@Test
	public void setNullBandTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		expected.expect(IllegalArgumentException.class);
		bm.setBand(null);
	}
	
	@Test
	public void setRoleTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		List<Role> role = new LinkedList<Role>();
		role.add(Role.vocalist);
		bm.setRole(role);
		assertTrue((Role.vocalist).equals(bm.getRole().get(0)));
	}
	
	@Test
	public void setNullRoleTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1,band1);
		expected.expect(IllegalArgumentException.class);
		bm.setRole(null);
	}

}