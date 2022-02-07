package com.ismo.brevets.metier;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.dao.DaoBrevet;
import com.ismo.brevets.dao.DaoDomaine;
import com.ismo.brevets.dao.DaoEntreprise;
import com.ismo.brevets.dao.DaoInventeur;
import com.ismo.brevets.dao.DaoInvention;
import com.ismo.brevets.dao.IDAO;

public class MetierFactory {

	public static IMetier getMetier(METIER type) {
		
		switch (type) {
				case BREVET:return new MetierBrevet();
				case ENTREPRISE:return new MetierEntreprise();
				case DOMAINE:return new MetierDomaine();
				case INVENTEUR:return new MetierInventeur();
				case INVENTION:return new MetierInvention();
		}
		
		return null;
	}
}
