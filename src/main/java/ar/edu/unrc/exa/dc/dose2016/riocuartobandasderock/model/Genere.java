package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.util.List;

public class Genere {
	private Integer id;
	private String name;
	private String description;
	private String release;
	private List<String> initiatedBy;
	private List<Genere> influence;
	
	public Genere(){
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getRelease(){
		return release;
	}
	
	public List<String> getinitiators(){
		return initiatedBy;
	}
	
	public List<Genere> getInfluence(){
		return influence;
	}
	
}
