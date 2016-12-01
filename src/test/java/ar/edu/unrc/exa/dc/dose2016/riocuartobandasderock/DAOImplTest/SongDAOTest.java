
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
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;


public class SongDAOTest {
	
	private SongDAO songDao;
	private Session session;
	
	@Before
	public void setUp(){
		session = SessionManager.getInstance().openSession();
		songDao = new SongDaoImpl(session);
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
    	
    	session.beginTransaction();
		boolean created = songDao.addSong("name", 100);
		session.getTransaction().commit();
		
		assertTrue(created);    	
    	
    }	
    
	@Test(expected = IllegalArgumentException.class)
	public void findByEmptyId() {
		songDao.findById("");
	}
	
	 @Test
	 public void updateTest(){
	    	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	session.save(s);
	    	s.setName("eman");
	    	s.setDuration(10);
			boolean updated = songDao.updateSong(s);
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
