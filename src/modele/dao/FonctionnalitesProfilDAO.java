package modele.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Fonctionnalites;
import modele.metiers.FonctionnalitesProfil;
import modele.metiers.Profil;

public class FonctionnalitesProfilDAO implements IFonctionnalitesProfil {
	
	Session s = null;

	@Override
	public void save(FonctionnalitesProfil fctPr) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(fctPr);
		tx.commit();
	}
	
	public void save(Collection<FonctionnalitesProfil> listFctProfil){
		
		ArrayList<FonctionnalitesProfil> listFctProf = new ArrayList<FonctionnalitesProfil>();
		listFctProf.addAll(listFctProfil);
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		int cpt = 0;
		while(cpt < listFctProf.size()){
			s.saveOrUpdate(listFctProf.get(cpt));
			cpt += 1;
		}
		tx.commit();
	}

	@Override
	public void delete(FonctionnalitesProfil fctPr) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(s == null)
					s = HibernateSessionFactory.getSession();
				Transaction tx = s.beginTransaction();
				s.delete(fctPr);
				tx.commit();
	}
	
public void delete(Collection<FonctionnalitesProfil> listFctProfil){
		
		ArrayList<FonctionnalitesProfil> listFctProf = new ArrayList<FonctionnalitesProfil>();
		listFctProf.addAll(listFctProfil);
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		int cpt = 0;
		while(cpt < listFctProf.size()){
			s.delete(listFctProf.get(cpt));
			cpt += 1;
		}
		tx.commit();
	}

	@Override
	public FonctionnalitesProfil getFonctionnalitesProfil(String idF, String idP) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from FonctionnalitesProfil where profil='"+idP+"' and fonctionnalite= '"+idF+"'");
		return (FonctionnalitesProfil) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<FonctionnalitesProfil> AllFonctionnalitesProfil() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from FonctionnalitesProfil");
		return (ArrayList<FonctionnalitesProfil>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Profil> AllProfil(){
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("select distinct profil from FonctionnalitesProfil");
		return (ArrayList<Profil>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Fonctionnalites> AllFonctionnalitesLibres(String profil){
		
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("select fonctionnalite from FonctionnalitesProfil fp where fp.profil = :prof and fp.nbValidReq=fp.nbValidEffect and fp.fonctionnaliteEffective=0").setString("prof",profil);
		return (ArrayList<Fonctionnalites>) req.list();
		
	}

}
