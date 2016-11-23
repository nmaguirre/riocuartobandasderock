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
			return a;
		}else{
			return null;
		}
	}
	
	/**
	 * @return Albums list contained
	 */
	public List<Album> getAll(){
		List<Album> l = new LinkedList<Album>();
		System.out.println("antes");
		l.addAll(this.currentSession.createQuery("from Album", Album.class).getResultList());
		System.out.println("despues");
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
		}		
		return byReleaseDateList;		
	}
	
	/**
	 * @param title
	 * @param releaseDate
	 * @param songs
	 * @param band
	 * @return true iff album was inserted into data base correctly
	 */
	public boolean create(String title, Date releaseDate, List<Object> songs, Object band){
		if(title==null || title.equals("") ) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null or empty title");
		if(band==null) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null Band");
		boolean isCreated=false;
		if(releaseDate!=null){
			//then the title and the day should not be in db. 
			List<Album> byTitle = this.findByTitle(title);
			List<Album> byReleaseDate = this.findByReleaseDate(releaseDate);
			
			boolean exist=false;
			for (int i = 0; i < byTitle.size(); i++) {
				if (byTitle.get(i).getTitle().compareTo(title)==0){
					exist=true;
				}
			}			
			if(exist){
				//then releaseDate not be in db
				for(int i=0;i<byReleaseDate.size();i++){
					System.out.println(releaseDate.toString());
					try {
						System.out.println(releaseDate+" - "+byReleaseDate.get(i).getReleaseDate());
						if(releaseDate.compareTo(byReleaseDate.get(i).getReleaseDate()) == 0){
							return false;
						}	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				isCreated=true;				
			}
			else{				
				isCreated=true;
			}
		}else if(releaseDate==null){
			//then the title not be in db.
			List<Album> byTitle = this.findByTitle(title);
			for(int i=0;i<byTitle.size();i++){
				if(byTitle.get(i).getTitle().compareTo(title) == 0){
					return false;
				}
			}
			isCreated=true;
		}
		if (isCreated){
			Album album = new Album(title,releaseDate);
			if (this.castSongsList(songs)!=null) album.setSongs(this.castSongsList(songs));
			if (this.castBand(band)!=null) album.setBand(this.castBand(band));
			if (!album.repOk()) throw new IllegalArgumentException ("Bad representation of album");
			this.currentSession.save(album);
		}
		return isCreated;
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
		if (songs==null) return null;
		for (int i=0; i<songs.size();i++){
			if (!( (Song) songs.get(i) ).repOk() ) return null;
			parseSongs.add( (Song) songs.get(i) );
		}
		return parseSongs;
	}
	
	/**
	 * This private method 'cast' the Band, 
	 * if an band does not surpass the repOk then returns a null.
	 * @param band
	 * @return band that was casted or null
	 */	
	private Band castBand (Object band){
		if (((Band)band).repOK()){
			return (Band) band;
		}
		return null;
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
	public boolean update(String id, String title, Date releaseDate, List<Object> songs, Object band){
		  if (id==null || id.equals("")) throw new IllegalArgumentException("Error : AlbumDaoImpl.update() null or empty Id");
		  Album toUpdate = this.findById(id);
		  if (toUpdate==null) return false;
		  if (title==null && releaseDate==null && songs==null && band==null) return true;

		  if(title!=null){
			if(title.equals("")){
				return false;
			}
		    toUpdate.setTitle(title);
		  }

		  if(releaseDate!=null){
		    toUpdate.setReleaseDate(releaseDate);
		  }

		  if(songs!=null){
		    if(this.castSongsList(songs)==null){
		      return false;
		    }
		    toUpdate.setSongs(this.castSongsList(songs));
		  }
		  
		  if(band!=null){
		    if(this.castBand(band)==null){
		      return false;
		    }
		    toUpdate.setBand(this.castBand(band));
		  }

		  this.currentSession.saveOrUpdate(toUpdate);
		  return true;
//		//skip representation
//		if (title==null && releaseDate==null && songs==null) return true;
//		if (title!=null && !title.equals("")){
//			if (releaseDate!=null){
//				toUpdate.setTitle(title);
//				toUpdate.setReleaseDate(releaseDate);
//				if (this.castSongsList(songs)!=null) toUpdate.setSongs(this.castSongsList(songs));
//				this.currentSession.saveOrUpdate(toUpdate);
//				//SessionManager.getInstance().getCurrentSession().saveOrUpdate(toUpdate);
//				return true;
//				
//			}
//			toUpdate.setTitle(title);
//			if (this.castSongsList(songs)!=null) toUpdate.setSongs(this.castSongsList(songs));
//			this.currentSession.saveOrUpdate(toUpdate);
//			return true;
//		}
//		if (title==null && releaseDate!=null){
//			toUpdate.setReleaseDate(releaseDate);
//			if (this.castSongsList(songs)!=null) toUpdate.setSongs(this.castSongsList(songs));
//			this.currentSession.saveOrUpdate(toUpdate);
//			return true;
//		}
//		if (title==null && releaseDate == null && songs!=null){
//			if (this.castSongsList(songs)!=null){ 
//				toUpdate.setSongs(this.castSongsList(songs));
//				this.currentSession.saveOrUpdate(toUpdate);
//				return true;
//			}
//		}
//		if (this.castSongsList(songs)==null) return false;
//		return false;
	}

	@Override
	public boolean create(String title, Date releaseDate, List<Object> songs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String id, String title, Date releaseDate,
			List<Object> songs) {
		// TODO Auto-generated method stub
		return false;
	}
}
