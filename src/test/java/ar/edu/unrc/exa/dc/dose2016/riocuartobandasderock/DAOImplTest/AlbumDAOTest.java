package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;

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
	

}
