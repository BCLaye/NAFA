package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Programme;

public class ProgrammeDAO implements IProgramme {
	
	Session s = null;

	@Override
	public void save(Programme prog) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(prog);
		tx.commit();
	}
	
	public void save(ArrayList<Programme> prog){
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		int cpt = 0;
		while(cpt < prog.size()){
			s.saveOrUpdate(prog.get(cpt));
			cpt += 1;
		}
		tx.commit();
	}

	@Override
	public void delete(Programme prog) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(prog);
		tx.commit();
	}

	@Override
	public Programme getProgramme(String id) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Programme where codeProgramme = :code").setString("code", id);
		return (Programme) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Programme> allProgramme() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Programme");
		return (ArrayList<Programme>) req.list();
	}

}
