package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;


import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class SongDaoImpl implements SongDAO{

	private Session currentSession=null;


	public SongDaoImpl(Session session) {
		this.currentSession = session;
	}

	/**
	 * fn getAllSongs
	 * description: Get all songs from the database
	 * @return the list with all found songs
	 */
	@Override
	public List<Song> getAllSongs() {
		List<Song> songList = new LinkedList<>();
		Query<Song> query;
		query = this.currentSession.createQuery("from Song", Song.class);
		songList.addAll(query.getResultList());
		return songList;
	}


	/**
	 * fn updateSong
	 * description: the method allows update an existing song in the database
	 * @param song represents the song to update
	 * @return true if the update was successful
	 */
	@Override
	public Boolean updateSong(String id, String name, Integer dur, String albumTitle){
		
		boolean updated = false; 
		if (id == null || id.isEmpty()) throw new IllegalArgumentException("Error: Song ID can't be null");
		
		if(name == null || name.isEmpty() || dur == null || albumTitle.isEmpty() || albumTitle == null) {
			throw new IllegalArgumentException("Wrong parameters");
		} else {
			Song song = findById(id);
			AlbumDAO adao = new AlbumDaoImpl(this.currentSession);
			List<Album> albumList = adao.findByTitle(albumTitle);
			Album alb = albumList.get(0);
			if (alb == null) return false;
			song.setName(name);
			song.setDuration(dur);
			song.setAlbum(alb);
			if (!song.repOk()) throw new IllegalArgumentException ("Bad representation of song");
			this.currentSession.save(song);
			updated = true;
		}
		
		return updated;
	}

		//this.currentSession.update(song)
	/**
	 * fn removeSong
	 * description: The method search in the data base by id the song, and it's try to delete the song.
	 * @param id represents the id of the song to search and delete
	 * @return true if the delete was successful
 	 */
	@Override
	public Boolean removeSong(String id){
		if (id != null) {
			Song s = findById(id);
			if (s != null){
				this.currentSession.delete(s);
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * fn addSong
	 * description: The method allows add a new song in the database
	 * @param name represents the name of the song to add in the database
	 * @param duration represents the duration of the song to add in the database
	 * @param duration represents the name of the song to add in the database
	 * @return true if the add was successful
	 */
	
	@Override
	public Boolean addSong(String name,Integer duration, String albumTitle){
		boolean result = false;
		if (name == null || name.isEmpty() || duration == null || albumTitle == null || albumTitle.isEmpty()){
			throw new IllegalArgumentException("Wrong parameters");
		} else {
			AlbumDAO adao = new AlbumDaoImpl(this.currentSession);
			List<Album> albumList = adao.findByTitle(albumTitle);
			if (albumList.isEmpty()) return false;
			Album alb = albumList.get(0);
			Song song = new Song(name,duration);
			song.setAlbum(alb);
			if (!song.repOk()) throw new IllegalArgumentException ("Bad representation of song");
			this.currentSession.save(song);
			result = true;
		}	
		return result;
	}


	/**
	 * fn findById
	 * description: The method allows to get a song found by a id
	 * @param id represents the id of the song to search.
	 * @return the song found or null if the song does not exist
	 */
	@Override
	public Song findById(String id){
		
		if (id == null || id == "") {
			throw new IllegalArgumentException("The param 'id' for search a song can't be null or empty.");
		} else {
			Song song = this.currentSession.find(Song.class, id);
			return song;
		}
	}


	/**
	 * fn findByName
	 * description: The method allows to get a song found by a name
	 * @param name represents the name of the song to search.
	 * @return the list of songs found or null if the song does not exist
	 */
	@Override
	public List<Song> findByName(String name){
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Parameter name can't be null or empty");
		} else {
			Query<Song> query = this.currentSession.createQuery("from Song where name=:n", Song.class);
			query.setParameter("n", name);
			return query.getResultList();
		}
	}

	/**
	 * fn findByDuration
	 * description: The method allows to get a song found by a duration
	 * @param duration represents the duration of the song to search.
	 * @return the list of songs found or null if the song does not exist
	 */

	@Override
	public List<Song> findByDuration(Integer duration){
		if (duration == null) {
			throw new IllegalArgumentException("Parameter duration can't be null");
		} else {
			Query<Song> query = this.currentSession.createQuery("from Song where duration=:d", Song.class);
			query.setParameter("d", duration);
			return query.getResultList();
		}
	}

}
