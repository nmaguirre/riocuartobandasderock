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
		bandList.addAll(bandDAO.findBandByName(bandToAdd.getName()));
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
		
	}
	
	
	
	
	
	/*@Test
	public void getBandsTestCase(){
		Band bandInst1= new Band();
		bandInst1.setName("Soda Stereo");
		bandInst1.setId("1");
		new Expectations(){
			{
				bandDao.getBand("1");
				returns (bandInst1);
			}
		};
		assertEquals(bandInst1,bandDao.getBand("1")  );
	}*/
}
