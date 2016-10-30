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
	private String artist1ID = artist1.getId();
	private Band band1 = new Band();
	private String band1ID = band1.getId();
	
	private Artist artist2 = new Artist("Taylor","Swift");
	private String artist2ID = artist2.getId();
	private Band band2 = new Band();
	private String band2ID = band2.getId();
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void constructorTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		assertTrue(artist1ID.equals(bm.getArtistID()));
		assertTrue(band1ID.equals(bm.getBandID()));
	}

	@Test
	public void setArtistIDTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		bm.setArtistID(artist2ID);
		assertTrue(artist2ID.equals(bm.getArtistID()));
	}
	
	/**
	 * The artist id can't be empty in the association class.
	 */
	@Test
	public void setEmptyArtistTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		expected.expect(IllegalArgumentException.class);
		bm.setArtistID("");
	}
	
	@Test
	public void setBandTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		band2.setName("TS's Band");
		bm.setBandID(band2ID);
		assertTrue(band2ID.equals(bm.getBandID()));
	}
	
	/**
	 * The band id can't be empty in the association class.
	 */
	@Test
	public void setEmptyBandTest(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		expected.expect(IllegalArgumentException.class);
		bm.setBandID("");
	}
}