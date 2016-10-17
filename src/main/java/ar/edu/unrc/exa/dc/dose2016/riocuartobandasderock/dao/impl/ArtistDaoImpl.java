package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public class ArtistDaoImpl implements ArtistDAO {

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
		// Configuration configuration = new Configuration().addPackage("models").configure("hibernate.cfg.xml").addAnnotatedClass(Artist.class);
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
		configuration.addAnnotatedClass(Artist.class);
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
	 * Get all artists from the database
	 * 
	 * @return list with all found artists
	 */
	@Override
	public List<Artist> getAllArtists() {
		List<Artist> artistList = new LinkedList<>();
		Query<Artist> query;
		query = currentSession.createQuery("from Artist", Artist.class);
		artistList.addAll(query.getResultList());
		return artistList;
	}

	/**
	 * Search an artist in database
	 * 
	 * @param artist
	 *            name, surname or nickname to search
	 * @return artist wanted
	 */
	@Override
	public List<Artist> findByName(String name) {
		if (name != null && name != "") {
			List<Artist> artistList = new LinkedList<>();
			Query<Artist> query;
			query = currentSession.createQuery("from Artist where name=:n", Artist.class);
			query.setString("n", name);
			artistList.addAll(query.getResultList());
			query = currentSession.createQuery("from Artist where surname=:n", Artist.class);
			query.setString("n", name);
			artistList.addAll(query.getResultList());
			query = currentSession.createQuery("from Artist where nickname=:n", Artist.class);
			query.setString("n", name);
			artistList.addAll(query.getResultList());
			return artistList;
		} else {
			return null;
		}
	}

	/**
	 * Search an artist in database
	 * 
	 * @param artist
	 *            id to search
	 * @return artist wanted
	 */
	@Override
	public Artist findById(String id) {
		if (id != null && id != "") {
			Artist artist = currentSession.find(Artist.class, id);
			return artist;
		} else {
			return null;
		}
	}

	/**
	 * Create an artist from the database
	 * 
	 * @param artist
	 *            to create
	 * @return boolean, true if the artist was created
	 */
	@Override
	public boolean createArtist(Artist artist) {
        SessionFactory sessionFactory = getSessionFactory();
		Session currentSession = sessionFactory.openSession();
        Transaction currentTransaction = currentSession.beginTransaction();
		currentSession.save(artist);
		currentTransaction.commit();
		currentSession.close();
		return true;
	}

	/**
	 * Update an artist in the database
	 * 
	 * @param artist
	 *            to update
	 * @return boolean, true if the artist was updated
	 */
	@Override
	public boolean updateArtist(Artist artist) {
		if (artist != null) {
			currentSession.update(artist);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Remove an artist from the database
	 * 
	 * @param id
	 *            from artist to delete
	 * @return boolean, true if the artist was deleted
	 */
	@Override
	public boolean deleteArtist(String id) {
		Artist artist = this.findById(id);
		if (artist != null) {
			currentSession.delete(artist);
			return true;
		} else {
			return false;
		}
	}

}
