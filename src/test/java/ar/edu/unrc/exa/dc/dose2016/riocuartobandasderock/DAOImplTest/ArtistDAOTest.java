package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;


public class ArtistDAOTest {

	private ArtistDAO artistDAO;
	private Session session;
	
	@Before
	public void setUp(){
		session = SessionManager.getInstance().openSession();
		artistDAO = new ArtistDaoImpl(session);
	}
	
	@After
	public void closeSession(){
		session.close();
	};
	
	/*
	 * CREATE ARTIST METHOD TESTS
	 */
	
	@Test
	public void createArtistTest_Artist_not_in_db() {
		List<Artist> artistList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		
		artistList.addAll(artistDAO.findByName(artistToAdd.getName()));		
		
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
		
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db for first time
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		
		// Add artistToAdd in db for second time
		session.beginTransaction();
		boolean successfulOperation = artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		assertTrue(!successfulOperation);
		 
	}
	
	@Test
	public void createArtistTest_Artist_in_db_cases() {
		
		String name = "Pablo";
		String surname = "b";
		String nickname = "";
		
		
		while(artistDAO.existArtist(name,surname,nickname)){
			surname+="b";
		}
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		
		String sameNameIgnoringCase = "pablo";
		
		// Add artistToAdd in db
		session.beginTransaction();
		boolean successfulOperation = artistDAO.createArtist(sameNameIgnoringCase, surname, nickname);
		session.getTransaction().commit();
		
		assertTrue(!successfulOperation); 
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_name() {
		String name = null;
		String surname = "b";
		String nickname = "";
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_surname() {
		String name = "a";
		String surname = null;
		String nickname = "";
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_nickname() {
		String name = "a";
		String surname = "b";
		String nickname = null;
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_empty_fields() {
		String name = "";
		String surname = "";
		String nickname = "";
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	/*
	 * FIND BY NAME METHOD TESTS
	 */
	
	@Test
	public void findByName_Artist_in_db() {
		List<Artist> obtained = new LinkedList<>();
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		obtained = artistDAO.findByName(name);
		
		assertEquals(obtained.get(0).getName(),name);
	}
	
	@Test
	public void findByName_Artist_not_in_db() {
		List<Artist> obtainedList = new LinkedList<>();
		String name = "i_will_not_find_u";
		String surname = "";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		obtainedList = artistDAO.findByName(name);
		
		assertEquals(obtainedList.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_null_name() {
		String name = null;
		artistDAO.findByName(name);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_empty_name() {
		String name = "";
		artistDAO.findByName(name);
	}
	
	
	
	/*
	 * FIND BY ID METHOD TESTS
	 */
	
	
	@Test
	public void findId_Artist_in_db() {
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		String artistId = artistDAO.getArtist(name, surname, nickname).get(0).getId();
		
		List<Artist> obtainedArtistList = artistDAO.findById(artistId);
		assertTrue(obtainedArtistList.size() == 1);
		
		Artist obtainedArtist = obtainedArtistList.get(0);
		
		assertTrue(obtainedArtist != null);
		assertEquals(obtainedArtist.getName(), artistToAdd.getName());
		assertEquals(obtainedArtist.getSurname(), artistToAdd.getSurname());
		assertEquals(obtainedArtist.getNickname(), artistToAdd.getNickname());
	}
	
	@Test
	public void findById_Artist_not_in_db() {
		List<Artist> obtainedArtist = artistDAO.findById("-1");
		assertTrue(obtainedArtist.isEmpty());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_null_id() {
		artistDAO.findById(null);
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_empty_Id() {
		artistDAO.findById("");
	}
	
	
	/*
	 * FIND BY SURNAME METHOD TESTS
	 */
	
	@Test
	public void findBySurname_Artist_in_db() {
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			surname+="b";
		}
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		List<Artist> obtained = artistDAO.findBySurname(surname);
		
		assertEquals(obtained.get(0).getSurname(),surname);
	}
	
	@Test
	public void findBySurname_Artist_not_in_db() {
		
		String name = "";
		String surname = "i_will_not_find_u";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			surname+="a";
		}
		List<Artist> obtained = artistDAO.findBySurname(surname);
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findBySurname_null_surname() {
		String surname = null;
		artistDAO.findBySurname(surname);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findBySurname_empty_surname() {
		String surname = "";
		artistDAO.findBySurname(surname);
	}
	
	
	/*
	 * FIND BY NICKNAME METHOD TESTS
	 */
	
	@Test
	public void findByNickname_Artist_in_db() {
		List<Artist> obtainedList = new LinkedList<>();
		
		String name = "a";
		String surname = "b";
		String nickname = "c";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			nickname+="a";
		}
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		obtainedList = artistDAO.findByNickname(nickname);
		assertEquals(obtainedList.get(0).getName(),name);
	}
	
	@Test
	public void findByNnickname_Artist_not_in_db() {
		List<Artist> obtainedList = new LinkedList<>();
		String name = "";
		String surname = "";
		String nickname = "i_will_not_find_u";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			nickname+="a";
		}
		 
		obtainedList = artistDAO.findByNickname(nickname);
		assertEquals(obtainedList.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByNickname_null_nickname() {
		
		String nickname = null;
		 
		artistDAO.findByNickname(nickname);
		 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findByNickname_empty_nickname() {
		String nickname = "";
		artistDAO.findByNickname(nickname);
	}
	
	
	/*
	 * UPDATE ARTIST METHOD TESTS
	 */
	
	@Test
	public void updateArtistTest_Artist_in_db() {

		String name = "a";
		String surname = "b";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		Artist artistToAdd = new Artist(name,surname,nickname);
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		List<Artist> obtainedListArtist = artistDAO.getArtist(name, surname, nickname);
		
		//check if the result list has only artist
		assertTrue(obtainedListArtist.size() == 1); 
		String obtainedId = obtainedListArtist.get(0).getId();

		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		// Obtain a new name to update the artist that isnt in BD already
		String updatedName = name;
		String updatedSurname = "updatedSurname";
		String updatedNickname = "updatedNickname";
		while(artistDAO.existArtist(updatedName,updatedSurname,updatedNickname)){
			updatedName+="a";
		}
		 
		session.beginTransaction();
		artistDAO.updateArtist(obtainedId, updatedName, updatedSurname, updatedNickname);
		session.getTransaction().commit();
		 
		Artist artistUpdated = artistDAO.findById(obtainedId).get(0); 
		
		// Check that in db the artist with id obtainedId was updated
		assertTrue(artistToAdd.equals(artistUpdated));
	}
	
	
	@Test
	public void updateArtistTest_Artist_already_in_db() {
		
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
		
		List<Artist> obtainedListArtist = artistDAO.getArtist(name, surname, nickname);
		
		//check if the result list has only artist
		assertTrue(obtainedListArtist.size() == 1); 
		String obtainedId = obtainedListArtist.get(0).getId();
		
		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		session.beginTransaction();
		boolean successfulOperation = artistDAO.updateArtist(obtainedId,name,surname,nickname);
		session.getTransaction().commit();
		
		// Check that update fail because already exists artist with the info to update		
		assertTrue(!successfulOperation);
	}
	
	
	@Test
	public void updateArtistTest_Artist_not_in_db() {
		String id = "-1";
		String name = "a";
		String surname = "b";
		String nickname = "";
			
		session.beginTransaction();
		boolean successfulOperation = artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
				
		// Check that an update fail with not artist id in DB		
		assertTrue(!successfulOperation);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_id() {
		String id = null;
		String name = "a";
		String surname = "b";
		String nickname = "";
			
		session.beginTransaction();
		artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_name() {
		String id = "-1";
		String name = null;
		String surname = "b";
		String nickname = "";
			
		session.beginTransaction();
		artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_surname() {
		String id = "1";
		String name = "a";
		String surname = null;
		String nickname = "";
			
		session.beginTransaction();
		artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_null_nickname() {
		String id = "1";
		String name = "a";
		String surname = "b";
		String nickname = null;
			
		session.beginTransaction();
		artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_empty_id() {
		String id = "";
		String name = "a";
		String surname = "b";
		String nickname = "";
			
		session.beginTransaction();
		artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void updateArtistTest_empty_fields() {
		
		String id = "2";
		String name = "";
		String surname = "";
		String nickname = "";
			
		session.beginTransaction();
		artistDAO.updateArtist(id, name, surname, nickname);
		session.getTransaction().commit();
	}
	
	
	/*
	 * DELETE ARTIST METHOD TESTS
	 */
	
	@Test
	public void deleteArtistTest_Artist_in_db() {
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 
		// Create the artist to add in db
		new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		List<Artist> obtainedListArtist = artistDAO.getArtist(name, surname, nickname);
		
		//check if the result list has only artist
		assertTrue(obtainedListArtist.size() == 1); 
		String obtainedId = obtainedListArtist.get(0).getId();
		
		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		session.beginTransaction();
		boolean successfulOperation = artistDAO.deleteArtist(obtainedId);
		session.getTransaction().commit();
		
		 
		boolean artistExistsinBd = artistDAO.existArtist(name, surname, nickname); 
		 	
		
		// Check that in db the artist with id obtainedId was deleted
		assertTrue(successfulOperation);
		assertTrue(!artistExistsinBd);
	}
	
	
	@Test
	public void deleteArtistTest_Artist_not_in_db() {
		String id = "-1";
		session.beginTransaction();
		boolean successfulOperation = artistDAO.deleteArtist(id);
		session.getTransaction().commit();
				
		// Check that an update fail with not artist id in DB		
		assertTrue(!successfulOperation);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteArtistTest_null_id() {
		String id = null;
		session.beginTransaction();
		artistDAO.deleteArtist(id);
		session.getTransaction().commit();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteArtistTest_empty_id() {
		String id = "";
		session.beginTransaction();
		artistDAO.deleteArtist(id);
		session.getTransaction().commit();
	}
	
}
