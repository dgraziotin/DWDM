package it.unibz.algorithms.types;

/**
 * This class represent a centroid. This class is useful for example for the k-means algorithm that assigns
 * each point to the cluster whose centroid is nearest.
 * It contains the coordinates x, y and also an object of type cluster.
 */
public class Centroid {

	private double x, y;
	private Cluster cluster;


	/**
	 * This constructor assigns only the coordinates to the centroid object
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public Centroid(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This method retrieves the number of data points, then calculates the new centroid. After that
	 * it calculates the new euclidean distance for each Instance. Finally, it calculates the new
	 * sum of squares for the cluster.
	 */
	public void calcCentroid() {
		int Instances = cluster.getNumInstances();
		double x=0, y=0;
		int i;
		for (i=0; i<Instances; i++) {
			x = x+cluster.getInstance(i).getX();
			y = y+cluster.getInstance(i).getY();
		}
		this.x = x / Instances;
		this.y = y / Instances;
		x = 0;
		y = 0;
		for (i = 0; i < Instances; i++) {
			cluster.getInstance(i).calcEuclideanDistance();
		}
		cluster.calcSumOfSquares();
	}

	/**
	 * This method sets the cluster object. Crucial because the constructor does not
	 * touch this class variable.
	 * @param c Cluster Object
	 */
	public void setCluster(Cluster c) {
		this.cluster = c;
	}

	/**
	 * This method returns the x coordinate of the cluster
	 * @return x coordinate
	 */
	public double getClusterX() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the cluster
	 * @return y coordinate
	 */
	public double getClusterY() {
		return y;
	}

}