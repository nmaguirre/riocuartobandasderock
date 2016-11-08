package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOTest {

	
	private BandMemberDAOImpl bandMemberDAO;
	private BandDaoImpl bandDAO;
	private ArtistDaoImpl artistDAO;
	private SessionManager session;
	
	@Before
	public void setUp(){
		bandDAO = new BandDaoImpl();
		artistDAO = new ArtistDaoImpl();
		bandMemberDAO = new BandMemberDAOImpl();
		session = SessionManager.getInstance();
	}
	
	/*
	 * CREATE BANDMEMBER METHOD TESTS
	 */
	
	@Test
	public void bandMember_not_in_db_artist_and_band_in_db() {
		
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();		
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		session.openCurrentSession();
		while(bandDAO.existBand(bandName, genre)){
			bandName+="a";
		}
		session.closeCurrentSession();		
		
		// Add band in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(bandName, genre);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		session.openCurrentSession();
		String artistId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		session.openCurrentSession();
		//String bandId = bandDAO.findBandByNameAndGenre(bandName).getId;
		session.closeCurrentSession();
		
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//boolean successfulOPeration = bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		// Check that the operation was successful and bandMember is in db
		//assertTrue(successfulOPeration);
		//assertTrue(bandMemberInBd);
	}
	
	@Test
	public void bandMember_not_in_db_but_artist_neither() {				
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		session.openCurrentSession();
		while(bandDAO.existBand(bandName, genre)){
			bandName+="a";
		}
		session.closeCurrentSession();		
		
		// Add band in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(bandName, genre);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * The operation must fail because
		 * the artist is not in db
		*/			
		
		String artistId = "-1";
		
		session.openCurrentSession();
		//String bandId = bandDAO.findBandByNameAndGenre(bandName).getId;
		session.closeCurrentSession();
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//boolean successfulOPeration = bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		// Check that the operation wasnt successful and bandMember is in db
		//assertTrue(!successfulOPeration);
		//assertTrue(!bandMemberInBd);
	}
	
	
	@Test
	public void bandMember_not_in_db_but_band_neither() {				
		
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();		
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * The operation must fail because
		 * the band is not in db
		*/			
		
		String bandId = "-1";
		
		session.openCurrentSession();
		String artistId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//boolean successfulOPeration = bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		// Check that the operation wasnt successful and bandMember is in db
		//assertTrue(!successfulOPeration);
		//assertTrue(!bandMemberInBd);
	}
	
	@Test
	public void bandMember_not_in_db_but_band_and_artist_neither() {				
		
				
		/*
		 * CREATE BANDMEMBER IN DB
		 * The operation must fail because
		 * the band and arsits are not in db
		*/			
		
		String bandId = "-1";
		String artistId = "-1";
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		// Check that the operation wasnt successful and bandMember is in db
		//assertTrue(!successfulOPeration);
		//assertTrue(!bandMemberInBd);
	}
	
	
	/*
	 * FIND BY ID METHOD TESTS
	 */
	
	@Test
	public void findByID_BandMember_in_db() {
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();		
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		session.openCurrentSession();
		while(bandDAO.existBand(bandName, genre)){
			bandName+="a";
		}
		session.closeCurrentSession();		
		
		// Add band in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(bandName, genre);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		session.openCurrentSession();
		String artistId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		session.openCurrentSession();
		//String bandId = bandDAO.findBandByNameAndGenre(bandName).getId;
		session.closeCurrentSession();
		
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//boolean successfulOPeration = bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		/*if(successfulOPeration && bandMemberInBd){
			session.openCurrentSession();
			BandMember bandM = bandMemberDAO.findById(artistId,bandId);
			session.closeCurrentSession();
			
			assertTrue(bandM != null);
			assertEquals(bandM.getArtistID(), artistId);
			assertEquals(bandM.getBandID(), bandId);
		}*/
	}
	
	@Test
	public void findById_BandMember_not_in_db() {
		
		String artistId = "-1";
		String bandId = "-1";
				
		session.openCurrentSession();
		BandMember bandM = bandMemberDAO.findById(artistId,bandId);
		session.closeCurrentSession();
		
		assertTrue(bandM == null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_null_artistId() {
		
		String artistId = null;
		String bandId = "1";
		
		session.openCurrentSession();
		BandMember obtained = bandMemberDAO.findById(artistId,bandId);
		session.closeCurrentSession();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_empty_artistId() {
		
		String artistId = "";
		String bandId = "1";
		
		session.openCurrentSession();
		BandMember obtained = bandMemberDAO.findById(artistId,bandId);
		session.closeCurrentSession();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_null_bandId() {
		
		String artistId = "1";
		String bandId = null;
		
		session.openCurrentSession();
		BandMember obtained = bandMemberDAO.findById(artistId,bandId);
		session.closeCurrentSession();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_empty_bandId() {
		
		String artistId = "1";
		String bandId = "";
		
		session.openCurrentSession();
		BandMember obtained = bandMemberDAO.findById(artistId,bandId);
		session.closeCurrentSession();
	}
	
	
	/*
	 * FIND BY ARTIST METHOD TESTS
	 */
	
	@Test
	public void findByArtist_BandMember_with_the_artist_in_db() {
		
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();		
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		session.openCurrentSession();
		while(bandDAO.existBand(bandName, genre)){
			bandName+="a";
		}
		session.closeCurrentSession();		
		
		// Add band in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(bandName, genre);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		session.openCurrentSession();
		String artistId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		session.openCurrentSession();
		//String bandId = bandDAO.findBandByNameAndGenre(bandName).getId;
		session.closeCurrentSession();
		
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//boolean successfulOPeration = bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		/*if(successfulOPeration && bandMemberInBd){
			session.openCurrentSession();
			List<BandMember> bandMs = bandMemberDAO.findByArtist(artistId);
			session.closeCurrentSession();
			
			assertTrue(!bandMs.isEmpty());
			assertEquals(bandMs.get(0).getArtistID(), artistId);
		}*/
	}
	
	@Test
	public void findByArtist_BandMember_with_the_artist_not_in_db() {
		
		String artistId = "-1";
				
		session.openCurrentSession();
		List<BandMember> bandMs = bandMemberDAO.findByArtist(artistId);
		session.closeCurrentSession();
		
		assertTrue(bandMs.isEmpty());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByArtist_null_artistId() {
		
		String artistId = null;
		
		session.openCurrentSession();
		List<BandMember> bandMs = bandMemberDAO.findByArtist(artistId);
		session.closeCurrentSession();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findByArtist_empty_artistId() {
		
		String artistId = "";
		
		session.openCurrentSession();
		List<BandMember> bandMs = bandMemberDAO.findByArtist(artistId);
		session.closeCurrentSession();
	}
	
	
	/*
	 * FIND BY BAND METHOD TESTS
	 */
	
	@Test
	public void findByBand_BandMember_with_the_band_in_db() {
		
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();		
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		session.openCurrentSession();
		while(bandDAO.existBand(bandName, genre)){
			bandName+="a";
		}
		session.closeCurrentSession();		
		
		// Add band in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(bandName, genre);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		session.openCurrentSession();
		String artistId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		session.openCurrentSession();
		//String bandId = bandDAO.findBandByNameAndGenre(bandName).getId;
		session.closeCurrentSession();
		
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//boolean successfulOPeration = bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberInBd = bandMemberDAO.exists(artistId,bandId);
		session.closeCurrentSession();
		
		/*if(successfulOPeration && bandMemberInBd){
			session.openCurrentSession();
			List<BandMember> bandMs = bandMemberDAO.findByBand(bandId);
			session.closeCurrentSession();
			
			assertTrue(!bandMs.isEmpty());
			assertEquals(bandMs.get(0).getBandID(), bandId);
		}*/
	}
	
	@Test
	public void findByBand_BandMember_with_the_band_not_in_db() {
		
		String bandId = "-1";
				
		session.openCurrentSession();
		List<BandMember> bandMs = bandMemberDAO.findByBand(bandId);
		session.closeCurrentSession();
		
		assertTrue(bandMs.isEmpty());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByBand_null_bandId() {
		
		String bandId = null;
		
		session.openCurrentSession();
		List<BandMember> bandMs = bandMemberDAO.findByBand(bandId);
		session.closeCurrentSession();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findByBand_empty_bandId() {
		
		String bandId = "";
		
		session.openCurrentSession();
		List<BandMember> bandMs = bandMemberDAO.findByBand(bandId);
		session.closeCurrentSession();
	}
	
	/*
	 * DELETE BANDMEMBER METHOD TESTS
	 */
	
	@Test
	public void deleteBandMemberTest_bandMember_in_db() {
		
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();		
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		session.openCurrentSession();
		while(bandDAO.existBand(bandName, genre)){
			bandName+="a";
		}
		session.closeCurrentSession();		
		
		// Add band in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(bandName, genre);
		session.closeCurrentSessionwithTransaction();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		session.openCurrentSession();
		String artistId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		session.openCurrentSession();
		//String bandId = bandDAO.findBandByNameAndGenre(bandName).getId;
		session.closeCurrentSession();
		
		
		// Add bandMember in db
		session.openCurrentSessionwithTransaction();
		//bandMemberDAO.createBandMember(artistId,bandId);
		session.closeCurrentSessionwithTransaction();
		
				
		session.openCurrentSessionwithTransaction();
		//boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		//boolean bandMemberExistsinBd = bandMemberDAO.existBandMember(bandId, artistId); 
		session.closeCurrentSession();	
		
		// Check that in db the bandMember was deleted
		//assertTrue(successfulOperation);
		//assertTrue(!bandMemberExistsinBd);
	}
	
	
	@Test
	public void deleteBandMemberTest_bandMember_not_in_db() {
		
		String bandId = "-1";
		String artistId = "-1";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.closeCurrentSessionwithTransaction();
				
		// Check that an update fail with not artist id in DB		
		assertTrue(!successfulOperation);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_null_artistId() {
		
		String bandId = "1";
		String artistId = null;
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_empty_artistId() {
		
		String bandId = "1";
		String artistId = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.closeCurrentSessionwithTransaction();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_null_bandId() {
		
		String bandId = null;
		String artistId = "1";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_empty_bandId() {
		
		String bandId = "";
		String artistId = "1";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.closeCurrentSessionwithTransaction();
	}
	
}
