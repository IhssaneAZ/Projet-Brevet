package com.ismo.brevets.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.utils.HibernateUtils;
import com.ismo.brevets.models.Brevet;
import com.ismo.brevets.models.Domaine;

public class DaoDomaine implements IDAO<Domaine> {

	@Override
	public List<Domaine> getAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Domaine> doms = s.createQuery("from Domaine").getResultList();
		t.commit();
		s.close();
		return doms;
	}

	@Override
	public Domaine getOne(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Domaine d = s.get(Domaine.class, id);
		t.commit();
		s.close();
		return d;
	}

	@Override
	public boolean save(Domaine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Domaine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Domaine obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
