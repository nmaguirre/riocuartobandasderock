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

}
