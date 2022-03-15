package com.ismo.brevets.metier;

import java.util.List;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.dao.DaoFactory;
import com.ismo.brevets.dao.IDAO;
import com.ismo.brevets.models.Invention;

public class MetierInvention implements IMetier<Invention> {

	IDAO<Invention> dao = DaoFactory.getDAO(DAO.INVENTION);
	
	@Override
	public List<Invention> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll(); 
	}

	@Override
	public Invention getOne(int id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}
	

	@Override
	public boolean save(Invention obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public boolean update(Invention obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

	@Override
	public boolean delete(Invention obj) {
		// TODO Auto-generated method stub
		return dao.delete(obj);
	}
	
	

}
