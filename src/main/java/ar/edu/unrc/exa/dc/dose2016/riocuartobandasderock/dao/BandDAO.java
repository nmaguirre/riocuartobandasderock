package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;
import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;


public interface BandDAO {
	
	   
	   public List<Band> getAllBands();
	   
	   public Band getBand(int id);
	   
	   public void updateBand(Band band);
	   
	   public void deleteBand(Band band);
	   
	   public void addBand(Band band);
	   
	   
	
}

