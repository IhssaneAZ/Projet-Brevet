package com.ismo.brevets.metier;


import java.util.List;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.dao.DaoFactory;
import com.ismo.brevets.dao.IDAO;
import com.ismo.brevets.models.Entreprise;

public class MetierEntreprise implements IMetier<Entreprise> {

	IDAO<Entreprise> dao = DaoFactory.getDAO(DAO.ENTREPRISE);
	
	@Override
	public List<Entreprise> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Entreprise getOne(int id) {
		// TODO Auto-generated method stub
		return null;
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
