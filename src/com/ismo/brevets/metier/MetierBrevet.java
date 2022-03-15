package com.ismo.brevets.metier;

import java.util.List;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.dao.DaoFactory;
import com.ismo.brevets.dao.IDAO;
import com.ismo.brevets.models.Brevet;


public class MetierBrevet implements IMetier<Brevet> {

	
	IDAO<Brevet> dao = DaoFactory.getDAO(DAO.BREVET);
	
	@Override
	public List<Brevet> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Brevet getOne(int id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public boolean save(Brevet obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public boolean update(Brevet obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

	@Override
	public boolean delete(Brevet obj) {
		// TODO Auto-generated method stub
		return dao.delete(obj);
	}

}
