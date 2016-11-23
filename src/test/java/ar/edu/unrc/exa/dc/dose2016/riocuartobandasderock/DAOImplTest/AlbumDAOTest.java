package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private Date exampleDate2;
	private Date exampleDate3;
	private Band banda;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	@Before
	public void setUp(){
		
		session = SessionManager.getInstance().openSession();
    	albumDao = new AlbumDaoImpl(session);
		try {
			exampleDate1 = sdf.parse("2010-10-10");
			exampleDate2 = sdf.parse("2010-08-10");
			exampleDate3 = sdf.parse("2010-08-11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		banda = new Band("Band1","rock");
	}
	
	@Test
	public void createAlbumIfNotInDB(){
		
		Transaction transaction = session.beginTransaction();
			
		boolean a = albumDao.create("AlbumTest1",exampleDate2,null,banda.getId());
		
		transaction.commit();
        
		assertTrue(a);
		session.close();
	}
	@Test
	public void createAlbumIfExistInDBwithOtherSession(){
		
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("A2",exampleDate1,null,banda.getId());
		boolean b = albumDao.create("A2",exampleDate1,null,banda.getId());
		
		transaction.commit();
		
		System.out.println(b);
	
		assertFalse(b);
		session.close();
	}
	
	@Test
	public void createAlbumIfInDB(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTest2",exampleDate2,null,banda.getId());
		boolean b = albumDao.create("AlbumTest2",exampleDate2,null,banda.getId());
	
		transaction.commit();
		
		assertFalse(b);
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		
		boolean c = albumDao.create("AlbumTestAux",null,null,banda.getId());
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDateAndSameTitle(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTestAux00",exampleDate1,null,banda.getId());
		
		boolean b = albumDao.create("AlbumTestAux01",exampleDate1,null,banda.getId());
		
		boolean c = albumDao.create("AlbumTestAux01",null,null,banda.getId());
		
		transaction.commit();
		
		assertFalse(c);
		
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDateAndDistinctTitle(){
		Transaction transaction = session.beginTransaction();
		
		boolean a = albumDao.create("AlbumTest00",exampleDate1,null,banda.getId());
		
		boolean b = albumDao.create("AlbumTest01",exampleDate2,null,banda.getId());
		
		boolean c = albumDao.create("AlbumTest02",null,null,banda.getId());
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumIfTitleIsNull(){
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create(null,null,null,banda.getId());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumIfTitleIsEmptyAndReleaseDateIsNull(){
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create("",null,null,banda.getId());	
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
		boolean a = albumDao.create("AlbumTest3",exampleDate2,null,banda.getId());
		List<Album> lista = albumDao.findByTitle("AlbumTest3");
		Album aux = lista.get(0);
		Album aux1 = albumDao.findById(aux.getId());
		transaction.commit();
		assertTrue(aux.getId().equals(aux1.getId()));
		session.close();
	}
	
	@Test
	public void findByIdNotInDB() {
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create("AlbumTest4",exampleDate1,null,banda.getId());
		Album aux1 = albumDao.findById("9893948593845");
		transaction.commit();
		assertNull(aux1);
		session.close();
	}
	
	@Test
	public void getAllAlbumWhenDBIsEmpty(){
		
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTest5",exampleDate2,null,banda.getId());
		albumDao.create("AlbumTest6",exampleDate2,null,banda.getId());
		List<Album> lista = albumDao.getAll();
		transaction.commit();
		assertTrue(lista.size()>=2);
		session.close();
	}
	
	@Test
	public void deleteAlbum(){
		
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestToDelete", exampleDate2,null,banda.getId());
		
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
		albumDao.create("AlbumTestToFind", exampleDate1,null,banda.getId());
		
		List<Album> queryAlbum = albumDao.findByTitle("AlbumTestToFind");
		
		transaction.commit();
		assertEquals("AlbumTestToFind", queryAlbum.get(0).getTitle() );
	}
	
	@Test
	public void updateAlbumWithTitleAndReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestToUpdate", exampleDate1,null,banda.getId());
		
		List<Album> toChange = albumDao.findByTitle("AlbumTestToUpdate");
		
		boolean res = albumDao.update(toChange.get(0).getId(),"AlbumTestSuccChange", exampleDate1,null,null);
		transaction.commit();
		session.close();
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithNullTitleAndReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate2",exampleDate1,null,banda.getId());
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate2");
		
		boolean res = albumDao.update(toChange1.get(0).getId(),null,exampleDate3,null,null);
		
		transaction.commit();
		session.close();
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithNullTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate3",exampleDate1,null,banda.getId());
		
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate3");
		
		boolean res = albumDao.update(toChange1.get(0).getId(),null,null,null,null);
		
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithEmptyTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate4",exampleDate1,null,banda.getId());
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate4");
		boolean res = albumDao.update(toChange1.get(0).getId(),"",null,null,null);
		
		transaction.commit();
		session.close();	
		
		System.out.println(res);
		
		assertFalse(res);
	}
	
	@Test
	public void updateAlbumWithTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		albumDao.create("AlbumTestUpdate5",exampleDate1,null,banda.getId());
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate5");
		boolean res = albumDao.update(toChange1.get(0).getId(),"AlbumTestUpdate6",null,null,null);
		
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	@Test
	public void updateNotExistAlbum(){
		
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update("1","AlbumTestUpdate6",null,null,null);
		transaction.commit();
		session.close();	
		
		assertFalse(res);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateAlbumWithNullId(){
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update(null,"AlbumTestUpdate6",null,null,null);
	}
		
}
















