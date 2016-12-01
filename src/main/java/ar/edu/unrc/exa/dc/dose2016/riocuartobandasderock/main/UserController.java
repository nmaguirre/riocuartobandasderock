package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.UserDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;

import spark.Request;
import spark.Response;
import spark.Session;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static UserController instance;

    private UserController(){}

    public static UserController getInstance(){
        if (instance == null)
            instance = new UserController();
        return instance;
    }

    public String create(Request req, Response res) {
        String name = req.queryParams("name");
        String password = req.queryParams("password");
        org.hibernate.Session session= SessionManager.getInstance().openSession();
        UserDAOImpl dao=new UserDAOImpl(session);
        if (dao.create(name, password)) {
            res.status(200);
            session.close();
            return "New user created successfully\n";
        }else {
            res.status(422);
            session.close();
            return "Failed to create new user\n";
        }
    }

    public String update(Request req, Response res) {
        String name = req.params("name");
        String password = req.queryParams("password");
        String new_password = req.queryParams("new_password");
        org.hibernate.Session session= SessionManager.getInstance().openSession();
        UserDAOImpl dao = new UserDAOImpl(session);
        User user = dao.find(name);
        if (user != null && new_password != null && user.password().equals(User.encodePassword(password))) {
            user.password(new_password);
            if (dao.update(user)) {
                res.status(200);
                session.close();
                return "User updated successfully\n";
            }
            else {
                res.status(422);
                session.close();
                return "Failed to update user\n";
            }
        } else {
            res.status(403);
            session.close();
            return "Access forbidden\n";
        }
    }

    public String delete(Request req, Response res) {
        String name = req.params("name");
        String password = req.queryParams("password");
        org.hibernate.Session session= SessionManager.getInstance().openSession();
        UserDAOImpl dao = new UserDAOImpl(session);
        User user = dao.find(name);
        if (user != null && user.password().equals(User.encodePassword(password))) {
            if (dao.delete(user.name())) {
                res.status(200);
                session.close();
                return "User deleted successfully\n";
            }
            else {
                res.status(422);
                session.close();
                return "Failed to delete user\n";
            }
        } else {
            res.status(403);
            session.close();
            return "Access forbidden\n";
        }
    }

    public boolean authenticated(Request req, Response res) {
        Session session = req.session();
        org.hibernate.Session sessionManager= SessionManager.getInstance().openSession();
        UserDAOImpl dao = new UserDAOImpl(sessionManager);
        Boolean result= session.attribute("name") != null &&
                session.attribute("password") != null &&
                dao.find(session.attribute("name")).password().equals(session.attribute("password"));
        sessionManager.close();
        return result;
    }

    public ModelAndView getLogin(Request req, Response res){
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("title", "Login");
        if (authenticated(req, res)) {
            attributes.put("template", Routes.already_logged());
            attributes.put("name", req.session().attribute("name"));
            return new ModelAndView(attributes, Routes.layout_sessions());
        } else {
            attributes.put("post_login_path", "/login");
            attributes.put("form_method", "POST");
            attributes.put("template", Routes.login());
            return new ModelAndView(attributes, Routes.layout_sessions());
        }
    }


    public ModelAndView postLogin(Request req, Response res) {
        Map<String, Object> attributes = new HashMap<>();
        Session session = req.session();
        if (authenticated(req, res)) {
            attributes.put("template", Routes.already_logged());
            attributes.put("name", req.session().attribute("name"));
            return new ModelAndView(attributes, Routes.layout_sessions());
        }
        String name = req.queryParams("name");
        String password = req.queryParams("password");
        org.hibernate.Session sessionManager= SessionManager.getInstance().openSession();
        UserDAOImpl dao = new UserDAOImpl(sessionManager);
        User user = dao.find(name);
        if (user != null && user.password().equals(User.encodePassword(password))) {
            session.attribute("name", user.name());
            session.attribute("password", user.password());
            session.maxInactiveInterval(120);
            res.status(200);
            sessionManager.close();
            res.redirect("/dashboard");
            return null;
        } else {
            res.status(403);
            sessionManager.close();
            attributes.put("post_login_path", "/login");
            attributes.put("form_method", "POST");
            attributes.put("error", "Los datos de inicio de sesión no son correctos. Intente nuevamente");
            attributes.put("template", Routes.login());
            return new ModelAndView(attributes, Routes.layout_sessions());
        }
    }

    public String getLogout(Request req, Response res) {
        Session session = req.session();
        if (session.attribute("name") == null) {
            res.status(403);
            res.redirect("/");
            return null;
        } else {
            session.invalidate();
            res.status(200);
            res.redirect("/");
            return null;
        }
    }
}
