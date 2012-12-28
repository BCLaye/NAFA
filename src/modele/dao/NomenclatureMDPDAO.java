package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.NomenclatureMDP;

public class NomenclatureMDPDAO implements INomenclatureMDP {
	
	Session s = null;

	@Override
	public void save(NomenclatureMDP nomMDP) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(nomMDP);
		tx.commit();
	}

	@Override
	public void delete(NomenclatureMDP nomMDP) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(nomMDP);
		tx.commit();
	}

	@Override
	public NomenclatureMDP getNomenclatureMDP(String id) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from NomenclatureMDP where id = :cod").setString("cod", id);
		return (NomenclatureMDP) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<NomenclatureMDP> allNomenclatureMDP() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from NomenclatureMDP");
		return (ArrayList<NomenclatureMDP>) req.list();
	}

}
