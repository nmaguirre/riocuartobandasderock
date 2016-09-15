package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public interface BandMemberDAO {

	
	public List<BandMember> getAllBandMember();
	
	public List<BandMember> findByName(String name);
	
	public List<BandMember> findByRole(String role); 

	public BandMember findById(int id);
	
	public boolean createBandMember(BandMember bandMember);
	
	public boolean updateBandMember(BandMember bandMember);
	
	public boolean deleteBandMember(BandMember bandMember);
	
}
