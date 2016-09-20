package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;

public class AlbumDaoImpl implements AlbumDAO{

	public Album findById(String id){
		return null;
	}
	
	public List<Album> getAllAlbums(){
		return null;
	}	
	
	public List<Album> findByBandName(String bandName){
		return null;
	}
	
	public Album findByName(String name){
		return null;
	}
	
	public List<Album> findByGenere(String genere){
		return null;
	}

	public List<Album> findByRecordLabel(String recordLabel){
		return null;
	}
	
	public List<Album> findByProducer(String producer){
		return null;
	}
	
	public List<Album> findByDuration(int duration){
		return null;
	}
	
	public List<Album> findBySong(String song){
		return null;
	}
	
	public List<Album> findByReleaseDate(int year){
		return null;
	}

	public boolean createAlbum(Album album){
		return false;
	}
	
	public boolean updateAlbum(Album album){
		return false;
	}
	
	public boolean deleteAlbum(String id){
		return false;
	}
	
}
