package com.ismo.brevets.ihm;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTable;

import com.ismo.brevets.ihm.modelTable.ModelTableEntreprise;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Entreprise;
import javax.swing.JScrollPane;

public class lstEntreprise extends JInternalFrame {
	private JTable table;
	private IMetier<Entreprise> metier = MetierFactory.getMetier(METIER.ENTREPRISE);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lstEntreprise frame = new lstEntreprise();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public lstEntreprise() {
		setBounds(100, 100, 789, 512);
		getContentPane().setLayout(null);
		
		List<Entreprise> ents = metier.getAll();
		
		ModelTableEntreprise model = new ModelTableEntreprise(ents);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 753, 268);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);

	}
}
