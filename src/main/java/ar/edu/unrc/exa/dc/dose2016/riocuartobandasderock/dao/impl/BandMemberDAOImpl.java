package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOImpl implements BandMemberDAO {

	private Session currentSession = null;

	/*
	 * This method initializes the session.
	 * @param Session session, current session.
	 */
	public BandMemberDAOImpl(Session session) {
		this.currentSession = session;
	}

	/**
	 * This method get all bandMembers.
	 * @return List of bandMembers.
	*/
	@Override
	public List<BandMember> getAllBandMembers() {
		List<BandMember> bandMemberList = new LinkedList<>();
		Query<BandMember> query;
		query = this.currentSession.createQuery("from BandMember", BandMember.class);
		bandMemberList.addAll(query.getResultList());
		return bandMemberList;
	}

	/**
	 * This method find an bandMember in database by bandID, and artistID.
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return BandMember that have a particular bandID and artistID.
	*/
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

	/**
	 * This method create an bandMember in database.
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return true if the create was successful.
	*/
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

	/**
	 * This method delete an bandMember in database.
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return true if the delete was successful.
	*/
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

	/**
	 * This method get all bands where the artist belongs.
	 * @param String artistName.
	 * @param String artistSurname.
	 * @param String artistNickname.
	 * @return List of bands where the artist, whit artistName, artistSurname and artistNickname, belongs.
	*/
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

	/**
	 * This method get all artist of a band.
	 * @param String bandName.
	 * @return List artist of a band, whit bandName.
	*/
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
	
	/**
	 * This method get all bands where the artist belongs.
	 * @param String artistId.
	 * @return List of bands where the artist, whit artistID, belongs.
	*/
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

	/**
	 * This method get all artist of a band.
	 * @param String bandId.
	 * @return List artist of a band, whit bandID.
	*/
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

	/**
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return true if exist the BandMember in database.
	*/
	@Override
	public boolean existBandMember(String idBand, String idArtist){
		if (idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")) {
			throw new IllegalArgumentException(
					"the 'idBand','idArtist' params for search a bandmember relation can not be null or empty.");
		} else {
			boolean result = false;
			Query<BandMember> query = this.currentSession
					.createQuery("from BandMember where artistID=:aID and bandID=:bID", BandMember.class);
			query.setParameter("aID", idArtist);
			query.setParameter("bID", idBand);
			List<BandMember> resultList = query.getResultList();
			if(!resultList.isEmpty()){
				result = true;
			}
			return result;
		}
	}
}
