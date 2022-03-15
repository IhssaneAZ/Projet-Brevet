package com.ismo.brevets.ihm.modelTable;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import com.ismo.brevets.models.Inventeur;

public class ModelTableInventeur extends AbstractTableModel {

	String[] cols = {"Numero", "Nom", "Prénom", "Adresse", "Date de Naissance","Entreprise"};
	List<Inventeur> inventeurs;
	
	public ModelTableInventeur(List<Inventeur> inventeurs) {
		super();
		this.inventeurs = inventeurs;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cols[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return inventeurs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Inventeur i = inventeurs.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return i.getNum();
			case 1: return i.getNom().toUpperCase();
			case 2: return i.getPrenom().toUpperCase();
			case 3: return i.getAdresse();
			case 4: return i.getDate_nais();
			case 5: return i.getEntreprise().getNom();
		}
		return null;
	}
//	String.format("E%04d"
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

}
