package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;

public interface UserDAO {


	/**
	* This method search an user in database
	*
	*@param String id
	*@return user with determinated id
	*/
	public User find(String id);

	/**
	* This method modify an user in database
	*
	*@param User band
	*@return true if successfully modified
	*/
	public boolean update(User band);
	
	/**
	* This method delete an user in database
	*
	*@param String id
	*@return true if successfully deleted
	*/
	public boolean delete(String id);


	/**
	* This method create an user in database
	*
	*@param String username
	*@param String password
	*@return true if successfully created
	*/
	public boolean create(String username, String password);
}
