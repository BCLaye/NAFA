package modele.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.LangueMenus;

public class LangueMenusDAO implements IlangueMenus {
	
	Session s = null;

	@Override
	public void save(LangueMenus langmenus) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(langmenus);
		tx.commit();
	}

	@Override
	public void delete(LangueMenus langmenus) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(langmenus);
		tx.commit();
	}

	@Override
	public LangueMenus getLangueMenus(String langue, String menus) {
		// TODO Auto-generated method stub
		if(s == null)
		s = HibernateSessionFactory.getSession();
	   Query req = s.createQuery("from LangueMenus where langue= :code and menus= :menus");
	      req.setString("code", langue);
	      req.setString("menus", menus);
	return (LangueMenus) req.uniqueResult();
	}

}
