package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Utilisateur;

public class UtilisateurDAO implements IUtilisateur {
	
	Session s = null;

	@Override
	public void save(Utilisateur util) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(util);
		tx.commit();
	}

	@Override
	public void delete(Utilisateur util) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(util);
		tx.commit();
	}

	@Override
	public Utilisateur getUtilisateur(String id) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Utilisateur where codeUtil= :code").setString("code", id);
		return (Utilisateur) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Utilisateur> allUtilisateur() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Utilisateur");
		return (ArrayList<Utilisateur>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Utilisateur> allUtilisateurNoAdminUser(){
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Utilisateur util where util not in (select utilisateur from UtilisateurProfil utilProf where utilProf.profil = :AdminUser) ").setString("AdminUser", "AdminUser");
		return (ArrayList<Utilisateur>) req.list();
	}

}
