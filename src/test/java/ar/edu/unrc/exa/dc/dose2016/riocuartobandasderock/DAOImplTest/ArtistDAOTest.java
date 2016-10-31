package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;


public class ArtistDAOTest {

	private ArtistDAO artistDAO;
	private SessionManager session;
	
	@Before
	public void setUp(){
		artistDAO = new ArtistDaoImpl();
		session = SessionManager.getInstance();
	}
	
	/*
	 * CREATE ARTIST METHOD TESTS
	 */
	
	@Test
	public void createArtistTest_Artist_not_in_db() {
		
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		session.openCurrentSession();
		artistList.addAll(artistDAO.findByName(artistToAdd.getName()));
		session.closeCurrentSession();
		
		
		List<Artist> result = new LinkedList<>();
		
		for(Artist currentArtist: artistList){
			// Add in result an artist if it is equals to artistToAdd
			if (currentArtist.equals(artistToAdd)){
				result.add(currentArtist);
			}
		}
		
		// Check that in db there is only 1 artist with artistToAdd information
		assertTrue(result.size() == 1);
		assertTrue(result.get(0).equals(artistToAdd));
	}
	
	
	@Test
	public void createArtistTest_Artist_in_db() {
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db for first time
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
		
		// Add artistToAdd in db for second time
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.createArtist(name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
		
		assertTrue(!successfulOperation);	
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_name() {
		String name = null;
		String surname = "b";
		String nickname = "";
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_surname() {
		String name = "a";
		String surname = null;
		String nickname = "";
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_nickname() {
		String name = "a";
		String surname = "b";
		String nickname = null;
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_empty_fields() {
		String name = "";
		String surname = "";
		String nickname = "";
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	/*
	 * FIND BY NAME METHOD TESTS
	 */
	
	@Test
	public void findByName_Artist_in_db() {
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByName(name);
		session.closeCurrentSession();
		
		assertEquals(obtained.get(0).getName(),name);
	}
	
	@Test
	public void findByName_Artist_not_in_db() {
		
		String name = "i_will_not_find_u";
		String surname = "";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByName(name);
		session.closeCurrentSession();
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_null_name() {
		
		String name = null;
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByName(name);
		session.closeCurrentSession();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_empty_name() {
		
		String name = "";
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByName(name);
		session.closeCurrentSession();
	}
	
	
	/*
	 * FIND BY SURNAME METHOD TESTS
	 */
	
	@Test
	public void findBySurname_Artist_in_db() {
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			surname+="b";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findBySurname(surname);
		session.closeCurrentSession();
		
		assertEquals(obtained.get(0).getSurname(),surname);
	}
	
	@Test
	public void findBySurname_Artist_not_in_db() {
		
		String name = "";
		String surname = "i_will_not_find_u";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			surname+="a";
		}
		session.closeCurrentSession();
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findBySurname(surname);
		session.closeCurrentSession();
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findBySurname_null_surname() {
		
		String surname = null;
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findBySurname(surname);
		session.closeCurrentSession();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findBySurname_empty_surname() {
		
		String surname = "";
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findBySurname(surname);
		session.closeCurrentSession();
	}
	
	
	/*
	 * FIND BY NICKNAME METHOD TESTS
	 */
	
	@Test
	public void findByNickname_Artist_in_db() {
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "c";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			nickname+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		session.closeCurrentSession();
		
		assertEquals(obtained.get(0).getName(),name);
	}
	
	@Test
	public void findByNnickname_Artist_not_in_db() {
		
		String name = "";
		String surname = "";
		String nickname = "i_will_not_find_u";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			nickname+="a";
		}
		session.closeCurrentSession();
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		session.closeCurrentSession();
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByNickname_null_nickname() {
		
		String nickname = null;
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		session.closeCurrentSession();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByNickname_empty_nickname() {
		
		String nickname = "";
		
		session.openCurrentSession();
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		session.closeCurrentSession();
	}
	
	
	/*
	 * UPDATE ARTIST METHOD TESTS
	 */
	
	@Test
	public void updateArtistTest_Artist_in_db() {
		
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		session.openCurrentSession();
		String obtainedId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		// Obtain a new name to update the artist that isnt in BD already
		session.openCurrentSession();
		String updatedName = name;
		String updatedSurname = "updatedSurname";
		String updatedNickname = "updatedNickname";
		while(artistDAO.existArtist(updatedName,updatedSurname,updatedNickname)){
			updatedName+="a";
		}
		session.closeCurrentSession();
		
		session.openCurrentSessionwithTransaction();
		artistDAO.updateArtist(obtainedId, updatedName, updatedSurname, updatedNickname);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		Artist artistUpdated = artistDAO.findById(obtainedId); 
		session.closeCurrentSession();	
		
		// Check that in db the artist with id obtainedId was updated
		assertTrue(artistUpdated.getName().equals(updatedName));
		assertTrue(artistUpdated.getSurname().equals(updatedSurname));
		assertTrue(artistUpdated.getNickname().equals(updatedNickname));
	}
	
	
	@Test
	public void updateArtistTest_Artist_not_in_db() {
		
		String id = "-1";
		String name = "a";
		String surname = "b";
		String nickname = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
				
		// Check that an update fail with not artist id in DB		
		assertTrue(!successfulOperation);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_id() {
		
		String id = null;
		String name = "a";
		String surname = "b";
		String nickname = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_name() {
		
		String id = "-1";
		String name = null;
		String surname = "b";
		String nickname = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_surname() {
		
		String id = "1";
		String name = "a";
		String surname = null;
		String nickname = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_nickname() {
		
		String id = "1";
		String name = "a";
		String surname = "b";
		String nickname = null;
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_empty_id() {
		
		String id = "";
		String name = "a";
		String surname = "b";
		String nickname = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_empty_fields() {
		
		String id = "2";
		String name = "";
		String surname = "";
		String nickname = "";
			
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	/*
	 * DELETE ARTIST METHOD TESTS
	 */
	
	@Test
	public void deleteArtistTest_Artist_in_db() {
		
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		session.openCurrentSession();
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		
		
		session.openCurrentSession();
		String obtainedId = artistDAO.getArtist(name, surname, nickname).getId();
		session.closeCurrentSession();
		
		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.deleteArtist(obtainedId);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		boolean artistExistsinBd = artistDAO.existArtist(name, surname, nickname); 
		session.closeCurrentSession();	
		
		// Check that in db the artist with id obtainedId was deleted
		assertTrue(successfulOperation);
		assertTrue(!artistExistsinBd);
	}
	
}
