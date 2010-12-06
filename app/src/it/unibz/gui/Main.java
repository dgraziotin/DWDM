package it.unibz.gui;

import it.unibz.algorithms.dbscan.DBScan;
import it.unibz.algorithms.dbscan.Utility;
import it.unibz.algorithms.kmeans.DataPoint;
import it.unibz.algorithms.kmeans.JCA;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JDesktopPane;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Main
{

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="10,10"

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu fileMenu = null;

	private JMenu editMenu = null;

	private JMenu helpMenu = null;

	private JMenuItem exitMenuItem = null;

	private JMenuItem aboutMenuItem = null;

	private JMenuItem cutMenuItem = null;

	private JMenuItem copyMenuItem = null;

	private JMenuItem pasteMenuItem = null;

	private JMenuItem saveMenuItem = null;

	private JDialog aboutDialog = null;

	private JPanel aboutContentPane = null;

	private JLabel aboutVersionLabel = null;

	private JDesktopPane jDesktopPane = null;

	private JLabel jLabel = null;

	private JRadioButton jKMeansRadioButton = null;

	private JRadioButton jDBSCANRadioButton = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private IntTextField jNRClustersTextField = null;

	private JLabel jLabel3 = null;

	private IntTextField jEpsilonTextField = null;

	private JLabel jMinPointsLabel4 = null;

	private IntTextField jMinPtsTextField = null;

	private JButton jExecuteButton = null;

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(741, 487);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("DWDM Project PART II");
			
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJDesktopPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}

	/**
	 * This method initializes jDesktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(33, 271, 38, 16));
			jLabel4.setText("Output");
			jMinPointsLabel4 = new JLabel();
			jMinPointsLabel4.setBounds(new Rectangle(269, 177, 81, 16));
			jMinPointsLabel4.setEnabled(false);
			jMinPointsLabel4.setText("MinPoints:");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(249, 146, 103, 16));
			jLabel3.setEnabled(false);
			jLabel3.setText("E - Neighbors:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(232, 115, 122, 16));
			jLabel2.setText("Number of clusters:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(322, 88, 106, 14));
			jLabel1.setText("Set Parameters");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(291, 17, 171, 16));
			jLabel.setText("Choose your Algorithm:");
			jDesktopPane = new JDesktopPane();
			jDesktopPane.add(jLabel, null);
			jDesktopPane.add(getJKMeansRadioButton(), null);
			jDesktopPane.add(getJDBSCANRadioButton(), null);
			jDesktopPane.add(jLabel1, null);
			jDesktopPane.add(jLabel2, null);
			jDesktopPane.add(getJNRClustersTextField(), null);
			jDesktopPane.add(jLabel3, null);
			jDesktopPane.add(getJEpsilonTextField(), null);
			jDesktopPane.add(jMinPointsLabel4, null);
			jDesktopPane.add(getJMinPtsTextField(), null);
			jDesktopPane.add(getJExecuteButton(), null);
			jDesktopPane.add(getJOutputTextArea(), null);
			jDesktopPane.add(jLabel4, null);
		}
		return jDesktopPane;
	}

	/**
	 * This method initializes jKMeansRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJKMeansRadioButton() {
		if (jKMeansRadioButton == null) {
			jKMeansRadioButton = new JRadioButton();
			jKMeansRadioButton.setBounds(new Rectangle(277, 54, 91, 21));
			jKMeansRadioButton.setSelected(true);
			jKMeansRadioButton.setText("K - Means");
			jKMeansRadioButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					UpdateControls(true);				}
			});
		}
		return jKMeansRadioButton;
	}

	/**
	 * This method initializes jDBSCANRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJDBSCANRadioButton() {
		if (jDBSCANRadioButton == null) {
			jDBSCANRadioButton = new JRadioButton();
			jDBSCANRadioButton.setBounds(new Rectangle(378, 54, 120, 21));
			jDBSCANRadioButton.setText("DBSCAN");
			
			jDBSCANRadioButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					UpdateControls(false);				}
			});
		}
		return jDBSCANRadioButton;
	}

	

	/**
	 * This method initializes jNRClustersTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJNRClustersTextField() {
		if (jNRClustersTextField == null) {
			jNRClustersTextField = new IntTextField(0,3);
			jNRClustersTextField.setBounds(new Rectangle(358, 114, 100, 20));
		}
		return jNRClustersTextField;
	}

	/**
	 * This method initializes jEpsilonTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJEpsilonTextField() {
		if (jEpsilonTextField == null) {
			jEpsilonTextField = new IntTextField(0,3);
			jEpsilonTextField.setBounds(new Rectangle(358, 144, 100, 20));
			jEpsilonTextField.setText("2");
			jEpsilonTextField.setEnabled(false);
		}
		return jEpsilonTextField;
	}

	/**
	 * This method initializes jMinPtsTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJMinPtsTextField() {
		if (jMinPtsTextField == null) {
			jMinPtsTextField = new IntTextField(0,3);
			jMinPtsTextField.setBounds(new Rectangle(358, 174, 100, 20));
			jMinPtsTextField.setText("4");
			jMinPtsTextField.setEnabled(false);
		}
		return jMinPtsTextField;
	}

	/**
	 * This method initializes jExecuteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJExecuteButton() {
		if (jExecuteButton == null) {
			jExecuteButton = new JButton();
			jExecuteButton.setBounds(new Rectangle(296, 216, 145, 35));
			jExecuteButton.setText("Execute");
			jExecuteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ExecuteAlgo();
				}
			});
		}
		return jExecuteButton;
	}


	/**
	 * This method initializes jOutputTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJOutputTextArea() {
		if (jOutputTextArea == null) {
			jOutputTextArea = new JTextArea();
			jOutputTextArea.setBounds(new Rectangle(24, 295, 670, 119));
		}
		return jOutputTextArea;
	}

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main application = new Main();
				application.getJFrame().setVisible(true);
			}
		});
	}
	
	private boolean kmeansselected = true;

	private JTextArea jOutputTextArea = null;

	private JLabel jLabel4 = null;
	
	/**
	 * Method used to update the visual controls
	 */
	private void UpdateControls(boolean iskmeans) {

		   if(iskmeans){
		  	 if(jKMeansRadioButton.isSelected()==kmeansselected)
		  		 return;
					kmeansselected=jKMeansRadioButton.isSelected();
		   }
		   if(!iskmeans){
		  	 if(jDBSCANRadioButton.isSelected()==!kmeansselected)
		  		 return;
					kmeansselected=!jDBSCANRadioButton.isSelected();
		  	 }

	  	 jDBSCANRadioButton.setSelected(!iskmeans);
		   jKMeansRadioButton.setSelected(iskmeans);
		jLabel2.setEnabled(iskmeans);
		jLabel3.setEnabled(!iskmeans);
		jMinPointsLabel4.setEnabled(!iskmeans);
		jNRClustersTextField.setEnabled(iskmeans);
		jEpsilonTextField.setEnabled(!iskmeans);
		jMinPtsTextField.setEnabled(!iskmeans);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void ExecuteAlgo() {
		if(kmeansselected){
			if(jNRClustersTextField.getText().trim().length()>0){
			Vector dataPoints = new Vector();
      dataPoints.add(new DataPoint(22,21,"p53"));
      dataPoints.add(new DataPoint(19,20,"bcl2"));
      dataPoints.add(new DataPoint(18,22,"fas"));
      dataPoints.add(new DataPoint(1,3,"amylase"));
      dataPoints.add(new DataPoint(3,2,"maltase"));

      JCA jca = new JCA(2,1000,dataPoints);
      jca.startAnalysis();

      Vector[] v = jca.getClusterOutput();
      for (int i=0; i<v.length; i++){
          Vector tempV = v[i];
          jOutputTextArea.append("-----------Cluster"+i+"---------\n");
          Iterator iter = tempV.iterator();
          while(iter.hasNext()){
              DataPoint dpTemp = (DataPoint)iter.next();
              jOutputTextArea.append(dpTemp.getObjName()+"["+dpTemp.getX()+","+dpTemp.getY()+"]\n");
          }
      }
			}
		}
		else{
			if(jEpsilonTextField.getText().trim().length()>0&&jMinPtsTextField.getText().trim().length()>0){
			try {
				String[] s = new String[]{"0,0","0,1","0,2","0,3",
					"0,4",
					"0,5",
					"12,1",
					"12,2",
					"12,3",
					"12,4",
					"12,5",
					"12,6",
					"0,6",
					"0,7",
					"12,7",
					"0,8",
					"0,9",
					"1,1"};
				jOutputTextArea.setText(Utility.display(DBScan.applyDbscan(s,Integer.parseInt(jEpsilonTextField.getText()),Integer.parseInt(jMinPtsTextField.getText()))));
			} catch (Exception e) {
				jOutputTextArea.setText("Something wrong happened");
			}

		}
		}
	}
}
