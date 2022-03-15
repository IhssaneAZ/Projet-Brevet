package com.ismo.brevets.ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.ismo.brevets.ihm.controlls.ControleDecimal;
import com.ismo.brevets.ihm.modelTable.ModelTableEntreprise;
import com.ismo.brevets.ihm.modelTable.ModelTableEntreprise;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Brevet;
import com.ismo.brevets.models.Domaine;
import com.ismo.brevets.models.Entreprise;
import com.ismo.brevets.models.Inventeur;
import com.ismo.brevets.models.Invention;
import com.ismo.brevets.models.Entreprise;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class lstEntreprise extends JInternalFrame {
	private JPanel contentPane;
	private IMetier<Entreprise> metier = MetierFactory.getMetier(METIER.ENTREPRISE);
	List<Entreprise> invt;
	List<Domaine> en;
	private JTable table;
	private JTextField txtNum;
	private JTextField txtNom;
	private JTextField txtActivite;
	ModelTableEntreprise model;
	JScrollPane scrollPane;
	private JTextField txtVille;
	private JTextField txtCA;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnValider;
	private JButton btnAnnuler;
	Status mode = Status.NORMAL;

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
		getContentPane().setBackground(SystemColor.menu);

		BasicInternalFrameUI basicInternalFrameUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener : basicInternalFrameUI.getNorthPane().getMouseListeners()) {
			basicInternalFrameUI.getNorthPane().removeMouseListener(listener);
		}
		setBackground(SystemColor.menu);
		setBounds(100, 100, 1325, 588);

		getContentPane().setLayout(null);
		invt = metier.getAll();

		// creation de la table
		model = new ModelTableEntreprise(invt);
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
				txtActivite.setText((String) table.getValueAt(row, 2));
				txtCA.setText(table.getValueAt(row, 3).toString());
				txtVille.setText((String) table.getValueAt(row, 4));
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
		txtNom.setBounds(1030, 158, 249, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtActivite = new JTextField();
		txtActivite.setBounds(1030, 207, 249, 20);
		getContentPane().add(txtActivite);
		txtActivite.setColumns(10);

		JLabel lblNum = new JLabel("Num\u00E9ro :");
		lblNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNum.setBounds(848, 114, 75, 14);
		getContentPane().add(lblNum);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNom.setBounds(848, 164, 73, 14);
		getContentPane().add(lblNom);

		JLabel lblActivite = new JLabel("Activit\u00E9 :");
		lblActivite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblActivite.setBounds(848, 209, 75, 14);
		getContentPane().add(lblActivite);

		JLabel lblNewLabel = new JLabel("Liste des entreprises");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(523, 11, 400, 35);
		getContentPane().add(lblNewLabel);

		JLabel lblVille = new JLabel("Ville :");
		lblVille.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblVille.setBounds(848, 308, 75, 14);
		getContentPane().add(lblVille);

		txtVille = new JTextField();
		txtVille.setColumns(10);
		txtVille.setBounds(1030, 306, 249, 20);
		getContentPane().add(txtVille);

		txtCA = new JTextField();
		txtCA.setDocument(new ControleDecimal());
		txtCA.setColumns(10);
		txtCA.setBounds(1030, 257, 249, 20);
		getContentPane().add(txtCA);

		JLabel lblCA = new JLabel("CA :");
		lblCA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCA.setBounds(848, 263, 73, 14);
		getContentPane().add(lblCA);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viderChamps();
				DisableButtons(true);
				mode = Status.ADD;
			}
		});
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.setEnabled(true);
		btnAjouter.setBounds(85, 520, 200, 38);
		getContentPane().add(btnAjouter);

		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisableButtons(true);
				mode = Status.UPDATE;
			}
		});
		btnModifier.setForeground(new Color(0, 0, 128));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModifier.setEnabled(true);
		btnModifier.setBounds(295, 520, 207, 38);
		getContentPane().add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int chx = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer cette entreprise?",
						"Avertissement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (chx == JOptionPane.YES_OPTION) {
					int row_index = table.getSelectedRow();
					int id = (int) table.getModel().getValueAt(row_index, 0);
					try {
						if (metier.delete(metier.getOne(id))) {
							Remplir();
							JOptionPane.showConfirmDialog(null, "entreprise supprimé", "Information",
									JOptionPane.OK_OPTION);
						}
					} catch (Exception ex) {
						JOptionPane.showConfirmDialog(null, "Erreur", "Avertissement", JOptionPane.OK_OPTION);
					}
				}
			}
		});
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimer.setEnabled(true);
		btnSupprimer.setBounds(512, 520, 219, 38);
		getContentPane().add(btnSupprimer);

		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNom.getText().equals("") || txtActivite.getText().equals("")) {

					JOptionPane.showConfirmDialog(null, "Veuillez Remplir les champs vides", "Avertissement",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {

					Entreprise ent = new Entreprise();
					ent.setNom(txtNom.getText());
					ent.setVille(txtVille.getText());
					ent.setActivite(txtActivite.getText());
					ent.setCa(Double.parseDouble(txtCA.getText()));
					

					switch (mode) {
					case ADD:
						metier.save(ent);
						JOptionPane.showMessageDialog(null, "Entreprise ajoute avec succes");
						break;
					case UPDATE:
						ent.setNum(Integer.valueOf(txtNum.getText()));
						metier.update(ent);
						JOptionPane.showMessageDialog(null, "Entreprise modifier avec succes");
						break;
					}
					Remplir();// this Method in order to refresh the table after we add a new entreprise
					mode = Status.NORMAL;
					DisableButtons(false);
				}

			}
		});
		btnValider.setForeground(new Color(0, 0, 128));
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setEnabled(false);
		btnValider.setBounds(831, 520, 219, 38);
		getContentPane().add(btnValider);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisableButtons(false);
				viderChamps();
			}
		});
		btnAnnuler.setForeground(new Color(0, 0, 128));
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnnuler.setEnabled(false);
		btnAnnuler.setBounds(1060, 520, 219, 38);
		getContentPane().add(btnAnnuler);
		
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
		txtActivite.setEnabled(d);
		txtCA.setEnabled(d);
		txtNom.setEnabled(d);
		txtVille.setEnabled(d);
	}

	// refresh the table
	public void Remplir() {
		List<Entreprise> inv = metier.getAll();
		model = new ModelTableEntreprise(inv);
		scrollPane.setViewportView(table);
		table.setModel(model);
	}
	public void viderChamps() {
		txtNom.setText("");
		txtActivite.setText("");
		txtCA.setText("");
		txtVille.setText("");
	}
}
