package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

public class Band {
  private Integer id;
  private List<Album> albumList;
  private List<BandMember> memberList;
  private String name;
  private String release;
  private String end;
  private Genere genere;

  public Band(Integer an_id, String a_name, Genere a_genere, List<BandMember> list_of_members, String a_release){
    id = an_id;
	name = a_name;
	genere = a_genere;
	memberList = list_of_members;
	release = a_release;
  }

  public Band(Integer an_id, String a_name, Genere a_genere, List<BandMember> list_of_members, String a_release, List<Album> list_of_albums){
	id = an_id;
	name = a_name;
	genere = a_genere;
	memberList = list_of_members;
	albumList = list_of_albums;
	release = a_release;
  }

  
  public Integer getId(){
    return id;
  }
  
  public List<Album> getAlbum(){
    return albumList;
  }
  
  public List<BandMember> getBandMember(){
    return memberList;
  }
  
  public String getName(){
    return name;
  }
  
  public String getRelease(){
    return release;
  }
  
  public String getEnd(){
    return end;
  }
  
  public Genere getGenere(){
    return genere;
  }
    
  public void setId(Integer an_id){
    id = an_id;
  }
  
  public void setAlbumList(List<Album> an_album_list){
    albumList = an_album_list;
  }
  
  public void setMemberList(List<BandMember> a_member_list){
	  memberList = a_member_list;
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