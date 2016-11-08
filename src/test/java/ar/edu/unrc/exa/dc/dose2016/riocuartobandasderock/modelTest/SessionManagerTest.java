package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;


import static org.junit.Assert.*;
import org.junit.Test;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;

public class SessionManagerTest {

	@Test
	/* If the session is created then it´s not null */
	public void notNullSessionTest(){
		SessionManager session =SessionManager.getInstance();
		assertNotEquals(null, session.getCurrentSession());
	}
	
	@Test
	/* If the session is closed then it´s null */
	//CHECK IF IT´S CORRECT WHAT I´M ASSUMING.
	public void NullSessionTest(){
		SessionManager session =SessionManager.getInstance();
		session.closeCurrentSession();
		assertEquals(null, session.getCurrentSession());
	}
	
	@Test
	/* If the current session is closed, then it remains closed until it´s become open again. */
	public void closeAndOpenCheckSessionTest(){
		SessionManager session =SessionManager.getInstance();
		session.closeCurrentSession();
		assertFalse(session.getCurrentSession().isOpen());
		
	}
}
