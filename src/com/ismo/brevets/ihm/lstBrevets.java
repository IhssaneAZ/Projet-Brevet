package com.ismo.brevets.ihm;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class lstBrevets extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lstBrevets frame = new lstBrevets();
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
		setBounds(100, 100, 774, 473);

	}

}
