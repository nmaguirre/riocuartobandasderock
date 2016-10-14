package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
/**
 * This class implements the AlbumDAO interface,
 * and contains the methods necessary 
 * for representation and management.
 * @author DOSE Team 2016
 *
 */
public class AlbumDaoImpl implements AlbumDAO{
	
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
		configuration.addAnnotatedClass(Album.class);
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
	 * Find one album by id
	 * @param id
	 * @return Album iff exists by id.
	 */
	public Album findById(String id){
		if((id!=null)&&(id!="")){
			Album a = new Album();
			a = currentSession.find(Album.class, id);
			return a;
		}else{
			return null;
		}
	}
	
	/**
	 * @return Albums list contained
	 */
	public List<Album> getAllAlbums(){
		List<Album> l = new LinkedList<Album>();
		l.addAll(currentSession.createQuery("from Album", Album.class).list());
		return l;
	}	
	
	/**
	 * @param bandName
	 * @return Albums list found by name band
	 */
	public List<Album> findByBandName(String bandName){		
		return null;
	}
	
	/**
	 * @param name
	 * @return Albums list found by title name.
	 */
	public List<Album> findByName(String name){
		List<Album> byNameList = new LinkedList<Album>();
		Query<Album> query = currentSession.createQuery("from Album where title = :name ");
		query.setParameter("name", name);
		byNameList.addAll(query.list());
		
		return byNameList;
	}
	
	/**
	 * @param genre
	 * @return Albums list found by genre
	 */
	public List<Album> findByGenere(String genre){
		return null;
	}
	
	/**
	 * @param recordLabel
	 * @return Albums list found by record label
	 */
	public List<Album> findByRecordLabel(String recordLabel){
		return null;
	}
	
	/**
	 * @param producer
	 * @return Albums list found by producer
	 */
	public List<Album> findByProducer(String producer){
		return null;
	}
	
	/**
	 * @param duration
	 * @return Albums list found by duration
	 */
	public List<Album> findByDuration(int duration){
		return null;
	}
	
	/**
	 * @param song
	 * @return Albums list found by song
	 */
	public List<Album> findBySong(String song){
		return null;
	}
	
	/**
	 * @param releaseDate
	 * @return Albums list found by release date.
	 */
	public List<Album> findByReleaseDate(Date releaseDate){
		List<Album> byReleaseDateList = new LinkedList<Album>();
		Query<Album> query = currentSession.createQuery("from Album where releaseDate =:date ");
		query.setParameter("date", releaseDate);
		byReleaseDateList.addAll(query.list());
		
		return byReleaseDateList;
		
	}
	
	/**
	 * @param album
	 * @return true iff album was inserted into data base correctly
	 */
	public boolean createAlbum(Album album){
		if (album != null){
			currentSession.save(album);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param album
	 * @return true iff the album was updated correctly
	 */
	public boolean updateAlbum(Album album){
		return false;
	}
	
	/**
	 * @param id
	 * @return true iff album was removed correctly
	 */
	public boolean deleteAlbum(String id){
		return false;
	}
	
}
