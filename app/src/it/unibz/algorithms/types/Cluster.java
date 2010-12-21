package it.unibz.algorithms.types;


import java.util.Vector;


/**
 * This class represent a cluster and contains its name, a centroid object, the square sum and the data points
 *
 */
public class Cluster {
	private String name;
	private Centroid centroid;
	private double sumSqr;
	@SuppressWarnings("rawtypes")
	private Vector dataPoints;


	/**
	 * This constructor initializes the classes attributes. Note: the variable centroid is going to receive a value
	 * by calling the method named setCentroid.
	 * @param name String
	 */
	@SuppressWarnings("rawtypes")
	public Cluster(String name) {
		this.name = name;
		this.centroid = null; //will be set by calling setCentroid()
		dataPoints = new Vector();
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
	 * This method intrinsically calls the method that calculates the euclidean distance and
	 * then calculates the sum of squares.
	 * @param dp Datapoint object
	 */
	@SuppressWarnings("unchecked")
	public void addDataPoint(DataPoint dp) { 
		dp.setCluster(this);
		this.dataPoints.addElement(dp);
		calcSumOfSquares();
	}

	/**
	 * This method removes the datapoind and updates the number of squares
	 * @param dp Datapoint object
	 */
	public void removeDataPoint(DataPoint dp) {
		this.dataPoints.removeElement(dp);
		calcSumOfSquares();
	}

	/**
	 * This method returns the number of datapoints
	 * @return int number of datapoints 
	 */
	public int getNumDataPoints() {
		return this.dataPoints.size();
	}

	/**
	 * This method returns an indexed data point
	 * @param pos index
	 * @return datapoint object
	 */
	public DataPoint getDataPoint(int pos) {
		return (DataPoint) this.dataPoints.elementAt(pos);
	}

	/**
	 * This method calculates the sum of squares
	 */
	public void calcSumOfSquares() {
		int size = this.dataPoints.size();
		double temp = 0;
		for (int i = 0; i < size; i++) {
			temp = temp + ((DataPoint) this.dataPoints.elementAt(i)).getCurrentEuDt();
		}
		this.sumSqr = temp;
	}

	/**
	 * This method return the sum of squares
	 * @return double value that represents the number of squares
	 */
	public double getSumSqr() {
		return this.sumSqr;
	}

	/**
	 * This method returns the Cluster name
	 * @return String cluster name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method returns a vector of datapoints
	 * @return Vector of datapoints
	 */
	@SuppressWarnings("rawtypes")
	public Vector getDataPoints() {
		return this.dataPoints;
	}

}