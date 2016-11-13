package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.sql.Select;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOImpl implements BandMemberDAO {

	private Session currentSession = null;

	public BandMemberDAOImpl(Session session) {
		this.currentSession = session;
	}

	//working
	@Override
	public List<BandMember> getAllBandMembers() {
		List<BandMember> bandMemberList = new LinkedList<>();
		Query<BandMember> query;
		query = this.currentSession.createQuery("from BandMember", BandMember.class);
		bandMemberList.addAll(query.getResultList());
		return bandMemberList;
	}

	//working
	@Override
	public BandMember findById(String idBand, String idArtist) {
		if (idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")) {
			throw new IllegalArgumentException(
					"the 'idBand','idArtist' params for search a bandmember relation can not be null or empty.");
		} else {
			Query<BandMember> query = this.currentSession
					.createQuery("from BandMember where artistID=:aID and bandID=:bID", BandMember.class);
			query.setParameter("aID", idArtist);
			query.setParameter("bID", idBand);
			List<BandMember> resultList = query.getResultList();
			return resultList.isEmpty() ? null : resultList.get(0);
		}
	}

	//working
	@Override
	public boolean createBandMember(String idBand, String idArtist) {
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = idBand == null || idArtist == null;
		if (!areNull) {
			areEmpty = idArtist.equals("") || idBand.equals("");
		}
		if (areNull || areEmpty) { // I see that the arguments are valid
			throw new IllegalArgumentException("the params for create a bandmember relation can't be null or empty.");
		} else {
			BandMember bm = new BandMember(idArtist, idBand);
			this.currentSession.save(bm);
			return true;
		}
	}

	//working
	@Override
	public boolean deleteBandMember(String idBand, String idArtist) {
		if (idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")) {
			throw new IllegalArgumentException(
					"the 'idBand','idArtist' params for delete a bandmember relation can not be null or empty.");
		} else {
			boolean result = true;
			Query<BandMember> query = this.currentSession
					.createQuery("delete BandMember where artistID=:aID and bandID=:bID");
			query.setParameter("aID", idArtist);
			query.setParameter("bID", idBand);
			int afectedRows = query.executeUpdate();
			if (afectedRows == 0) {
				result = false;
			}
			return result;
		}
	}

	//working
	@Override
	public List<Band> findByArtistByAttributes(String artistName, String artistSurname, String artistNickname) {
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = artistName == null || artistNickname == null || artistNickname == null;
		if(!areNull){
			areEmpty = artistName.equals("") && artistNickname.equals("") && artistSurname.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for search band list can't be null or empty.");
		} else {
			Query<Band> query = this.currentSession.createQuery(
					"select b from Artist a, BandMember bm, Band b "
					+ "where bm.artistID = a.artistID and b.id = bm.bandID and "
					+ "a.name = :aName and a.surname = :aSurname and a.nickname = :aNickname",
					Band.class);
			query.setParameter("aName", artistName);
			query.setParameter("aNickname", artistNickname);
			query.setParameter("aSurname", artistSurname);
			return query.getResultList();
		}
	}

	//working
	@Override
	public List<Artist> findByBandByAttributes(String bandName) {
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = bandName == null;
		if(!areNull){
			areEmpty = bandName.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for search artist list can't be null or empty.");
		} else {
			Query<Artist> query = this.currentSession.createQuery(
					"select a from Artist a, BandMember bm, Band b "
					+ "where bm.bandID = b.id and a.artistID = bm.artistID and b.name = :bName",
					Artist.class);
			query.setParameter("bName", bandName);
			return query.getResultList();
		}
	}
	
	//working
	@Override
	public List<Band> findByArtist(String artistId) {
		if (artistId == null || artistId.equals("")) {
			throw new IllegalArgumentException(
					"the 'artistId' param for search a Band List by Artist can not be null or empty.");
		} else {
			Query<Band> query = this.currentSession.createQuery(
					"select b from Band b, BandMember bm where bm.artistID = :aID and b.id = bm.bandID",
					Band.class);
			query.setParameter("aID", artistId);
			return query.getResultList();
		}
	}

	//working
	@Override
	public List<Artist> findByBand(String bandId) {
		if (bandId == null || bandId.equals("")) {
			throw new IllegalArgumentException(
					"the 'bandId' param for search an Artist List by Band can not be null or empty.");
		} else {
			Query<Artist> query = this.currentSession.createQuery(
					"select a from Artist a, BandMember bm where bm.bandID = :bID and a.artistID = bm.artistID",
					Artist.class);
			query.setParameter("bID", bandId);
			return query.getResultList();
		}
	}

	public static void main(String args[]){
		int i = 14;
		Session sessionArtist = SessionManager.getInstance().openSession();
		Session sessionBand = SessionManager.getInstance().openSession();
		Session sessionBM = SessionManager.getInstance().openSession();
		ArtistDAO aDao = new ArtistDaoImpl(sessionArtist);
		BandDAO bDao = new BandDaoImpl(sessionBand);
		BandMemberDAO bmDao = new BandMemberDAOImpl(sessionBM);
		List<Band> band;
		List<Artist> artist;
		
		String idArtist;
		String idBand;
		String bandName = "name";
		String bandGenre = "genre";
		String arName = "name";
		String arNickname = "nickname";
		String arSurname = "surname";
		
		
		//creo banda y artista
		sessionBand.beginTransaction();
		bDao.createBand(bandName+i, bandGenre+i);
		sessionBand.getTransaction().commit();
		sessionArtist.beginTransaction();
		aDao.createArtist(arName+i, arSurname+i, arNickname+i);
		sessionArtist.getTransaction().commit();
		
		band = bDao.findByNameAndGenre(bandName+i, bandGenre+i);
		artist = aDao.getArtist(arName+i, arSurname+i, arNickname+i);
		
		idArtist = artist.get(0).getId();
		idBand = band.get(0).getId();
		bandName = band.get(0).getName();
		bandGenre = band.get(0).getGenre();
		arName = artist.get(0).getName();
		arNickname = artist.get(0).getNickname();
		arSurname = artist.get(0).getSurname();
		
		System.out.println("id de artista creado: "+idArtist);
		System.out.println("id de banda creado: "+idBand);
		
		//creo la relacion
		sessionBM.beginTransaction();
		bmDao.createBandMember(idBand, idArtist);
		sessionBM.getTransaction().commit();
		System.out.println("se creo la relacion entre: "+idArtist+" y "+idBand);
		
		//busqueda de bandas por ArtistID
		System.out.println("-- Busqueda por ArtistID : "+idArtist+" tiene las bandas: ");
		List<Band> bandList = bmDao.findByArtist(idArtist);
		
		for(Band b : bandList){
			System.out.println(b.getName());
			System.out.println(b.getGenre());
		}
		
		//busqueda de bandas por atributos del artista
		System.out.println("-- Busqueda por atributos de artistas: name="+arName+" surname="+arSurname+" nickname="+arNickname+" tiene las bandas: ");
		bandList = bmDao.findByArtistByAttributes(arName, arSurname, arNickname);		
		for(Band b : bandList){
			System.out.println(b.getName());
			System.out.println(b.getGenre());
		}
		//busqueda de artistas por BandID
		System.out.println("-- Busqueda por BandID : "+idBand+" tiene los artistas: ");
		List<Artist> artistList = bmDao.findByBand(idBand);
		
		for(Artist a : artistList){
			System.out.println(a.getName());
			System.out.println(a.getSurname());
			System.out.println(a.getNickname());
		}
		
		//busqueda de artistas por BandName
				System.out.println("-- Busqueda por BandName : "+bandName+" tiene los artistas: ");
		artistList = bmDao.findByBandByAttributes(bandName);
		
		for(Artist a : artistList){
			System.out.println(a.getName());
			System.out.println(a.getSurname());
			System.out.println(a.getNickname());
		}
		
		
		sessionArtist.close();
		sessionBand.close();
		sessionBM.close();
		
	}
}
