import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class extends form JFrame.
 * Creates a form with 2 panels where the user can
 * choose a file from JFileChooser object.
 *  
 *  @author Lieby,Rodolfo,Valdeci
 *  @version 1.0
 *  @since 2018-12-01
 */

public class WindowForm extends JFrame{

	/**
	 * Creates all Jframe elements
	 */
	private static final long serialVersionUID = 1L;
	private JPanel msgPanel;
	private JPanel chooserPanel;
	private JLabel smsLabel = new JLabel("JInterfaceFile - Choose a file below");
	private JLabel fChoosedLabel = new JLabel("File choosed: ");
	private JLabel fCreatedLabel = new JLabel("File converted: ");
	private JLabel statusLabel = new JLabel("Status: ");
	private String message, fileName;

	public WindowForm() {
		// Create master Jframe
		setTitle("JInterfaceFile");
		setBackground(Color.gray);

		setPreferredSize(new Dimension(900,700));
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
		/**
		 *  Create a panel that show text messages 
		 */
		
		msgPanel = new JPanel();
		msgPanel.setLayout(new GridLayout(4,1));
		msgPanel.setPreferredSize(new Dimension(800, 200));

		// Format text font
		smsLabel.setFont(new Font("Serif", Font.BOLD, 30));
		fChoosedLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		fCreatedLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		statusLabel.setFont(new Font("Serif", Font.BOLD, 25));
		statusLabel.setForeground(Color.red);

		// Add text to the panel
		msgPanel.add(smsLabel,  BorderLayout.CENTER);
		msgPanel.add(fChoosedLabel);
		msgPanel.add(fCreatedLabel);
		msgPanel.add(statusLabel);
	}

	public void createChooserPanel() {
		/**
		 *  Creates the JFileChooser Panel 
		 */

		chooserPanel = new JPanel();
		chooserPanel.setLayout(new BorderLayout());
		chooserPanel.setPreferredSize(new Dimension(400, 300));

		// Create JFileChooser object
		final JFileChooser csvChooser = new JFileChooser();

		// Allows to view folders and files, but only select files
		csvChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// Filter the file extension. Show only CSV
		FileFilter filter = new FileNameExtensionFilter("*.csv","csv");
		csvChooser.addChoosableFileFilter(filter);
		csvChooser.setFileFilter(filter);

		// Add JFileChooser to the chooserPanel
		chooserPanel.add(csvChooser, BorderLayout.CENTER);

		// Add a listener to recognize when the button is clicked
		csvChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEv) {

				// If the Choose is press
				if (actEv.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
					
					fileName = csvChooser.getSelectedFile().getAbsolutePath();
					statusLabel.setText("Status: Processing...");
					fChoosedLabel.setText("File choosed: " + fileName );
					
					try {
						message = JInterfaceFile.convertFile(fileName);
						statusLabel.setText("Status: " + message);
						fCreatedLabel.setText("File created: "+ fileName.replace(".csv", ".xml"));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}// try
				}else if (actEv.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)){
					System.exit(0);			    	 
				}
			}// actionPerformed
		});// addActionListener

	}//createChooserPanel

	public static void main(String args[]) {
		/**
		 *  Creates the instance of this application 
		 */		
		WindowForm mainForm = new WindowForm();
		mainForm.pack();
		mainForm.setVisible(true);

	}
}

