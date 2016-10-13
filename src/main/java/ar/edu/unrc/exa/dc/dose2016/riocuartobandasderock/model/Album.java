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

	/** The album band performer. */
	private Band performer;

	/** The album producers. */
	private List<String> producers;

	/** The album duration in seconds. */
	private int duration;

	/** The album genre. */
	private String genre;

	/** The album styles or subgenres. */
	private List<Style> styles;

	/** The album discography. */
	private String discography;

	/** The album songs. */
	private List<Song> songs;
	
	/** The album release date. */
	private Date releaseDate;

	/** The album recording date. */
	private Date recordingDate;

	/** The album international standard recording code (ISRC). */
	private int ISRC;

	/** The album universal product code (UPC). */
	private int UPC;

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
	public Album(String title, Band performer) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.performer = performer;
	}

	/**
	 * Full parameterized constructor. Instantiates a new album setting the fields title, performer, releaseDate, duration, genre, recordingDate, discography, ISRC and UPC with their corresponding parameters.
	 *
	 * @param title The album title.
	 * @param performer The album band performer.
	 * @param releaseDate The album release date.
	 * @param duration The album duration in seconds.
	 * @param genre The album genre.
	 * @param recordingDate The album recording date.
	 * @param discography The album discography.
	 * @param ISRC The album ISRC number.
	 * @param UPC The album UPC number.
	 */
	public Album(String title, Band performer, Date releaseDate, int duration, String genre, Date recordingDate, String discography, int ISRC, int UPC) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.performer = performer;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = genre;
		this.recordingDate = recordingDate;
		this.discography = discography;
		this.ISRC = ISRC;
		this.UPC = UPC;
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
	 * Returns the Album band performer.
	 *
	 * @return performer
	 */
	public Band getPerformer() {
		return performer;
	}

	/**
	 * Sets the Album field performer with the parameter performer.
	 *
	 * @param performer The new album band performer.
	 */
	public void setPerformer(Band performer) {
		this.performer = performer;
	}

	/**
	 * Returns the Album producers.
	 *
	 * @return producers
	 */
	public List<String> getProducers() {
		return producers;
	}

	/**
	 * Sets the Album field producers with the parameter producers.
	 *
	 * @param producers The new album producers.
	 */
	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	/**
	 * Adds a specific producer to the list of producers.
	 *
	 * @param producer The new producer to be added.
	 */
	public void addProducer(String producer) {
		this.producers.add(producer);
	}

	/**
	 * Removes a specific producer from the list of producers.
	 *
	 * @param producer The producer to be removed.
	 * @return True if the producer could be removed, false otherwise.
	 */
	public Boolean removeProducer(String producer) {
		return this.producers.remove(producer);
	}

	/**
	 * Returns the album duration in seconds.
	 *
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the Album field duration with the parameter duration.
	 *
	 * @param duration The new album duration in seconds.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Returns the Album genre.
	 *
	 * @return genre
	 */
	public String geString() {
		return genre;
	}

	/**
	 * Sets the Album field genre with the parameter genre.
	 *
	 * @param genre The new album genre.
	 */
	public void seString(String genre) {
		this.genre = genre;
	}

	/**
	 * Returns the album styles.
	 *
	 * @return styles
	 */
	public List<Style> getStyles() {
		return styles;
	}

	/**
	 * Sets the Album field styles with the parameter styles.
	 *
	 * @param styles The new album styles.
	 */
	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}

	/**
	 * Adds a specific style to the list of styles.
	 *
	 * @param style The new style to be added.
	 */
	public void addStyle(Style style) {
		this.styles.add(style);
	}

	/**
	 * Removes a specific style from the list of styles.
	 *
	 * @param style The style to be removed.
	 * @return True if the style could be removed, false otherwise.
	 */
	public Boolean removeStyle(Style style) {
		return this.styles.remove(style);
	}

	/**
	 * Returns the Album discography.
	 *
	 * @return discography
	 */
	public String getDiscography() {
		return discography;
	}

	/**
	 * Sets the Album field discography with the parameter discography.
	 *
	 * @param discography The new album discography.
	 */
	public void setDiscography(String discography) {
		this.discography = discography;
	}

	/**
	 * Adds the parameter song in the specified position of the list of Songs.
	 *
	 * @param song The Song to be added.
	 * @param position Index at which the specified Song is to be inserted.
	 */
	public void addSong(Song song, int position) {
		this.songs.add(position, song);
	}

	/**
	 * Removes the song at the specified position in this list of Songs.
	 *
	 * @param position The position of the Song to be removed.
	 * @return The song removed from the list.
	 */
	public Song removeSong(int position) {
		return this.songs.remove(position);
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

	/**
	 * Returns the Album recording date.
	 *
	 * @return recordingDate
	 */
	public Date getRecordingDate() {
		return recordingDate;
	}

	/**
	 * Sets the Album field recordingDate with the parameter recordingDate.
	 *
	 * @param recordingDate The new album recording date.
	 */
	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}
	
	/**
	 * Returns the album ISRC number.
	 *
	 * @return ISRC
	 */
	public int getISRC() {
		return ISRC;
	}

	/**
	 * Sets the Album field ISRC with the parameter ISRC.
	 *
	 * @param ISRC The new album ISRC number.
	 */
	public void setISRC(int ISRC) {
		this.ISRC = ISRC;
	}

	/**
	 * Returns the album UPC number.
	 *
	 * @return UPC
	 */
	public int getUPC() {
		return UPC;
	}

	/**
	 * Sets the Album field UPC with the parameter UPC.
	 *
	 * @param UPC The new album UPC number.
	 */
	public void setUPC(int UPC) {
		this.UPC =UPC;
	}

}
