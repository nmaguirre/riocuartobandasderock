package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.DAOImplTest;


import org.junit.Test;
import org.hibernate.Session;
import static org.junit.Assert.*;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;

public class SessionManagerTest {
	
	private Session session = SessionManager.getInstance().openSession();

	@Test
	/* If a session is opened then it's not null */
	public void notNullSessionTest(){
		assertNotNull(session);
		session.close();
	}
	
	@Test
	/* If the session is closed then it's null */
	//CHECK IF IT'S CORRECT WHAT I'M ASSUMING.
	public void NullSessionTest(){
		session.close();
		assertNull(session);
	}
	
	@Test
	/* If the current session is closed, then it remains closed until it's become open again. */
	public void closeAndOpenCheckSessionTest(){
		session.close();
		assertFalse(session.isOpen());
		
	}
	
	@Test
	/* Cheking the getSessionFactory properties. */
	public void getSessionFactoryTest(){
		String dbHost = ServerOptions.getInstance().getDbHost();
		String dbPort = ServerOptions.getInstance().getDbPort();
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
