package com.ismo.brevets.ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.ismo.brevets.ihm.modelTable.ModelTableBrevet;
import com.ismo.brevets.ihm.modelTable.ModelTableInventeur;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Inventeur;
import com.ismo.brevets.models.Invention;
import com.ismo.brevets.models.Brevet;
import javax.swing.ListSelectionModel;

public class lstBrevets extends JInternalFrame {

	private JPanel contentPane;
	private IMetier<Brevet> metier = MetierFactory.getMetier(METIER.BREVET);
	private IMetier<Inventeur> metier1 = MetierFactory.getMetier(METIER.INVENTEUR);
	private IMetier<Invention> metier2 = MetierFactory.getMetier(METIER.INVENTION);
	List<Brevet> brevs;
	List<Inventeur> inventeurs;
	List<Invention> inventions;
	private JTable table;
	private JTextField txtNum;
	private JTextField txtNom;
	private JTextField txtDateDepot;
	private JComboBox<String> cmbInventeur;
	private JComboBox<String> cmbInvention;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnAnnuler;
	private JButton btnValider;
	Status mode = Status.NORMAL;
	ModelTableBrevet model;
	JScrollPane scrollPane;
	private JTextField txtDateValidation;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lstInventeur frame = new lstInventeur();
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
	public lstBrevets() {

		getContentPane().setBackground(SystemColor.menu);

		BasicInternalFrameUI basicInternalFrameUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener : basicInternalFrameUI.getNorthPane().getMouseListeners()) {
			basicInternalFrameUI.getNorthPane().removeMouseListener(listener);
		}
		setBackground(SystemColor.menu);
		setBounds(100, 100, 1325, 588);

		// creation et remplissage du comboBox
		cmbInvention = new JComboBox<String>();
		cmbInvention.setBounds(1030, 348, 249, 22);
		getContentPane().add(cmbInvention);

		cmbInventeur = new JComboBox<String>();
		cmbInventeur.setBounds(1030, 305, 249, 22);
		getContentPane().add(cmbInventeur);

		getContentPane().setLayout(null);
		brevs = metier.getAll();
		inventeurs = metier1.getAll();
		inventions = metier2.getAll();

		for (int i = 0; i < inventeurs.size(); i++) {

			cmbInventeur.addItem(inventeurs.get(i).getNom());

		}
		for (int i = 0; i < inventions.size(); i++) {

			cmbInvention.addItem(inventions.get(i).getDescriptif());

		}

