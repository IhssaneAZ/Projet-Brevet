package com.ismo.brevets.ihm.modelTable;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.models.Entreprise;

public class ModelTableEntreprise  extends AbstractTableModel{

	String[] cols = {"Numero", "Raison sociale", "Activite", "CA", "Ville"};
	List<Entreprise> entreprises;
	
	public ModelTableEntreprise(List<Entreprise> entreprises) {
		super();
		this.entreprises = entreprises;
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
		return entreprises.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Entreprise e = entreprises.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return String.format("E%04d", e.getNum());
			case 1: return e.getNom().toUpperCase();
			case 2: return e.getActivite();
			case 3: return e.getCa();
			case 4: return e.getVille();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

}
