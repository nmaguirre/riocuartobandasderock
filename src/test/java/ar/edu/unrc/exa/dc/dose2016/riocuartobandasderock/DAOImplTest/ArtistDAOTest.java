package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
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
		
		session.close();
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
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db for first time
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		
		// Add artistToAdd in db for second time
		session.beginTransaction();
		boolean successfulOperation = artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		assertTrue(!successfulOperation);
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_name() {
		String name = null;
		String surname = "b";
		String nickname = "";
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_surname() {
		String name = "a";
		String surname = null;
		String nickname = "";
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_null_nickname() {
		String name = "a";
		String surname = "b";
		String nickname = null;
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createArtistTest_Artist_with_empty_fields() {
		String name = "";
		String surname = "";
		String nickname = "";
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name, surname, nickname);
		session.getTransaction().commit();
		session.close();
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
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		List<Artist> obtained = artistDAO.findByName(name);
		
		assertEquals(obtained.get(0).getName(),name);
		session.close();
	}
	
	@Test
	public void findByName_Artist_not_in_db() {
		
		String name = "i_will_not_find_u";
		String surname = "";
		String nickname = "";
		
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		List<Artist> obtained = artistDAO.findByName(name);
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_null_name() {
		
		String name = null;
		
		List<Artist> obtained = artistDAO.findByName(name);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_empty_name() {
		
		String name = "";
		
		List<Artist> obtained = artistDAO.findByName(name);
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
		
		while(artistDAO.existArtist(name,surname,nickname)){
			surname+="b";
		}
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		List<Artist> obtained = artistDAO.findBySurname(surname);
		
		assertEquals(obtained.get(0).getSurname(),surname);
		session.close();
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

		List<Artist> obtained = artistDAO.findBySurname(surname);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findBySurname_empty_surname() {
		
		String surname = "";
		

		List<Artist> obtained = artistDAO.findBySurname(surname);
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
		
		while(artistDAO.existArtist(name,surname,nickname)){
			nickname+="a";
		}
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		 
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		 
		
		assertEquals(obtained.get(0).getName(),name);
		session.close();
	}
	
	@Test
	public void findByNnickname_Artist_not_in_db() {
		
		String name = "";
		String surname = "";
		String nickname = "i_will_not_find_u";
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			nickname+="a";
		}
		 
		
		 
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		 
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByNickname_null_nickname() {
		
		String nickname = null;
		
		 
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		 
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByNickname_empty_nickname() {
		
		String nickname = "";
		
		 
		List<Artist> obtained = artistDAO.findByNickname(nickname);
		 
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
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		 
		String obtainedId = artistDAO.getArtist(name, surname, nickname).getId();
		 
		
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
		
		 
		Artist artistUpdated = artistDAO.findById(obtainedId); 
		 	
		
		// Check that in db the artist with id obtainedId was updated
		assertTrue(artistUpdated.getName().equals(updatedName));
		assertTrue(artistUpdated.getSurname().equals(updatedSurname));
		assertTrue(artistUpdated.getNickname().equals(updatedNickname));
		session.close();
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
		
		 
		while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		}
		 
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db
		session.beginTransaction();
		artistDAO.createArtist(name,surname,nickname);
		session.getTransaction().commit();
		
		
		 
		String obtainedId = artistDAO.getArtist(name, surname, nickname).getId();
		 
		
		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		session.beginTransaction();
		boolean successfulOperation = artistDAO.deleteArtist(obtainedId);
		session.getTransaction().commit();
		
		 
		boolean artistExistsinBd = artistDAO.existArtist(name, surname, nickname); 
		 	
		
		// Check that in db the artist with id obtainedId was deleted
		assertTrue(successfulOperation);
		assertTrue(!artistExistsinBd);
		session.close();
	}
	
}
