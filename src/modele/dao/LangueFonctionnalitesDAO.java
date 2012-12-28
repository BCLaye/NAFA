package modele.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.LangueFonctionnalites;

public class LangueFonctionnalitesDAO implements ILangueFonctionnalites {
	
	Session s = null;

	@Override
	public void save(LangueFonctionnalites lgf) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(lgf);
		tx.commit();
	}

	@Override
	public void delete(LangueFonctionnalites lgf) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(lgf);
		tx.commit();
	}
	
	@SuppressWarnings("rawtypes")
	public void delete(String fct){
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctionnalites where fonctionnalite = :fction").setString("fction",fct);
		List list= req.list();
		Transaction tx = s.beginTransaction();
		int nb = 0;
		while(nb < list.size()){
			s.delete(list.get(nb));
			nb +=1;
		}
		tx.commit();
	}

	@Override
	public LangueFonctionnalites getLangueFonctionnalite(String ld, String fct) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctionnalites where langue = :lan and fonctionnalite = :fction");
		req.setString("lan", ld);
		req.setString("fction", fct);
		return (LangueFonctionnalites) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LangueFonctionnalites> allLangueFonctionnalite() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctionnalites");
		return  (ArrayList<LangueFonctionnalites>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<LangueFonctionnalites> allLangueFonctionnalite(String codeLangue){
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueFonctionnalites where langue = :lg").setString("lg", codeLangue);
		return  (ArrayList<LangueFonctionnalites>) req.list();
	}

}
