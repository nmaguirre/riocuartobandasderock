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
	}
	
	@Test
	public void addArtistTest_Artist_not_in_db() {
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist("Luca", "Prodan", "");
		
		List<Artist> artistList = new LinkedList<>();
		
		// Search all artist with Luca as name
		artistDAO.openCurrentSession();
		artistList.addAll(artistDAO.findByName(artistToAdd.getName()));
		artistDAO.closeCurrentSession();
		
		// Check that the artist to add is not in db
		for(Artist currentArtist: artistList){
			assertTrue(!currentArtist.equals(artistToAdd));
		}

		// Add artistToAdd in db
		artistDAO.openCurrentSessionwithTransaction();
		artistDAO.createArtist("Luca","Prodan","");
		artistDAO.closeCurrentSessionwithTransaction();
		
		
		artistList = new LinkedList<>();
		
		artistDAO.openCurrentSession();
		artistList.addAll(artistDAO.findByName(artistToAdd.getName()));
		artistDAO.closeCurrentSession();
		
		
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
	public void addArtistTest_Artist_in_db() {
		
		String name = "a";
		String surname = "b";
		String nickname = "";
		
		//while(artistDAO.existArtist(name,surname,nickname)){
			name+="a";
		//}
		
		// Create the artist to add in db
		Artist artistToAdd = new Artist(name, surname, nickname);
		
		// Add artistToAdd in db for first time
		artistDAO.openCurrentSessionwithTransaction();
		artistDAO.createArtist(name, surname, nickname);
		artistDAO.closeCurrentSessionwithTransaction();
		
		// Add artistToAdd in db for second time
		artistDAO.openCurrentSessionwithTransaction();
		boolean successfulOperation = artistDAO.createArtist(name, surname, nickname);
		artistDAO.closeCurrentSessionwithTransaction();
		
		assertTrue(!successfulOperation);
		
	}
	
}
