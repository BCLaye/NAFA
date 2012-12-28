package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Fonctionnalites;

public class FonctionnalitesDAO implements IFonctionnalites {
	
	Session s = null;
	@Override
	public void save(Fonctionnalites fct) {
		// TODO Auto-generated method stub
		if(s ==null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(fct);
		tx.commit();
	}

	@Override
	public void delete(Fonctionnalites fct) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(fct);
		tx.commit();
	}

	@Override
	public Fonctionnalites getFonctionnalite(String id) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Fonctionnalites where codeFonctionnalite= :code").setString("code",id);
		return (Fonctionnalites) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Fonctionnalites> allFonctionnalites() {
		// TODO Auto-generated method stub
		if(s == null)
		s = HibernateSessionFactory.getSession();
	Query req = s.createQuery("from Fonctionnalites");
	return (ArrayList<Fonctionnalites>) req.list();
	}

}
