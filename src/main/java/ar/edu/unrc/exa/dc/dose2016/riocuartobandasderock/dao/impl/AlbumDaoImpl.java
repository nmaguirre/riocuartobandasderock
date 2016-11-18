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
	 * @param title
	 * @param releaseDate
	 * @return true iff album was inserted into data base correctly
	 */
	public boolean create(String title, Date releaseDate){
		if(title==null || title.equals("") ) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null or empty title");
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
					
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					try {
						if(releaseDate.compareTo(f.parse(byReleaseDate.get(i).getReleaseDate().toString())) == 0){
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
	 * This method receives the fields to be updated 
	 * and also the id of the album to be updated. 
	 * If any of the fields are null, 
	 * then you do not want to update that field. 
	 * In case of some field, which receives, not null 
	 * then it is updated with the new field.
	 * @param id
	 * @param title
	 * @param releaseDate
	 * @return true iff update was successful
	 */
	public boolean update(String id, String title, Date releaseDate){
		if (id==null) throw new IllegalArgumentException("Error : AlbumDaoImpl.update() null Id");
		Album toUpdate = this.findById(id);
		if (toUpdate==null) return false;
		//skip representation
		if (title==null && releaseDate==null) return true;
		if (title!=null && !title.equals("")){
			if (releaseDate!=null){
				toUpdate.setTitle(title);
				toUpdate.setReleaseDate(releaseDate);
				this.currentSession.saveOrUpdate(toUpdate);
				//SessionManager.getInstance().getCurrentSession().saveOrUpdate(toUpdate);
				return true;
			}
			toUpdate.setTitle(title);
			this.currentSession.saveOrUpdate(toUpdate);
			return true;
		}
		if (title==null && releaseDate!=null){
			toUpdate.setReleaseDate(releaseDate);
			this.currentSession.saveOrUpdate(toUpdate);
			return true;
		}
		return false;
	}
}
