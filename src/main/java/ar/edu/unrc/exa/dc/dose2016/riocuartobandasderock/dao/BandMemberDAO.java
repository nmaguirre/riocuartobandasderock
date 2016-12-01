package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;

public interface BandMemberDAO {

	/**
	 * This method get all bandMembers.
	 * @return List of bandMembers.
	*/
	public List<BandMember> getAllBandMembers();

	/**
	 * This method get all bands where the artist belongs.
	 * @param String artistId.
	 * @return List of bands where the artist, whit artistID, belongs.
	*/
	public List<Band> findByArtist(String artistId);
	
	/**
	 * This method get all bands where the artist belongs.
	 * @param String artistName.
	 * @param String artistSurname.
	 * @param String artistNickname.
	 * @return List of bands where the artist, whit artistName, artistSurname and artistNickname, belongs.
	*/
	public List<Band> findByArtistByAttributes(String artistName,String artistSurname, String artistNickname);
	
	/**
	 * This method get all artist of a band.
	 * @param String bandId.
	 * @return List artist of a band, whit bandID.
	*/
	public List<Artist> findByBand(String bandId);
	
	/**
	 * This method get all artist of a band.
	 * @param String bandName.
	 * @return List artist of a band, whit bandName.
	*/
	public List<Artist> findByBandByAttributes(String bandName);
	
	/**
	 * This method find an bandMember in database by bandID, and artistID.
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return BandMember that have a particular bandID and artistID.
	*/
	public BandMember findById(String idBand, String idArtist);
	
	/**
	 * This method create an bandMember in database.
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return true if the create was successful.
	*/
	public boolean createBandMember(String idBand, String idArtist);
	
	/**
	 * This method delete an bandMember in database.
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return true if the delete was successful.
	*/
	public boolean deleteBandMember(String idBand, String idArtist);
	
	/**
	 * @param String idBand, band ID.
	 * @param String idArtist, artist ID.
	 * @return true if exist the BandMember in database.
	*/
	public boolean existBandMember(String idBand, String idArtist);
}
