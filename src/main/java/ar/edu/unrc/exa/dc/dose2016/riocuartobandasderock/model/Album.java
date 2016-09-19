package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.Date;
import java.util.UUID;
import java.util.List;

public class Album {

	private String id;
	private String title;
	private Band artist;
	private List<String> producers;
	private Date releaseDate;
	private int duration;		// in seconds
	private Genre genre;		// genre is given	
	private List<Style> styles; 
	private Date recordingDate;
	private String discography;
	private List<Song> songs;
	private int ISRC; 			// international standard recording code
	private int UPC; 			// universal product code
	
	public Album() {
		
	}
	
	public Album(String title, Band artist) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.artist = artist;
	}
	
	public Album(String title, Band artist, Date releaseDate, int duration, Genre genre, Date recordingDate,
			String discography, int ISRC, int UPC) {
		// this.id = UUID.randomUUID().toString(); // responsability of DAO
		this.title = title;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = genre;
		this.recordingDate = recordingDate;
		this.discography = discography;
		this.ISRC = ISRC;
		this.UPC = UPC;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
	public void addProducer(String producer) {
		this.producers.add(producer);
	}
	
	public Boolean removeProducer(String producer){
		return this.producers.remove(producer);
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
	
	public void addSong(Song song, int position) {
		this.songs.add(position, song);
	}
	
	public Song removeSong(int position){
		return this.songs.remove(position);
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}
	
}