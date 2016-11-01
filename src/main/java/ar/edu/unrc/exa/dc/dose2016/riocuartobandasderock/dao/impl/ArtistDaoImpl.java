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

	/**
	 * 
	 * @param String id
	 * @return Artist that have a particular id	
	 */
	@Override
	public Artist findById(String id) {
		if(id == null || id.equals("")){
			throw new IllegalArgumentException("the 'id' param for search an artist can not be null or empty.");
		} else {
			Query<Artist> query = SessionManager.getInstance().getCurrentSession().
					createQuery("from Artist where artistID=:id", Artist.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		}
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param surname
	 * @param nickname
	 * @return true if the update was successful
	 */
	@Override
	public boolean updateArtist(String id, String name, String surname, String nickname) {
		boolean result = true;
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = id == null || name == null || nickname == null || surname == null;
		if(!areNull){
			areEmpty = name.equals("") && nickname.equals("") && surname.equals("");
			areEmpty = areEmpty || id.equals("");//if all params are empty or id is empty
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for update artist can't be null or empty.");
		} else {
			Query<Artist> query = SessionManager.getInstance().
					getCurrentSession().createQuery("update Artist set name = :name,"
					+ " nickname = :nickname, surname = :surname where artistID=:id");
			query.setParameter("name", name);
			query.setParameter("nickname", nickname);
			query.setParameter("surname", surname);
			query.setParameter("id", id);
			int afectedRows = query.executeUpdate();
			if(afectedRows == 0){
				result = false;
			}
			return result;
		}
	}

	/**
	 * 
	 * @param String id
	 * @return true if the delete was successful
	 */
	@Override
	public boolean deleteArtist(String id) {
		if(id == null || id.equals("")){
			throw new IllegalArgumentException("the 'id' param for delete an artist can not be null or empty.");
		} else {
			boolean result = true;
			Query<Artist> query = SessionManager.getInstance().getCurrentSession()
					.createQuery("delete Artist where artistID=:id");
			query.setParameter("id", id);
			int afectedRows = query.executeUpdate();
			if(afectedRows == 0){
				result = false;
			}
			return result;
		}
	}

	/**
	 * Search for an artist by its parameters
	 * 
	 * @param name
	 * @param surname
	 * @param nickname
	 * @return artist wanted, null if artist not found
	 */
	@Override
	public Artist getArtist(String name, String surname, String nickname) {
		Artist result = null;
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = name == null || nickname == null || surname == null;
		if(!areNull){
			areEmpty = name.equals("") && nickname.equals("") && surname.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for get artist id can't be null or empty.");
		} else {
			if(existArtist(name, surname, nickname)){
				Query<Artist> query = SessionManager.getInstance().
						getCurrentSession().createQuery("from Artist where name = :name and"
						+ " nickname = :nickname and surname = :surname", Artist.class);
				query.setParameter("name", name);
				query.setParameter("nickname", nickname);
				query.setParameter("surname", surname);
				result = query.getSingleResult();
			}
			return result;
		}
	}

}
