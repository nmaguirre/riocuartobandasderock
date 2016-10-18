package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Role;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public interface BandMemberDAO {

	/**
	* This method get all bandMembers
	* 
	* @return List of bandMembers
	*/
	public List<BandMember> getAllBandMembers();
	
	/**
	* 
	* @param String artistId, id of an Artist
	* 
	* @return BandMembers that have a particular Artist and any Band
	*/
	public List<BandMember> findByArtist(String artistId);
	
	/**
	* 
	* @param String bandId. Id of an Band
	* 
	* @return BandMembers that have a particular Band and any Artist
	*/
	public List<BandMember> findByBand(String bandId);
	
	/**
	* 
	* @param Role role
	* 
	* @return BandMembers that have artist with a particular role and any Band
	*/
	public List<BandMember> findByRole(Role role);

	/**
	* 
	* @param String idBand, String idArtist. ID of an Artist and a Band
	* 
	* @return BandMember that have a particular id
	*/
	public BandMember findById(String idBand, String idArtist);
	
	/**
	* 
	* @param BandMember bandMember
	* 
	* @return true if the create was successful
	*/
	public boolean createBandMember(BandMember bandMember);
	
	/**
	* 
	* @param BandMember bandMember
	* 
	* @return true if the update was successful
	*/
	public boolean updateBandMember(BandMember bandMember);
	
	
	/**
	* 
	* @param String idBand, String idArtist. ID of an Artist and a Band
	* 
	* @return true if the delete was successful
	*/
	public boolean deleteBandMember(String idBand, String idArtist);
	
}
