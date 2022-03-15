package com.ismo.brevets.metier;

import java.util.List;

import com.ismo.brevets.dao.DAO;

import com.ismo.brevets.dao.DaoFactory;
import com.ismo.brevets.dao.IDAO;

import com.ismo.brevets.models.Domaine;


public class MetierDomaine implements IMetier<Domaine> {
	IDAO<Domaine> dao = DaoFactory.getDAO(DAO.DOMAINE);
	
	@Override
	public List<Domaine> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Domaine getOne(int id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public boolean save(Domaine obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public boolean update(Domaine obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

	@Override
	public boolean delete(Domaine obj) {
		// TODO Auto-generated method stub
		return dao.delete(obj);
	}

}
