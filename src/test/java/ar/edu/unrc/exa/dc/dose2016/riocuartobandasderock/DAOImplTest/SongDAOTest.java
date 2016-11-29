
package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;


import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SongDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;


public class SongDAOTest {
	
	private SongDAO songDao;
	private Session session;
	private Album alb;
	private Band band;
	
	@Before
	public void setUp(){
		session = SessionManager.getInstance().openSession();
		songDao = new SongDaoImpl(session);
		alb = new Album("albName");
		band = new Band("bandName","Rock");
    	session.beginTransaction();
    	session.save(band);
    	alb.setBand(band);
    	session.save(alb);
    	session.getTransaction().commit();
	}
	
	@After
	public void closeSession(){
		session.close();
	};

            
    @Test
	public void findByIdTest() {
    	
    	Song song = new Song("name",100);
    	session.beginTransaction();
		session.save(song);
		session.getTransaction().commit();
		
		String id = song.getId();

		assertEquals(song,songDao.findById(id));
	}
        
    
    @Test
	public void findByNameTest() {
    	
       	Song song = new Song("name",100);
    	session.beginTransaction();
		session.save(song);
		session.getTransaction().commit();
		
		List<Song> listOfSongs = songDao.findByName("name");
		
		for(Song s: listOfSongs){
			assertEquals("name", s.getName());
		} 
	}
        
    @Test
	public void findByDurationTest() {
    	Song song = new Song("name",100);
    	session.beginTransaction();
		session.save(song);
		session.getTransaction().commit();
		
		List<Song> listOfSongs = songDao.findByDuration(100);
		
		for(Song s: listOfSongs){
			assertEquals(100, s.getDuration());
		} 

	}
    
    
    @Test
    public void addTest(){
    	
    	String albumName = alb.getTitle();    	
    	
    	session.beginTransaction();
		boolean created = songDao.addSong("name", 100, albumName);
		session.getTransaction().commit();
		
		assertTrue(created);    	
    	
    }	
    
	@Test(expected = IllegalArgumentException.class)
	public void findByEmptyId() {
		songDao.findById("");
	}
	
	 @Test
	 public void updateTest(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	session.save(s);
	    	String id = s.getId();
			boolean updated = songDao.updateSong(id, "eman", 10, albumName);
			session.getTransaction().commit();
			
			assertTrue(updated);
			assertEquals("eman",s.getName());
			assertEquals(10,s.getDuration());	    	
	    	
	 }	
	 
	 
	 @Test
	 public void removeTest(){
	    	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	session.save(s);
	    	String id = s.getId();
			boolean removed = songDao.removeSong(id);
			session.getTransaction().commit();
			
			assertTrue(removed);
   	
	    	
	 }
	 
	 
    
    
    
}
