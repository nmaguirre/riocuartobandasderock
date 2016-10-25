package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class SessionManagerHibernate implements SessionManager{

	private Session currentSession;

	private Transaction currentTransaction;
	
	/**
	 * Empty, private default constructor.
	 */
	private SessionManagerHibernate() { }
	
	/**
	 * Singleton, on demand instance of ServerOptions.
	 */
	private static SessionManagerHibernate instance = null;
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
		configuration.addAnnotatedClass(Album.class);
		configuration.addAnnotatedClass(Band.class);
		configuration.addAnnotatedClass(Song.class);
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
	 * Returns the (sole) instance of ServerOptions, on demand.
	 * It is created the first time it is accessed, and from that
	 * point onwards, the same instance is returned.
	 * See the Singleton Pattern for reference.
	 * @return the instance of ServerOptions.
	 */
	public static SessionManagerHibernate getInstance() {
		if (instance==null) instance = new SessionManagerHibernate();
		return instance;
	}
	
	

}
