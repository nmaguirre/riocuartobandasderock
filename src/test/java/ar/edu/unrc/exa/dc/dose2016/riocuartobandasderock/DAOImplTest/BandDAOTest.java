package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

import mockit.Expectations;

import mockit.Mocked;

public class BandDAOTest {
	@Mocked BandDAO bandDao;
	

	@Test
	public void getBandsTestCase(){
		Band bandInst1= new Band();
		bandInst1.setName("Soda Stereo");
		bandInst1.setId("1");
		new Expectations(){
			{
				bandDao.getBand("1");
				returns (bandInst1);
			}
		};
		assertEquals(bandInst1,bandDao.getBand("1")  );
	}
}
