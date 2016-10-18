package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;


public class ArtistDAOTest {

	private ArtistDAO artistDAO;
	
	@Before
	public void setUp(){
		artistDAO = new ArtistDaoImpl();
		artistDAO.openCurrentSession();
	}
	
	@After 
	public void finish() {
		artistDAO.closeCurrentSession();
	}
	
	@Test
	public void addArtistTest_Artist_not_in_db() {
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist("Luca", "Prodan", "");
		
		List<Artist> artistList = new LinkedList<>();
		// Search all artist with Luca as name
		artistList.addAll(artistDAO.findByName(artistToAdd.getName()));
		
		// Check that the artist to add is not in db
		for(Artist currentArtist: artistList){
			assertTrue(!currentArtist.equals(artistToAdd));
		}

		// Add artistToAdd in db
		//artistDAO.createArtist("Luca","Prodan","");
		
		artistList = new LinkedList<>();
		artistList.addAll(artistDAO.findByName(artistToAdd.getName()));
		for(Artist currentArtist: artistList){
			// Remove all artist that arent equals to artistToAdd
			if (!currentArtist.equals(artistToAdd)){
				artistList.remove(currentArtist);
			}
		}
		
		// Check that in db there is only 1 artist with artistToAdd information
		assertEquals(artistList.size(),1);
		assertTrue(artistList.get(0).equals(artistToAdd));
		
	}
}
