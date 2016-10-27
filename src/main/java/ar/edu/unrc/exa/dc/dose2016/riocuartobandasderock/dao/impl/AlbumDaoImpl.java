package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
/**
 * This class implements the AlbumDAO interface,
 * and contains the methods necessary 
 * for representation and management.
 * @author DOSE Team 2016
 *
 */
public class AlbumDaoImpl implements AlbumDAO{
	/**
	 * currentSession represents a Session.
	 */
	private Session currentSession;
	/**
	 * currentTransaction represents a Session with Transaction.
	 */
	private Transaction currentTransaction;
	
	/**
	 * Find one album by id
	 * @param id
	 * @return Album iff exists by id.
	 */
	public Album findById(String id){
		if((id!=null)&&(id!="")){
			Album a = new Album();
			a = currentSession.find(Album.class, id);
			return a;
		}else{
			return null;
		}
	}
	
	/**
	 * @return Albums list contained
	 */
	public List<Album> getAllAlbums(){
		List<Album> l = new LinkedList<Album>();
		l.addAll(currentSession.createQuery("from Album", Album.class).list());
		return l;
	}	
	
	
	/**
	 * @param name
	 * @return Albums list found by title name.
	 */
	public List<Album> findByName(String name){
		List<Album> byNameList = new LinkedList<Album>();
		Query<Album> query = currentSession.createQuery("from Album where title = :name ");
		query.setParameter("name", name);
		byNameList.addAll(query.list());
		
		return byNameList;
	}
	
	
	/**
	 * @param releaseDate
	 * @return Albums list found by release date.
	 */
	public List<Album> findByReleaseDate(Date releaseDate){
		List<Album> byReleaseDateList = new LinkedList<Album>();
		if (releaseDate!=null){
			Query<Album> query = currentSession.createQuery("from Album where releaseDate =:date ");
			query.setParameter("date", releaseDate);
			byReleaseDateList.addAll(query.list());
		}		
		return byReleaseDateList;		
	}
	
	/**
	 * @param album
	 * @return true iff album was inserted into data base correctly
	 */
	public boolean createAlbum(String title, Date releaseDate){
		if(title==null ) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : Database doesnt support null title");
		if(title.equals("") && releaseDate==null) throw new IllegalArgumentException("Error: AlbumDaoImpl.createAlbum() : incorrect parameters");
		boolean isCreated=false;
		if(!title.equals("")){
			//case title=some and releaseDate=(?)
			List<Album> lt = this.findByName(title);
			for(int i=0;i<lt.size();i++){
				if(lt.get(i).getReleaseDate().equals(releaseDate)){
					return false;
				}
			}
			Album album = new Album(title,releaseDate);
			currentSession.save(album);
			isCreated=true;
		}else if(releaseDate!=null){
			//case title=(?) and releaseDate= some
			List<Album> byReleaseDate = this.findByReleaseDate(releaseDate);
			for(int i=0; i<byReleaseDate.size();i++){
				if( byReleaseDate.get(i).getTitle().equals(title) ){
					return false;
				}
			}
			Album album = new Album(title,releaseDate);
			currentSession.save(album);
			isCreated=true;
		}		
		return isCreated;
	} 
	
}
