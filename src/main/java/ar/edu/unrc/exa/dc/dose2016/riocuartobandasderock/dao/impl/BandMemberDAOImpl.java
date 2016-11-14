package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.query.Query;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Role;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOImpl implements BandMemberDAO{

	private Session currentSession = null;

	/*
	 * This method initializes the session.
	 * @param Session session, current session.
	 */
	public BandMemberDAOImpl(Session session) {
		this.currentSession = session;
	}

	@Override
	public List<BandMember> getAllBandMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BandMember> findByArtist(String artistId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method returns all the artist that has the band.
	 * @param String bandId.
	 * @return List of artist that belongs to the band with id 'bandId'.
	*/
	@Override
	public List<Artist> findByBand(String bandId) {
		if (bandId == null || bandId.equals("")) {
			throw new IllegalArgumentException(
					"the 'bandId' param for search a List of Artist can not be null or empty.");
		} else {
			Query<Artist> query = this.currentSession.createQuery(
					"select a from Artist a, BandMember bm where bm.bandID = :aID and a.id = bm.artistID",
					Artist.class);
			query.setParameter("aID", bandId);
			return query.getResultList();
		}
	}

	@Override
	public List<BandMember> findByRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BandMember findById(String idBand, String idArtist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBandMember(BandMember bandMember) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBandMember(BandMember bandMember) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBandMember(String idBand, String idArtist) {
		// TODO Auto-generated method stub
		return false;
	}

}
