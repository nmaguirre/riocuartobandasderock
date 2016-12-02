package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;
import java.lang.reflect.Type;
import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import spark.*;

public class JsonUtil {
	public static String toJsonBand(Object object) {
		Gson g = new GsonBuilder().setExclusionStrategies( new ExcludeFields() ).create();
		Type type = new TypeToken<List<Band>>(){}.getType();
		return g.toJson(object,type);
	}
	
	public static ResponseTransformer jsonBand() {
	    return JsonUtil::toJsonBand;
	  }
	
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	public static ResponseTransformer json() {
	    return JsonUtil::toJson;
	  }
	
	
	public static String toJsonAlbum(Object object){
		Gson g = new GsonBuilder().setExclusionStrategies( new ExcludeFields() ).create();
		Type type = new TypeToken<List<Album>>(){}.getType();
		return g.toJson(object,type);
	}
	
	public static ResponseTransformer jsonAlbum(){
		return JsonUtil::toJsonAlbum;
	}
	
	}