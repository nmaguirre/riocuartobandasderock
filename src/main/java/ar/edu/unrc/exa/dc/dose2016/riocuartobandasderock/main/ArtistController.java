package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.BandMember;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandMemberDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import spark.Response;
import spark.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import java.util.List;
import java.util.LinkedList;

import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 *the ArtistController class treats http requests referred to the Artist model
 */
public class ArtistController {

	/**
	 * one implementation ArtistDao to connect to db
	 */
	private static ArtistController instance;

	/**
     * Constructor
     */
	private  ArtistController(){
	}

	/**
	 *  Method return SingletonInstance of ArtistController
	 */
	public static ArtistController getInstance() {
		if (instance==null){
			instance=new ArtistController();
		}
		return instance;
	}


	/**
	* Search artist by his Id
	* @param req it contain id of the artist to search
	* @param res (Response)
	* @return one Artist with id parameters
	*/
	public List<Artist> getArtistById (Request req, Response res){
		if (req.params(":id").isEmpty()){
			res.status(400);
		}
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO=new ArtistDaoImpl(session);
		List <Artist> artist = artistDAO.findById(req.params(":id"));
		session.close();
		int status = (artist.size()>0)? 200:204;
		res.status(status);
		return artist;
	}

	/**
	* Search one artist
	* @param req it contain id of the artist to search
	* @param res (Response)
	* @return one Artist.
	*/
	public List<Artist> getOneArtist (Request req, Response res){

		
		String name = req.queryParams("name");
		if (name==null){
			name="";
		}
		String surname = req.queryParams("surname");
		if (surname==null){
			surname="";
		}
		String nickname = req.queryParams("nickname");
		if (nickname==null){
			nickname="";
		}
		boolean isEmpty=((name.isEmpty()) && (surname.isEmpty()) && (nickname.isEmpty()));
		if (isEmpty){
			res.status(400);
			return null;
		}
		else{
			Session session = SessionManager.getInstance().openSession();
			ArtistDAO artistDAO=new ArtistDaoImpl(session);
			List <Artist> artist = artistDAO.getArtist(name,surname,nickname);
			session.close();
			int status =(artist.size()>0)? 200:204;
			res.status(status);
			return artist;
		}
	}

	/**
	 * get all Artist
	 * @param req (Request)
	 * @param res (Response)
	 * @return  List of Artist
	 */
	// public List<Artist> getAllArtists(Request req, Response res){
	public ModelAndView getAllArtists(Request req, Response res){
		Map<String, Object> attributes = new HashMap<>();
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO = new ArtistDaoImpl(session);
		List<Artist> artists = artistDAO.getAllArtists();
		session.close();
		int status = (artists.size()>0)? 200:204;
		res.status(status);
		// return artists;
		attributes.put("title", "Artistas");
  	attributes.put("artists", artists);
  	attributes.put("template", Routes.index_artist());
    return new ModelAndView(attributes, Routes.layout_dashboard());

	}




  public ModelAndView showArtist(Request req,Response res){
    Map<String, Object> attributes = new HashMap<>();

    Session session = SessionManager.getInstance().openSession();
    ArtistDAO artistDAO = new ArtistDaoImpl(session);

    Artist artist = artistDAO.findById(req.params(":id")).get(0);
    attributes.put("artist", artist);
    attributes.put("title", "Artista");
    attributes.put("template", Routes.show_artist());
    attributes.put("title", "Show");
    return new ModelAndView(attributes, Routes.layout_dashboard());
  }

  public ModelAndView newArtist(Request req,Response res){
    Map<String, Object> attributes = new HashMap<>();
    Session session = SessionManager.getInstance().openSession();
    BandDAO bdao = new BandDaoImpl(session);
    List<Band> bands = bdao.getAllBands();
    attributes.put("template", Routes.new_artist());
    attributes.put("bands", bands);
    attributes.put("title", "Crear un nuevo artista");
    return new ModelAndView(attributes, Routes.layout_dashboard());
  }

  public ModelAndView editArtist(Request req,Response res){
    Map<String, Object> attributes = new HashMap<>();

    String id = req.params(":id");
		attributes.put("id", id);

		Session session = SessionManager.getInstance().openSession();
    ArtistDAO artistDAO = new ArtistDaoImpl(session);
		List<Artist> artists = artistDAO.findById(req.params(":id"));

		attributes.put("name", artists.get(0).getName());
		attributes.put("surname", artists.get(0).getSurname());
		attributes.put("nickname", artists.get(0).getNickname());

    attributes.put("template", Routes.edit_artist());
    attributes.put("title", "Editar");
    return new ModelAndView(attributes, Routes.layout_dashboard());
  }


