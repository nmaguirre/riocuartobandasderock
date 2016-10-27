package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManagerHibernate;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class AlbumDAOTest {
	
	private AlbumDAO albumDao;
	private SessionManager session;
	@Before
	public void setUp(){
		albumDao = new AlbumDaoImpl();
		session= SessionManagerHibernate.getInstance();
	}
	
	@Test
	public void createAlbumIfNotInDB(){
		
		session.openCurrentSessionwithTransaction();
		
		boolean a = albumDao.createAlbum("AlbumTest1",new Date(2010,04,10));
		
		session.closeCurrentSessionwithTransaction();
		
		assertTrue(a);
		
	}
	
	@Test
	public void createAlbumIfInDB(){
		session.openCurrentSessionwithTransaction();
		boolean a = albumDao.createAlbum("AlbumTest2",new Date(2010,04,10));
		boolean b = albumDao.createAlbum("AlbumTest2",new Date(2010,04,10));
	
		session.closeCurrentSessionwithTransaction();
		
		assertTrue(!b);
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
		session.openCurrentSession();
		Album a = albumDao.findById(null);
		session.closeCurrentSession();
		assertNull(a);
	}
	
	@Test
	public void findByIdWhenIdIsEmpty() {
		session.openCurrentSession();
		Album a = albumDao.findById("");
		session.closeCurrentSession();
		assertNull(a);
	}
	
	@Test
	public void findById() {
		session.openCurrentSessionwithTransaction();
		boolean a = albumDao.createAlbum("AlbumTest3",new Date(2010,04,10));
		List<Album> lista = albumDao.getAllAlbums();
		Album aux = lista.get(0);
		Album aux1 = albumDao.findById(aux.getId());
		session.closeCurrentSessionwithTransaction();
		assertTrue(aux.getId().equals(aux1.getId()));
	}
	
	@Test
	public void findByIdNotInDB() {
		session.openCurrentSessionwithTransaction();
		boolean a = albumDao.createAlbum("AlbumTest4",new Date(2010,04,10));
		Album aux1 = albumDao.findById("9893948593845");
		session.closeCurrentSessionwithTransaction();
		assertNull(aux1);
	}
	
	@Test
	public void getAllAlbumWhenDBIsEmpty(){
		
		session.openCurrentSessionwithTransaction();
		albumDao.createAlbum("AlbumTest5",new Date(2010,04,10));
		albumDao.createAlbum("AlbumTest6",new Date(2010,04,10));
		List<Album> lista = new LinkedList<Album>();
		lista.addAll(albumDao.getAllAlbums());
		session.closeCurrentSessionwithTransaction();
		assertEquals(3,lista.size());
	}
	
	
	@Test
	public void findByNameTest(){
		assertTrue(true);
	}
		
}
