package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.List;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Role;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public class BandMemberDAOImpl implements BandMemberDAO{

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

	@Override
	public List<BandMember> findByBand(String bandId) {
		// TODO Auto-generated method stub
		return null;
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
