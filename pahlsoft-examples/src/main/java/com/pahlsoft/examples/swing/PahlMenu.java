package com.pahlsoft.examples.swing;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class PahlMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4992541567836206417L;

	public static void main(String[] args) {
		// Program Version
		String version = new String("PahlSoft V1.0");

		// Define Objects
		final JFrame frame = new JFrame(version);
		JMenu file_menu = new JMenu("File");
		file_menu.setMnemonic(KeyEvent.VK_U);
		//file_menu.add(new JMenuItem("Exit"));

		JMenuItem exitItem = new JMenuItem("Exit");

		// Add some logic to the Exit Menuitem
		exitItem.setMnemonic(KeyEvent.VK_Q);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Event.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting");
				System.exit(0);
			}
		});

		file_menu.add(exitItem);
		
		final JPanel statusPanel = new JPanel();
		
		
		JMenu perspective_menu = new JMenu("Perspectives");
		//file_menu.setMnemonic(KeyEvent.VK_U);
		//file_menu.add(new JMenuItem("Exit"));

		frame.add(statusPanel);
		
		JMenuItem perspectiveItem1 = new JMenuItem("Architectural");
		perspectiveItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusPanel.setBackground(Color.green);
			}
		});
		
		JMenuItem perspectiveItem2 = new JMenuItem("Security");
		perspectiveItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				statusPanel.setBackground(Color.red);
			}
		});
		
		JMenuItem perspectiveItem3 = new JMenuItem("Coding");
		perspectiveItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				statusPanel.setBackground(Color.red);
				
			}
		});
		
		perspective_menu.add(perspectiveItem1);
		perspective_menu.add(perspectiveItem2);
		perspective_menu.add(perspectiveItem3);
		
		// Add Menus To MenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(file_menu);
		menuBar.add(perspective_menu);

		// Pack Frame and all objects
		frame.setJMenuBar(menuBar);

		frame.pack();

		// Set Values on Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 800);
		frame.setForeground(Color.BLUE);
		frame.setVisible(true);
	}

}

