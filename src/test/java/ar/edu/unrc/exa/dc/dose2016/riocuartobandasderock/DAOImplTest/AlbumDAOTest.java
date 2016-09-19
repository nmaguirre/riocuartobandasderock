package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

public class AlbumDAOTest {
	
	@Mocked AlbumDAO albumDao;
	
	
	@Test
	public void findByIdTestCase() {
		Album albumModel = new Album();
		albumModel.setId(3);//this 3 must change to string
		new Expectations(){
			{
				albumDao.findById(withEqual("3"));
				returns (albumModel);
			}
		};
		assertEquals(albumModel,albumDao.findById("3"));
	}
	
	@Test
	public void getAllAlbumTestCase(){
		List<Album> allAlbums = new LinkedList<Album>();
		Album albumInst1= new Album();
		Album albumInst2= new Album();
		Album albumInst3= new Album();
	
		albumInst1.setTitle("Hey Jude");
		albumInst1.setId(1);
		
		albumInst2.setTitle("Say No More");
		albumInst2.setId(2);
		
		albumInst3.setTitle("Volumen I");
		albumInst3.setId(3);
		
		allAlbums.add(albumInst1);
		allAlbums.add(albumInst2);
		allAlbums.add(albumInst3);
		
		new Expectations(){
			{
				albumDao.getAllAlbums();
				returns (allAlbums);
			}
		};
		assertEquals(allAlbums,albumDao.getAllAlbums()  );
	}
	
	@Test
	public void findByNameTest(){
		Album albumInst1 = new Album();
		
		albumInst1.setTitle("Pendulum");
		albumInst1.setId(1);
		
		new Expectations(){{
			albumDao.findByName("Pendulum");
			returns(albumInst1);
		}};
		assertEquals(albumInst1,albumDao.findByName("Pendulum"));
	}
	
	@Test
	public void findByBandNameTest(){
		List<Album> allAlbums = new LinkedList<Album>();
		Band bandInst1 = new Band();
		Band bandInst2 = new Band();
		Album albumInst1= new Album();
		Album albumInst2= new Album();
		Album albumInst3= new Album();
		
		bandInst1.setName("Creedence Clearwater Revival");
		bandInst1.setName("ZZ Top");
		albumInst1.setArtist(bandInst1);
		albumInst2.setArtist(bandInst1);
		albumInst3.setArtist(bandInst2);
		
		allAlbums.add(albumInst1);
		allAlbums.add(albumInst2);
		
		new Expectations(){{
			albumDao.findByBandName("Creedence Clearwater Revival");
			returns(allAlbums);
		}};
		
		assertEquals(allAlbums,albumDao.findByBandName("Creedence Clearwater Revival"));
		
	}
	
	@Test
	public void findByProducersTest(){
		List<Album> allAlbums = new LinkedList<Album>();
		
		List<String> producers1 = new LinkedList<String>();
		producers1.add("Productor1");
		producers1.add("Productor2");
		
		List<String> producers2 = new LinkedList<String>();
		producers2.add("Productor2");
		producers2.add("Productor3");
		
		Album albumInst1= new Album();
		Album albumInst2= new Album();
		
		albumInst1.setProducers(producers1);
		albumInst2.setProducers(producers2);
		
		allAlbums.add(albumInst1);
		allAlbums.add(albumInst2);
		
		new Expectations(){{
			albumDao.findByProducer("Productor2");
			returns(allAlbums);
		}};
		
		assertEquals(allAlbums,albumDao.findByProducer("Productor2"));
		
	}
	
	@Test
	public void findByDurationTest(){
		List<Album> allAlbums = new LinkedList<Album>();
		Album albumInst1 = new Album();		
		
		albumInst1.setDuration(2460);
		
		allAlbums.add(albumInst1);
		
		new Expectations(){{
			albumDao.findByDuration(2460);
			returns(allAlbums);
		}};
		assertEquals(allAlbums,albumDao.findByDuration(2460));
	}
	

}
