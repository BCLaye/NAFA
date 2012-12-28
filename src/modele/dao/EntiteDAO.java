package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modele.metiers.Entite;

public class EntiteDAO implements IEntite {
	
	Session s= null;

	@Override
	public void save(Entite ent) {
		// TODO Auto-generated method stub
		if(s ==null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(ent);
		tx.commit();

	}

	@Override
	public void delete(Entite ent) {
		// TODO Auto-generated method stub
		if(s ==null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(ent);
		tx.commit();
	}

	@Override
	public Entite getEntite(String id) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Entite where codeEntite= :code").setString("code",id);
		return (Entite) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Entite> allEntite() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Entite");
		return (ArrayList<Entite>) req.list();
	}

}
