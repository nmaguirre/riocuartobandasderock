package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface SessionManager {
	
	public Session openCurrentSession();

	public Session openCurrentSessionwithTransaction();

	public void closeCurrentSession();

	public void closeCurrentSessionwithTransaction();

	public Session getCurrentSession();

	public void setCurrentSession(Session currentSession);
	
	public Transaction getCurrentTransaction();

	public void setCurrentTransaction(Transaction currentTransaction);

}
