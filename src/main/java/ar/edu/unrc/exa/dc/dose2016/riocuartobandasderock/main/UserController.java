package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.UserDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.UserDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;
import spark.Request;
import spark.Response;
import spark.Session;

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
        if (dao.create(name, password)) {
            res.status(200);
            return "New user created successfully\n";
        }
        else {
            res.status(422);
            return "Failed to create new user\n";
        }
    }

    public String update(Request req, Response res) {
        String name = req.params("name");
        String password = req.queryParams("password");
        String new_password = req.queryParams("new_password");
        User user = dao.find(name);
        if (user != null && new_password != null && user.password().equals(User.encodePassword(password))) {
            user.password(new_password);
            if (dao.update(user)) {
                res.status(200);
                return "User updated successfully\n";
            }
            else {
                res.status(422);
                return "Failed to update user\n";
            }
        } else {
            res.status(403);
            return "Access forbidden\n";
        }
    }

    public String delete(Request req, Response res) {
        String name = req.params("name");
        String password = req.queryParams("password");
        User user = dao.find(name);
        if (user != null && user.password().equals(User.encodePassword(password))) {
            if (dao.delete(user.name())) {
                res.status(200);
                return "User deleted successfully\n";
            }
            else {
                res.status(422);
                return "Failed to delete user\n";
            }
        } else {
            res.status(403);
            return "Access forbidden\n";
        }
    }

    public String login(Request req, Response res) {
        Session session = req.session();
        if (session.attribute("name") != null) {
            res.status(200);
            return "Already logged in as:\n" + session.attribute("name") + "\n";
        }
        String name = req.queryParams("name");
        String password = req.queryParams("password");
        User user = dao.find(name);
        if (user != null && user.password().equals(User.encodePassword(password))) {
            session.attribute("name", user.name());
            session.maxInactiveInterval(60);
            res.status(200);
            return "Logged in successfully\n";
        } else {
            res.status(403);
            return "Invalid credentials\n";
        }
    }

    public String logout(Request req, Response res) {
        Session session = req.session();
        if (session.attribute("name") == null) {
            res.status(403);
            return "You're not logged in\n";
        } else {
            session.invalidate();
            res.status(200);
            return "Logged out successfully\n";
        }
    }
}
