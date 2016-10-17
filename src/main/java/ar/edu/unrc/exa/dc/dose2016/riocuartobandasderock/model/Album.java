package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.Date;
import java.util.UUID;
import java.util.List;

/**
 * Class Album. The Album class models an music album.
 */
public class Album {

	/** The album ID. */
	private String id;

	/** The album title. */
	private String title;


	/** The album release date. */
	private Date releaseDate;

	
	/**
	 * Default or non-parameterized constructor. Instantiates a new empty Album.
	 */
	public Album() {
		
	}

	/**
	 * Basic parameterized constructor. Instantiates a new album setting the fields title and performer with their corresponding parameters.
	 *
	 * @param title The album title.
	 * @param performer The album band performer.
	 */
	public Album(String title) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
	}

	/**
	 * Full parameterized constructor. Instantiates a new album setting the fields title, releaseDate with their corresponding parameters.
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
	 * Sets the Album field id with the parameter id.
	 *
	 * @param id The new album id.
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * Sets the Album field releaseDate with the parameter releaseDate.
	 *
	 * @param releaseDate The new album release date.
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", releaseDate="
				+ releaseDate + "]";
	}



}
