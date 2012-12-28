package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.JournalValidation;

public class JournalValidationDAO implements IJournalValidation {
	
	Session s = null;

	@Override
	public void save(JournalValidation jv) {
		// TODO Auto-generated method stub
	 if( s == null)
		 s = HibernateSessionFactory.getSession();
	 Transaction tx = s.beginTransaction();
	 s.saveOrUpdate(jv);
	 tx.commit();
	}

	@Override
	public void delete(JournalValidation jv) {
		// TODO Auto-generated method stub
		if( s == null)
			 s = HibernateSessionFactory.getSession();
		 Transaction tx = s.beginTransaction();
		 s.delete(jv);
		 tx.commit();
	}

	@Override
	public JournalValidation getJournalValidation(String codeUtil,
			String codeFct) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery(" form JournalValidation where util =:util and fonctionnalite =:fct");
		req.setString("util", codeUtil);
		req.setString("fct", codeFct);
		return (JournalValidation) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JournalValidation> allJournalValidation() {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery(" form JournalValidation");
		return (ArrayList<JournalValidation>) req.list();
	}

}
