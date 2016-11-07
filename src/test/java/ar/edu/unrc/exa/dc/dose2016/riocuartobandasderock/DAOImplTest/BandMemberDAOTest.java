package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

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
		
		// Check that the operation was successful and bandMember is in db
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
		
		// Check that the operation was successful and bandMember is in db
		//assertTrue(!successfulOPeration);
		//assertTrue(!bandMemberInBd);
	}
	
	
	
}
