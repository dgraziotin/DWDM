package it.unibz.gui;

import it.unibz.algorithms.DBScan;
import it.unibz.algorithms.KMeans;
import it.unibz.algorithms.Utility;
import it.unibz.algorithms.types.DataPoint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.SwingWorker;

public class TaskManager implements PropertyChangeListener {
	/**
	 * An abstract class to perform lengthy GUI-interacting tasks in a dedicated thread.
	 */
	private SwingWorker<Void, Void> task;
	/**
	 * Boolean to indicate when we are dealing with Kmean algorithm
	 */
	private boolean isKmeans;
	/**
	 * 
	 */
	private ProgressGui progressGui;

	public TaskManager(boolean isKmeans) {
		this.isKmeans = isKmeans;
	}
	
	/**
	 * Method responsible for invoking the KMeans algorithm inside a task (a thread)
	 * @param dataPoints
	 * @param parseInt
	 * @param parseInt2
	 */
	public void StartupKMeans(Vector<DataPoint> dataPoints, int parseInt, int parseInt2) {
		this.task = new KMeans(parseInt, parseInt2, dataPoints);
		this.task.addPropertyChangeListener(this);
		this.task.execute();
	}
	
	/**
	 * Method responsible for starting the DBSCAN algorithm inside a task (a new thread)
	 * @param dataPoints
	 * @param e
	 * @param minp
	 */
	public void StartupDBScan(List<DataPoint> dataPoints, int e, int minp) {
		this.task = new DBScan(dataPoints, e, minp);
		this.task.addPropertyChangeListener(this);
		this.task.execute();
	}

	/**
	 * Invoked when task's progress property changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			
			progressGui.getProgressBar().setValue(progress);
			progressGui.getTaskProgress().setText(String.format("Completed %d%% of "
					+ (isKmeans ? "KMeans" : "DBScan"), task.getProgress()));
			if (progress == 100) {
				if (!isKmeans)
					progressGui.getDataOutput().setText(Utility.display(DBScan.getScanResult()));
				else
					progressGui.getDataOutput().setText(Utility.display(((KMeans) this.task)
							.getClusterOutput()));
				((JDialog) progressGui.getParent().getParent().getParent())
						.dispose();
			}
		}
	}
	/**
	 * Attempts to kill the task (the thread)
	 */
	public void killprocess() {
		task.cancel(true);
	}
	
}