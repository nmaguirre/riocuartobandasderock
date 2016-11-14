package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.modelTest;


import org.junit.Test;
import org.hibernate.Session;
import static org.junit.Assert.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;

public class SessionManagerTest {
	
	private Session session;

	@Test
	/* If a session is opened then itŽs not null */
	public void notNullSessionTest(){
		session = SessionManager.getInstance().openSession();
		assertNotEquals(null, session);
	}
	
	@Test
	/* If the session is closed then itŽs null */
	//CHECK IF ITŽS CORRECT WHAT IŽM ASSUMING.
	public void NullSessionTest(){
		session =SessionManager.getInstance().openSession();
		session.close();
		assertEquals(null, session);
	}
	
	@Test
	/* If the current session is closed, then it remains closed until itŽs become open again. */
	public void closeAndOpenCheckSessionTest(){
		session =SessionManager.getInstance().openSession();
		session.close();
		assertFalse(session.isOpen());
		
	}
	
	@Test
	/* Cheking the getSessionFactory properties. */
	public void getSessionFactoryTest(){
		String dbHost = ServerOptions.getInstance().getDbHost();
		String dbPort = ServerOptions.getInstance().getDbPort();
		session =SessionManager.getInstance().openSession();
		assertTrue("hibernate.dialect", session.getProperties().containsKey("org.hibernate.dialect.PostgreSQLDialect"));
		assertTrue("hibernate.connection.driver_class", session.getProperties().containsKey("org.postgresql.Driver"));
		assertTrue("hibernate.connection.username", session.getProperties().containsKey("rock_db_owner"));
		assertTrue("hibernate.connection.password", session.getProperties().containsKey("rockenrio4"));
		assertTrue("hibernate.connection.url", session.getProperties().containsKey("jdbc:postgresql://" + dbHost + ":" + dbPort + "/rcrockbands"));
		assertTrue("connection_pool_size", session.getProperties().containsKey("1"));
		assertTrue("hibernate.hbm2ddl.auto", session.getProperties().containsKey("update"));
		assertTrue("show_sql", session.getProperties().containsKey("false"));
		assertTrue("hibernate.current_session_context_class", session.getProperties().containsKey("thread"));
	}
}
