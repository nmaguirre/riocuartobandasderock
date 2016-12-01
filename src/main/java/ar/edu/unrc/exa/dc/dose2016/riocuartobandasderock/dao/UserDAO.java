package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.User;

public interface UserDAO {
	public User find(String id);
	public boolean update(User band);
	public boolean delete(String id);
	public boolean create(String username, String password);
}
