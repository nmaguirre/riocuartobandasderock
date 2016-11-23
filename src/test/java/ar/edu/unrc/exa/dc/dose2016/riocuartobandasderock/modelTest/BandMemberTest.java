package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

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
	public void constructorTestArtist(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		assertTrue(artist1ID.equals(bm.getArtistID()));
	}
	
	@Test
	public void constructorTestBand(){
		band1.setName("Miranda");
		BandMember bm = new BandMember(artist1ID,band1ID);
		assertEquals(band1ID, bm.getBandID());
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
		assertEquals(band2ID, bm.getBandID());
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
	
	/*
	 * BandMember with artistID and bandID not empty, should return true.
	 */
	@Test
	public void repOkTest(){
		BandMember bm = new BandMember(artist1ID,band1ID);
		assertTrue(bm.repOk());
	}
	
	/*
	 * BandMember with artistID not empty and bandID empty, should return false.
	 */
	@Test
	public void repOkTest2(){
		BandMember bm = new BandMember(artist1ID,"");
		assertFalse(bm.repOk());
	}
	
	/*
	 * BandMember with artistID empty and bandID not empty, should return false.
	 */
	@Test
	public void repOkTest4(){
		BandMember bm = new BandMember("", band1ID);
		assertFalse(bm.repOk());
	}
	
	/*
	 * BandMember with artistID and bandID empty, should return false.
	 */
	@Test
	public void repOkTest5(){
		BandMember bm = new BandMember("", "");
		assertFalse(bm.repOk());
	}
}