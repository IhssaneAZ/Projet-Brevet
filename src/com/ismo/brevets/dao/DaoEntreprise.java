package com.ismo.brevets.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtils;
import com.ismo.brevets.models.Entreprise;

public class DaoEntreprise implements IDAO<Entreprise> {

	@Override
	public List<Entreprise> getAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Entreprise> ents = s.createQuery("from Entreprise").getResultList();
		t.commit();
		s.close();
		return ents;
	}

	@Override
	public Entreprise getOne(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Entreprise e = s.get(Entreprise.class, id);
		t.commit();
		s.close();
		return e;
	}

	@Override
	public boolean save(Entreprise obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Entreprise obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Entreprise obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
