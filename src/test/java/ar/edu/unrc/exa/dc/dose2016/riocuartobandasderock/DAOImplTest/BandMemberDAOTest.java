package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;

import org.junit.Before;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandMemberDAOImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;

public class BandMemberDAOTest {

	
	private BandMemberDAOImpl bandMemberDAO;
	private SessionManager session;
	
	@Before
	public void setUp(){
		bandMemberDAO = new BandMemberDAOImpl();
		session = SessionManager.getInstance();
	}
}
