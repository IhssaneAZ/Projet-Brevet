package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.ui.ApplicationFrame;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;


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
		
		setResizable(false);
		setTitle("Gestion des brevets");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInventeur = new JMenu("Inventeurs");
		menuBar.add(mnInventeur);
		
		JMenuItem mntmListeDesInventeurs = new JMenuItem("Liste des inventeurs");
		mntmListeDesInventeurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				lstInventeur i = new lstInventeur();
				i.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				i.setLocation(0, 0);
				desktopPane.add(i);
				i.setVisible(true);
			}
		});

		mnInventeur.add(mntmListeDesInventeurs);
		
	
		JMenu mnInventions = new JMenu("Inventions");
		menuBar.add(mnInventions);
		
		JMenuItem mntmListeDesInventions = new JMenuItem("Liste des inventions");
		mntmListeDesInventions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				lstInventions i = new lstInventions();
				i.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				i.setLocation(0, 0);
				desktopPane.add(i);
				i.setVisible(true);
			}
		});

		mnInventions.add(mntmListeDesInventions);
		
		JMenu mnEntreprises = new JMenu("Entreprises");
		menuBar.add(mnEntreprises);
		
		JMenuItem mntmListeDesEntreprises = new JMenuItem("Liste des entreprises");
		mntmListeDesEntreprises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				lstEntreprise e = new lstEntreprise();
				e.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				e.setLocation(0,0);
				desktopPane.add(e);
				e.setVisible(true);
				
			}
		});
		mnEntreprises.add(mntmListeDesEntreprises);
		
		JMenu mnBrevets = new JMenu("Brevets");
		menuBar.add(mnBrevets);
		
		JMenuItem mntmListeDesBrevets = new JMenuItem("Liste des brevets");
		mntmListeDesBrevets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				lstBrevets e = new lstBrevets();
				e.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				e.setLocation(0,0);
				desktopPane.add(e);
				e.setVisible(true);
				
			}
		});
		mnBrevets.add(mntmListeDesBrevets);
		
		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);
		
		JMenuItem mntmListeDesInventeurs_1 = new JMenuItem("Liste des inventeurs");
		mntmListeDesInventeurs_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Afficher("ListeInventeur.jasper");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnReporting.add(mntmListeDesInventeurs_1);
		
		JMenuItem mntmListeDesInventions_1 = new JMenuItem("Liste des inventions");
		mntmListeDesInventions_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Afficher("ListeInventions.jasper");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnReporting.add(mntmListeDesInventions_1);
		
		JMenuItem mntmListeDesEntreprises_1 = new JMenuItem("Liste des entreprises");
		mntmListeDesEntreprises_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Afficher("ListeEntreprises.jasper");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnReporting.add(mntmListeDesEntreprises_1);
		
		JMenuItem mntmListeDesBrevets_1 = new JMenuItem("Liste des brevets");
		mntmListeDesBrevets_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Afficher("ListeBrevets.jasper");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnReporting.add(mntmListeDesBrevets_1);
		
		JMenu mnGraphes = new JMenu("Graphes");
		menuBar.add(mnGraphes);
		
		JMenuItem mntmInventionParEntreprise = new JMenuItem("Invention par entreprise");
		mntmInventionParEntreprise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDBCPieDataset dataset;
				
					try {
						String query = "SELECT NOM_ENTREPRISE , NUM_INVENTION from entreprise , invention ";
						dataset = new JDBCPieDataset(
								"jdbc:mysql://localhost:3306/dbbrevets", "com.mysql.jdbc.Driver",
								"root", "");
						dataset.executeQuery(query);
						JFreeChart chart = ChartFactory.createPieChart("INVENTION PAR ENTREPRISE", dataset, true,
				    			true, false);
				    	ChartPanel chartPanel = new ChartPanel(chart);
				    	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
				    	ApplicationFrame f = new ApplicationFrame("Chart");
				    	f.setContentPane(chartPanel);
				    	f.pack();
				    	f.setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	
			}
		});
		mnGraphes.add(mntmInventionParEntreprise);
		
		JMenuItem mntmInventionParDomaine = new JMenuItem("Invention par domaine");
		mntmInventionParDomaine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDBCPieDataset dataset;
				
				try {
					String query = "SELECT NOM_DOMAINE , NUM_INVENTION from domaine , invention ";
					dataset = new JDBCPieDataset(
							"jdbc:mysql://localhost:3306/dbbrevets", "com.mysql.jdbc.Driver",
							"root", "");
					dataset.executeQuery(query);
					JFreeChart chart = ChartFactory.createPieChart("INVENTION PAR DOMAINE", dataset, true,
			    			true, false);
			    	ChartPanel chartPanel = new ChartPanel(chart);
			    	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
			    	ApplicationFrame f = new ApplicationFrame("Chart");
			    	f.setContentPane(chartPanel);
			    	f.pack();
			    	f.setVisible(true);
				} catch (ClassNotFoundException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				} catch (SQLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
	    	
			}
		});
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
	public void Afficher(String path) throws ClassNotFoundException, SQLException {
		Connection conn = null; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3308/dbbrevets",
                    "root", "123456");
           
            JasperPrint print = JasperFillManager.fillReport(path, null, conn);
            JRViewer viewer = new JRViewer(print);
            viewer.setOpaque(true);
            
            JFrame frame = new JFrame("Jasper report");

            frame.getContentPane().add(viewer);
            frame.setSize(new Dimension(750, 650));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        
        
	}
	
	

	
}
