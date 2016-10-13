package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public class SongDaoImpl implements SongDAO{
	
	private Session currentSession;
	
	private Transaction currentTransaction;

	/**
	 * Build a session factory
	 * 
	 * @return SessionFactory
	 */
	private SessionFactory getSessionFactory() {
		String dbHost = ServerOptions.getInstance().getDbHost();
		String dbPort = ServerOptions.getInstance().getDbPort();
		Configuration configuration = new Configuration().addPackage("models");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		configuration.setProperty("hibernate.connection.username", "rock_db_owner");
		configuration.setProperty("hibernate.connection.password", "rockenrio4");
		configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://"+dbHost+":"+dbPort+"/rcrockbands");
		configuration.setProperty("connection_pool_size", "1");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "false");
		configuration.setProperty("hibernate.current_session_context_class", "thread");
		configuration.addAnnotatedClass(Song.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sf = configuration.buildSessionFactory(builder.build());
		return sf;
	}
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	@Override
	public List<Song> getAllSongs() {
		List<Song> songList = new LinkedList<>();
		Query<Song> query;
		query = currentSession.createQuery("from Song", Song.class);
		songList.addAll(query.getResultList());
		return songList;
	}
	   
	public Boolean updateSong(Song song){
		if (song != null) {
			currentSession.update(song);
			return true;
		} else {
			return false;
		}
	}
	   
	public Boolean removeSong(Song song){
		if (song != null) {
			currentSession.delete(song);
			return true;
		} else {
			return false;
		}
	}
	   
	public Boolean addSong(Song song){
		return false;
	}
	
	public Song findById(String id){
		if (id != null && id != "") {
			Song song = currentSession.find(Song.class, id);
			return song;
		} else {
			return null;
		}
	}
	
	public List<Song> findByBandName(String BandName){
		return null;
	}
	
	public List<Song> findByName(String name){
		if (name != null && name != "") {
			List<Song> songList = new LinkedList<>();
			Query<Song> query;
			query = currentSession.createQuery("from Song where name=:n", Song.class);
			query.setString("n", name);
			songList.addAll(query.getResultList());
			query = currentSession.createQuery("from Song where surname=:n", Song.class);
			query.setString("n", name);
			songList.addAll(query.getResultList());
			query = currentSession.createQuery("from Song where nickname=:n", Song.class);
			query.setString("n", name);
			songList.addAll(query.getResultList());
			return songList;
		} else {
			return null;
		}
	}
                    
    public List<Song> findByGenere(String genere){
    	return null;
    }
                    
    public List<Song> findByAuthor(String author){
    	return null;
    }
                    
    public List<Song> findByAlbum(String album){
    	return null;
    }
	
	public List<Song> findByDuration(int duration){
		return null;
	}



}
