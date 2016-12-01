package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

public class BandDaoImpl implements BandDAO {


	// private SessionManager SessionManager;
	
	/**
	 * This method counts the number of bands 
	 * 
	 * @return number of bands
	 */
	
	
	public int cantBands() {
		List<Band> bandList = new LinkedList<>();
		Query<Band> query;
		query = this.currentSession.createQuery("from Band", Band.class);
		bandList.addAll(query.getResultList());
		return bandList.size();
	}
	
	private Session currentSession=null;


	public BandDaoImpl(Session session) {
		this.currentSession = session;
	}
	/**
	 * Get all bands from the database
	 *
	 * @return list with all found bands
	 */
	@Override
	public List<Band> getAllBands() {
		List<Band> bandList = new LinkedList<>();
		Query<Band> query;
		query = this.currentSession.createQuery("from Band", Band.class);
		bandList.addAll(query.getResultList());
		return bandList;
	}
	/**
	 * Search a band in database
	 *
	 * @param band id to search
	 *
	 * @return band wanted
	 */
	@Override
	public Band getBand(String id){
		if (id != null && id != "") {
			Band band = this.currentSession.find(Band.class, id);
			return band;
		} else {
			return null;
		}
	}


	/**
	 *
	 * @param id of the band to modify
	 * @param new name
	 * @param new genre
	 * @return true if the update was successful
	 */
	@Override
	public boolean updateBand(String id, String new_name, String new_genre) {
		boolean result = true;
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = id == null || new_name == null || new_genre == null ;
		if(!areNull){
			areEmpty = new_name.equals("") && new_genre.equals("") ;
			areEmpty = areEmpty || id.equals("");//if all params are empty or id is empty
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for update band can't be null or empty.");
		} else {
			Query<Band> query = this.currentSession.createQuery("update Band set name = :name,"
					+ " genre = :genre, where bandID=:id", Band.class);
			query.setParameter("name", new_name);
			query.setParameter("genre", new_genre);
			query.setParameter("id", id);
			int afectedRows = query.executeUpdate();
			if(afectedRows == 0){
				result = false;
			}
			return result;
		}
	}

	/**
	 * Remove a band from the database
	 *
	 * @param id from band to delete
	 *
	 * @return boolean, true if the band was deleted
	 */
	@Override
	public boolean deleteBand(String id){
		Band band = this.getBand(id);
		if (band != null && band.repOK() && this.existBand(band.getName())) {
			this.currentSession.delete(band);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create a band in the database
	 *
	 * @param name from a band
	 * @param genre from a band
	 *
	 * @return boolean, true if the band was created
	 */
	@Override
	public boolean createBand(String name, String genre) {
		boolean result;
		boolean areNull = false;
		boolean areEmpty = false;
		areNull = name == null || genre == null;
		if(!areNull){
			areEmpty = name.equals("") && genre.equals("");
		}
		if(areNull || areEmpty){
			throw new IllegalArgumentException("the params for create band can't be null or empty.");
		} else {
			if(existBand(name)){
				result = false;
			} else {
				Band band = new Band(name, genre);
				this.currentSession.save(band);
				result = true;
			}
			return result;
		}
	}
	/**
	 * @param name of band
	 * @param genre of band
	 *
	 * @return true if exists a band
	 */
	@Override
	public boolean existBand(String name){
		boolean result = false;
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = name == null;
		if(!areNull){
			areEmpty = name.equals("");
		}
		if(areNull || areEmpty){
			throw new IllegalArgumentException("the params for search band can't be null or empty.");
		} else {

			String hq1 = "FROM Band A WHERE A.name = :paramName";
			Query<Band> query = this.currentSession.createQuery(hq1, Band.class);
			query.setParameter("paramName", name);
			List<Band> bandList = query.getResultList();
			if(bandList.isEmpty()){
				result = false;
			}else{
				result = true;
			}
		}
		return result;
	}
	/**
	 * Search a band in database
	 *
	 * @param band name to search
	 * @return band wanted
	 */
	@Override
	public List<Band> findByName(String name){
		if(name == null || name.equals("")){
				throw new IllegalArgumentException("the 'name' param for search a band can not be null or empty.");
			} else {
				Query<Band> query = this.currentSession.createQuery("from Band where name=:n", Band.class);
				query.setParameter("n", name);
				return query.getResultList();
			}
	}

	/**
	    * Search a band in database
	    *
	    * @param band genre to search
	    *
	    * @return list of bands wanted
	    */
	   public List<Band> findByGenre(String genre){
		   if (genre == null || genre.equals("")){
			   throw new IllegalArgumentException("the 'genre' param for search a band can not be null or empty.");
		   } else {
			   Query<Band> query = this.currentSession.createQuery("from Band where genre=:g", Band.class);
			   query.setParameter("g", genre);
			   return query.getResultList();
		 }
	   }
	   /**
	    * Search a band in database
	    *
	    * @param band genre to search
	    * @param band name to search
	    *
	    * @return list of bands wanted
	    */

	   public List<Band> findByNameAndGenre(String name,String genre){
			boolean areEmpty = false;
			boolean areNull = false;
			areNull = name == null || genre == null;
			if(!areNull){
				areEmpty = name.equals("") && genre.equals("");
			}
			if(areNull || areEmpty){
				throw new IllegalArgumentException("the params for search band can't be null or empty.");
			} else {
				String hq1 = "FROM Band A WHERE A.name = :paramName and A.genre = :paramGenre";
				Query<Band> query = this.currentSession.createQuery(hq1, Band.class);
				query.setParameter("paramName", name);
				query.setParameter("paramGenre", genre);
				List<Band> bandList = query.getResultList();
				return bandList;
				}
		}

	   @Override
		public Band findById(String id) {
			if(id == null || id.equals("")){
				throw new IllegalArgumentException("the 'id' param for search an band can not be null or empty.");
			} else {
				Query<Band> query = this.currentSession.
						createQuery("from Band where bandID=:id", Band.class);
				query.setParameter("id", id);
				return query.getSingleResult();
			}
		}

		public List<Album> findAlbums(String bandID){
			if(bandID == null || bandID.equals("")){
				throw new IllegalArgumentException("the 'id' param can not be null or empty.");
			} else {
				Query<Album> query = this.currentSession.createQuery("from Artist where bandID=:id", Album.class);
				query.setParameter("id", bandID);
				List<Album> result = query.getResultList();
				return result;
			}	
		}

}
