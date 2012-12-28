package modele.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.Menus;

public class MenusDAO implements IMenus {
	
	Session s = null;

	@Override
	public void save(Menus menus) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(menus);
		tx.commit();
	}

	@Override
	public void delete(Menus menus) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(menus);
		tx.commit();
	}

	@Override
	public Menus getMenus(String menus) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Menus where codeMenus= :code").setString("code", menus);
		return (Menus) req.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Menus> allMenus(){
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from Menus");
		return (ArrayList<Menus>) req.list();
	}
	
	//les menus du profil  ne snt pas rattaché o profil et à aucun otre menus
		@SuppressWarnings("unchecked")
		public ArrayList<Menus> allMenusLibres(String prof){
			
			if(s == null)
				s = HibernateSessionFactory.getSession();
			Query req = s.createQuery("from Menus where profil= :prof and profilEffectif = 0").setString("prof", prof);
			ArrayList<Menus> listMenus = (ArrayList<Menus>) req.list(), listChercher = new ArrayList<Menus>();
			int cpt = 0;
			while(cpt < listMenus.size()){
				if(listMenus.get(cpt).getMenus().size() == 0)
					listChercher.add(listMenus.get(cpt));
				cpt ++;
			}
			return listChercher;			
		}
}
