package modele.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.metiers.HistoriqueMDP;

public class HistoriqueMDPDAO implements IHistoriqueMPD {
	
	Session s = null;

	@Override
	public void save(HistoriqueMDP hpass) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(hpass);
		tx.commit();
	}

	@Override
	public void delete(HistoriqueMDP hpass) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(hpass);
		tx.commit();
	}

	@Override
	public HistoriqueMDP getHistoriqueMDP(String codeUtil) {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from HistoriqueMDP where util =: id").setString("id", codeUtil);
		return (HistoriqueMDP) req.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HistoriqueMDP> allHistoriqueMDP() {
		// TODO Auto-generated method stub
		if(s == null)
			s = HibernateSessionFactory.getSession();
		Query req = s.createQuery("from HistoriqueMDP");
		return (ArrayList<HistoriqueMDP>) req.list();
	}

}
