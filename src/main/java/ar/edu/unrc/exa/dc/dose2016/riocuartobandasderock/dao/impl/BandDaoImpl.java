package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;

public class BandDaoImpl implements BandDAO {
	
	private Session currentSession;

	private Transaction currentTransaction;

	@Override
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	@Override
	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	@Override
	public void closeCurrentSession() {
		currentSession.close();
	}

	@Override
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}


	private static SessionFactory getSessionFactory() {
		String dbHost = ServerOptions.getInstance().getDbHost();
		String dbPort = ServerOptions.getInstance().getDbPort();
		// Configuration configuration = new Configuration().addPackage("models").configure("hibernate.cfg.xml").addAnnotatedClass(Band.class);
		Configuration configuration = new Configuration().addPackage("models");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		configuration.setProperty("hibernate.connection.username", "rock_db_owner");
		configuration.setProperty("hibernate.connection.password", "rockenrio4");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:postgresql://" + dbHost + ":" + dbPort + "/rcrockbands");
		configuration.setProperty("connection_pool_size", "1");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "false");
		configuration.setProperty("hibernate.current_session_context_class", "thread");
		configuration.addAnnotatedClass(Band.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sf = configuration.buildSessionFactory(builder.build());
		return sf;
	}

	@Override
	public Session getCurrentSession() {
		return currentSession;
	}

	@Override
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	@Override
	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	@Override
	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
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
		query = currentSession.createQuery("from Band", Band.class);
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
			Band band = currentSession.find(Band.class, id);
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
	public Boolean updateBand(Band band){
			if (band != null) {
				currentSession.update(band);
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
	public Boolean deleteBand(String id){
		Band band = this.getBand(id);
		if (band != null) {
			currentSession.delete(band);
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
	public Boolean createBand(String name, String genre) {
		Boolean result;
		Boolean areNull = name == null && genre == null;
		Boolean areEmpty = name.equals("") && genre.equals("");
		if(areNull || areEmpty){
			throw new IllegalArgumentException("the params for create band can't be null or empty.");
		} else {
			String hq1 = "FROM Band A WHERE A.name = :paramName and A.genre = :paramGenre";
			Query<Band> query = currentSession.createQuery(hq1, Band.class);
			query.setParameter("paramName", name);
			query.setParameter("paramGenre", genre);
			List<Band> bandList = query.getResultList();
			if(bandList != null || !bandList.isEmpty()){
				result = false;
			} else {
				Band band = new Band(name, genre);
				currentSession.save(band);
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
		String hq1 = "FROM Artist A WHERE A.name = :paramName and A.genre = :paramGenre";
		Query<Band> query = currentSession.createQuery(hq1, Band.class);
		query.setParameter("paramName", name);
		query.setParameter("paramGenre", genre);
		List<Band> bandList = query.getResultList();
		if(!bandList.isEmpty()){
			result = true;
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
				Query<Band> query = currentSession.createQuery("from Band where name=:n", Band.class);
				query.setParameter("n", name);
				return query.getResultList();
			}
		}
	}
	   
	

