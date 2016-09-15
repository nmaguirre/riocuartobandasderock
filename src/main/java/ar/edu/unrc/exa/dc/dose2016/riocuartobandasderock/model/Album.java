package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

public class Album {

	private int id;
	private String title;
	private Band artist;
	private List<String> producers;
//	private Date releaseDate;
	private int duration;		// in seconds
//	private Genre genre;		// genre is given	
	private List<Style> styles; 
// private Date recordingDate;
	private String discography;
	private List<Song> songs;
	private int ISRC; 			// international standard recording code
	private int UPC; 			// universal product code
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Band getArtist() {
		return artist;
	}
	public void setArtist(Band artist) {
		this.artist = artist;
	}
	public List<String> getProducers() {
		return producers;
	}
	public void setProducers(List<String> producers) {
		this.producers = producers;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<Style> getStyles() {
		return styles;
	}
	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}
	public String getDiscography() {
		return discography;
	}
	public void setDiscography(String discography) {
		this.discography = discography;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public int getISRC() {
		return ISRC;
	}
	public void setISRC(int iSRC) {
		ISRC = iSRC;
	}
	public int getUPC() {
		return UPC;
	}
	public void setUPC(int uPC) {
		UPC = uPC;
	}
	
	
	

}
