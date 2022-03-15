package com.ismo.brevets.ihm.modelTable;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.models.Brevet;
import com.ismo.brevets.models.Entreprise;
import com.ismo.brevets.models.Invention;

public class ModelTableBrevet extends AbstractTableModel{

	String[] cols = {"Numero","Description" ,"Date Depot", "Date Validation", "Invention","Inventeur"};
	List<Brevet> brevets;
	
	public ModelTableBrevet(List<Brevet> brevets) {
		super();
		this.brevets = brevets;
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
		return brevets.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Brevet b = brevets.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return b.getNum();
			case 1: return b.getDescription();
			case 2: return b.getDateDepot();
			case 3: return b.getDateValidation();
			case 4: return b.getInvention().getDescriptif();
			case 5: return b.getInventeur().getNom();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

}
