 import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.File;

 import javax.swing.JButton;
 import javax.swing.JDialog;
 import javax.swing.JFileChooser;
 import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class WindowForm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPaneV;
	private JSplitPane splitPaneH;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	public WindowForm() {
		setTitle("Split Pane Application");
		setBackground(Color.gray);
		
		JPanel topPanel = new JPanel();
		
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(700, 500));
		getContentPane().add(topPanel);
		
		// Create the panels
		createPanel1();
		createPanel2();
		createPanel3();
		
		// Create a splitter pane
	    splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    topPanel.add(splitPaneV, BorderLayout.CENTER);
	
	    splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    splitPaneH.setLeftComponent(panel1);
	    splitPaneH.setRightComponent(panel2);
	
	    splitPaneV.setLeftComponent(splitPaneH);
	    splitPaneV.setRightComponent(panel3);
	}

	public void createPanel1() {
	    panel1 = new JPanel();
	    panel1.setLayout(new BorderLayout());
	
	    // Add some buttons
	    panel1.add(new JButton("North"), BorderLayout.NORTH);
	    panel1.add(new JButton("South"), BorderLayout.SOUTH);
	    panel1.add(new JButton("East"), BorderLayout.EAST);
	    panel1.add(new JButton("West"), BorderLayout.WEST);
	    panel1.add(new JButton("Center"), BorderLayout.CENTER);
	
	}
	
	public void createPanel2() {
	    panel2 = new JPanel();
	    panel2.setLayout(new FlowLayout());
	
	    panel2.add(new JButton("Button 1"));
	    panel2.add(new JButton("Button 2"));
	    panel2.add(new JButton("Button 3"));
	}

	public void createPanel3() {
		panel3 = new JPanel();
	    panel3.setLayout(new BorderLayout());
	    panel3.setPreferredSize(new Dimension(400, 100));
	    panel3.setMinimumSize(new Dimension(100, 50));
	    final JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    panel3.add(fileChooser, BorderLayout.NORTH);
	    fileChooser.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	          if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
	             System.out.println("File selected: " + fileChooser.getSelectedFile());
	              }
	           }
	        });
	    }
	
	public static void main(String args[]) {
	// Create an instance of the test application
		WindowForm mainFrame = new WindowForm();
	    mainFrame.pack();
	    mainFrame.setVisible(true);
	}
}
	 
