package it.unibz.gui;

import it.unibz.algorithms.DBScan;
import it.unibz.algorithms.KMeans;
import it.unibz.algorithms.Utility;
import it.unibz.algorithms.types.DataPoint;

import java.awt.*;
import javax.swing.*;

import java.beans.*;
import java.util.List;
import java.util.Vector;

/**
 * Our personalized progress bar, showed when our algorithms are executed.
 * We need it because one of our datasets contains 30k+ tuples and there 
 * was a need to let the user know about the status of the operations.
 */
public class ProgressBar extends JPanel implements PropertyChangeListener {

	/**
	 * Needed for new versions of Java
	 */
	private static final long serialVersionUID = 7365554958844408361L;
	/**
	 * The default Swing progressbar
	 */
	public JProgressBar progressBar;
	/**
	 * A text area for outputting some text about the status
	 */
	private JTextArea taskOutput;
	/**
	 * An abstract class to perform lengthy GUI-interacting tasks in a dedicated thread.
	 */
	private SwingWorker<Void, Void> task;
	/**
	 * Boolean to indicate when we are dealing with Kmean algorithm
	 */
	private boolean isKmeans = true;
	/**
	 * The text area that hold the output of our tasks
	 */
	private JTextArea dataOutput;

	/**
	 * Method responsible for starting the DBSCAN algorithm inside a task (a new thread)
	 * @param dataPoints
	 * @param e
	 * @param minp
	 */
	public void StartupDBScan(List<DataPoint> dataPoints, int e, int minp) {
		task = new DBScan(dataPoints, e, minp);
		task.addPropertyChangeListener(this);
		task.execute();
	}

	/**
	 * Sets up the GUI for progress bar and output
	 * @param isKmeans
	 * @param result
	 */
	public ProgressBar(boolean isKmeans, JTextArea result) {
		super(new BorderLayout());
		this.dataOutput = result;
		this.isKmeans = isKmeans;
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		taskOutput = new JTextArea(5, 20);
		taskOutput.setMargin(new Insets(5, 5, 5, 5));
		taskOutput.setEditable(false);

		JPanel panel = new JPanel();
		panel.add(progressBar);

		add(panel, BorderLayout.PAGE_START);
		add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	}

	/**
	 * Invoked when task's progress property changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			taskOutput.setText(String.format("Completed %d%% of "
					+ (isKmeans ? "KMeans" : "DBScan"), task.getProgress()));
			if (progress == 100) {
				if (!isKmeans)
					dataOutput.setText(Utility.display(DBScan.getScanResult()));
				else
					dataOutput.setText(Utility.display(((KMeans) task)
							.getClusterOutput()));
				((JDialog) ProgressBar.this.getParent().getParent().getParent())
						.dispose();
			}
		}
	}

	/**
	 * Method responsible for invoking the KMeans algorithm inside a task (a thread)
	 * @param dataPoints
	 * @param parseInt
	 * @param parseInt2
	 */
	public void StartupKMeans(Vector<DataPoint> dataPoints, int parseInt, int parseInt2) {
		task = new KMeans(parseInt, parseInt2, dataPoints);
		task.addPropertyChangeListener(this);
		task.execute();
	}

	/**
	 * Attempts to kill the task (the thread)
	 */
	public void killprocess() {
		task.cancel(true);
	}

}
