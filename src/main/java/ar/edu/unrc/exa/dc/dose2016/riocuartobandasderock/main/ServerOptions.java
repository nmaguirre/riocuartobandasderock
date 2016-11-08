package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

/**
 * Class ServerOptions holds all server options, most of which can be set from the
 * command line.
 * Its implementation follows the Singleton design pattern, for convenience (simpler
 * access from anywhere in the project).
 * @author Nazareno Aguirre
 *
 */
public class ServerOptions {

	/**
	 * Empty, private default constructor.
	 */
	private ServerOptions() { }

	/**
	 * Singleton, on demand instance of ServerOptions.
	 */
	private static ServerOptions instance = null;

	/**
	 * Server option that holds the hosts for the database.
	 * Default value is "localhost", i.e., assumes that database
	 * runs on the same host as the server.
	 */
	private String dbHost = "localhost";

	/**
	 * Server option that holds the port where the database
	 * server listens for clients. Default value is "5432", i.e.
	 * the default port for PostgreSQL server.
	 */
	private String dbPort = "7500";

	/**
	 * Server option that holds the port where the application
	 * server listens for clients. Default value is "4567".
	 */
	private String appPort = "4567";

	/**
	 * Returns the (sole) instance of ServerOptions, on demand.
	 * It is created the first time it is accessed, and from that
	 * point onwards, the same instance is returned.
	 * See the Singleton Pattern for reference.
	 * @return the instance of ServerOptions.
	 */
	public static ServerOptions getInstance() {
		if (instance==null) instance = new ServerOptions();
		return instance;
	}

	/**
	 * Sets the database host, that the application communicates with.
	 * @param dbHost is the new database host
	 */
	public void setDbHost(String dbHost) {
		if (dbHost==null || dbHost=="") throw new IllegalArgumentException("invalid hostname");
		this.dbHost = dbHost;
	}

	/**
	 * Sets the database port, where the application locates the database server it
	 * communicates with.
	 * @param dbPort is the new database port
	 */
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	/**
	 * Sets the application port, that is used to enter to the application.
	 * @param appPort is the new application port
	 */
	public void setAppPort(String appPort) {
		this.appPort = appPort;
	}

	/**
	 * Returns the host where the database that the application communicates with, resides.
	 * @return the host where the database server is running
	 */
	public String getDbHost() {
		return this.dbHost;
	}

	/**
	 * Returns the port where the database server that the application communicates with, is listening.
	 * @return the port where the database server is listening.
	 */
	public String getDbPort() {
		return this.dbPort;
	}

	/**
	 * Returns the port where the application is listening.
	 * @return the port where the application is listening.
	 */
	public String getAppPort() {
		return this.appPort;
	}

}
