package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class Album models a music album.
 * @author Gabriela Bertorello
 * @author Mariano Ontivero
 */
@Entity
@Table(name = "AlbumDB")
public class Album {

	/** The album ID. */
	@Id
	@Column(name = "AlbumID")
	private String id;

	/** The album title. */
	@Column(name = "title")
	private String title;

	/** The album release date. */
	@Column(name = "releaseDate")
	private Date releaseDate;

	/** The album songs. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = CascadeType.ALL)
	private List<Song> songs = new LinkedList<Song>();

	/**
	 * Default or non-parameterized constructor. Instantiates a new empty Album.
	 */
	public Album() {
		
	}

	/**
	 * Basic parameterized constructor. Instantiates a new album setting the field title with their corresponding parameter.
	 *
	 * @param title The album title.
	 */
	public Album(String title) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
	}

	/**
	 * Full parameterized constructor. Instantiates a new album setting the fields title and releaseDate with their corresponding parameters.
	 *
	 * @param title The album title.
	 * @param releaseDate The album release date.
	 */

	public Album(String title, Date releaseDate) {

		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.releaseDate = releaseDate;
	}

	/**
	 * Returns the Album ID.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the Album title.
	 *
	 * @return title
	 */
	 public String getTitle() {
		return title;
	}

	 /**
	  * Sets the Album field title with the parameter title.
	  *
	  * @param title The new album title.
	  */
	public void setTitle(String title) {
		this.title = title;
	}

	/**

	 * Returns the album release date.
	 *
	 * @return relaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	public String toString(){
		if (releaseDate != null)
			return releaseDate.toString();
		else return " ";
	} 

	/**
	 * Sets the Album field releaseDate with the parameter releaseDate.
	 *
	 * @param releaseDate The new album release date.
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Returns The Album Songs.
	 *
	 * @return songs
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * Sets the Album field songs with the parameter songs.
	 *
	 * @param songs The new album songs.
	 */
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	/**
	 * This method return if the Album's representation is correct 
	 *
	 * @return true if the Album's representation is correct, otherwise false
	 */
	public boolean repOk() {
		return (this.title != null) && (! this.title.isEmpty()); //&&  (! this.getSongs().isEmpty())
	}
	
}
