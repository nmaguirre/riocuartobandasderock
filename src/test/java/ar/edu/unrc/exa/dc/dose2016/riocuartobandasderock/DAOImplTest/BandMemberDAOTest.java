package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOTest {

	
	private BandMemberDAOImpl bandMemberDAO;
	private BandDaoImpl bandDAO;
	private ArtistDaoImpl artistDAO;
	private Session session;
	
	@Before
	public void setUp(){
		session = SessionManager.getInstance().openSession();
		bandDAO = new BandDaoImpl(session);
		artistDAO = new ArtistDaoImpl(session);
		bandMemberDAO = new BandMemberDAOImpl(session);
	}
	@After
	public void closeSession(){
		session.close();
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
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}	
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		while(bandDAO.existBand(bandName)){
			bandName+="a";
		}		
		
		// Add band in db
		session.beginTransaction();
		bandDAO.createBand(bandName, genre);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();
		
		String bandId = bandDAO.findByNameAndGenre(bandName, genre).get(0).getId();
		
		// Add bandMember in db
		session.beginTransaction();
		boolean successfulOPeration = bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
		boolean bandMemberInBd = bandMemberDAO.existBandMember(bandId,artistId);
		
		// Check that the operation was successful and bandMember is in db
		assertTrue(successfulOPeration);
		assertTrue(bandMemberInBd);
	}
	
	@Test(expected = PersistenceException.class)
	public void bandMember_not_in_db_but_artist_neither() {				
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		while(bandDAO.existBand(bandName)){
			bandName+="a";
		}
		
		// Add band in db
		session.beginTransaction();
		bandDAO.createBand(bandName, genre);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * The operation must fail because
		 * the artist is not in db
		*/			
		
		String artistId = "-1";
		
		String bandId = bandDAO.findByNameAndGenre(bandName, genre).get(0).getId();
		 
		
		// Add bandMember in db
		session.beginTransaction();
		bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();

	}
	
	
	@Test(expected = PersistenceException.class)
	public void bandMember_not_in_db_but_band_neither() {				
		
		/*
		 * CREATE ARTIST IN DB
		*/
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 		
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * The operation must fail because
		 * the band is not in db
		*/			
		
		String bandId = "-1";
		
		 
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();
		 
		
		// Add bandMember in db
		session.beginTransaction();
		bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
	}
	
	@Test(expected = PersistenceException.class)
	public void bandMember_not_in_db_but_band_and_artist_neither() {				
						
		/*
		 * CREATE BANDMEMBER IN DB
		 * The operation must fail because
		 * the band and artist are not in db
		*/			
		
		String bandId = "-1";
		String artistId = "-1";
		
		// Add bandMember in db
		session.beginTransaction();
		bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
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
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 		
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		 
		while(bandDAO.existBand(bandName)){
			bandName+="a";
		}
		 		
		
		// Add band in db
		session.beginTransaction();
		bandDAO.createBand(bandName, genre);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		 
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();
		 
		String bandId = bandDAO.findByNameAndGenre(bandName, genre).get(0).getId();
		 
		
		// Add bandMember in db
		session.beginTransaction();
		boolean successfulOPeration = bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
		 
		boolean bandMemberInBd = bandMemberDAO.existBandMember(bandId,artistId);
		 
		
		if(successfulOPeration && bandMemberInBd){
			 
			BandMember bandM = bandMemberDAO.findById(bandId,artistId);
			 
			assertEquals(bandM.getArtistID(), artistId);
			assertEquals(bandM.getBandID(), bandId);
		}
	}
	
	@Test
	public void findById_BandMember_not_in_db() {
		
		String artistId = "-1";
		String bandId = "-1";
				
		 
		BandMember bandM = bandMemberDAO.findById(bandId,artistId);
		 
		
		assertTrue(bandM == null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_null_artistId() {
		
		String artistId = null;
		String bandId = "1";
		
		 
		bandMemberDAO.findById(bandId,artistId);
		 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_empty_artistId() {
		
		String artistId = "";
		String bandId = "1";
		
		 
		bandMemberDAO.findById(bandId,artistId);
		 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_null_bandId() {
		
		String artistId = "1";
		String bandId = null;
		
		 
		bandMemberDAO.findById(bandId,artistId);
		 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_empty_bandId() {
		
		String artistId = "1";
		String bandId = "";
		
		 
		bandMemberDAO.findById(bandId,artistId);	 
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
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 		
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		 
		while(bandDAO.existBand(bandName)){
			bandName+="a";
		}
		 		
		
		// Add band in db
		session.beginTransaction();
		bandDAO.createBand(bandName, genre);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		 
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();	
		 
		String bandId = bandDAO.findByNameAndGenre(bandName, genre).get(0).getId();
		 
		
		
		// Add bandMember in db
		session.beginTransaction();
		boolean successfulOPeration = bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
		 
		boolean bandMemberInBd = bandMemberDAO.existBandMember(bandId,artistId);
		 
		
		if(successfulOPeration && bandMemberInBd){
			 
			List<Band> bands = bandMemberDAO.findByArtist(artistId);
			 
			
			assertTrue(!bands.isEmpty());
			assertEquals(bands.get(0).getId(), bandId);
			assertEquals(bands.get(0).getName(), bandName);
			assertEquals(bands.get(0).getGenre(), genre);
		}
	}
	
	@Test
	public void findByArtist_BandMember_with_the_artist_not_in_db() {
		String artistId = "-1";
		List<Band> bandList = bandMemberDAO.findByArtist(artistId);
		
		assertTrue(bandList.isEmpty());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByArtist_null_artistId() {
		String artistId = null;
		bandMemberDAO.findByArtist(artistId);
		 
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByArtist_empty_artistId() {
		String artistId = "";
		bandMemberDAO.findByArtist(artistId);
		 
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
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 		
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		 
		while(bandDAO.existBand(bandName)){
			bandName+="a";
		}
		 		
		
		// Add band in db
		session.beginTransaction();
		bandDAO.createBand(bandName, genre);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		 
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();
		 
		
		 
		String bandId = bandDAO.findByNameAndGenre(bandName, genre).get(0).getId();
		 
		
		
		// Add bandMember in db
		session.beginTransaction();
		boolean successfulOPeration = bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
		 
		boolean bandMemberInBd = bandMemberDAO.existBandMember(bandId,artistId);
		 
		
		if(successfulOPeration && bandMemberInBd){
			 
			List<Artist> artists = bandMemberDAO.findByBand(bandId);
			 
			
			assertTrue(!artists.isEmpty());
			assertEquals(artists.get(0).getId(), artistId);
			assertEquals(artists.get(0).getName(), name);
			assertEquals(artists.get(0).getSurname(), surname);
			assertEquals(artists.get(0).getNickname(), nickname);
		}
	}
	
	@Test
	public void findByBand_BandMember_with_the_band_not_in_db() {
		String bandId = "-1";
		List<Artist> artistList = bandMemberDAO.findByBand(bandId);
		assertTrue(artistList.isEmpty());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByBand_null_bandId() {
		String bandId = null;
		bandMemberDAO.findByBand(bandId);
		 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findByBand_empty_bandId() {
		String bandId = "";
		bandMemberDAO.findByBand(bandId);
		 
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
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 		
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		
		/*
		 * CREATE BAND IN DB
		*/
		
		String bandName = "a";
		String genre = "b";		
		
		 
		while(bandDAO.existBand(bandName)){
			bandName+="a";
		}
		 		
		
		// Add band in db
		session.beginTransaction();
		bandDAO.createBand(bandName, genre);
		session.getTransaction().commit();
		
		/*
		 * CREATE BANDMEMBER IN DB
		 * (Artist and Band is recent created, then
		 * the bandmember with their ids isnt in bd)
		*/			
		
		 
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();
		 
		
		 
		String bandId = bandDAO.findByNameAndGenre(bandName, genre).get(0).getId();
		 
		
		
		// Add bandMember in db
		session.beginTransaction();
		bandMemberDAO.createBandMember(bandId,artistId);
		session.getTransaction().commit();
		
				
		session.beginTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.getTransaction().commit();
		
		 
		boolean bandMemberExistsinBd = bandMemberDAO.existBandMember(bandId,artistId); 
		 	
		
		// Check that in db the bandMember was deleted
		assertTrue(successfulOperation);
		assertTrue(!bandMemberExistsinBd);
	}
	
	
	@Test
	public void deleteBandMemberTest_bandMember_not_in_db() {
		
		String bandId = "-1";
		String artistId = "-1";
			
		session.beginTransaction();
		boolean successfulOperation = bandMemberDAO.deleteBandMember(bandId, artistId);
		session.getTransaction().commit();
				
		// Check that a delete fail without bandmember in DB		
		assertTrue(!successfulOperation);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_null_artistId() {
		
		String bandId = "1";
		String artistId = null;
			
		session.beginTransaction();
		bandMemberDAO.deleteBandMember(bandId, artistId);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_empty_artistId() {
		
		String bandId = "1";
		String artistId = "";
			
		session.beginTransaction();
		bandMemberDAO.deleteBandMember(bandId, artistId);
		session.getTransaction().commit();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_null_bandId() {
		
		String bandId = null;
		String artistId = "1";
			
		session.beginTransaction();
		bandMemberDAO.deleteBandMember(bandId, artistId);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteBandMemberTest_empty_bandId() {
		
		String bandId = "";
		String artistId = "1";
			
		session.beginTransaction();
		bandMemberDAO.deleteBandMember(bandId, artistId);
		session.getTransaction().commit();
	}
	
}
