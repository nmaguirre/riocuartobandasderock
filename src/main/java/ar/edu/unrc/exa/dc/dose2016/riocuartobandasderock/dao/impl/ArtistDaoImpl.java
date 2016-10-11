package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public class ArtistDaoImpl implements ArtistDAO{

	@Override
	public List<Artist> getAllArtists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artist> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Artist findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createArtist(Artist artist) {
	  Configuration configuration = new Configuration().addPackage("models");
	  configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
	  configuration.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");
	  configuration.setProperty("hibernate.connection.username","rock_db_owner");
	  configuration.setProperty("hibernate.connection.password","rockenrio4");
	  configuration.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/rcrockbands");
	  configuration.setProperty("connection_pool_size","1");
	  configuration.setProperty("hibernate.hbm2ddl.auto","update");
	  configuration.setProperty("show_sql","false");
	  configuration.setProperty("hibernate.current_session_context_class","thread");
	  configuration.addAnnotatedClass(Artist.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		Session currentSession = sessionFactory.openSession();
        Transaction currentTransaction = currentSession.beginTransaction();
		currentSession.save(artist);
		currentTransaction.commit();
		currentSession.close();
		return true;
	}

	@Override
	public boolean updateArtist(Artist artist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteArtist(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
