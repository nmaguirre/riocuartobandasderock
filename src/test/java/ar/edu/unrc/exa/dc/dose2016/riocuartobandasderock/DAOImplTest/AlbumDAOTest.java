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
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class AlbumDAOTest {
	
	private AlbumDAO albumDao;
	
	@Before
	public void setUp(){
		albumDao = new AlbumDaoImpl();
	}
	
	@Test
	public void createAlbumIfNotInDB(){
		
		albumDao.openCurrentSessionwithTransaction();
		boolean a = albumDao.createAlbum("AlbumTest",new Date(2010,04,10));
		List<Album> lista = new LinkedList<Album>();
		lista.addAll(albumDao.getAllAlbums());
		System.out.println("Creacion simple:");
		for(int i=0;i<lista.size();i++){
			System.out.println(lista.get(i).getTitle());
		}
		albumDao.closeCurrentSessionwithTransaction();
		
		assertTrue(a);
		
	}
	
	@Test
	public void createAlbumIfInDB(){
		albumDao.openCurrentSessionwithTransaction();
		boolean a = albumDao.createAlbum("Tocando Fondo",new Date(2010,04,10));
		boolean b = albumDao.createAlbum("Tocando Fondo",new Date(2010,04,10));
		List<Album> lista = new LinkedList<Album>();
		lista.addAll(albumDao.getAllAlbums());
		System.out.println("Duplicado: ");
		for(int i=0;i<lista.size();i++){
			System.out.println(lista.get(i).getTitle());
		}
		albumDao.closeCurrentSessionwithTransaction();
		
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
//	
//	@Test
//	public void findByIdWhenIdIsNull() {
//		albumDao.openCurrentSession();
//		Album a = albumDao.findById(null);
//		albumDao.closeCurrentSession();
//		assertNull(a);
//	}
//	
//	@Test
//	public void findByIdWhenIdIsEmpty() {
//		albumDao.openCurrentSession();
//		Album a = albumDao.findById("");
//		albumDao.closeCurrentSession();
//		assertNull(a);
//	}
//	
//	@Test
//	public void findById() {
//		albumDao.openCurrentSessionwithTransaction();
//		boolean a = albumDao.createAlbum("Tocando Fondo",new Date(2010,04,10));
//		List<Album> lista = albumDao.getAllAlbums();
//		Album aux = lista.get(0);
//		Album aux1 = albumDao.findById(aux.getId());
//		albumDao.closeCurrentSessionwithTransaction();
//		assertTrue(aux.getId().equals(aux1.getId()));
//	}
//	
//	@Test
//	public void findByIdNotInDB() {
//		albumDao.openCurrentSessionwithTransaction();
//		boolean a = albumDao.createAlbum("Tocando Fondo",new Date(2010,04,10));
//		Album aux1 = albumDao.findById("9893948593845");
//		albumDao.closeCurrentSessionwithTransaction();
//		assertNull(aux1);
//	}
//	
//	@Test
//	public void getAllAlbumWhenDBIsEmpty(){
//		
//		albumDao.openCurrentSessionwithTransaction();
//		albumDao.createAlbum("Tocando Fondo",new Date(2010,04,10));
//		albumDao.createAlbum("5to Piso",new Date(2010,04,10));
//		List<Album> lista = new LinkedList<Album>();
//		lista.addAll(albumDao.getAllAlbums());
//		albumDao.closeCurrentSessionwithTransaction();
//		assertEquals(3,lista.size());
//	}
//	
//	
//	@Test
//	public void findByNameTest(){
//		assertTrue(true);
//	}
		
}
