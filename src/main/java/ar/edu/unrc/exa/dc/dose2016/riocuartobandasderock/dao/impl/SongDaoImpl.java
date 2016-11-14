package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
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
	public Boolean updateSong(Song song){
		if (song != null) {
			this.currentSession.update(song);
			return true;
		} else {
			return false;
		}
	}
	 
	
	/**
	 * fn removeSong
	 * description: The method search in the data base by id the song, and it's try to delete the song.
	 * @param id represents the id of the song to search and delete
	 * @return true if the delete was successful
 	 */
	@Override   
	public Boolean removeSong(String id){
		if (id != null) {
			this.currentSession.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * fn addSong
	 * description: The method allows add a new song in the database
	 * @param name represents the name of the song to add in the database
	 * @param duration represents the duration of the song to add in the database
	 * @return true if the add was successful
	 */
	@Override   
	public Boolean addSong(String name,Integer duration){
		boolean result = false;
		if ((name != null && !name.equals("")) || duration != null){
			Song song = new Song(name,duration);
			this.currentSession.save(song);
			result = true;
		}
		else {
			throw new IllegalArgumentException("the parameters for creating a song can not all be empty or null");
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
		if (id != null && id != "") {
			Song song = this.currentSession.find(Song.class, id);
			return song;
		} else {
			return null;
		}
	}
	
	@Override
	public List<Song> findByAuthor(String author){
		return null;
	}
	
	
	/**
	 * fn findByName
	 * description: The method allows to get a song found by a name
	 * @param name represents the name of the song to search.
	 * @return the list of songs found or null if the song does not exist
	 */
	@Override
	public List<Song> findByName(String name){
		if (name != null && name != "") {
			Query<Song> query = this.currentSession.createQuery("from Song where name=:n", Song.class);
			query.setParameter("n", name);
			return query.getResultList();
		} else {
			throw new IllegalArgumentException("the 'name' param for search an song can not be null or empty.");
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
		if (duration != null && duration.equals("")) {
			Query<Song> query = this.currentSession.createQuery("from Song where duration=:n", Song.class);
			query.setParameter("n", duration);
			return query.getResultList();
		} else {
			throw new IllegalArgumentException("the 'duration' param for search an song can not be null or empty.");
		}
	}



}
