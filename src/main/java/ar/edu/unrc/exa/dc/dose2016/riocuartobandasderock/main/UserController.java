package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.UserDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.UserDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;
import spark.Request;
import spark.Response;

public class UserController {
    private static UserController instance;
    private UserDAO dao;
    
    private UserController(){
    	dao = new UserDAOImpl();
    }

    public static UserController getInstance(){
        if (instance == null)
            instance = new UserController();
        return instance;
    }
    
    public String create(Request req, Response res) {
    	String name = req.queryParams("name");
    	String password = req.queryParams("password");
    	if (dao.create(name, password))
    		return "success create\n";
    	else
    		return "failed create\n";
    }

    public String update(Request req, Response res) {
    	String name = req.params("name");
    	String password = req.queryParams("password");
    	String new_password = req.queryParams("new_password");
    	User user = dao.find(name);
    	if (user != null && new_password != null && user.password().equals(User.encodePassword(password))) {
    		user.password(new_password);
        	if (dao.update(user))
        		return "success update\n";
    		else
    			return "failed update\n";
    	} else
    		return "not found update\n";
    }
    
	public String delete(Request req, Response res) {
		return null;
	}
}
