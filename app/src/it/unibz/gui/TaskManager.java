package it.unibz.gui;

import it.unibz.algorithms.DBScan;
import it.unibz.algorithms.KMeans;
import it.unibz.algorithms.Utility;
import it.unibz.algorithms.types.Instance;

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
	//Used for showing noise removal
	private int k=0;
	/**
	 * 
	 */
	private ProgressGui progressGui;

	public TaskManager(boolean isKmeans, ProgressGui progressGui) {
		this.isKmeans = isKmeans;
		this.progressGui = progressGui;
	}

	/**
	 * Method responsible for invoking the KMeans algorithm inside a task (a thread)
	 * @param Instances
	 * @param parseInt
	 * @param parseInt2
	 */
	public void startupKMeans(Vector<Instance> Instances, int parseInt, int parseInt2) {
		k=parseInt;
		this.task = new KMeans(parseInt, parseInt2, Instances);
		this.task.addPropertyChangeListener(this);
		this.task.execute();
	}

	/**
	 * Method responsible for starting the DBSCAN algorithm inside a task (a new thread)
	 * @param Instances
	 * @param e
	 * @param minp
	 */
	public void startupDBScan(List<Instance> Instances, int e, int minp) {
		this.task = new DBScan(Instances, e, minp);
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
					progressGui.getDataOutput().setText(Utility.display(((DBScan) this.task)
							.getClusters(),true));
				else{
					progressGui.getDataOutput().setText("");
					if(k!=((KMeans) this.task).getClusters().size())
						progressGui.getDataOutput().setText((k-((KMeans) this.task).getClusters().size())+" Clusters removed due to noise");
					progressGui.getDataOutput().append(Utility.display(((KMeans) this.task)
							.getClusters(),false));
				}
				((JDialog) progressGui.getParent().getParent().getParent())
				.dispose();
			}
		}
	}
	/**
	 * Attempts to kill the task (the thread)
	 */
	public void killProcess() {
		task.cancel(true);
	}

}