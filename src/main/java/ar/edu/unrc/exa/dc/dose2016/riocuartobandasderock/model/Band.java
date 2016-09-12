package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

public class Band {
	private Integer id;
	private List<Album> albumList;
	private List<BandMember> memberList;
	private List<Song> songList;
	private String name;
	private String release;
	private String end;
	private Genere genere;
	
	public Band(){	
	}
	
	public void setId(Integer an_id){
		id = an_id;
	}
	
	public void setAlbumList(List<Album> an_album_list){
		albumList = an_album_list;
	}
	
	public void setMemberList(List<BandMember> a_member_list){
	}
	
	public void setSongList(List<Song> a_song_list){
		songList = a_song_list;
	}
	
	public void setName(String a_name){
		name = a_name;
	}
	
	public void setRelease(String a_release){
		release = a_release;
	}
	
	public void setEnd(String an_end){
		end = an_end;
	}
	
	public void setGenere(Genere a_genere){
		genere = a_genere;
	}
	
	
}
