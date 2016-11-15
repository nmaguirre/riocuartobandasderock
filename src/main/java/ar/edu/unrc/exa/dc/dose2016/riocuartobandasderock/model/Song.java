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
	* duration represents the duration of this song
	* album represents the album where the song belongs
	*/
	
	@Id
	@Column(name = "idSong")
	private String idSong;
	
	@Column(name = "name")
	private String name;

	@Column(name = "duration")
	private int duration;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "albumID")
	private Album album;
	
	/**
	 * Default constructor of class song
	 */
	public Song() {

	}
	
	/**
	 * Constructor of the class Song with parameters
	 * @param id represent the id of the song to assign
	 * @param nsong represents the name of the song to assign
	 * @param dur represents the duration of the song to assign
	 * @param alb represent the album to assign
	 */
	public Song(String id,String nsong, int dur){
		this.idSong = id;
		name = nsong;
		duration = dur;
	}
	public Song(String nsong, int dur){
		this(UUID.randomUUID().toString(),nsong, dur);
		name = nsong;
		duration = dur;
	}
	

// Seters and geters of the method song............
	
	/**
	 * fn getId
	 * description: This method allows obtain on request the id of the song
	 * @return the id of the song in format String
	 */
	public String getId(){
		return idSong;
	}
	
	/**
	 * proc setId
	 * description: Sets the id of the song
	 * @param i represents the id of the song to assign
	 */
	public void setId(String i){
		idSong = i;
	}
	
	/**
	 * fn getName
	 * description: This method allows obtain on request the name of the song
	 * @return the name of the song in format String
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * proc setName
	 * description: Sets the name of the song
	 * @param nsong represents the name to assign
	 */
	public void setName(String nsong){
		name = nsong;
	}
	
	/**
	 * fn getDuration
	 * description: This method allows obtain on request the duration of the song
	 * @return the duration of the song in format int
	 */
	public int getDuration(){
		return duration;
	}
	
	/**
	 * proc setDuration
	 * description: Sets a duration for the song
	 * @param dur represents the duration to assign
	 */
	public void setDuration(int dur){
		duration = dur;
	}
	
	/**
	 * fn getDurationAsString
	 * description: This method allows obtain on request the duration of the song in format of conventional time
	 * @return the duration in format of conventional time
	 **/
	public String getDurationAsString(){
		int cociente = duration/60;
		int resto = duration%60;
		return cociente + ":" + resto;
	} 
	
	
	/**
	 * fn getAlbum
	 * description: This method allows obtain on request the album where the song belongs
	 * @return the album where the song belongs
	 */
	public Album getAlbum(){
		 return album;
	}
		  	
	/**
	* proc setAlbum
    * description: Sets an album for the song
	* @param alb represents the album to assign
	*/
	public void setAlbum(Album alb){
		 album = alb;

    }
	
}
