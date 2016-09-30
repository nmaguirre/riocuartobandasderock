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
        Configuration configuration = new Configuration().addPackage("models").configure( "hibernate.cfg.xml").addAnnotatedClass(Artist.class);
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
