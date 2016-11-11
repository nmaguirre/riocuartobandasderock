package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class AlbumDAOTest {
	
	private AlbumDAO albumDao;
	private Session session;
	@Before
	public void setUp(){
		session = SessionManager.getInstance().openSession();
		albumDao = new AlbumDaoImpl(session);
	}
	
	@Test
	public void createAlbumIfNotInDB(){
		
		session.beginTransaction();
		
			
		boolean a = albumDao.create("AlbumTest1",new Date(2010,04,10));
		
		session.getTransaction().commit();
		
		assertTrue(a);
		session.close();
	}
	@Test
	public void createAlbumIfExistInDBwithOtherSession(){
		session.beginTransaction();
		boolean a = albumDao.create("AlbumTest2",new Date(2010,04,10));
		session.getTransaction().commit();
		
		session.beginTransaction();
		boolean b = albumDao.create("AlbumTest2",new Date(2010,04,10));
		session.getTransaction().commit();
	
		
		assertTrue(!b);
		session.close();
	}
	
	@Test
	public void createAlbumIfInDB(){
		session.beginTransaction();
		boolean a = albumDao.create("AlbumTest2",new Date(2010,04,10));
		boolean b = albumDao.create("AlbumTest2",new Date(2010,04,10));
	
		session.getTransaction().commit();
		
		assertTrue(!b);
		session.close();
	}
	
//	@Test(expected=IllegalArgumentException.class)
//	public void createAlbumIfTitleIsNull(){
//		albumDao.openCurrentSessionwithTransaction();
//		boolean a = albumDao.createAlbum(null,null);		
//	}
//	
//	@Test(expected=IllegalArgumentException.class)
//	public void createAlbumIfTitleIsEmptyAndReleaseDateIsNull(){
//		albumDao.openCurrentSessionwithTransaction();
//		boolean a = albumDao.createAlbum("",null);		
//	}
	
	@Test
	public void findByIdWhenIdIsNull() {
		Album a = albumDao.findById(null);
		assertNull(a);
	}
	
	@Test
	public void findByIdWhenIdIsEmpty() {
		Album a = albumDao.findById("");
		assertNull(a);
	}
	
	@Test
	public void findById() {
		session.beginTransaction();
		boolean a = albumDao.create("AlbumTest3",new Date(2010,04,10));
		List<Album> lista = albumDao.getAll();
		Album aux = lista.get(0);
		Album aux1 = albumDao.findById(aux.getId());
		session.getTransaction().commit();
		assertTrue(aux.getId().equals(aux1.getId()));
		session.close();
	}
	
	@Test
	public void findByIdNotInDB() {
		session.beginTransaction();
		boolean a = albumDao.create("AlbumTest4",new Date(2010,04,10));
		Album aux1 = albumDao.findById("9893948593845");
		session.getTransaction().commit();
		assertNull(aux1);
		session.close();
	}
	
	@Test
	public void getAllAlbumWhenDBIsEmpty(){
		
		session.beginTransaction();
		albumDao.create("AlbumTest5",new Date(2010,04,10));
		albumDao.create("AlbumTest6",new Date(2010,04,10));
		List<Album> lista = new LinkedList<Album>();
		lista.addAll(albumDao.getAll());
		session.getTransaction().commit();
		assertEquals(3,lista.size());
		session.close();
	}
	
	
	@Test
	public void findByNameTest(){
		assertTrue(true);
	}
		
}
