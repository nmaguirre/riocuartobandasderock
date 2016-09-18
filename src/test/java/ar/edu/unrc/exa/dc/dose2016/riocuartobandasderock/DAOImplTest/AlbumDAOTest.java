package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;

//Working in this class test
public class AlbumDAOTest {
	
	@Mocked AlbumDaoImpl album;
	@Mocked Album albumModel;
	
	@Test
	public void findByIdTestCase() {
		
		album = new AlbumDaoImpl();
		albumModel = new Album();
		
		//set atributtes
		albumModel.setId(2);
		
		new Expectations(){
			{
			//	album.findById(2);
			//	returns albumModel;
			}
		};
	}

}
