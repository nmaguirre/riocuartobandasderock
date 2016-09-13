package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

/**
 * This class represents a song played by Rio Cuarto's bands 
 */
public class Song {
	
   /**
	* id represents the id of the song
	* nameSong represents the name of the song
	* gender represents the gender of the song
	* bands represents the bands from Rio Cuarto who played this song
	* duration represents the duration of this song
	* author represents the author who composed the song
	*/
	private int id;
	private String nameSong;
	private String gender;   // will be changed to type enum Gender
	private List<Band> bands;
	private int duration;
	private String author;	//will be changed to type People
	
	
	/**
	 * Default constructor of class song
	 */
	public Song() {

	}
	
	/**
	 * Constructor of the class Song with parameters
	 * @param i represent the id of the song to assign
	 * @param nsong represents the name of the song to assign
	 * @param gen represents the gender of the song to assign
	 * @param band represents the band's name of the song to assign
	 * @param dur represents the duration of the song to assign
	 * @param aut represents the author of the song to assign
	 */
	public Song(int i, String nsong, String gen, List<Band> band, int dur, String aut){
		id = i;
		nameSong = nsong;
		gender = gen;
		bands = band;
		duration = dur;
		author = aut;
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
	 * @return the gender of the song
	 */
	public String getGender(){
		return gender;
	}
	
	/**
	 * Sets a gender for the song
	 * @param gen represents the gender to assign
	 */
	public void setGender(String gen){
		gender = gen;
	}
	
	/**
	 * @return a list with the bands who played the song
	 */
	public List<Band> getBands(){
		return bands;
	}
	/**
	 * Sets a bands for the song
	 * @param band represents the bands to assign
	 */
	public void setBands(List<Band> band){
		bands = band;
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
	
}
