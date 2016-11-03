package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;


import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

public class BandDaoImpl implements BandDAO {

	private SessionManager SessionManager;


	/**
	 * Get all bands from the database
	 *
	 * @return list with all found bands
	 */
	@Override
	public List<Band> getAllBands() {
		List<Band> bandList = new LinkedList<>();
		Query<Band> query;
		query = SessionManager.getInstance().getCurrentSession().createQuery("from Band", Band.class);
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
			Band band = SessionManager.getInstance().getCurrentSession().find(Band.class, id);
			return band;
		} else {
			return null;
		}
	}

	/**
	 * Update a band in the database
	 *
	 * @param band to update
	 *
	 * @return boolean, true if the band was updated
	 */
	@Override
	public boolean updateBand(Band band){
			if (band.repOK()) {
				SessionManager.getInstance().getCurrentSession().update(band);
				return true;
			} else {
				return false;
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
		if (band.repOK()) {
			SessionManager.getInstance().getCurrentSession().delete(band);
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
			if(this.existBand(name)){
				result = false;
			} else {
				Band band = new Band(name, genre);
				SessionManager.getInstance().getCurrentSession().save(band);
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
			Query<Band> query = SessionManager.getInstance().getCurrentSession().createQuery(hq1, Band.class);
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
	public List<Band> findBandByName(String name){
		if(name == null || name.equals("")){
				throw new IllegalArgumentException("the 'name' param for search a band can not be null or empty.");
			} else {
				Query<Band> query = SessionManager.getInstance().getCurrentSession().createQuery("from Band where name=:n", Band.class);
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
	   public List<Band> findBandByGenre(String genre){
		   if (genre == null || genre.equals("")){
			   throw new IllegalArgumentException("the 'genre' param for search a band can not be null or empty.");
		   } else {
			   Query<Band> query = SessionManager.getInstance().getCurrentSession().createQuery("from Band where genre=:g", Band.class);
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

	   public List<Band> findBandByNameAndGenre(String name,String genre){
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
				Query<Band> query = SessionManager.getInstance().getCurrentSession().createQuery(hq1, Band.class);
				query.setParameter("paramName", name);
				query.setParameter("paramGenre", genre);
				List<Band> bandList = query.getResultList();
				return bandList;
				}
		}
	
}
