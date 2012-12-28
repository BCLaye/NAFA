package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Langue;

public class LangueDAO implements ILangue {
	
	Session s = null;

	@Override
	public void save(Langue lg) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(lg);
		tx.commit();
	}

	@Override
	public void delete(Langue lg) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(lg);
		tx.commit();
	}

	@Override
	public Langue getLangue(String code) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Langue where codeLangue = :code").setString("code", code);
		return (Langue) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Langue> allLangue() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Langue");
		return (ArrayList<Langue>) req.list();
	}

}
