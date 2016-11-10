package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOImpl implements BandMemberDAO{

	@Override
	public List<BandMember> getAllBandMembers() {
		List<BandMember> bandMemberList = new LinkedList<>();
		Query<BandMember> query;
		query = SessionManager.getInstance().getCurrentSession().createQuery("from BandMember", BandMember.class);
		bandMemberList.addAll(query.getResultList());
		return bandMemberList;
	}

	@Override
	public List<BandMember> findByArtist(String artistId) {
		if(artistId == null || artistId.equals("")){
			throw new IllegalArgumentException("the 'artistId' param for search a BandMember List by Artist can not be null or empty.");
		} else {
			Query<BandMember> query = SessionManager.getInstance().getCurrentSession().createQuery("from BandMember where artistID=:aID", BandMember.class);
			query.setParameter("aID", artistId);
			return query.getResultList();
		}
	}

	@Override
	public List<BandMember> findByBand(String bandId) {
		if(bandId == null || bandId.equals("")){
			throw new IllegalArgumentException("the 'bandId' param for search a BandMember List by Band can not be null or empty.");
		} else {
			Query<BandMember> query = SessionManager.getInstance().getCurrentSession().createQuery("from BandMember where bandID=:bID", BandMember.class);
			query.setParameter("bID", bandId);
			return query.getResultList();
		}
	}

	@Override
	public BandMember findById(String idBand, String idArtist) {
		if(idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")){
			throw new IllegalArgumentException("the 'idBand','idArtist' params for search a bandmember relation can not be null or empty.");
		} else {
			Query<BandMember> query = SessionManager.getInstance().getCurrentSession().
					createQuery("from BandMember where artistID=:aID and bandID=:bID",BandMember.class);
			query.setParameter("aID", idArtist);
			query.setParameter("bID", idBand);
			List<BandMember> resultList = query.getResultList();
			return resultList.isEmpty() ? null : resultList.get(0);
		}
	}

	@Override
	public boolean createBandMember(String idBand, String idArtist) {
		boolean areEmpty = false;
		boolean areNull = false;
		areNull = idBand == null || idArtist == null;
		if(!areNull){
			areEmpty = idArtist.equals("") || idBand.equals("");
		}
		if(areNull || areEmpty){ //I see that the arguments are valid
			throw new IllegalArgumentException("the params for create a bandmember relation can't be null or empty.");
		} else {
			BandMember bm = new BandMember(idArtist, idBand);
			SessionManager.getInstance().getCurrentSession().save(bm);
			return true;
		}
	}

	@Override
	public boolean deleteBandMember(String idBand, String idArtist) {
		if(idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")){
			throw new IllegalArgumentException("the 'idBand','idArtist' params for delete a bandmember relation can not be null or empty.");
		} else {
			boolean result = true;
			Query<BandMember> query = SessionManager.getInstance().getCurrentSession()
					.createQuery("delete BandMember where artistID=:aID and bandID=:bID",BandMember.class);
			query.setParameter("aID", idArtist);
			query.setParameter("bID", idBand);
			int afectedRows = query.executeUpdate();
			if(afectedRows == 0){
				result = false;
			}
			return result;
		}
	}

}
