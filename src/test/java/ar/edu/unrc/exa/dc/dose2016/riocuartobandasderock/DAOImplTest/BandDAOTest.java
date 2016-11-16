package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;




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


public class BandDAOTest {

	private BandDAO bandDAO;
	private Session session;
	
	@Before
	public void setUp(){
		session = SessionManager.getInstance().openSession();
		bandDAO = new BandDaoImpl(session);
	}
	
	/*
	 * CREATE BAND METHOD TESTS
	 */
	
	@Test
	public void createBandTest_Artist_not_in_db() {
		
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		while(bandDAO.existBand(name)){
			name+="a";
		}
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add abandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name,genre);
		session.getTransaction().commit();
		
		
		bandList.addAll(bandDAO.findByName(bandToAdd.getName()));		
		
		List<Band> result = new LinkedList<>();
		
		for(Band currentBand: bandList){
			// Add in result an band if it is equals to bandToAdd
			if (currentBand.equals(bandToAdd)){
				result.add(currentBand);
			}
		}
		
		// Check that in db there is only 1 artist with artistToAdd information
		assertTrue(result.size() == 1);
		assertTrue(result.get(0).equals(bandToAdd));
		
		session.close();
	}
	
	
	@Test
	public void createBandTest_Band_in_db() {
		
		String name = "a";
		String genre = "b";
		
		
		
		while(bandDAO.existBand(name)){
			name+="a";
		}
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db for first time
		session.beginTransaction();
		bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		
		// Add bandToAdd in db for second time
		session.beginTransaction();
		boolean successfulOperation = bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		assertTrue(!successfulOperation);
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_null_name() {
		String name = null;
		String genre = "b";
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_null_surname() {
		String name = "a";
		String genre = null;
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_null_nickname() {
		String name = "a";
		String genre = "b";
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_empty_fields() {
		String name = "";
		String genre = "";
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		session.close();
	}
	
	
	/*
	 * FIND BY NAME METHOD TESTS
	 */
	
	@Test
	public void findByName_Band_in_db() {
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		while(bandDAO.existBand(name)){
			name+="a";
		}
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name,genre);
		session.getTransaction().commit();
		
		List<Band> obtained = bandDAO.findByName(name);
		
		assertEquals(obtained.get(0).getName(),name);
		session.close();
	}
	
	@Test
	public void findByName_Band_not_in_db() {
		
		String name = "i_will_not_find_u";
		String genre = "";
		
		
		while(bandDAO.existBand(name)){
			name+="a";
		}
		List<Band> obtained = bandDAO.findByName(name);
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_null_name() {
		
		String name = null;
		
		List<Band> obtained = bandDAO.findByName(name);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_empty_name() {
		
		String name = "";
		
		List<Band> obtained = bandDAO.findByName(name);
	}
	
	
	
	
	
	/*
	 * FIND BY genre METHOD TESTS
	 */
	
	@Test
	public void findByGenre_Artist_in_db() {
		List<Band> bandtList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		while(bandDAO.existBand(name)){
			name+="a";
		}
		
		// Create the artist to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name,genre);
		session.getTransaction().commit();
		
		 
		List<Band> obtained = bandDAO.findByGenre(genre);
		 
		
		assertEquals(obtained.get(0).getName(),name);
		session.close();
	}
	
	@Test
	public void findByGenre_Band_not_in_db() {
		
		String name = "";
		String genre = "rock";
		
		
		 
		while(bandDAO.existBand(name)){
			name+="a";
		}
		 
		
		 
		List<Band> obtained = bandDAO.findByGenre(genre);
		 
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_null_genre() {
		
		String genre = null;
		
		 
		List<Band> obtained = bandDAO.findByGenre(genre);
		 
	}
	
	
	
	
	
	/*
	 * UPDATE BAND METHOD TESTS
	 */
	
	@Test
	public void updateBandTest_Band_in_db() {
		
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		 
		while(bandDAO.existBand(name)){
			name+="a";
		}
		 
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name, genre);
		session.getTransaction().commit();
		
		
		String obtainedId = bandDAO.getBand(name).getId();
		 
		
		// If obtained id is -1 then the artist isnt created in DB
		assertTrue(obtainedId != "-1");
		
		// Obtain a new name to update the artist that isnt in BD already
		 
		String updatedName = name;
		String updatedGenre = "updatedGenre";
		while(bandDAO.existBand(updatedName)){
			updatedName+="a";
		}
		 
		
		session.beginTransaction();
		bandDAO.updateBand(obtainedId , updatedName, updatedGenre);
		session.getTransaction().commit();
		
		 
		Band bandUpdated = bandDAO.findById(obtainedId); 
		
		
		// Check that in db the band with id obtainedId was updated
		assertTrue(bandUpdated.getName().equals(updatedName));
		assertTrue(bandUpdated.getGenre().equals(updatedGenre));
		session.close();
	}
	
	
	/*
	 * DELETE BAND METHOD TESTS
	 */
	
	@Test
	public void deleteBandTest_Band_in_db() {
		
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		 
		while(bandDAO.existBand(name)){
			name+="a";
		}
		 
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.beginTransaction();
		bandDAO.createBand(name,genre);
		session.getTransaction().commit();
		
		
		 
		String obtainedId = bandDAO.getBand(name).getId();
		 
		
		// If obtained id is -1 then the band isnt created in DB
		assertTrue(obtainedId != "-1");
		
		session.beginTransaction();
		boolean successfulOperation = bandDAO.deleteBand(obtainedId);
		session.getTransaction().commit();
		
		 
		boolean bandExistsinBd = bandDAO.existBand(name); 
		 	
		
		// Check that in db the artist with id obtainedId was deleted
		assertTrue(successfulOperation);
		assertTrue(!bandExistsinBd);
		session.close();
	}
	
}