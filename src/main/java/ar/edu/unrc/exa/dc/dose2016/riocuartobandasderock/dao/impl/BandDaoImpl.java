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
			if (band != null) {
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
		if (band != null) {
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
			if(this.existBand(name, genre)){
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
	public boolean existBand(String name, String genre){
		boolean result = false;
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
	public List<Band> FindByGenre(String BandGenre){
		if ((BandGenre == null) || (BandGenre.equals("")) ){
			throw new IllegalArgumentException("the 'Genero' param for search a band can not be null or empty.");
		}else{
			Query<Band> query = SessionManager.getInstance().getCurrentSession().createQuery("from Band where genre=:paramgenre", Band.class);
			query.setParameter("paramgenre", BandGenre);
			return query.getResultList();
		}
	}	
	
	public List<Band> FindByNameAndGenre(String BandName,String BandGenre ){
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = BandName == null || BandGenre == null;
		if(!areNull){
			areEmpty = BandName.equals("") && BandGenre.equals("");
		}
		if(areNull || areEmpty){ 
			throw new IllegalArgumentException("the params for search band can't be null or empty.");
		} else {
			
			String hq1 = "FROM Band A WHERE A.name = :paramName and A.genre = :paramGenre";
			Query<Band> query = SessionManager.getInstance().getCurrentSession().createQuery(hq1, Band.class);
			query.setParameter("paramName", BandName);
			query.setParameter("paramGenre", BandGenre);
			return query.getResultList();
			
		}
		
	}
}

	

	