		// creation de la table
		model = new ModelTableBrevet(brevs);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 74, 675, 356);
		getContentPane().add(scrollPane);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Evenement pour afficher les informations d'une ligne de la table dans les
		// textField
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				txtNum.setText(String.format("%d", table.getValueAt(row, 0)));
				txtNom.setText((String) table.getValueAt(row, 1));
				txtDateDepot.setText((String) table.getValueAt(row, 2).toString());
				txtDateValidation.setText((String) table.getValueAt(row, 3).toString());
				cmbInventeur.setSelectedItem((String) table.getValueAt(row, 5));
				cmbInvention.setSelectedItem((String) table.getValueAt(row, 4));
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);

		// Boutton Ajouter

		btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viderChamps();
				DisableButtons(true);
				mode = Status.ADD;
			}
		});
		btnAjouter.setBounds(85, 520, 200, 38);
		getContentPane().add(btnAjouter);

		// Boutton Supprimer
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int chx = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer cette brevet?",
						"Avertissement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (chx == JOptionPane.YES_OPTION) {
					int row_index = table.getSelectedRow();
					int id = (int) table.getModel().getValueAt(row_index, 0);
					try {
						if (metier.delete(metier.getOne(id))) {
							Remplir();
							JOptionPane.showConfirmDialog(null, "brevet supprimé", "Information",
									JOptionPane.OK_OPTION);
						}
					} catch (Exception ex) {
						JOptionPane.showConfirmDialog(null, "Erreur", "Avertissement", JOptionPane.OK_OPTION);
					}
				}
			}
		});
		btnSupprimer.setBounds(512, 520, 219, 38);
		getContentPane().add(btnSupprimer);

		// Boutton Modifier
		btnModifier = new JButton("Modifier");
		btnModifier.setForeground(new Color(0, 0, 128));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisableButtons(true);
				mode = Status.UPDATE;
			}
		});
		btnModifier.setBounds(295, 520, 207, 38);
		getContentPane().add(btnModifier);

		// Boutton Annuler
		// Reset All the component
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisableButtons(false);
				viderChamps();
			}
		});
		
		btnAnnuler.setForeground(new Color(0, 0, 128));
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnnuler.setBounds(1060, 520, 219, 38);
		getContentPane().add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viderChamps();
				DisableButtons(false);
			}});
		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtNom.getText().equals("") || txtDateValidation.getText().equals("")
						|| txtDateDepot.getText().equals("")) {

					JOptionPane.showConfirmDialog(null, "Veuillez Remplir les champs vides", "Avertissement",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {
					Brevet b = new Brevet();
					
					b.setDescription(txtNom.getText());
					b.setDateDepot(LocalDate.parse(txtDateDepot.getText(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd", getLocale())));
					b.setDateValidation(LocalDate.parse(txtDateValidation.getText(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd", getLocale())));
					Inventeur inventeur = new Inventeur();
					Invention invention = new Invention();
					inventeur.setNum(cmbInventeur.getSelectedIndex() + 1);
					invention.setNum(cmbInvention.getSelectedIndex() + 1);
					b.setInventeur(inventeur);
					b.setInvention(invention);

					switch (mode) {
					case ADD:
						metier.save(b);
						JOptionPane.showMessageDialog(null, "Brevet ajoute avec succes");
						break;
					case UPDATE:
						b.setNum(Integer.valueOf(txtNum.getText()));
						metier.update(b);
						JOptionPane.showMessageDialog(null, "Brevet modifier avec succes");
						break;
					}
					Remplir();// this Method in order to refresh the table after we add a new brevet
					mode = Status.NORMAL;
					DisableButtons(false);
				}

			}
		});
		btnValider.setForeground(new Color(0, 0, 128));
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setBounds(831, 520, 219, 38);
		getContentPane().add(btnValider);

		txtNum = new JTextField();
		txtNum.setSelectionColor(Color.BLACK);
		txtNum.setEnabled(false);
		txtNum.setBounds(1030, 108, 249, 20);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);

		txtNom = new JTextField();
		txtNom.setBounds(1030, 160, 249, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtDateDepot = new JTextField();
		txtDateDepot.setBackground(SystemColor.window);
		// cet evenement pour le placeHolder du textbox Date
		txtDateDepot.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtDateDepot.getText().equals("Exemple: 2000-02-24")) {
					txtDateDepot.setText("");
					txtDateDepot.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDateDepot.getText().equals("")) {
					txtDateDepot.setText("Exemple: 2000-02-24");
					txtDateDepot.setForeground(new Color(128, 128, 128));
				}
			}
		});

		txtDateDepot.setForeground(Color.LIGHT_GRAY);
		txtDateDepot.setText("Exemple: 2000-02-24");
		txtDateDepot.setBounds(1030, 216, 249, 20);
		getContentPane().add(txtDateDepot);
		txtDateDepot.setColumns(10);

		JLabel lblNum = new JLabel("Num\u00E9ro :");
		lblNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNum.setBounds(848, 114, 75, 14);
		getContentPane().add(lblNum);

		JLabel lblDescriptif = new JLabel("Descriptif : ");
		lblDescriptif.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDescriptif.setBounds(848, 162, 102, 14);
		getContentPane().add(lblDescriptif);

		JLabel lblPrenom = new JLabel("Date du depot :");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPrenom.setBounds(850, 218, 132, 14);
		getContentPane().add(lblPrenom);

		JLabel lblAdresse = new JLabel("Date de validation :");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAdresse.setBounds(848, 263, 134, 14);
		getContentPane().add(lblAdresse);

		JLabel lbllInventeur = new JLabel("Inventeur :");
		lbllInventeur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lbllInventeur.setBounds(848, 307, 134, 14);
		getContentPane().add(lbllInventeur);

		JLabel lblInvention = new JLabel(" Invention :");
		lblInvention.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblInvention.setBounds(848, 351, 89, 14);
		getContentPane().add(lblInvention);

		JLabel lblNewLabel = new JLabel("Liste des brevets");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(523, 11, 400, 35);
		getContentPane().add(lblNewLabel);

		txtDateValidation = new JTextField();
		txtDateValidation.setText("Exemple: 2000-02-24");
		txtDateValidation.setForeground(Color.LIGHT_GRAY);
		txtDateValidation.setColumns(10);
		txtDateValidation.setBackground(Color.WHITE);
		txtDateValidation.setBounds(1030, 261, 249, 20);
		getContentPane().add(txtDateValidation);

		DisableButtons(false);

	}

	public void DisableButtons(boolean d) {
		btnAjouter.setEnabled(!d);
		btnModifier.setEnabled(!d);
		btnSupprimer.setEnabled(!d);
		btnAnnuler.setEnabled(d);
		btnValider.setEnabled(d);

		table.setEnabled(!d);
		txtNom.setEnabled(d);
		txtDateDepot.setEnabled(d);
		txtDateValidation.setEnabled(d);
		cmbInventeur.setEnabled(d);
		cmbInvention.setEnabled(d);
	}

	// refresh the table
	public void Remplir() {
		List<Brevet> brv = metier.getAll();
		model = new ModelTableBrevet(brv);
		scrollPane.setViewportView(table);
		table.setModel(model);

	}

	public void viderChamps() {
		txtNom.setText("");
		txtDateValidation.setText("");
		txtDateDepot.setText("");
		cmbInvention.setSelectedIndex(0);
		cmbInventeur.setSelectedIndex(0);// ocp the Item by default
	}
}
