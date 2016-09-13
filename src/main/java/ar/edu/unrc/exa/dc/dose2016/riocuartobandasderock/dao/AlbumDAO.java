package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;

/**
 * @author Dose Team 2016
 */

public interface AlbumDAO {
	
	/**
	 * @param id
	 * @return 
	 */
	public Album findById(int id);
	
	/**
	 * @param bandName
	 * @return 
	 */
	public List<Album> findByNameBand(String bandName);
	
	/**
	 * @param name
	 * @return  
	 */
	public Album findByName(String name);
	
	/**
	 * @param year
	 * @return
	 */
	public List<Album> findByYear(int year);
	
	/**
	 * @param genere
	 * @return
	 */
	public List<Album> findByGenere(String genere);

	/**
	 * @param recordLabel
	 * @return
	 */
	public List<Album> findByRecordLabel(String recordLabel);
	
	
	
}
