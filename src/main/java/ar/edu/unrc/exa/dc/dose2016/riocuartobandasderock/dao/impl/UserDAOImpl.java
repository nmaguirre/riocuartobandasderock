package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import org.hibernate.Session;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.UserDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;

public class UserDAOImpl implements UserDAO {
	private Session currentSession = null;
	
	public UserDAOImpl(Session session){
		this.currentSession = session;
	}

    /**
    * This method search an user in database
    *
    *@param String id
    *@return user with determinated id
    */
    @Override
    public User find(String name) {
        if (name != null && name != "") {
            User user = this.currentSession.find(User.class, name);
            return user;
        } else {
            return null;
        }
    }
    /**
    * This method create an user in database
    *
    *@param String username
    *@param String password
    *@return true if successfully created
    */
    @Override
    public boolean create(String name, String password) {
        if (name == null || name == "" || password == null || password == "") {
            throw new IllegalArgumentException("the params for create user can't be null or empty.");
        } else if (find(name) != null) {
            return false;
        } else {
        	this.currentSession.save(new User(name, password));
            return true;
        }
    }
    /**
    * This method modify an user in database
    *
    *@param User band
    *@return true if successfully modified
    */
    @Override
    public boolean update(User user) {
        if (user.repOk()) {
        	this.currentSession.update(user);
            return true;
        } else
            return false;
    }
    /**
    * This method delete an user in database
    *
    *@param String id
    *@return true if successfully deleted
    */
    @Override
    public boolean delete(String name) {
        User user = find(name);
        if (user != null) {
        	this.currentSession.delete(user);
            return true;
        } else
            return false;
    }
}
