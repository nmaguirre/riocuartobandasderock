package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

public class Options {

	private Options() { }
	
	private static Options instance = null;
	
	private String dbHost = "localhost";
	private String dbPort = "2345";
	
	public static Options getInstance() {
		if (instance==null) instance = new Options();
		return instance;
	}
	
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	
	public String getDbHost() {
		return this.dbHost;
	}
	
	public String getDbPort() {
		return this.dbPort;
	}
}
