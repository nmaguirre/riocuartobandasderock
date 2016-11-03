package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.UserDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;

public class UserDAOImpl implements UserDAO {
    @Override
    public User find(String name) {
        if (name != null && name != "") {
            User user = SessionManager.getInstance().openCurrentSession().find(User.class, name);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean create(String name, String password) {
        if (name == null || name == "" || password == null || password == "") {
            throw new IllegalArgumentException("the params for create user can't be null or empty.");
        } else if (find(name) != null) {
            return false;
        } else {
            SessionManager.getInstance().openCurrentSessionwithTransaction().save(new User(name, password));
            SessionManager.getInstance().closeCurrentSessionwithTransaction();
            return true;
        }
    }

    @Override
    public boolean update(User user) {
        if (user.repOk()) {
            SessionManager.getInstance().openCurrentSessionwithTransaction().update(user);
            SessionManager.getInstance().closeCurrentSessionwithTransaction();
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(String name) {
        User user = find(name);
        if (user != null) {
            SessionManager.getInstance().openCurrentSessionwithTransaction().delete(user);
            SessionManager.getInstance().closeCurrentSessionwithTransaction();
            return true;
        } else
            return false;
    }
}
