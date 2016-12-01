package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.query.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

/**
 * This class implements the AlbumDAO interface,
 * and contains the methods necessary 
 * for representation and management.
 * @author DOSE Team 2016
 *
 */
public class AlbumDaoImpl implements AlbumDAO{
//	/**
//	 * this.currentSession represents a Session.
//	 */
//	private SessionManager SessionManager;
//	/*
//	 * currentTransaction represents a Session with Transaction.
//	 */
//	private Transaction currentTransaction;
	
	private Session currentSession=null;
	
	public AlbumDaoImpl(Session session) {
		this.currentSession = session;
	}

	/**
	 * Find one album by id
	 * @param id
	 * @return Album iff exists by id.
	 */
	public Album findById(String id){
		if((id!=null)&&(id!="")){
			Album a = new Album();
			a = this.currentSession.find(Album.class, id);
			if(a==null){
				return null;
			}else{
				a.setSongs(this.findSongs(a.getId()));
				return a;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * @return Albums list contained
	 */
	public List<Album> getAll(){
		List<Album> l = new LinkedList<Album>();
		l.addAll(this.currentSession.createQuery("from Album", Album.class).getResultList());
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setSongs(this.findSongs(l.get(i).getId()));		
		}
		return l;
	}	
	
	
	/**
	 * @param title
	 * @return Albums list found by title
	 */
	public List<Album> findByTitle(String title){
		List<Album> byNameList = new LinkedList<Album>();
		if (title!=null){
			Query<Album> query = this.currentSession.createQuery("from Album where title = :title ");
			query.setParameter("title", title);
			byNameList.addAll(query.getResultList());
			for (int i = 0; i < byNameList.size(); i++) {
				byNameList.get(i).setSongs(this.findSongs(byNameList.get(i).getId()));
			}
		}
		return byNameList;
	}
	
	
	/**
	 * @param releaseDate
	 * @return List Albums found by release date.
	 */
	public List<Album> findByReleaseDate(Date releaseDate){
		List<Album> byReleaseDateList = new LinkedList<Album>();
		if (releaseDate!=null){
			Query<Album> query = this.currentSession.createQuery("from Album where releaseDate =:date ");
			query.setParameter("date", releaseDate);
			byReleaseDateList.addAll(query.getResultList());
			for (int i = 0; i < byReleaseDateList.size(); i++) {
				byReleaseDateList.get(i).setSongs(this.findSongs(byReleaseDateList.get(i).getId()));
			}
		}		
		return byReleaseDateList;		
	}
	
	public List<Song> findSongs(String id_album){
		List<Song> songs = new LinkedList<Song>();
		if(id_album==null || id_album.equals("")){
			return songs;
		}
		Query<Song> query = this.currentSession.createQuery("Select s FROM Song as s INNER JOIN Album as a ON a.id =:id and a.id = s.album");
			
		query.setParameter("id", id_album);
		
		songs.addAll(query.getResultList());
		return songs;
	}
	
	/**
	 * @param title
	 * @param releaseDate
	 * @param songs
	 * @param band
	 * @return true iff album was inserted into data base correctly
	 */
	public boolean create(String title, Date releaseDate, String id_band){
		if(title==null || title.equals("") ) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null or empty title");
		if(id_band==null) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null Band");
		
		if(releaseDate == null){
			List<Album> byTitle = this.findByTitle(title);
			for(int i=0;i<byTitle.size();i++){
				if(byTitle.get(i).getTitle().compareTo(title) == 0){
					return false;
				}
			}
		}else if(releaseDate != null){
			List<Album> byTitle = this.findByTitle(title);
			for (int i = 0; i < byTitle.size(); i++) {
				try {
					if((byTitle.get(i).getTitle().compareTo(title)==0)&&(byTitle.get(i).getReleaseDate().compareTo(releaseDate)==0)){
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Album album = new Album(title,releaseDate);
		BandDAO bdao = new BandDaoImpl(this.currentSession);
		try{
			Band b = bdao.findById(id_band);
			album.setBand(b);
		}catch(IllegalArgumentException e){
			System.out.println("ERROR: "+ e);
		}
		if (!album.repOk()) throw new IllegalArgumentException ("Bad representation of album");
		this.currentSession.save(album);
		return true;
	} 
	/**
	 * This method deletes an album found by id
	 * @param id
	 * @return true iff album was delete
	 */
	public boolean delete(String id){
		Album toDelete = this.findById(id);
		if (toDelete!= null) {
			this.currentSession.delete(toDelete); 
			return true;
		}
		return false;
	}
	
	/**
	 * This private method 'cast' the list of songs, 
	 * if a song does not surpass the repOk then returns a null list.
	 * @param songs
	 * @return Songs List that was casted
	 */
	private List<Song> castSongsList (List<Object> songs){
		List<Song> parseSongs = new LinkedList<Song>();
		if (songs==null) return parseSongs;
		for (int i=0; i<songs.size();i++){
			if (!( (Song) songs.get(i) ).repOk() ) return null;
			parseSongs.add( (Song) songs.get(i) );
		}
		return parseSongs;
	}
	
	/**
	 * This method receives the fields to be updated 
	 * and also the id of the album to be updated. 
	 * If any of the fields are null, 
	 * then you do not want to update that field. 
	 * In case of some field, which receives, not null 
	 * then it is updated with the new field.
	 * @param id
	 * @param title
	 * @param releaseDate
	 * @param songs
	 * @return true iff update was successful
	 */
	public boolean update(String id, String title, Date releaseDate, String id_band){
		  if (id==null || id.equals("")) throw new IllegalArgumentException("Error : AlbumDaoImpl.update() null or empty Id");
		  Album toUpdate = this.findById(id);
		  if (toUpdate==null) return false;
		  if (title==null && releaseDate==null && id_band==null) return true;
		  
		  if(title!=null && releaseDate!=null){
			if(title.equals("")){
				return false;
			}
			List<Album> byTitle = this.findByTitle(title);
			for (int i = 0; i < byTitle.size(); i++) {
				try {
					if ((byTitle.get(i).getTitle().compareTo(title)==0)&&(byTitle.get(i).getReleaseDate().compareTo(releaseDate)==0)){
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    toUpdate.setTitle(title);
		    toUpdate.setReleaseDate(releaseDate);
		  }
		  
		  if(title==null && releaseDate!=null){
			  List<Album> byTitle = this.findByTitle(toUpdate.getTitle());
			  for (int i = 0; i < byTitle.size(); i++) {
				try {
					if ((byTitle.get(i).getTitle().compareTo(toUpdate.getTitle())==0)&&(byTitle.get(i).getReleaseDate().compareTo(releaseDate)==0)){
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  toUpdate.setReleaseDate(releaseDate);
		  }
		  
		  if(title!=null && releaseDate==null){
			  if(title.equals("")){
				  return false;
			  }
			  try {
				  List<Album> byDate = this.findByReleaseDate(toUpdate.getReleaseDate());
				  for (int i = 0; i < byDate.size(); i++) {
					  if ((byDate.get(i).getTitle().compareTo(title)==0)&&(byDate.get(i).getReleaseDate().compareTo(toUpdate.getReleaseDate())==0)){
						  return false;
					  }
				  }
			  } catch (Exception e1) {
				// TODO Auto-generated catch block
				  e1.printStackTrace();
			  }
			  toUpdate.setTitle(title);
		  }
		  
		  if(id_band!=null){
			BandDAO bdao = new BandDaoImpl(this.currentSession);
		    toUpdate.setBand(bdao.findById(id_band));
		  }
		  
		  this.currentSession.saveOrUpdate(toUpdate);
		  return true;
	}
}
