package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class AlbumDAOTest {
	
	@Mocked AlbumDAO albumDao;
	private Album albumInst1;
	private Album albumInst2;
	private Album albumInst3;
	private Album albumInst4;
	
	@Before
	public void setUp(){
		Band bandInst1 = new Band();
		Band bandInst2 = new Band();
		Band bandInst3 = new Band();
		bandInst1.setName("The Beatles");
		bandInst2.setName("Charly Garcia");
		bandInst3.setName("Creedence Clearwater Revival");
		
		List<String> producers1 = new LinkedList<String>();
		producers1.add("Productor1");
		producers1.add("Productor2");
		
		List<String> producers2 = new LinkedList<String>();
		producers2.add("Productor2");
		producers2.add("Productor3");
		
		Song track1 = new Song();
		Song track2 = new Song();
		Song track3 = new Song();
		Song track4 = new Song();
		Song track5 = new Song();
		
		track1.setName("Song1");
		track2.setName("Song2");
		track3.setName("Song3");
		track4.setName("Song4");
		track5.setName("Song5");
		
		List<Song> songs = new LinkedList<Song>();
		songs.add(track1);
		songs.add(track2);
		songs.add(track3);
		songs.add(track4);
		songs.add(track5);
		

		albumInst1= new Album("Hey Jude");
		albumInst2= new Album("Say No More");
		albumInst3= new Album("Pendulum");


	}
	
	@Test
	public void findByIdTestCase() {
		Album albumModel = new Album();
		albumModel.setId("3");
		
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
		
		allAlbums.add(albumInst1);
		allAlbums.add(albumInst2);
		allAlbums.add(albumInst3);
		
		new Expectations(){{
				albumDao.getAllAlbums();
				returns (allAlbums);
		}};
		assertEquals(allAlbums,albumDao.getAllAlbums()  );
	}
	
	
	@Test
	public void findByNameTest(){
		List<Album> allAlbums = new LinkedList<Album>();
		allAlbums.add(albumInst3);		
		new Expectations(){{
			albumDao.findByName("Pendulum");
			returns(allAlbums);
		}};
		assertEquals(allAlbums,albumDao.findByName("Pendulum"));
	}
		
}
