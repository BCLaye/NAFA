package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Fonctions;

public class FonctionsDAO implements IFonctions {
	
	Session s = null;
	
	@Override
	public void save(Fonctions ft) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(ft);
		tx.commit();
	}

	@Override
	public void delete(Fonctions ft) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(ft);
		tx.commit();
	}

	@Override
	public Fonctions getFonction(String id) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery(" from Fonctions where code_fonction = :code").setString("code", id);
		return (Fonctions) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Fonctions> allFonctions() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery(" from Fonctions");
		return (ArrayList<Fonctions>) req.list();
	}

}
