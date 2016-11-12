package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOImpl implements BandMemberDAO{

	private Session currentSession=null;
	
	public BandMemberDAOImpl(Session session) {
		this.currentSession = session;
	}
	
	@Override
	public List<BandMember> getAllBandMembers() {
		List<BandMember> bandMemberList = new LinkedList<>();
		Query<BandMember> query;
		query = this.currentSession.createQuery("from BandMember", BandMember.class);
		bandMemberList.addAll(query.getResultList());
		return bandMemberList;
	}

	@Override
	public BandMember findById(String idBand, String idArtist) {
		if(idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")){
			throw new IllegalArgumentException("the 'idBand','idArtist' params for search a bandmember relation can not be null or empty.");
		} else {
			Query<BandMember> query = this.currentSession.
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
			this.currentSession.save(bm);
			return true;
		}
	}

	@Override
	public boolean deleteBandMember(String idBand, String idArtist) {
		if(idBand == null || idArtist == null || idBand.equals("") || idArtist.equals("")){
			throw new IllegalArgumentException("the 'idBand','idArtist' params for delete a bandmember relation can not be null or empty.");
		} else {
			boolean result = true;
			Query<BandMember> query = this.currentSession.createQuery("delete BandMember where artistID=:aID and bandID=:bID",BandMember.class);
			query.setParameter("aID", idArtist);
			query.setParameter("bID", idBand);
			int afectedRows = query.executeUpdate();
			if(afectedRows == 0){
				result = false;
			}
			return result;
		}
	}

	@Override
	public List<Band> findByArtistByAttributes(String artistName, String artistSurname, String artistNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artist> findByBandByAttributes(String bandName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Band> findByArtist(String artistId) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public List<Artist> findByBand(String bandId) {
		// TODO Auto-generated method stub
				return null;
	}

}
