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
	public void getAllBandsTestCase(){
		List<Band> allBands = new LinkedList<Band>();
		Band bandInst1= new Band();
		Band bandInst2= new Band();
		Band bandInst3= new Band();
	
		bandInst1.setName("Soda Stereo");
		bandInst1.setId("1");
		
		bandInst2.setName("Babasonicos");
		bandInst2.setId("2");
		
		bandInst3.setName("Eruca Sativa");
		bandInst3.setId("3");
		
		allBands.add(bandInst1);
		allBands.add(bandInst2);
		allBands.add(bandInst3);
		
		new Expectations(){
			{
				bandDao.getAllBands();
				returns (allBands);
			}
		};
		assertEquals(allBands,bandDao.getAllBands()  );
	}
	

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
