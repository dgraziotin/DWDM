package it.unibz.algorithms.types;

import it.unibz.algorithms.KMeans;

/**
This class represents a point for clustering.
It contains the values to be aligned in both x and y direction.

Additional fieds for the euclidean distance are needed to ensure proper working of both algorithms
*/

public class DataPoint {
private double mX,mY;
private Cluster mCluster;
private double mEuDt;
private boolean isKey;

private boolean isClassed;

public boolean isKey () {

return isKey;

}

public void setKey (boolean isKey) {

this.isKey = isKey;

this.isClassed = true;

}

public boolean isClassed () {

return isClassed;

}

public void setClassed (boolean isClassed) {

this.isClassed = isClassed;

}

public DataPoint(double x, double y, String name) {
    this.mX = x;
    this.mY = y;
    this.mCluster = null;
}


public DataPoint(String value, String value2) {
	 this.mX=Double.parseDouble(value);
	  this.mY=Double.parseDouble(value2);
}

public void setCluster(Cluster cluster) {
    this.mCluster = cluster;
    calcEuclideanDistance();
}

public void calcEuclideanDistance() { 

//called when DP is added to a cluster or when a Centroid is recalculated.
    mEuDt = Math.sqrt(Math.pow((mX - mCluster.getCentroid().getCx()),
2) + Math.pow((mY - mCluster.getCentroid().getCy()), 2));
}

public double testEuclideanDistance(Centroid c) {
    return Math.sqrt(Math.pow((mX - c.getCx()), 2) + Math.pow((mY - c.getCy()), 2));
}

public double getX() {
    return mX;
}

public double getY() {
    return mY;
}

public Cluster getCluster() {
    return mCluster;
}

public double getCurrentEuDt() {
    return mEuDt;
}

public String print() {
	 return "<"+this.mX+","+this.mY+">";
}

}