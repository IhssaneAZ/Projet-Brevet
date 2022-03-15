package com.ismo.brevets.ihm;

import java.awt.Color;
import java.awt.EventQueue;
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

import com.ismo.brevets.ihm.modelTable.ModelTableInventeur;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Entreprise;
import com.ismo.brevets.models.Inventeur;

import java.awt.Font;
import javax.swing.ListSelectionModel;

public class lstInventeur extends JInternalFrame {

	private JPanel contentPane;
	private IMetier<Inventeur> metier = MetierFactory.getMetier(METIER.INVENTEUR);
	private IMetier<Entreprise> metier1 = MetierFactory.getMetier(METIER.ENTREPRISE);
	List<Inventeur> invt;
	List<Entreprise> en;
	private JTable table;
	private JTextField txtNum;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JTextField txtDate;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnAnnuler;
	JComboBox<String> cmb;
	ModelTableInventeur model;
	JScrollPane scrollPane;
	Status mode = Status.NORMAL;
	private JButton btnValider;

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
	public lstInventeur() {

		getContentPane().setBackground(SystemColor.menu);

		BasicInternalFrameUI basicInternalFrameUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener : basicInternalFrameUI.getNorthPane().getMouseListeners()) {
			basicInternalFrameUI.getNorthPane().removeMouseListener(listener);
		}
		setBackground(SystemColor.menu);
		setBounds(100, 100, 1325, 588);

		// creation et remplissage du comboBox
		cmb = new JComboBox<String>();
		cmb.setBounds(1030, 348, 249, 22);
		getContentPane().add(cmb);
		getContentPane().setLayout(null);
		invt = metier.getAll();
		en = metier1.getAll();

		for (int i = 0; i < en.size(); i++) {

			cmb.addItem(en.get(i).getNom());

		}

		// creation de la table
		model = new ModelTableInventeur(invt);
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
				txtPrenom.setText((String) table.getValueAt(row, 2));
				txtAdresse.setText((String) table.getValueAt(row, 3));
				txtDate.setText(table.getValueAt(row, 4).toString());
				cmb.setSelectedItem((String) table.getValueAt(row, 5));
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);
		
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

		txtPrenom = new JTextField();
		txtPrenom.setBounds(1030, 212, 249, 20);
		getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);

		txtAdresse = new JTextField();
		txtAdresse.setBounds(1030, 261, 249, 20);
		getContentPane().add(txtAdresse);
		txtAdresse.setColumns(10);

		txtDate = new JTextField();
		txtDate.setBackground(SystemColor.window);
		// cet evenement pour le placeHolder du textbox Date
		txtDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtDate.getText().equals("Exemple: 2000-02-24")) {
					txtDate.setText("");
					txtDate.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDate.getText().equals("")) {
					txtDate.setText("Exemple: 2000-02-24");
					txtDate.setForeground(new Color(128, 128, 128));
				}
			}
		});

		txtDate.setForeground(Color.LIGHT_GRAY);
		txtDate.setText("Exemple: 2000-02-24");
		txtDate.setBounds(1030, 301, 249, 20);
		getContentPane().add(txtDate);
		txtDate.setColumns(10);

		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(mode);
				if (txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtAdresse.getText().equals("")
						|| txtDate.getText().equals("")) {

					JOptionPane.showConfirmDialog(null, "Veuillez Remplir les champs vides", "Avertissement",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {

					Inventeur i = new Inventeur();
					i.setNom(txtNom.getText());
					i.setPrenom(txtPrenom.getText());
					i.setAdresse(txtAdresse.getText());
					i.setDate_nais(
							LocalDate.parse(txtDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd", getLocale())));
					Entreprise ent = new Entreprise();
					ent.setNum(cmb.getSelectedIndex() + 1);
					i.setEntreprise(ent);

					switch (mode) {
					case ADD:
						metier.save(i);
						JOptionPane.showMessageDialog(null, "Inventeur ajoute avec succes");
						break;
					case UPDATE:
						i.setNum(Integer.parseInt(txtNum.getText()));
						metier.update(i);
						JOptionPane.showMessageDialog(null, "Inventeur modifier avec succes");
						break;
					}
					Remplir();// this Method in order to refresh the table after we add a new inventeur
					mode = Status.NORMAL;
					DisableButtons(false);
				}
			}
		});
		btnValider.setForeground(new Color(0, 0, 128));
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setBounds(831, 520, 219, 38);
		getContentPane().add(btnValider);

		// Boutton Ajouter

		btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.setBounds(85, 520, 200, 38);
		getContentPane().add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viderChamps();
				DisableButtons(true);
				mode = Status.ADD;
			}
		});

		// Boutton Supprimer
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int chx = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer cet inventeur?",
						"Avertissement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (chx == JOptionPane.YES_OPTION) {
					int row_index = table.getSelectedRow();
					int id = (int) table.getModel().getValueAt(row_index, 0);
					try {
						if (metier.delete(metier.getOne(id))) {
							Remplir();
							JOptionPane.showConfirmDialog(null, "inventeur supprimé", "Information",
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

		
		JLabel lblNum = new JLabel("Num\u00E9ro :");
		lblNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNum.setBounds(848, 114, 75, 14);
		getContentPane().add(lblNum);

		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNom.setBounds(848, 162, 46, 14);
		getContentPane().add(lblNom);

		JLabel lblPrenom = new JLabel("Pr\u00E9nom :");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPrenom.setBounds(850, 218, 73, 14);
		getContentPane().add(lblPrenom);

		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAdresse.setBounds(848, 263, 75, 14);
		getContentPane().add(lblAdresse);

		JLabel lblDate = new JLabel("Date de Naissance :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDate.setBounds(848, 307, 134, 14);
		getContentPane().add(lblDate);

		JLabel lblEntreprise = new JLabel(" Entreprise :");
		lblEntreprise.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblEntreprise.setBounds(848, 351, 89, 14);
		getContentPane().add(lblEntreprise);

		JLabel lblNewLabel = new JLabel("Liste des inventeurs");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(523, 11, 400, 35);
		getContentPane().add(lblNewLabel);



	}

	public void DisableButtons(boolean d) {
		btnAjouter.setEnabled(!d);
		btnModifier.setEnabled(!d);
		btnSupprimer.setEnabled(!d);
		btnAnnuler.setEnabled(d);
		btnValider.setEnabled(d);

		table.setEnabled(!d);
		txtNom.setEnabled(d);
		txtPrenom.setEnabled(d);
		txtAdresse.setEnabled(d);
		txtDate.setEnabled(d);
		cmb.setEnabled(d);
	}

	// refresh the table
	public void Remplir() {
		List<Inventeur> inv = metier.getAll();
		model = new ModelTableInventeur(inv);
		scrollPane.setViewportView(table);
		table.setModel(model);

	}

	public void viderChamps() {
		txtNom.setText("");
		txtPrenom.setText("");
		txtAdresse.setText("");
		txtDate.setText("");
		cmb.setSelectedIndex(0);// ocp the Item by default
	}
}