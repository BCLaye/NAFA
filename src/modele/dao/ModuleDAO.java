package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Module;

public class ModuleDAO implements IModule {
	
	Session s = null;

	@Override
	public void save(Module mod) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(mod);
		tx.commit();
	}

	@Override
	public void delete(Module mod) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(mod);
		tx.commit();
	}

	@Override
	public Module getModule(String id) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Module where codeModule = :code").setString("code", id);
		return (Module) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Module> allModule() {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Module");
		return (ArrayList<Module>) req.list();
	}

}
