package com.ismo.brevets.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtils;
import com.ismo.brevets.models.Inventeur;
import com.ismo.brevets.models.Invention;

public class DaoInvention implements IDAO<Invention> {

	@Override
	public List<Invention> getAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Invention> invts = s.createQuery("from Invention").getResultList();
		t.commit();
		s.close();
		return invts;
	}

	@Override
	public Invention getOne(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Invention d = s.get(Invention.class, id);
		t.commit();
		s.close();
		return d;
	}

	@Override
	public boolean save(Invention obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Invention obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Invention obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
