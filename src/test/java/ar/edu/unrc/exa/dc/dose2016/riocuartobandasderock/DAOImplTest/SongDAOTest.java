
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
	public void findById() {
    	
    	Song song = new Song("name",100);
    	session.beginTransaction();
		session.save(song);
		session.getTransaction().commit();
		
		String id = song.getId();

		assertEquals(song,songDao.findById(id));
	}
        
    
    @Test(expected = IllegalArgumentException.class)
	public void findByNullId() {
    	songDao.findById(null);
	}
    
	@Test(expected = IllegalArgumentException.class)
	public void findByEmptyId() {
		songDao.findById("");
	}
        
    
    @Test
	public void findByName() {
    	
       	Song song = new Song("name",100);
    	session.beginTransaction();
		session.save(song);
		session.getTransaction().commit();
		
		List<Song> listOfSongs = songDao.findByName("name");
		
		for(Song s: listOfSongs){
			assertEquals("name", s.getName());
		} 
	}
    
	@Test(expected = IllegalArgumentException.class)
	public void findByNullName() {
		songDao.findByName(null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void findByEmptyName() {
		songDao.findByName("");
	}
        
    @Test
	public void findByDuration() {
    	Song song = new Song("name",100);
    	session.beginTransaction();
		session.save(song);
		session.getTransaction().commit();
		
		List<Song> listOfSongs = songDao.findByDuration(100);
		
		for(Song s: listOfSongs){
			assertEquals(100, s.getDuration());
		} 

	}
    
	@Test(expected = IllegalArgumentException.class)
	public void findByNullDuration() {
		songDao.findByDuration(null);
	}
    
    
    @Test
    public void addSongWithCorrectParameters(){
    	
    	String albumName = alb.getTitle();    	
    	
    	session.beginTransaction();
		boolean created = songDao.addSong("name", 100, albumName);
		session.getTransaction().commit();
		
		assertTrue(created);    	
    	
    }	
    
    @Test
    public void addSongWithInexistentAlbum(){
    	
    	session.beginTransaction();
		boolean created = songDao.addSong("name", 100, "malbum");
		session.getTransaction().commit();
		
		assertFalse(created);    	
    	
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void addSongWithNullName(){
    	
    	String albumName = alb.getTitle();    	
    	
    	session.beginTransaction();
		songDao.addSong(null, 100, albumName);
		session.getTransaction().commit();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addSongWithEmptyName(){
    	
    	String albumName = alb.getTitle();    	
    	
    	session.beginTransaction();
		songDao.addSong("", 100, albumName);
		session.getTransaction().commit();
    }	
    
    @Test(expected = IllegalArgumentException.class)
    public void addSongWithNullDuration(){
    	
    	String albumName = alb.getTitle();    	
    	
    	session.beginTransaction();
		songDao.addSong("name", null, albumName);
		session.getTransaction().commit();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addSongWithNullAlbumName(){   	
    	
    	session.beginTransaction();
		songDao.addSong("name", 100, null);
		session.getTransaction().commit();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addSongWithEmptyAlbumName(){   	
    	
    	session.beginTransaction();
		songDao.addSong("name", 100, "");
		session.getTransaction().commit();
    }


    
	
	@Test
	public void updateSongWithCorrectParameters(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			boolean updated = songDao.updateSong(id, "eman", 10, albumName);
			session.getTransaction().commit();
			
			assertTrue(updated);
			assertEquals("eman",s.getName());
			assertEquals(10,s.getDuration());	    	
	    	
	 }	
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void updateSongWithNullName(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			songDao.updateSong(id, null, 10, albumName);
			session.getTransaction().commit();
			    	
	    	
	 }	
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void updateSongWithEmptyName(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			songDao.updateSong(id, "", 10, albumName);
			session.getTransaction().commit();
			    	
	    	
	 }
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void updateSongWithNullDuration(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			songDao.updateSong(id, "eman", null , albumName);
			session.getTransaction().commit();
			    	
	    	
	 }
	 
	 @Test(expected = NullPointerException.class)
	 public void updateSongWithNullAlbumName(){
	    	  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			songDao.updateSong(id, "eman", 10 ,null);
			session.getTransaction().commit();
			    	
	    	
	 }
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void updateSongWithEmptyAlbumName(){
	    	  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			songDao.updateSong(id, "eman", 10 , "");
			session.getTransaction().commit();
			    	
	    	
	 }
	 
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void updateSongWithNullId(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
			songDao.updateSong(null, "eman", 10, albumName);
			session.getTransaction().commit();
			    	
	    	
	 }	
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void updateSongWithEmptyId(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
			songDao.updateSong("", "eman", 10, albumName);
			session.getTransaction().commit();
			    	
	    	
	 }
	 

	 public void updateSongWithInexistentId(){
	    	
		 	String albumName = alb.getTitle();  
		 	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	s.setAlbum(alb);
	    	session.save(s);
	    	String id = s.getId();
			boolean updated = songDao.updateSong(id+"a", "eman", 10, albumName);
			session.getTransaction().commit();
			
			assertFalse(updated);
			    	
	    	
	 }
	 
	 
	 @Test
	 public void removeExistentSong(){
	    	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	session.save(s);
	    	String id = s.getId();
			boolean removed = songDao.removeSong(id);
			session.getTransaction().commit();
			
			assertTrue(removed);
   	
	    	
	 }
	 
	 @Test
	 public void removeInexistentSong(){
	    	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	session.save(s);
	    	String id = s.getId();
			boolean removed = songDao.removeSong(id+"a");
			session.getTransaction().commit();
			
			assertFalse(removed);
   	
	    	
	 }
	 
	 @Test
	 public void removeSongWithNullId(){
	    	
	    	session.beginTransaction();
	    	Song s = new Song("name", 100);
	    	session.save(s);
			boolean removed = songDao.removeSong(null);
			session.getTransaction().commit();
			
			assertFalse(removed);
   	
	    	
	 }
	 
	 
    
    
    
}
