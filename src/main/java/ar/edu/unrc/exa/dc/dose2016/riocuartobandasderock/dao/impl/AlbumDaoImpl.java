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
		l.addAll(this.currentSession.createQuery("from Album", Album.class).getResultList());
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
	 * @param id_album
	 * @return Song list founds by album id.
	 */
	public List<Song> findSongs(String id_album){
		List<Song> songs = new LinkedList<Song>();
		if(id_album==null || id_album.equals("")){
			return null;
		}
		Query<Song> query = this.currentSession.createQuery("from Album inner join Song where AlbumID=:id and AlbumID=albumId");
		return songs;
	}
	
	/**
	 * @param title
	 * @param releaseDate
	 * @param songs
	 * @param band
	 * @return true iff album was inserted into data base correctly
	 */
	public boolean create(String title, Date releaseDate, List<Object> songs, String id_band){
		if(title==null || title.equals("") ) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null or empty title");
		if(id_band==null) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null Band");
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
					try {
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
			if (songs!=null){
				if (this.castSongsList(songs)==null) return false;//because Songs repOK not correct
			}
			album.setSongs(this.castSongsList(songs));//Album support null list songs	
			
			BandDAO bdao = new BandDaoImpl(this.currentSession);
			try{
				Band b = bdao.findById(id_band); 
				album.setBand(b);
			}catch(IllegalArgumentException e){
				System.out.println("ERROR: "+ e);
			}
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
		if (id==null || id.equals("")) return false;
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
	public boolean update(String id, String title, Date releaseDate, List<Object> songs, String id_band){
		  if (id==null || id.equals("")) throw new IllegalArgumentException("Error : AlbumDaoImpl.update() null or empty Id");
		  Album toUpdate = this.findById(id);
		  if (toUpdate==null) return false;
		  if (title==null && releaseDate==null && songs==null && id_band==null) return true;

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
		  
		  if(id_band!=null){
			  BandDAO bdao = new BandDaoImpl(this.currentSession);
			  try{
					Band b = bdao.findById(id_band); 
					toUpdate.setBand(bdao.findById(id_band));
				}catch(IllegalArgumentException e){
					System.out.println("ERROR: "+ e);
				}		    
		  }

		  this.currentSession.saveOrUpdate(toUpdate);
		  return true;
	}
}
