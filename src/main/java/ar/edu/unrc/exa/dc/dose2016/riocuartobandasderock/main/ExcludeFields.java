package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExcludeFields implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
    	
    	boolean cicleSongAlbum = (f.getDeclaringClass() == Song.class && f.getName().equals("album"));
    	boolean cicleAlbumBand = (f.getDeclaringClass() == Album.class && f.getName().equals("band"));
        return ( cicleSongAlbum || cicleAlbumBand );
        
    }

}