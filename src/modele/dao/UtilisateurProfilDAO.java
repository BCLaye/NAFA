package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.UtilisateurProfil;

public class UtilisateurProfilDAO implements IUtilisateurProfil {
	
	Session s = null;

	@Override
	public void save(UtilisateurProfil utilProf) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(utilProf);
		tx.commit();
	}
	
	public void save(ArrayList<UtilisateurProfil> utilProf){
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		int cpt = 0;
		while(cpt < utilProf.size()){
			s.saveOrUpdate(utilProf.get(cpt));
			cpt += 1;
		}
		tx.commit();		
	}

	@Override
	public void delete(UtilisateurProfil utilProf) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(utilProf);
		tx.commit();
	}

	@Override
	public UtilisateurProfil getUtilisateurProfil(String codeUtil,String codeProf) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from UtilisateurProfil where utilisateur = :codeutil and profil = :codeprof");
		req.setString("codeutil", codeUtil);
		req.setString("codeprof", codeProf);
		return (UtilisateurProfil) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UtilisateurProfil> allUtilisateurProfil() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from UtilisateurProfil");
		return (ArrayList<UtilisateurProfil>) req.list();
	}

}
