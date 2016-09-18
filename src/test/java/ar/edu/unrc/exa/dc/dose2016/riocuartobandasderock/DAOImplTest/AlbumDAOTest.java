package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;

//Working in this class test
public class AlbumDAOTest {
	
	@Mocked AlbumDaoImpl albumDao;
	@Mocked Album albumModel;
	
	//Working in this test
	@Test
	public void findByIdTestCase() {
		albumDao = new AlbumDaoImpl();
		albumModel = new Album();
		
		new Expectations(){
			{
				albumDao.findById(withEqual(3));
				albumModel.setId(3);
				returns (albumModel);
				assertEquals(3,albumModel.getId());
			}
		};
	}
}
