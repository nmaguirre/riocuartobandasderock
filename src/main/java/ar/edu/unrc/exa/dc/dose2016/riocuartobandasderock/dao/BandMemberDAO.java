package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public interface BandMemberDAO {

	
	public List<BandMember> getAllBandMember();
	public BandMember findById(int id);
	public BandMember findByName(String name);
	
	public boolean insertBandMember(BandMember bandMember);
	public boolean updateBandMember(BandMember bandMember);
	public boolean deleteBandMember(BandMember bandMember);
	
}
