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
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;


import com.ismo.brevets.ihm.modelTable.ModelTableInvention;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Domaine;
import com.ismo.brevets.models.Invention;


import java.awt.Font;
import javax.swing.ListSelectionModel;

public class lstInventions extends JInternalFrame {

	private JPanel contentPane;
	private IMetier<Invention> metier = MetierFactory.getMetier(METIER.INVENTION);
	private IMetier<Domaine> metier1 =  MetierFactory.getMetier(METIER.DOMAINE);
	List<Invention> invt;
	List<Domaine> en;
	private JTable table;
	private JTextField txtNum;
	private JTextField txtDescriptif;
	private JTextArea txtResume;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnAnnuler;
	private JComboBox<String> cmb;
	ModelTableInvention model;
	JScrollPane scrollPane;
	JScrollPane scroll;
	private JButton btnValider;
	Status mode = Status.NORMAL;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lstInventions frame = new lstInventions();
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
	public lstInventions() {
		getContentPane().setBackground(SystemColor.menu);
		
		BasicInternalFrameUI basicInternalFrameUI=((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI());
		for (MouseListener listener :basicInternalFrameUI.getNorthPane().getMouseListeners()) {
			basicInternalFrameUI.getNorthPane().removeMouseListener(listener);
		}
		setBackground(SystemColor.menu);
		setBounds(100, 100, 1325, 588);
		
		
		//creation et remplissage du comboBox
     	cmb= new JComboBox<String>();
    	cmb.setBounds(1030, 294, 249, 22);
    	getContentPane().add(cmb);
		getContentPane().setLayout(null);
		 invt= metier.getAll();
		 en=metier1.getAll();
		
	  for (int i=0; i < en.size(); i++) {
			
		  cmb.addItem(en.get(i).getNom());
		
		}
		
       //creation de la table
		model= new ModelTableInvention(invt);
	    scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 74, 675, 356);
		getContentPane().add(scrollPane);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Evenement pour afficher les informations d'une ligne de la table dans les textField
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row =table.getSelectedRow();
				txtNum.setText(String.format("%d",table.getValueAt(row, 0)));
				txtDescriptif.setText((String) table.getValueAt(row, 1));
				txtResume.setText((String) table.getValueAt(row, 2));
				cmb.setSelectedItem((String) table.getValueAt(row, 3));
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		
	  //Boutton Ajouter
     		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viderChamps();
				DisableButtons(true);
				mode = Status.ADD;
				
			}});
		btnAjouter.setBounds(85, 520, 200, 38);
		getContentPane().add(btnAjouter);
		
		// Boutton Supprimer
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
     int chx = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de vouloir supprimer cet invention?","Avertissement",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(chx==JOptionPane.YES_OPTION) {	
			    int row_index = table.getSelectedRow(); 
			    int id  = (int)table.getModel().getValueAt(row_index, 0); 
			     try {
			     if(metier.delete(metier.getOne(id))) {
			    	 Remplir();
			    	 JOptionPane.showConfirmDialog(null,"invention supprimé","Information",JOptionPane.OK_OPTION);
			     }
			     }catch(Exception ex){
			    	 JOptionPane.showConfirmDialog(null, "Erreur","Avertissement",JOptionPane.OK_OPTION);
			     }
			}
		}});
		btnSupprimer.setBounds(512, 520, 219, 38);
		getContentPane().add(btnSupprimer);
		
		
		
		//Boutton Modifier
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
		
		//Boutton Annuler
		//Reset All the component
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
		
		
	
		
		txtNum = new JTextField();
		txtNum.setSelectionColor(Color.BLACK);
		txtNum.setEnabled(false);
		txtNum.setBounds(1030, 108, 249, 20);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);
		
		txtDescriptif = new JTextField();
		txtDescriptif.setBounds(1030, 158, 249, 20);
		getContentPane().add(txtDescriptif);
		txtDescriptif.setColumns(10);
		
		txtResume = new JTextArea();
		txtResume.setBounds(1030, 207, 249, 61);
		getContentPane().add(txtResume);
		txtResume.setColumns(10);
		txtResume.setLineWrap(true);
		txtResume.setWrapStyleWord(true);
		
		scroll = new JScrollPane (txtResume, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(1030, 207, 249, 61);
		getContentPane().add(scroll);

		
		JLabel lblNum = new JLabel("Num\u00E9ro :");
		lblNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNum.setBounds(848, 114, 75, 14);
		getContentPane().add(lblNum);
		
		JLabel lblDescriptif = new JLabel("Descriptif :");
		lblDescriptif.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDescriptif.setBounds(848, 164, 73, 14);
		getContentPane().add(lblDescriptif);
		
		JLabel lblResume = new JLabel("Resume :");
		lblResume.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblResume.setBounds(848, 209, 75, 14);
		getContentPane().add(lblResume);
		
		JLabel lblDomaine = new JLabel("Domaine :");
		lblDomaine.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDomaine.setBounds(848, 297, 89, 14);
		getContentPane().add(lblDomaine);
		
		JLabel lblNewLabel = new JLabel("Liste des inventions");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(523, 11, 400, 35);
		getContentPane().add(lblNewLabel);
		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDescriptif.getText().equals("") || txtResume.getText().equals("")) {

					JOptionPane.showConfirmDialog(null, "Veuillez Remplir les champs vides", "Avertissement",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {
					Invention i = new Invention();
					i.setDescriptif(txtDescriptif.getText());
					i.setResume(txtResume.getText());
	         		Domaine d=new Domaine();
	         		d.setNum(cmb.getSelectedIndex() + 1);
					i.setDomaine(d);
					switch (mode) {
					case ADD:
						metier.save(i);
						JOptionPane.showMessageDialog(null, "Invention ajoute avec succes");
						break;
					case UPDATE:
						i.setNum(Integer.valueOf(txtNum.getText()));
						metier.update(i);
						JOptionPane.showMessageDialog(null, "Invention modifier avec succes");
						break;
					}
					Remplir();// this Method in order to refresh the table after we add a new invention
					mode = Status.NORMAL;
					DisableButtons(false);
			}}
		});
		btnValider.setForeground(new Color(0, 0, 128));
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnValider.setEnabled(false);
		btnValider.setBounds(831, 520, 219, 38);
		getContentPane().add(btnValider);
	
		DisableButtons(false);
	
	
	}
	public void DisableButtons(boolean d) {
		btnAjouter.setEnabled(!d);
		btnModifier.setEnabled(!d);
		btnSupprimer.setEnabled(!d);
		btnAnnuler.setEnabled(d);
		btnValider.setEnabled(d);

		table.setEnabled(!d);
		txtDescriptif.setEnabled(d);
		txtResume.setEnabled(d);
		cmb.setEnabled(d);
	}
	public void viderChamps() {
		txtDescriptif.setText("");
		txtResume.setText("");
		cmb.setSelectedIndex(0);
	}
// refresh the table 
	public void Remplir() {
		 List<Invention> inv = metier.getAll();	
		  model = new ModelTableInvention(inv);
		  scrollPane.setViewportView(table);
		   table.setModel(model);
		
	}
}