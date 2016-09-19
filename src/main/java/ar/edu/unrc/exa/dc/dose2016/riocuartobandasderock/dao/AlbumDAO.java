package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;
//import java.util.Date;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

/**
 * @author Dose Team 2016
 */

public interface AlbumDAO {
	
	/**
	 * @param id
	 * @return 
	 */
	public Album findById(String id);
	
	/**
	 * @return All albums 
	 */
	public List<Album> getAllAlbums();	
	
	/**
	 * @param bandName
	 * @return 
	 */
	public List<Album> findByBandName(String bandName);
	
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
	
	/**
	 * @param producer
	 * @return
	 */
	public List<Album> findByProducer(String producer);
	
	/**
	 * @param duration
	 * @return
	 */
	public List<Album> findByDuration(int duration);
	
	/**
	 * @param song
	 * @return
	 */
	public List<Album> findBySong(Song song);
	
//	/**
//	 * @param releaseDate
//	 * @return
//	 */
//	public List<Album> findByReleaseDate(Date releaseDate);
	
//	/**
//	 * @param recordDate
//	 * @return
//	 */
//	public List<Album> findByRecordDate(Date recordDate);
	
	/**
	 * @param album
	 */
	public void createAlbum(Album album);
	
	/**
	 * @param album
	 */
	public void updateAlbum(Album album);
	
	/**
	 * @param album
	 */
	public void deleteAlbum(Album album);
	
}
