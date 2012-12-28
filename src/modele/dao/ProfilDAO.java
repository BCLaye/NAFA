package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Profil;

public class ProfilDAO implements IProfil {
	
	Session s = null;

	@Override
	public void save(Profil prof) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(prof);
		tx.commit();
	}

	@Override
	public void delete(Profil prof) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(prof);
		tx.commit();
	}

	@Override
	public Profil getProfil(String cod) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Profil where codeProfil = :id").setString("id", cod);
		return (Profil) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Profil> allProfil() {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Profil where codeProfil <> :id").setString("id", "AdminUser");
		return (ArrayList<Profil>) req.list();
	}

}
