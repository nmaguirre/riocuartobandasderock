package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

import mockit.Expectations;

import mockit.Mocked;

public class BandDAOTest {
	private BandDAO bandDAO;
	private SessionManager session;

	@Before
	public void setUp(){
		bandDAO = new BandDaoImpl();
		session = SessionManager.getInstance();
	}
	
	/*
	 * CREATE BAND METHOD TESTS
	 */
	
	@Test
	public void createBandTest_Band_not_in_db() {
		
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		session.openCurrentSession();
		while(bandDAO.existBand(name,genre)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the artist to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name,genre);
		session.closeCurrentSessionwithTransaction();
		
		
		session.openCurrentSession();
		bandList.addAll(bandDAO.findByName(bandToAdd.getName()));
		session.closeCurrentSession();
		
		
		List<Band> result = new LinkedList<>();
		
		for(Band currentBand: bandList){
			// Add in result an artist if it is equals to artistToAdd
			if (currentBand.equals(bandToAdd)){
				result.add(currentBand);
			}
		}
		
		// Check that in db there is only 1 artist with artistToAdd information
		assertTrue(result.size() == 1);
		assertTrue(result.get(0).equals(bandToAdd));
	}
	
	@Test
	public void createBandTest_Band_in_db() {
		
		String name = "a";
		String genre = "b";
		
		
		session.openCurrentSession();
		while(bandDAO.existBand(name,genre)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the band to add in db
		Band artistToAdd = new Band(name, genre);
		
		// Add bandToAdd in db for first time
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
		
		// Add bandToAdd in db for second time
		session.openCurrentSessionwithTransaction();
		boolean successfulOperation = bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
		
		assertTrue(!successfulOperation);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_null_name() {
		String name = null;
		String genre = "b";
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name,genre);
		
		// Add bandToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_null_surname() {
		String name = "a";
		String genre = null;
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_null_nickname() {
		String name = "a";
		String genre = "b";
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createBandTest_Band_with_empty_fields() {
		String name = "";
		String genre = "";
		
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
	}
	
	
	/*
	 * FIND BY NAME METHOD TESTS
	 */
	
	@Test
	public void findByName_Band_in_db() {
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		session.openCurrentSession();
		while(bandDAO.existBand(name, genre)){
			name+="a";
		}
		session.closeCurrentSession();
		
		// Create the band to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add bandToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByName(name);
		session.closeCurrentSession();
		
		assertEquals(obtained.get(0).getName(),name);
	}
	
	@Test
	public void findByName_Band_not_in_db() {
		
		String name = "i_will_not_find_u";
		String genre = "";
			
		session.openCurrentSession();
		while(bandDAO.existBand(name,genre)){
			name+="a";
		}
		session.closeCurrentSession();
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByName(name);
		session.closeCurrentSession();
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_null_name() {
		
		String name = null;
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByName(name);
		session.closeCurrentSession();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByName_empty_name() {
		
		String name = "";
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByName(name);
		session.closeCurrentSession();
	}
	
	
	/*
	 * FIND BY genre METHOD TESTS
	 */
	
	@Test
	public void findBygenre_Band_in_db() {
		List<Band> bandList = new LinkedList<>();
		
		String name = "a";
		String genre = "b";
		
		
		session.openCurrentSession();
		while(bandDAO.existBand(name, genre)){
			genre+="b";
		}
		session.closeCurrentSession();
		
		// Create the genre to add in db
		Band bandToAdd = new Band(name, genre);
		
		// Add artistToAdd in db
		session.openCurrentSessionwithTransaction();
		bandDAO.createBand(name, genre);
		session.closeCurrentSessionwithTransaction();
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByGenre(genre);
		session.closeCurrentSession();
		
		assertEquals(obtained.get(0).getGenre(),genre);
	}
	
	
	
	@Test
	public void findByGenre_Band_not_in_db() {
		
		String name = "";
		String genre = "rock";
		
		
		session.openCurrentSession();
		while(bandDAO.existBand(name,genre)){
			genre+="a";
		}
		session.closeCurrentSession();
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByGenre(genre);
		session.closeCurrentSession();
		
		assertEquals(obtained.size(), 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByGenre_null_surname() {
		
		String genre = null;
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByGenre(genre);
		session.closeCurrentSession();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByGenre_empty_surname() {
		
		String genre = "";
		
		session.openCurrentSession();
		List<Band> obtained = bandDAO.findByGenre(genre);
		session.closeCurrentSession();
	}
	
	
	
	
	
	
	
	
	
}
