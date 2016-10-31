package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class represents a song played by Rio Cuarto's bands 
 */

@Entity
@Table(name = "SongDB")
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
	private String id;
	
	@Column(name = "name")
	private String nameSong;

	@Column(name = "duration")
	private int duration;
	
	@ManyToOne
    @JoinColumn(name="id")
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
	public Song(String id,String nsong, int dur){
		this.id = id;
		nameSong = nsong;
		duration = dur;
	}
	public Song(String nsong, int dur){
		this(UUID.randomUUID().toString(),nsong, dur);
		nameSong = nsong;
		duration = dur;
	}
	

// Seters and geters of the method song............
	
	/**
	 * @return the id of the song
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Sets the id of the song
	 * @param i represents the id of the song to assign
	 */
	public void setId(String i){
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
	 * @return the duration in format of conventional time
	 **/
	public String getDurationAsString(){
		int cociente = duration/60;
		int resto = duration%60;
		return cociente + ":" + resto;
	} 
	
}
