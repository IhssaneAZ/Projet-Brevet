package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Gestion des brevets");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 442);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInventeur = new JMenu("Inventeurs");
		menuBar.add(mnInventeur);
		
		JMenuItem mntmListeDesInventeurs = new JMenuItem("Liste des inventeurs");
		mntmListeDesInventeurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lstBrevets frm = new lstBrevets();
	
				desktopPane.add(frm);
				frm.setVisible(true);
			}
		});
		mnInventeur.add(mntmListeDesInventeurs);
		
		JMenu mnInventions = new JMenu("Inventions");
		menuBar.add(mnInventions);
		
		JMenuItem mntmListeDesInventions = new JMenuItem("Liste des inventions");
		mnInventions.add(mntmListeDesInventions);
		
		JMenu mnEntreprises = new JMenu("Entreprises");
		menuBar.add(mnEntreprises);
		
		JMenuItem mntmListeDesEntreprises = new JMenuItem("Liste des entreprises");
		mntmListeDesEntreprises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lstEntreprise e = new lstEntreprise();
				desktopPane.add(e);
				e.setVisible(true);
			}
		});
		mnEntreprises.add(mntmListeDesEntreprises);
		
		JMenu mnBrevets = new JMenu("Brevets");
		menuBar.add(mnBrevets);
		
		JMenuItem mntmListeDesBrevets = new JMenuItem("Liste des brevets");
		mnBrevets.add(mntmListeDesBrevets);
		
		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);
		
		JMenuItem mntmListeDesInventeurs_1 = new JMenuItem("Liste des inventeurs");
		mnReporting.add(mntmListeDesInventeurs_1);
		
		JMenuItem mntmListeDesInventions_1 = new JMenuItem("Liste des inventions");
		mnReporting.add(mntmListeDesInventions_1);
		
		JMenuItem mntmListeDesEntreprises_1 = new JMenuItem("Liste des entreprises");
		mnReporting.add(mntmListeDesEntreprises_1);
		
		JMenuItem mntmListeDesBrevets_1 = new JMenuItem("Liste des brevets");
		mnReporting.add(mntmListeDesBrevets_1);
		
		JMenu mnGraphes = new JMenu("Graphes");
		menuBar.add(mnGraphes);
		
		JMenuItem mntmInventionParEntreprise = new JMenuItem("Invention par entreprise");
		mnGraphes.add(mntmInventionParEntreprise);
		
		JMenuItem mntmInventionParDomaine = new JMenuItem("Invention par domaine");
		mnGraphes.add(mntmInventionParDomaine);
		
		JMenu mnAPropos = new JMenu("A propos");
		menuBar.add(mnAPropos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
}
