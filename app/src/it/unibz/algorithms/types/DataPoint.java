package it.unibz.algorithms.types;

/**
 * This class is a representation of a clustering point. It contains also the method that calculates the
 * eclaudean distance and a second method that tests it.
 *
 */
public class DataPoint {
	private double x,y;
	private Cluster cluster;
	private double euclideanDistance;
	private boolean isKey;
	private boolean isClassed;
	

	/**
	 * Returns true whether if it is the key or false otherwise
	 * @return True if it is the key
	 */
	public boolean isKey () {
		return isKey;
	}

	/**
	 * This method sets a new value to the instance variable isKey
	 * @param isKey Boolean
	 */
	public void setKey (boolean isKey) {
		this.isKey = isKey;
		this.isClassed = true;
	}

	/**
	 * This method tells whether the datapoint is classed or not
	 * @return True if the datapoint is classed.
	 */
	public boolean isClassed () {
		return isClassed;
	}

	/**
	 * This method sets a new value to the variable isClased
	 * @param isClassed Boolean value
	 */
	public void setClassed (boolean isClassed) {
		this.isClassed = isClassed;
	}

	/**
	 * This constructor sets the the coordinates of a datapoint and its name 
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param name datapoint name
	 */
	public DataPoint(double x, double y, String name) {
		this.x = x;
		this.y = y;
		this.cluster = null;
	}


	/**
	 * This constructor sets the the coordinates of a datapoint
	 * @param value coordinate x
	 * @param value2 coordinate y
	 */
	public DataPoint(String value, String value2) {
		this.x=Double.parseDouble(value);
		this.y=Double.parseDouble(value2);
	}

	/**
	 * This method sets a new value to the cluster variable and calls the method
	 * that calculates the eucludean distance
	 * @param cluster cluster object
	 */
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
		calcEuclideanDistance();
	}

	/**
	 * This method calculates the euclidean distance
	 */
	public void calcEuclideanDistance() { 
		//called when DP is added to a cluster or when a Centroid is recalculated.
		euclideanDistance = Math.sqrt(Math.pow((x - cluster.getCentroid().getClusterX()),
				2) + Math.pow((y - cluster.getCentroid().getClusterY()), 2));
	}

	/**
	 * This method is in charge of cheching the validity of the euclidean distance
	 * @param c Centroid object
	 * @return double value
	 */
	public double testEuclideanDistance(Centroid c) {
		return Math.sqrt(Math.pow((x - c.getClusterX()), 2) + Math.pow((y - c.getClusterY()), 2));
	}

	/**
	 * This method return the x coordinate
	 * @return double x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * This method return the y coordinate
	 * @return double y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Return an object of type cluster
	 * @return cluster
	 */
	public Cluster getCluster() {
		return cluster;
	}

	/**
	 * Returns the current euclidean distance
	 * @return double the euclidean distance
	 */
	public double getCurrentEuDt() {
		return euclideanDistance;
	}

	/**
	 * This method generates a string with the coordinates
	 * @return String with the coordinates
	 */
	public String print() {
		return "<"+this.x+","+this.y+">";
	}

}