package com.ismo.brevets.ihm.modelTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.MetierInvention;
import com.ismo.brevets.models.Inventeur;
import com.ismo.brevets.models.Invention;

public class ModelTableInvention  extends AbstractTableModel{

	String[] cols = {"Numero", "Descriptif", "Resume", "Domaine"};
	List<Invention> inventions;

	
	public ModelTableInvention(List<Invention> inventions) {
		super();
		this.inventions = inventions;
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
		return inventions.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Invention i = inventions.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return i.getNum();
			case 1: return i.getDescriptif().toUpperCase();
			case 2: return i.getResume();
			case 3: return i.getDomaine().getNom();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

}
