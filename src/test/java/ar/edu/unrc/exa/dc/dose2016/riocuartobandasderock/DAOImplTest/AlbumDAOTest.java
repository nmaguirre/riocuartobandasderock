package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;

//Working in this class test
public class AlbumDAOTest {
	
	@Mocked AlbumDAO albumDao;
	
	//Working in this test
	@Test
	public void findByIdTestCase() {
		Album albumModel = new Album();
		albumModel.setId(3);
		new Expectations(){
			{
				albumDao.findById(withEqual(3));
				returns (albumModel);
			}
		};
		assertEquals(albumModel,albumDao.findById(3));
	}
}
