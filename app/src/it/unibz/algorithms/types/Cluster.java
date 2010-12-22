package it.unibz.algorithms.types;


import java.util.List;
import java.util.Vector;


/**
 * This class represent a cluster and contains its name, a centroid object, the square sum and the data points
 *
 */
public class Cluster {
	private String name;
	private Centroid centroid;
	private Vector<Instance> Instances;


	/**
	 * This constructor initializes the classes attributes. Note: the variable centroid is going to receive a value
	 * by calling the method named setCentroid.
	 * @param name String
	 */
	public Cluster(String name) {
		this.name = name;
		this.centroid = null;
		Instances = new Vector<Instance>();
	}


	/**
	 * This method sets the value of the centroid object
	 * @param c Centroid object
	 */
	public void setCentroid(Centroid c) {
		centroid = c;
	}

	/**
	 * This method returns the centroid object
	 * @return centroid object
	 */
	public Centroid getCentroid() {
		return centroid;
	}

	/**
	 * The method adds a specific Instance to the Cluster
	 * @param dp Instance object
	 */
	public void addInstance(Instance dp) {
		dp.setCluster(this);
		this.Instances.addElement(dp);
	}

	/**
	 * This method removes the Instance
	 * @param dp Instance object
	 */
	public void removeInstance(Instance dp) {
		this.Instances.removeElement(dp);
	}

	/**
	 * This method returns the number of Instances
	 * @return int number of Instances
	 */
	public int getNumInstances() {
		return this.Instances.size();
	}

	/**
	 * This method returns an indexed data point
	 * @param pos index
	 * @return Instance object
	 */
	public Instance getInstance(int pos) {
		return this.Instances.elementAt(pos);
	}

	/**
	 * This method returns the Cluster name
	 * @return String cluster name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method returns a vector of Instances
	 * @return Vector of Instances
	 */
	public Vector<Instance> getInstances() {
		return this.Instances;
	}

	/**
	 * Removes all Instances from the Cluster
	 */
	public void clear() {
		Instances.clear();
	}

	/**
	 * Adds all instances to the Cluster
	 * @param tmpLst Instances to add
	 */
	public void addAll(List<Instance> list) {
		Instances.addAll(list);
	}

	/**
	 * Checks if the Cluster contains a specific Instance 
	 * @param p The instance to check
	 * @return True if contained
	 */
	public boolean contains(Instance p) {
		return Instances.contains(p);
	}

}