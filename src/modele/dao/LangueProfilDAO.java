package modele.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.LangueProfil;

public class LangueProfilDAO implements ILangueProfil {
	
	Session s=null;

	@Override
	public void save(LangueProfil lp) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(lp);
		tx.commit();
	}

	@Override
	public void delete(LangueProfil lp) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(lp);
		tx.commit();
	}

	@Override
	public LangueProfil getLangueProfil(String lg, String pr) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueProfil lp where profil = :prof and langue = :lan");
		req.setString("lan", lg);
		req.setString("prof", pr);
		return (LangueProfil) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LangueProfil> allLangueProfil() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueProfil where profil <> :Admin");
		      req.setString("Admin", "AdminUser");
		return (ArrayList<LangueProfil>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<LangueProfil> allLangueProfil(String codeLangue){
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from LangueProfil where langue = :lg and profil <> :Admin");
		      req.setString("lg", codeLangue);
		      req.setString("Admin", "AdminUser");
		return (ArrayList<LangueProfil>) req.list();
	}

}
