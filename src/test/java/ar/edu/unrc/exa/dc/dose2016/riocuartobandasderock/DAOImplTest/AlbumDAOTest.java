package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
	
	private AlbumDaoImpl albumDao;
	private Session session;
	private Date exampleDate1;

	@Before
	public void setUp(){
		
		session = SessionManager.getInstance().openSession();
    	albumDao = new AlbumDaoImpl(session);
		exampleDate1 = new Date(2010,04,10);
	}
	
	@Test
	public void createAlbumIfNotInDB(){
		
		Transaction transaction = session.beginTransaction();
			
		boolean a = albumDao.create("AlbumTest1",new Date(2010,04,10));
		
		transaction.commit();
        
		assertTrue(a);
		session.close();
	}
	@Test
	public void createAlbumIfExistInDBwithOtherSession(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTest2",new Date(2010,04,10));
		boolean b = albumDao.create("AlbumTest2",new Date(2010,04,10));
		
		transaction.commit();
	
		assertFalse(b);
		session.close();
	}
	
	@Test
	public void createAlbumIfInDB(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTest2",new Date(2010,04,10));
		boolean b = albumDao.create("AlbumTest2",new Date(2010,04,10));
	
		transaction.commit();
		
		assertFalse(b);
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		
		boolean c = albumDao.create("AlbumTestAux",null);
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDateAndSameTitle(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTestAux00",new Date(2010,03,11));
		
		boolean b = albumDao.create("AlbumTestAux01",new Date(2010,07,11));
		
		boolean c = albumDao.create("AlbumTestAux01",null);
		
		transaction.commit();
		
		assertFalse(c);
		
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDateAndDistinctTitle(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTest00",new Date(2010,03,11));
		
		boolean b = albumDao.create("AlbumTest01",new Date(2010,07,11));
		
		boolean c = albumDao.create("AlbumTest02",null);
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumIfTitleIsNull(){
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create(null,null);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumIfTitleIsEmptyAndReleaseDateIsNull(){
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create("",null);	
	}
	
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
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create("AlbumTest3",new Date(2010,04,10));
		List<Album> lista = albumDao.getAll();
		Album aux = lista.get(0);
		Album aux1 = albumDao.findById(aux.getId());
		transaction.commit();
		assertTrue(aux.getId().equals(aux1.getId()));
		session.close();
	}
	
	@Test
	public void findByIdNotInDB() {
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create("AlbumTest4",new Date(2010,04,10));
		Album aux1 = albumDao.findById("9893948593845");
		transaction.commit();
		assertNull(aux1);
		session.close();
	}
	
	@Test
	public void getAllAlbumWhenDBIsEmpty(){
		
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTest5",new Date(2010,04,10));
		albumDao.create("AlbumTest6",new Date(2010,04,10));
		List<Album> lista = new LinkedList<Album>();
		lista.addAll(albumDao.getAll());
		transaction.commit();
		assertEquals(3,lista.size());
		session.close();
	}
	
	@Test
	public void deleteAlbum(){
		
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestToDelete", new Date(2016,01,01));
		
		List<Album> query = albumDao.findByTitle("AlbumTestToDelete");
		
		boolean isDelete = albumDao.delete(query.get(0).getId());		

		transaction.commit();
		assertTrue(isDelete);
	}
	
	@Test
	public void deleteAlbumIfNotExistInDB(){
		Transaction transaction = session.beginTransaction();
		boolean isDelete = albumDao.delete("OneIDThatNotExist");
		transaction.commit();
		session.close();
		assertFalse(isDelete);
	}
	
	@Test
	public void findByNameTest(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestToFind", exampleDate1);
		
		List<Album> queryAlbum = albumDao.findByTitle("AlbumTestToFind");
		
		transaction.commit();
		assertEquals("AlbumTestToFind", queryAlbum.get(0).getTitle() );
	}
	
	@Test
	public void updateAlbumWithTitleAndReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestToUpdate", exampleDate1);
		
		List<Album> toChange = albumDao.findByTitle("AlbumTestToUpdate");
		
		boolean res = albumDao.update(toChange.get(0).getId(), "AlbumTestSuccChange", new Date(2016,15,15));
		transaction.commit();
		session.close();
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithNullTitleAndReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate2",exampleDate1);
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate2");
		
		boolean res = albumDao.update(toChange1.get(0).getId(),null,new Date(2016,15,15));
		
		transaction.commit();
		session.close();
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithNullTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate3",exampleDate1);
		
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate3");
		
		boolean res = albumDao.update(toChange1.get(0).getId(),null,null);
		
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithEmptyTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate4",exampleDate1);
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate4");
		boolean res = albumDao.update(toChange1.get(0).getId(),"",null);
		
		transaction.commit();
		session.close();	
		
		assertFalse(res);
	}
	
	@Test
	public void updateAlbumWithTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate5",exampleDate1);
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate5");
		boolean res = albumDao.update(toChange1.get(0).getId(),"AlbumTestUpdate6",null);
		
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	@Test
	public void updateNotExistAlbum(){
		
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update("1","AlbumTestUpdate6",null);
		transaction.commit();
		session.close();	
		
		assertFalse(res);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateAlbumWithNullId(){
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update(null,"AlbumTestUpdate6",null);
	}
		
}
















