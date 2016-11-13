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

	//not working
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

	//not working
	@Override
	public List<Band> findByArtistByAttributes(String artistName, String artistSurname, String artistNickname) {
		List<Band> result = new LinkedList<>();
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = artistName == null || artistNickname == null || artistNickname == null;
		if(!areNull){
			areEmpty = artistName.equals("") && artistNickname.equals("") && artistSurname.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for search band list can't be null or empty.");
		} else {
			/*Query<Band> query = this.currentSession.createQuery(
					"select band.bandID, band.name, band.genre, "
					+ "from BandMember bm join bm.bandID band where bm.artistID = :aID",
					Band.class);
			query.setParameter("aID", artistId);
			result= query.getResultList();
			*/
			result = null;
		}
		return result;
	}

	//not working
	@Override
	public List<Artist> findByBandByAttributes(String bandName) {
		List<Artist> result = new LinkedList<>();
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = bandName == null;
		if(!areNull){
			areEmpty = bandName.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for search artist list can't be null or empty.");
		} else {
			/*Query<Band> query = this.currentSession.createQuery(
					"select band.bandID, band.name, band.genre, "
					+ "from BandMember bm join bm.bandID band where bm.artistID = :aID",
					Band.class);
			query.setParameter("aID", artistId);
			result= query.getResultList();
			*/
			result = null;
		}
		return result;
	}
	
	//not working
	@Override
	public List<Band> findByArtist(String artistId) {
		if (artistId == null || artistId.equals("")) {
			throw new IllegalArgumentException(
					"the 'artistId' param for search a Band List by Artist can not be null or empty.");
		} else {
			Query<Band> query = this.currentSession.createQuery(
					"select band.bandID, band.name, band.genre "
					+ "from BandMember bm join bm.bandID band where bm.artistID = :aID",
					Band.class);
			query.setParameter("aID", artistId);
			return query.getResultList();
		}
	}

	//not working
	@Override
	public List<Artist> findByBand(String bandId) {
		if (bandId == null || bandId.equals("")) {
			throw new IllegalArgumentException(
					"the 'bandId' param for search an Artist List by Band can not be null or empty.");
		} else {
			Query<Artist> query = this.currentSession.createQuery(
					"select art.artistID, art.name, art.surname, art.nickname "
					+ "from BandMember bm join bm.artistID art where bm.bandID = :bID",
					Artist.class);
			query.setParameter("bID", bandId);
			return query.getResultList();
		}
	}

	
}
