package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.LangueFonctions;

public class LangueFonctionsDAO implements ILangueFonctions {
	
	Session s = null;

	@Override
	public void save(LangueFonctions lf) {
		// TODO Auto-generated method stub
		if(s == null)
			s  = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(lf);
		tx.commit();
	}

	@Override
	public void delete(LangueFonctions lf) {
		// TODO Auto-generated method stub
		if(s == null)
			s  = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(lf);
		tx.commit();
	}

	@Override
	public LangueFonctions getLangueFonctions(String lg, String fct) {
		// TODO Auto-generated method stub
		if(s == null)
			s  = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctions where langue := lan and fonction:= fction");
		req.setString("lan",lg);
		req.setString("fction",fct);
		return (LangueFonctions) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LangueFonctions> allLangueFonctions() {
		// TODO Auto-generated method stub
		if(s == null)
			s  = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctions");
		return (ArrayList<LangueFonctions>) req.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<LangueFonctions> allLangueFonctions(String codeLangue){
		// TODO Auto-generated method stub
		if(s == null)
			s  = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctions where langue = :lang").setString("lang",codeLangue);
		return (ArrayList<LangueFonctions>) req.list();
	}

}
