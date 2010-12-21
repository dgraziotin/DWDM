package it.unibz.algorithms;

import it.unibz.algorithms.types.Centroid;
import it.unibz.algorithms.types.Cluster;
import it.unibz.algorithms.types.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingWorker;

/**
 * This class represents the KMeans algorithm in all its iterative steps
 * Extends SwingWorker in order to be executed as a background Thread and
 * to keep control of progress.
 */
public class KMeans  extends SwingWorker<Void,Void>{
	private static List<Cluster> clusters;
	private int numberiterations;
	private Vector<Instance> instances = new Vector<Instance>();
	/**
	 * Base constructor initializes the needed Clusters
	 */
	public KMeans(int k, int iter, Vector<Instance> instances) {
		clusters = new ArrayList<Cluster>();
		for (int i = 0; i < k; i++) {
			clusters.add(new Cluster("Cluster " + (i+1)));
		}
		this.numberiterations = iter;
		this.instances = instances;
	}

	/**
	 * Method used to complete step 1 of the Kmeans algorithm.
	 * It initalizes the various centroids
	 */
	private void initializeCentroids() {
		for (int n = 1; n <= clusters.size(); n++) {
			Centroid c1 = generateCentroid(n);
			clusters.get(n - 1).setCentroid(c1);
			c1.setCluster(clusters.get(n - 1));
		}
	}

	/**
	 * Method generates the first centroids used to perform initial steps
	 * @param currentpos Is the current position of the loop
	 * @return A new Cluster for given position
	 */
	private Centroid generateCentroid(int currentpos){
		double x1,x2,y1,y2 = 0;

		x1 = (instances.elementAt(0)).getX();
		y1 = (instances.elementAt(0)).getY();
		x2 = (instances.elementAt(0)).getX();
		y2 = (instances.elementAt(0)).getY();
		for (int i = 0; i < instances.size(); i++) {
			Instance dp = instances.elementAt(i);
			x1 = (dp.getX() > x1) ? dp.getX() : x1;
			x2 = (dp.getX() < x2) ? dp.getX() : x2;
			y1 = (dp.getY() > y1) ? dp.getY() : y1;
			y2 = (dp.getY() < y2) ? dp.getY() : y2;
		}
		return new Centroid((((x1-x2) / (clusters.size() + 1)) * currentpos) + x1,
				(((y1-y2) / (clusters.size() + 1)) * currentpos) + y1);
	}

	/**
	 * Executes the main Kmeans algorithm composed by various steps
	 * 
	 * Note: The various isCancelled() are used to proper stop this thread if the user closes the operation.
	 * Note: The setProgress() method calls are used to keep the ProgressBar up to date
	 * 
	 * 1. Initialize centroid
	 * 2. assign Instances randomly to clusters
	 * 3. Perform the initial evaluation of distances --> Done directly when adding a Instance to a cluster
	 * 4. Refresh Centroids at least once
	 * 5. Start the main computation and stop if we no more moves can be done or the selected number of iterations
	 * 		has been reached
	 */
	@Override
	protected Void doInBackground() throws Exception {
		setProgress(0);
		//Step 1
		initializeCentroids();
		//Step 2 + 3
		randomAssign();
		//Retrieve number of iteration to do (for ProgressBar)
		int n = instances.size();
		if (isCancelled())
			return null;
		if (isCancelled())
			return null;
		//Step 4
		refreshCentroids();
try{
		for (int i = 0; i < numberiterations; i++) {
			for (int j = 0; j < clusters.size(); j++) {
				for (int k = 0; k < clusters.get(j).getNumInstances(); k++) {
					if (isCancelled())
						return null;
					double currentED = clusters.get(j).getInstance(k).getCurrentEuDt();
					Cluster temp = null;
					boolean toreassign = false;
					//Look if cluster has to be reassigned
					for (int l = 0; l < clusters.size(); l++) {
						if (currentED > clusters.get(j).getInstance(k).testEuclideanDistance(clusters.get(j).getCentroid())) {
							currentED = clusters.get(j).getInstance(k).testEuclideanDistance(clusters.get(j).getCentroid());
							temp = clusters.get(j);
							toreassign = true;
						}
					}
					//Has to be reassigned
					if (toreassign) {
						temp.addInstance(clusters.get(j).getInstance(k));
						clusters.get(j).removeInstance(clusters.get(j).getInstance(k));
						refreshCentroids();
						//Refresh progress
						setProgress(Math.min(Math.round((float)(i+1*j+1*k*100)/(n)), 100));
					}
				}
			}
		}
}
catch(Exception e){
	e.printStackTrace();
}
		setProgress(100);
		return null;
	}

	/**
	 * Method refreshes all clusters centroid
	 */
	private void refreshCentroids() {
		for (int i = 0; i < clusters.size(); i++) {
			clusters.get(i).getCentroid().calcCentroid();
		}
	}

	/**
	 * Method is used for step 2.
	 * It randomly assigns various Instances to Cluster
	 */
	private void randomAssign() {
		int n = 0;
		//Step 2
		while (n < instances.size()) {
			for (int l = 0; l < clusters.size() && n < instances.size(); l++)
			{
				clusters.get(l).addInstance(instances.elementAt(n));
				n++;
			}
		}
	}

	/**
	 * Method returns all Clusters to be printed out
	 */
	public List<Cluster>  getClusters(){
		return clusters;
	}
}


