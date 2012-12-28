package modele.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Fonctionnalites;
import modele.metiers.Profil;
import modele.metiers.ValidationFonctionnalites;

public class ValidationFonctionnalitesDAO implements IValidationFonctionnalites {
	
	Session s = null;

	@Override
	public void save(ValidationFonctionnalites validfct) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(validfct);
		tx.commit();
	}
	
	public void save(Collection<ValidationFonctionnalites> validfct){
		ArrayList<ValidationFonctionnalites> listValid = new ArrayList<ValidationFonctionnalites>();
		listValid.addAll(validfct);
		int cpt = 0;
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		while(cpt < listValid.size()){
			s.saveOrUpdate(listValid.get(cpt));
			cpt += 1;
		}
		tx.commit();
	}

	@Override
	public void delete(ValidationFonctionnalites valifct) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(valifct);
		tx.commit();		
	}
	
	public void delete(Collection<ValidationFonctionnalites>  valifct){
		ArrayList<ValidationFonctionnalites> listValid = new ArrayList<ValidationFonctionnalites>();
		listValid.addAll(valifct);
		int cpt = 0;
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		while(cpt < listValid.size()){
			s.delete(listValid.get(cpt));
			cpt += 1;
		}
		tx.commit();
	}

	@Override
	public ValidationFonctionnalites getValidationFonctionnalites(
			String codeFct, String codeProf, String codeProfVal) {
		// TODO Auto-generated method stub
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from ValidationFonctionnalites where fonctionnalite = :idf and profil = :idprof and profilValidation = :idprofval");
		req.setString("idf", codeFct);
		req.setString("idprof", codeProf);
		req.setString("idprofval", codeProfVal);
		return (ValidationFonctionnalites) req.uniqueResult();
	}

	@Override
	public ArrayList<ValidationFonctionnalites> allValidationFonctionnalites() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Profil> allProfil(){
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("select distinct profil from ValidationFonctionnalites");
		return (ArrayList<Profil>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Fonctionnalites> allFonctionnalites( String profil){
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("select distinct fonctionnalite from ValidationFonctionnalites val where val.profil = :prof").setString("prof", profil);
		return (ArrayList<Fonctionnalites>) req.list();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Profil> allProfilValid( String profil, String fct){
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("select profilValidation from ValidationFonctionnalites val where val.profil = :prof and val.fonctionnalite = :fct");
		      req.setString("prof", profil);
		      req.setString("fct", fct);
		return (ArrayList<Profil>) req.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<ValidationFonctionnalites> allValidation(String profil, String fctionnalite){
		if( s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from ValidationFonctionnalites where profil = :prof and fonctionnalite = :fct order by rangProfilValid");
		      req.setString("prof", profil);
		      req.setString("fct", fctionnalite);
		return (ArrayList<ValidationFonctionnalites>) req.list();
	}
	
	
	// la liste des validation dont le rang est inférieur à randInf et qui sont tjrs invalides		
		public Boolean testValidation(String profil, String fctionnalite, int rangInf){
			if( s == null)
				s = HibernateSessionFactory.getSession();
			Query req = s.createQuery("from ValidationFonctionnalites where profil = :prof and fonctionnalite = :fct and nbValidEffect = 0 and rangProfilValid < :rang");
			      req.setString("prof", profil);
			      req.setString("fct", fctionnalite);
			      req.setInteger("rang", rangInf);
			if(req.list().size() == 0)
				return false;
			else
			return true;      
		}

}
