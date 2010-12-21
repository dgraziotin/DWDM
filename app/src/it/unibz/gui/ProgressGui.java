package it.unibz.gui;

import it.unibz.algorithms.types.DataPoint;

import java.awt.*;
import javax.swing.*;

import java.util.List;
import java.util.Vector;

/**
 * Our personalized progress bar, showed when our algorithms are executed.
 * We need it because one of our datasets contains 30k+ tuples and there 
 * was a need to let the user know about the status of the operations.
 */
public class ProgressGui extends JPanel{

	/**
	 * Needed for new versions of Java
	 */
	private static final long serialVersionUID = 7365554958844408361L;
	/**
	 * The default Swing progressbar
	 */
	public JProgressBar progressBar;
	
	/**
	 * @return the progressBar
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	/**
	 * @param progressBar the progressBar to set
	 */
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	/**
	 * A text area for outputting some text about the status
	 */
	private JTextArea taskProgress;
	
	/**
	 * @return the taskProgress
	 */
	public JTextArea getTaskProgress() {
		return taskProgress;
	}

	/**
	 * @param taskProgress the taskProgress to set
	 */
	public void setTaskProgress(JTextArea taskProgress) {
		this.taskProgress = taskProgress;
	}

	/**
	 * 
	 */
	private TaskManager taskManager;
	/**
	 * The text area that hold the output of our tasks
	 */
	private JTextArea dataOutput;

	/**
	 * Sets up the GUI for progress bar and output
	 * @param isKmeans
	 * @param result
	 */
	public ProgressGui(boolean isKmeans, JTextArea dataOutput) {
		super(new BorderLayout());
		this.taskManager = new TaskManager(isKmeans);
		this.progressBar = new JProgressBar(0, 100);
		this.progressBar.setValue(0);
		this.progressBar.setStringPainted(true);

		this.taskProgress = new JTextArea(5, 20);
		this.taskProgress.setMargin(new Insets(5, 5, 5, 5));
		this.taskProgress.setEditable(false);
		
		this.dataOutput = dataOutput;

		JPanel panel = new JPanel();
		panel.add(progressBar);

		add(panel, BorderLayout.PAGE_START);
		add(new JScrollPane(taskProgress), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	}

	/**
	 * @param dataOutput the dataOutput to set
	 */
	public void setDataOutput(JTextArea dataOutput) {
		this.dataOutput = dataOutput;
	}

	/**
	 * @return the dataOutput
	 */
	public JTextArea getDataOutput() {
		return dataOutput;
	}	
	
	/**
	 * Method responsible for invoking the KMeans algorithm inside a task (a thread)
	 * @param dataPoints
	 * @param parseInt
	 * @param parseInt2
	 */
	public void StartupKMeans(Vector<DataPoint> dataPoints, int parseInt, int parseInt2) {
		taskManager.StartupKMeans(dataPoints, parseInt, parseInt2);
	}
	
	/**
	 * Method responsible for starting the DBSCAN algorithm inside a task (a new thread)
	 * @param dataPoints
	 * @param e
	 * @param minp
	 */
	public void StartupDBScan(List<DataPoint> dataPoints, int e, int minp) {
		taskManager.StartupDBScan(dataPoints, e, minp);
	}
	
	public void killprocess(){
		taskManager.killprocess();
	}

	

}
