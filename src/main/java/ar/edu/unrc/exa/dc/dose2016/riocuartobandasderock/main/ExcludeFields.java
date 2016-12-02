package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

<<<<<<< HEAD
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
=======
>>>>>>> 10c87e646e0e0956729e7c4a3dfc46c7731a4b06
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExcludeFields implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {

        return (f.getDeclaringClass() == Song.class && f.getName().equals("album")||
        		f.getDeclaringClass() == Band.class && f.getName().equals("albums"));
    }

}