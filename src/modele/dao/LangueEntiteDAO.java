package modele.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.LangueEntite;

public class LangueEntiteDAO implements ILangueEntite {
	
	Session s = null;

	@Override
	public void save(LangueEntite lge) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(lge);
		tx.commit();
	}

	@Override
	public void delete(LangueEntite lge) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(lge);
		tx.commit();
	}

	@Override
	public LangueEntite getLangueEntite(String codeLg, String codeEnt) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueEntite where langue = :lg and entite = :ent");
		req.setString("lg",codeLg);
		req.setString("ent",codeEnt);
		return (LangueEntite) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LangueEntite> allLangueEntite() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueEntite");
		return  (ArrayList<LangueEntite>)req.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LangueEntite> allLangueEntite(String codelangue){
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueEntite where langue = :lg").setString("lg", codelangue);
		return  (ArrayList<LangueEntite>)req.list();
	}

}
