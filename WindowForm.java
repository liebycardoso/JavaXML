import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WindowForm extends JFrame{
	
	private JPanel msgPanel;
	private JPanel chooserPanel;
	private JLabel smsLabel = new JLabel("CHOOSE A FILE ");
	private JLabel fChoosedLabel = new JLabel("File choosed: ");
	private JLabel fCreatedLabel = new JLabel("File converted: ");
	private JLabel statusLabel = new JLabel("Status: ");
	private String message;
	
	public WindowForm() {
		// Create master Jframe
		setTitle("JInterfaceFile");
		setBackground(Color.gray);
		
		setPreferredSize(new Dimension(900,600));
        setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        // Create the panels
 		createMsgPanel();
 		createChooserPanel();
        
 		// Split panel into 2 (Message and JFileChooser)
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, msgPanel, chooserPanel);
        getContentPane().add(splitPane);
				
	}

	public void createMsgPanel() {
		// Create panel that show text messages
		
	    msgPanel = new JPanel();
	    msgPanel.setLayout(new GridLayout(4,1));
	    msgPanel.setPreferredSize(new Dimension(800, 100));
	    
	    // Format text font
	    smsLabel.setFont(new Font("Serif", Font.BOLD, 40));
	    fChoosedLabel.setFont(new Font("Serif", Font.BOLD, 20));
	    fCreatedLabel.setFont(new Font("Serif", Font.BOLD, 20));
		statusLabel.setFont(new Font("Serif", Font.BOLD, 25));
		statusLabel.setForeground(Color.red);
   
		// Add text to the panel
	    msgPanel.add(smsLabel);
	    msgPanel.add(fChoosedLabel);
	    msgPanel.add(statusLabel);
	    msgPanel.add(fCreatedLabel);
	}

	public void createChooserPanel() {
		
		// Create the JFileChooser Panel
		chooserPanel = new JPanel();
	    chooserPanel.setLayout(new BorderLayout());
	    chooserPanel.setPreferredSize(new Dimension(400, 400));
	    
	    // Create JFileChooser object
	    final JFileChooser csvChooser = new JFileChooser();
	    
	    // Allows to view folders and files, but only select files
	    csvChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    
	    // Filter the file extension. Show only CSV
	    FileFilter filter = new FileNameExtensionFilter("*.csv","csv");
	    csvChooser.addChoosableFileFilter(filter);
	    csvChooser.setFileFilter(filter);
	    
	    // Add JFileChooser to the chooserPanel
	    chooserPanel.add(csvChooser, BorderLayout.SOUTH);
	    
	    // Add a listener to recognize when the button is clicked
	    csvChooser.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	
	    	// If the Choose is hit
	    	if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
	    		
				statusLabel.setText("Status: Processing...");
				fChoosedLabel.setText("File choosed: " + csvChooser.getSelectedFile().getName());
				//chooserPanel.setVisible(false);
				JInterfaceFile interfaceFile = new JInterfaceFile(csvChooser.getSelectedFile().getName());
				statusLabel.setText("Status: conversion completed successfully");
				fCreatedLabel.setText("File created: ");
				try {
					message = interfaceFile.convertFile();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
				}// try
		           } // if APPROVE_SELECTION
	         }// actionPerformed
	        });// addActionListener
	    
	    }//createChooserPanel
	
	public static void main(String args[]) {
	// Create the instance of this application
		WindowForm mainForm = new WindowForm();
		mainForm.pack();
		mainForm.setVisible(true);
	    
	}
}
	 
