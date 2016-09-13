package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;


public interface BandDAO {
	
	   
	   public List<Band> getAllBands();
	   
	   public Band getBand(int id);
	   
	   public Boolean updateBand(Band band);
	   
	   public Boolean deleteBand(Band band);
	   
	   public Boolean addBand(Band band);
	   
	   
	
}

