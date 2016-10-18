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
	 * Search a band in database
	 * 
	 * @param band id to search
	 * 
	 * @return band wanted
	 */
	
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
	 * Create a band from the database
	 * 
	 * @param band to create
	 * 
	 * @return boolean, true if the band was created
	 */
	
	public Boolean addBand(Band band){
		SessionFactory sessionFactory = getSessionFactory();
		Session currentSession = sessionFactory.openSession();
		Transaction currentTransaction = currentSession.beginTransaction();
	    currentSession.save(band);
	    currentTransaction.commit();
	    currentSession.close();
	    return true;
		
	}
	public List<Band> findBandByName(String name){
		if (name != null && name != ""){
			List<Band> bandList = new LinkedList<>();
			Query<Band> query;
			query = currentSession.createQuery("from Band where name=:n", Band.class);
			query.setString("n", name);
			bandList.addAll(query.getResultList());
			return bandList;
		} else {
					return null;
		}
			
	}  
	
}
