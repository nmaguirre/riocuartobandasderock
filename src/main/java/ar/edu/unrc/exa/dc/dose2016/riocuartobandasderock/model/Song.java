package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents a song played by Rio Cuarto's bands 
 */

@Entity
@Table(name = "Songs")
public class Song {
	
   /**
	* id represents the id of the song
	* nameSong represents the name of the song
	* genere represents the genere of the song
	* performBy represents the bands from Rio Cuarto who played this song
	* duration represents the duration of this song
	* author represents the author who composed the song
	* album represents the album where the song belongs
	*/
	
	@Id
	@Column(name = "idSong")
	private int id;
	
	@Column(name = "nameSong")
	private String nameSong;
	
	@Column(name = "genre")
	private TGenre genre;  
	
	//@OneToMany(cascade= CascadeType.ALL)
	//@JoinColumn(name="IdSong")
	//@IndexColumn(name="bandId")
	private List<Band> performBy;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "author")
	private String author;	//will be changed to type People
	
	@Column(name = "album")
	private Album album;
	
	/**
	 * Default constructor of class song
	 */
	public Song() {

	}
	
	/**
	 * Constructor of the class Song with parameters
	 * @param i represent the id of the song to assign
	 * @param nsong represents the name of the song to assign
	 * @param gen represents the genre of the song to assign
	 * @param band represents the band's name of the song to assign
	 * @param dur represents the duration of the song to assign
	 * @param aut represents the author of the song to assign
	 * @param alb represent the album to assign
	 */
	public Song(int i, String nsong, TGenre gen, List<Band> band, int dur, String aut, Album alb){
		id = i;
		nameSong = nsong;
		genre = gen;
		performBy = band;
		duration = dur;
		author = aut;
		album = alb;
	}

// Seters and geters of the method song............
	
	/**
	 * @return the id of the song
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Sets the id of the song
	 * @param i represents the id of the song to assign
	 */
	public void setId(int i){
		id = i;
	}
	
	/**
	 * @return the name of the song
	 */
	public String getName(){
		return nameSong;
	}
	
	/**
	 * Sets the name of the song
	 * @param nsong represents the name to assign
	 */
	public void setName(String nsong){
		nameSong = nsong;
	}
	
	/**
	 * @return the genre of the song
	 */
	public TGenre getGenre(){
		return genre;
	}
	
	/**
	 * Sets a genre for the song
	 * @param gen represents the genre to assign
	 */
	public void setGenre(TGenre gen){
		genre = gen;
	}
	
	/**
	 * @return a list with the bands who played the song
	 */
	public List<Band> getBands(){
		return performBy;
	}
	/**
	 * Sets a bands for the song
	 * @param band represents the bands to assign
	 */
	public void setBands(List<Band> band){
		performBy = band;
	}
	
	/**
	 * @return the duration of the song
	 */
	public int getDuration(){
		return duration;
	}
	
	/**
	 * Sets a duration for the song
	 * @param dur represents the duration to assign
	 */
	public void setDuration(int dur){
		duration = dur;
	}
	
	/**
	 * @return the author of the song
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * Sets an author for the song
	 * @param aut represents the author to assign
	 */
	public void setAuthor(String aut){
		author = aut;
	}
	
	/**
	 * @return the album of the song
	 */
	public Album getAlbum(){
		return album;
	}
	
	/**
	 * Sets an album for the song
	 * @param alb represents the album to assign
	 */
	public void setAlbum(Album alb){
		album = alb;
	}
}