	/**
	 * search for artists by name
	 * @param req It contains the name to search for artists
	 * @param res (Response)
	 * @return List artist with name parameters
	 */
	public List<Artist> getArtistByName (Request req, Response res){
		if (req.params(":name").isEmpty()){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO = new ArtistDaoImpl(session);
		List<Artist> artists = artistDAO.findByName(req.params(":name"));
		session.close();
		int status = (artists.size()!=0)? 200:204;
		res.status(status);
		return artists;
	}

	/**
	 * search for artists by surname
	 * @param req It contains the surname to search for artists
	 * @param res (Response)
	 * @return List artist with surname parameters
	 */
	public List<Artist> getArtistBySurname (Request req, Response res){
		if (req.params(":surname").isEmpty()){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO=new ArtistDaoImpl(session);
		List<Artist> artists = artistDAO.findBySurname(req.params(":surname"));
		session.close();
		int status = (artists.size()!=0)? 200:204;
		res.status(status);
		return artists;
	}

	/**
	 * search for artists by nickname
	 * @param req It contains the nickname to search for artists
	 * @param res (Response)
	 * @return List artist with nickname parameters
	 */
	public List<Artist> getArtistByNickname (Request req, Response res){
		if (req.params(":nickname").isEmpty()){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO=new ArtistDaoImpl(session);
		List<Artist> artists = artistDAO.findByNickname(req.params(":nickname"));
		session.close();
		int status = (artists.size()!=0)? 200:204;
		res.status(status);
		return artists;
	}

	/**
	 * creates an artist
	 * @param req It contains the attributes of the new artist
	 * @param res (Response)
	 * @return a string that describes the result of createArtist
	 */
	public ModelAndView createArtist(Request req,Response res){
  	Map<String, Object> attributes = new HashMap<>();
    List<String> errors = new LinkedList<>();

    String name = req.queryParams("name");
    String surname = req.queryParams("surname");
    String nickname = req.queryParams("nickname");
    String band_ids[] = req.queryParamsValues("band_ids[]");

    if (name == null || name.equals(""))
      errors.add("El <strong>nombre</strong> no puede estar en blanco");
    if (surname == null || surname.equals(""))
      errors.add("El <strong>apellido</strong> no puede estar en blanco");
    if (nickname == null || nickname.equals(""))
      errors.add("El <strong>apodo</strong> no puede estar en blanco");

    if (!errors.isEmpty()) {
      attributes.put("errors", errors);
      attributes.put("template", Routes.new_artist());
      return new ModelAndView(attributes, Routes.layout_dashboard());
    }

    Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO = new ArtistDaoImpl(session);
	  BandMemberDAO bandMemberDAO = new BandMemberDAOImpl(session);
    Transaction transaction = null;
    Boolean status_artist = false;
    Boolean status_band_member = false;

    try {
      transaction = session.beginTransaction();
      status_artist = artistDAO.createArtist(name, surname, nickname);
      Artist current = artistDAO.getArtist(name, surname, nickname).get(0);
	    for (String s : band_ids) {
	    	status_band_member = bandMemberDAO.createBandMember(s, current.getId());
	    }
      transaction.commit();

      if (status_artist && status_band_member) {
        List<Artist> artists = artistDAO.getAllArtists();
        attributes.put("success", "El artista fue creado");
        attributes.put("artists", artists);
        attributes.put("template", Routes.index_artist());
        session.close();
        res.status(201);
        return new ModelAndView(attributes, Routes.layout_dashboard());
      } else if (status_artist && !status_band_member) {
	      transaction.rollback();
	      session.close();
        errors.add("No se pudo crear el artista en las bandas seleccionadas");
        attributes.put("errors", errors);
        attributes.put("template", Routes.new_artist());
        session.close();
        res.status(409);
        return new ModelAndView(attributes, Routes.layout_dashboard());
      } else {
        errors.add("Ya existe un artista con los mismos datos");
        attributes.put("errors", errors);
        attributes.put("template", Routes.new_artist());
        session.close();
        res.status(409);
        return new ModelAndView(attributes, Routes.layout_dashboard());
      }

    } catch(HibernateException e) {
      e.printStackTrace();
      transaction.rollback();
      session.close();
      res.status(409);
      errors.add("El artista no pudo ser creado");
      attributes.put("errors", errors);
      attributes.put("template", Routes.new_artist());
      return new ModelAndView(attributes, Routes.layout_dashboard());
    }
	}

	public ModelAndView updateView(Request req, Response res){
		Map<String, Object> attributes = new HashMap<>();
		String id = req.attribute(":id");
		attributes.put("title", "Actualizar");
		attributes.put("id", id);
		attributes.put("template", Routes.edit_artist());
    return new ModelAndView(attributes, Routes.layout_dashboard_update());
	}

	/**
	 * Update an artist in database
	 * @param res (Response)
	 * @return a string that describes the result of updateArtist
	 */
	public String updateArtist(Request req, Response res){
		//Not Implemented yet
		/*String user = req.session().attribute("name");
			if ((user==null)||(!(user.equals("admin")))){
			res.status(401);
			return "Forbidden Access";
		}*/
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO=new ArtistDaoImpl(session);
		List <Artist> artists = artistDAO.findById(req.params(":id"));
		session.close();
		if (artists.size()==0){
			res.status(400);
			return "Request invalid";
		}
		Artist artist = artists.get(0);
		String name = req.queryParams("name");
		if (name==null){
			name=artist.getName();
		}
		String surname = req.queryParams("surname");
		if (surname==null){
			surname=artist.getSurname();
		}
		String nickname = req.queryParams("nickname");
		if (nickname==null){
			nickname=artist.getNickname();
		}
		if((name.isEmpty()) && (surname.isEmpty()) && (nickname.isEmpty())){
			res.status(400);
			return "Request invalid";
		}
		session = SessionManager.getInstance().openSession();
		artistDAO=new ArtistDaoImpl(session);
		Transaction transaction = null;
		boolean status = false;
		String result = "Fail";
		try{
			transaction = session.beginTransaction();
			status = artistDAO.updateArtist(artist.getId(),name,surname,nickname);
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
			status = false;
			e.printStackTrace();
		}
		finally{
			session.close();
			if (status){
				res.status(200);
				result = "Success";
			}
			else{
				res.status(409);
				result = "Fail";
			}
		}
		return result;
	}

	/**
	* delete an artist by his Id
	* @param req it contain id of the artist to delete
	* @param res (Response)
	* @return a string that describes the result of deleteArtist
	*/
	public String deleteArtist(Request req, Response res){
		//Not Implemented yet
		/*String user = req.session().attribute("name");
			if ((user==null)||(!(user.equals("admin")))){
			res.status(401);
			return "Forbidden Access";
		}*/
		if ((req.params(":id")).isEmpty()){
			res.status(400);
			return "Request invalid";
		}
		Session session = SessionManager.getInstance().openSession();
		ArtistDAO artistDAO=new ArtistDaoImpl(session);
		Transaction transaction = null;
		boolean status = false;
		String result = "Fail";
		try{
		transaction = session.beginTransaction();
		status = artistDAO.deleteArtist(req.params(":id"));
		transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
			status = false;
			e.printStackTrace();
		}
		finally{
			session.close();
			if (status){
				res.status(200);
				result = "Success";
			}
			else{
				res.status(409);
				result = "Fail";
			}
		}
		return result;
	}

	/**
	 * search Artists of a Band by his name
	 * @param req it contain name of the Band to search Artist
	 * @param res (Response)
	 * @return List of Bands
	 */
	public List<Band> getBandMembersByArtistId(Request req, Response res){
		String artistID = req.params(":artistID");
		if((artistID=="")||(artistID==null)){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();
		BandMemberDAO bmDAO=new BandMemberDAOImpl(session);
		List<Band> bandMembers = bmDAO.findByArtist(artistID);
		session.close();
		int status = (bandMembers.size()>0)? 200:204;
		res.status(status);
		return bandMembers;
	}

	/**
	 * search BandMembers of a Artist by his name, surname and nickname
	 * @param req it contain idArtist to search
	 * @param res (Response)
	 * @return List of Bands
	 */
	public List<Band> getBandMembersByArtist(Request req, Response res){
		String aName = req.queryParams("artistName");
		if (aName==null){
			aName="";
		}
		String aSurname = req.queryParams("artistSurname");
		if (aSurname==null){
			aSurname="";
		}
		String aNickname = req.queryParams("artistNickname");
		if (aNickname==null){
			aNickname="";
		}
		if((aName.isEmpty()) && (aSurname.isEmpty()) && (aNickname.isEmpty())){
			res.status(400);
			return null;
		}
		Session session = SessionManager.getInstance().openSession();
		BandMemberDAO bmDAO=new BandMemberDAOImpl(session);
		List<Band> bandMembers = bmDAO.findByArtistByAttributes(aName,aSurname,aNickname);
		session.close();
		int status = (bandMembers.size()>0)? 200:204;
		res.status(status);
		return bandMembers;
	}
}
