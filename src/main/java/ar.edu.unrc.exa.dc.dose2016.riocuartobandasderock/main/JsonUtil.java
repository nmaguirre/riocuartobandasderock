package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;
import com.google.gson.Gson;
import spark.*;

public class JsonUtil {
	public static String toJson(Object object) {
	    return new Gson().toJson(object);
	}
	
	public static ResponseTransformer json() {
	    return JsonUtil::toJson;
	  }
	}