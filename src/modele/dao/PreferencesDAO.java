package modele.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Preferences;

public class PreferencesDAO implements IPreferences {
	
	Session s = null;

	@Override
	public void save(ArrayList<Preferences> prefs) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		int cpt = 0;
		while(cpt<prefs.size()){
			s.saveOrUpdate(prefs.get(cpt));
			cpt ++;
		}
		tx.commit();
	}

	@Override
	public void delete(Preferences pref) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(pref);
		tx.commit();
	}
	
	@Override
	public Preferences getPreferences() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Preferences where id = :code").setString("code", "theme1B");
		return (Preferences) req.uniqueResult();
	}

	@Override
	public Preferences getPreferences(String codeUtil) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Preferences where utilisateur := code").setString("code", codeUtil);
		return (Preferences) req.uniqueResult();
	}
	
	public Preferences getPreferences(String theme, String couleur) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Preferences where theme = :model and couleur = :context");
				    req.setString("model", theme);
				    req.setString("context", couleur);
		return (Preferences) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Preferences> allPreferences() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Preferences");
		return (ArrayList<Preferences>) req.uniqueResult();
	}

}
