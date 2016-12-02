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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SongDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlbumDAOTest {
	
	private AlbumDaoImpl albumDao;
	private BandDAO bdao;
	private SongDAO sdao;
	private Session session;
	private Date exampleDate1;
	private Date exampleDate2;
	private Date exampleDate3;
	private Date exampleDateAux;
	private Band banda;
	private Band band2;
	private Song song1;
	private Song song2;
	private Song song3;
	private Song songWrongFormed;
	List<Object> songs;
	List<Object> songsListWrongFormed;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setUp(){
		
		session = SessionManager.getInstance().openSession();
    	albumDao = new AlbumDaoImpl(session);
    	bdao = new BandDaoImpl(session);
		sdao = new SongDaoImpl(session);
    	
		try {
			exampleDate1 = sdf.parse("2010-10-10");
			exampleDateAux = sdf.parse("2010-08-10");
			exampleDate2 = sdf.parse("2010-08-10");
			exampleDate3 = sdf.parse("2010-08-11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		banda = new Band("Band1","rock");
		band2= new Band("Band2","Folklore");
		song1 = new Song("Song1", 200);
		song2 = new Song("Song2", 200);
		song3 = new Song("Song3",300);
		
		songs = new LinkedList<Object>();
		
		songWrongFormed= new Song(null,100);
		songsListWrongFormed = new LinkedList<Object>();
		songsListWrongFormed.add(songWrongFormed);
			
	}
	
	@Test
	public void aA01createBandInEmptyDB(){
		Transaction transaction = session.beginTransaction();
		boolean b = bdao.createBand(banda.getName(), banda.getGenre());
		boolean a = bdao.createBand(band2.getName(), band2.getGenre());
		transaction.commit();
		assertTrue(b);
		session.close();
	}
	
	@Test 
	public void aA02createAlbumWithTitleReleaseDateAndBandId(){
		Transaction transaction = session.beginTransaction();
		
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean b = albumDao.create("AlbumTest1",exampleDate2,band.getId());
		transaction.commit();
		session.close();
		assertTrue(b);
	}
			
	@Test
	public void createAlbumIfNotInDB(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0); //use in others tests
		boolean a = albumDao.create("AlbumTest1_1",exampleDate2,band.getId());
		transaction.commit();
		assertTrue(a);
		session.close();
	}
	 
	@Test
	public void createAlbum(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		
		boolean a = albumDao.create("AlbumTest2",exampleDate2,band.getId());
		boolean b = albumDao.create("AlbumTest2",exampleDate2,band.getId());
		transaction.commit();
		session.close();
		assertFalse(b);
	}
	
	@Test
	public void createAlbumWithNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean c = albumDao.create("AlbumTestAux",null,band.getId());
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDateAndSameTitle(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean a = albumDao.create("AlbumTestAux00",exampleDate1,band.getId());
		
		boolean b = albumDao.create("AlbumTestAux01",exampleDate1,band.getId());
		
		boolean c = albumDao.create("AlbumTestAux01",null,band.getId());
		
		transaction.commit();
		
		assertFalse(c);
		
		session.close();
	}
	
	@Test
	public void createAlbumWithNullReleaseDateAndDistinctTitle(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean a = albumDao.create("AlbumTest00",exampleDate1,band.getId());
		
		boolean b = albumDao.create("AlbumTest01",exampleDate2,band.getId());
		
		boolean c = albumDao.create("AlbumTest02",null,band.getId());
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}

	@Test
	public void createAlbumWhenNotNullReleaseDateAndTitleExist(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		
		List<Album> findTitle = albumDao.findByTitle("AlbumTest1");
		
		boolean a = albumDao.create("AlbumTest1",exampleDate2,band.getId());
		transaction.commit();
		session.close();
		
		assertFalse(a);	
		
	}
	@Test
	public void createAlbumWithReleaseDateNUllAndTitleNotExist(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean c = albumDao.create("NullReleaseDate",null,band.getId());
		
		transaction.commit();
		
		assertTrue(c);
		
		session.close();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumIfTitleIsNull(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean a = albumDao.create(null,null,band.getId());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumIfTitleIsEmptyAndReleaseDateIsNull(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean a = albumDao.create("",null,band.getId());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAlbumWithIDBandNull(){
		Transaction transaction = session.beginTransaction();
		boolean a = albumDao.create("SameTitle",exampleDate2,null);
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
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean a = albumDao.create("AlbumTest3",exampleDate2,band.getId());
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
		Band band = bdao.findByName(banda.getName()).get(0);
		boolean a = albumDao.create("AlbumTest4",exampleDate1,band.getId());
		Album aux1 = albumDao.findById("9893948593845");
		transaction.commit();
		assertNull(aux1);
		session.close();
	}
	
	@Test
	public void findByReleaseDateWhenDateParamIsNull(){
		List<Album> findByReleaseDate = albumDao.findByReleaseDate(null);
		assertEquals(0,findByReleaseDate.size());
	}
	@Test
	public void findSongsByNullAlbumID(){
		List<Song> songs = albumDao.findSongs(null);
		assertEquals(0,songs.size());
	}
	@Test
	public void findSongsByEmptyAlbumID(){
		List<Song> songs = albumDao.findSongs("");
		assertEquals(0,songs.size());
	}
	
	
	@Test
	public void getAllAlbumWhenDBIsEmpty(){
		
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTest5",exampleDate2,band.getId());
		albumDao.create("AlbumTest6",exampleDate2,band.getId());
		List<Album> lista = albumDao.getAll();
		transaction.commit();
		session.close();
		assertTrue(lista.size()>=2);
	}
	
	@Test
	public void deleteAlbum(){
		
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestToDelete", exampleDate2,band.getId());
		
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
	public void findByTitleTest(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestToFind", exampleDate1,band.getId());
		
		List<Album> queryAlbum = albumDao.findByTitle("AlbumTestToFind");
		
		transaction.commit();
		assertEquals("AlbumTestToFind", queryAlbum.get(0).getTitle() );
	}
	@Test
	public void findByTitleWhenTitleParamIsNull(){
		List<Album> byTitle = albumDao.findByTitle(null);
		assertEquals(0,byTitle.size());
	}
	
	@Test
	public void updateAlbumWithTitleAndReleaseDate(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestToUpdate", exampleDate1,band.getId());
		
		List<Album> toChange = albumDao.findByTitle("AlbumTestToUpdate");
		
		boolean res = albumDao.update(toChange.get(0).getId(),"AlbumTestSuccChange", exampleDate1,null);
		transaction.commit();
		session.close();
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithNullTitleAndReleaseDate(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestUpdate2",exampleDate1,band.getId());
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate2");
		
		boolean res = albumDao.update(toChange1.get(0).getId(),null,exampleDate3,null);
		
		transaction.commit();
		session.close();
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithOnlyBandID(){
		
		Transaction transaction = session.beginTransaction();
		
		Band band = bdao.findByName(band2.getName()).get(0);
		
		List<Album> toUpdate = albumDao.findByTitle("AlbumTest1");
		albumDao.update(toUpdate.get(0).getId(), null,null,band.getId());
		transaction.commit();
		session.close();
	}
	@Test
	public void updateAlbumWithNullTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestUpdate3",exampleDate1,band.getId());
		
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate3");
		
		boolean res = albumDao.update(toChange1.get(0).getId(),null,null,null);
		
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	@Test
	public void updateAlbumWithEmptyTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestUpdate4",exampleDate1,band.getId());
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate4");
		boolean res = albumDao.update(toChange1.get(0).getId(),"",null,null);
		
		transaction.commit();
		session.close();	
		
		assertFalse(res);
	}
	
	@Test
	public void updateAlbumWithTitleAndNullReleaseDate(){
		Transaction transaction = session.beginTransaction();
		Band band = bdao.findByName(banda.getName()).get(0);
		albumDao.create("AlbumTestUpdate5",exampleDate1,band.getId());
		List<Album> toChange1 = albumDao.findByTitle("AlbumTestUpdate5");
		boolean res = albumDao.update(toChange1.get(0).getId(),"AlbumTestUpdate6",null,null);
		
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	@Test
	public void updateNotExistAlbum(){
		
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update("1","AlbumTestUpdate6",null,null);
		transaction.commit();
		session.close();	
		
		assertFalse(res);
	}
	
	@Test
	public void updateAlbumWithEmptyTitle(){
		Transaction transaction = session.beginTransaction();
		List<Album> alb = albumDao.findByTitle("AlbumTest1");
		boolean res = albumDao.update(alb.get(0).getId(),"", exampleDate1,null);
		transaction.commit();
		session.close();	
		
		assertFalse(res);
	}
	
	@Test
	public void updateAlbumWithTitleExistent(){
		Transaction transaction = session.beginTransaction();
		List<Album> alb = albumDao.findByTitle("AlbumTest1");
		boolean res = albumDao.update(alb.get(0).getId(),"AlbumTest1", exampleDate1,null);
		transaction.commit();
		session.close();	
		
		assertTrue(res);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void updateAlbumWithNullId(){
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update(null,"AlbumTestUpdate6",null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateAlbumWithEmptyId(){
		Transaction transaction = session.beginTransaction();
		boolean res = albumDao.update("","AlbumTestUpdate6",null,null);
	}
	
}
















