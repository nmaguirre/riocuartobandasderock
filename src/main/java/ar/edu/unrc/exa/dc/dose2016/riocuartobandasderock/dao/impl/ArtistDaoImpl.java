package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

/**
 * 
 * @author Adrian Galfioni, Ezequiel Zensich.
 *
 * Artist DAO Implementation.
 *
 */
public class ArtistDaoImpl implements ArtistDAO {

	
	/**
	 * Get all artists from the database
	 * 
	 * @return list with all found artists
	 */
	@Override
	public List<Artist> getAllArtists() {
		List<Artist> artistList = new LinkedList<>();
		Query<Artist> query;
		query = SessionManager.getInstance().getCurrentSession().createQuery("from Artist", Artist.class);
		artistList.addAll(query.getResultList());
		return artistList;
	}

	/**
	 * Search an artist in database
	 * 
	 * @param artist surname to search
	 * @return artist wanted
	 */
	@Override
	public List<Artist> findBySurname(String surname) {
		if(surname == null || surname.equals("")){
			throw new IllegalArgumentException("the 'surname' param for search an artist can not be null or empty.");
		} else {
			Query<Artist> query = SessionManager.getInstance().getCurrentSession().createQuery("from Artist where surname=:n", Artist.class);
			query.setParameter("n", surname);
			return query.getResultList();
		}
	}
	
	/**
	 * Search an artist in database
	 * 
	 * @param artist nickname to search
	 * @return artist wanted
	 */
	@Override
	public List<Artist> findByNickname(String nickname) {
		if(nickname == null || nickname.equals("")){
			throw new IllegalArgumentException("the 'nickname' param for search an artist can not be null or empty.");
		} else {
			Query<Artist> query = SessionManager.getInstance().getCurrentSession().createQuery("from Artist where nickname=:n", Artist.class);
			query.setParameter("n", nickname);
			return query.getResultList();
		}
	}
	
	/**
	 * Search an artist in database
	 * 
	 * @param artist name to search
	 * @return artist wanted
	 */
	@Override
	public List<Artist> findByName(String name) {
		if(name == null || name.equals("")){
			throw new IllegalArgumentException("the 'name' param for search an artist can not be null or empty.");
		} else {
			Query<Artist> query = SessionManager.getInstance().getCurrentSession().createQuery("from Artist where name=:n", Artist.class);
			query.setParameter("n", name);
			return query.getResultList();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @param nickname
	 * 
	 * @return true if this Artist exists in the database.
	 */
	@Override
	public boolean existArtist(String name, String surname, String nickname){
		boolean result = false;
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = name == null || nickname == null || surname == null;
		if(!areNull){
			areEmpty = name.equals("") && nickname.equals("") && surname.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for search artist can't be null or empty.");
		} else {
			//look for the artist in the database
			String hq1 = "FROM Artist A WHERE A.name = :paramName and A.nickname = :paramNickname and A.surname = :paramSurname";
			Query<Artist> query = SessionManager.getInstance().getCurrentSession().createQuery(hq1, Artist.class);
			query.setParameter("paramName", name);
			query.setParameter("paramNickname", nickname);
			query.setParameter("paramSurname", surname);
			List<Artist> artistList = query.getResultList();
			//if the artist is already in database
			if(!artistList.isEmpty()){
				result = true;
			}
		}
		return result;
	}

	/**
	 * Create an artist in the database
	 * 
	 * @param name from an artist
	 * @param nickname from an artist
	 * @param surname from an artist
	 * 
	 * @return boolean, true if the artist was created
	 */
	@Override
	public boolean createArtist(String name, String surname, String nickname) {
		boolean result;
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = name == null || nickname == null || surname == null;
		if(!areNull){
			areEmpty = name.equals("") && nickname.equals("") && surname.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for create artist can't be null or empty.");
		} else {
			//if the artist is already in database
			if(existArtist(name, surname, nickname)){
				result = false;
			} else { //if not exist, register the new artist in database
				Artist artist = new Artist(name, surname, nickname);
				SessionManager.getInstance().getCurrentSession().save(artist);
				result = true;
			}
			return result;
		}
	}

	@Override
	public Artist findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateArtist(String id, String name, String surname, String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteArtist(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
