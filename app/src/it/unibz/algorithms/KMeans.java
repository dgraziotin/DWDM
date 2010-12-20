package it.unibz.algorithms;

import it.unibz.algorithms.types.Centroid;
import it.unibz.algorithms.types.Cluster;
import it.unibz.algorithms.types.DataPoint;

import java.util.Vector;

import javax.swing.SwingWorker;


public class KMeans  extends SwingWorker<Void,Void>{
    private Cluster[] clusters;
    private int miter;
    @SuppressWarnings("rawtypes")
		private Vector mDataPoints = new Vector();
    private double mSWCSS;

    @SuppressWarnings("rawtypes")
		public KMeans(int k, int iter, Vector dataPoints) {
        clusters = new Cluster[k];
        for (int i = 0; i < k; i++) {
            clusters[i] = new Cluster("Cluster" + i);
        }
        this.miter = iter;
        this.mDataPoints = dataPoints;
    }

    private void calcSWCSS() {
        double temp = 0;
        for (int i = 0; i < clusters.length; i++) {
            temp = temp + clusters[i].getSumSqr();
        }
        mSWCSS = temp;
    }


    @SuppressWarnings("rawtypes")
		public Vector[] getClusterOutput() {
        Vector v[] = new Vector[clusters.length];
        for (int i = 0; i < clusters.length; i++) {
            v[i] = clusters[i].getDataPoints();
        }
        return v;
    }


    private void setInitialCentroids() {
        //kn = (round((max-min)/k)*n)+min where n is from 0 to (k-1).
        double cx = 0, cy = 0;
        for (int n = 1; n <= clusters.length; n++) {
            cx = (((getMaxXValue() - getMinXValue()) / (clusters.length + 1)) * n) + getMinXValue();
            cy = (((getMaxYValue() - getMinYValue()) / (clusters.length + 1)) * n) + getMinYValue();
            Centroid c1 = new Centroid(cx, cy);
            clusters[n - 1].setCentroid(c1);
            c1.setCluster(clusters[n - 1]);
        }
    }

    private double getMaxXValue() {
        double temp;
        temp = ((DataPoint) mDataPoints.elementAt(0)).getX();
        for (int i = 0; i < mDataPoints.size(); i++) {
            DataPoint dp = (DataPoint) mDataPoints.elementAt(i);
            temp = (dp.getX() > temp) ? dp.getX() : temp;
        }
        return temp;
    }

    private double getMinXValue() {
        double temp = 0;
        temp = ((DataPoint) mDataPoints.elementAt(0)).getX();
        for (int i = 0; i < mDataPoints.size(); i++) {
            DataPoint dp = (DataPoint) mDataPoints.elementAt(i);
            temp = (dp.getX() < temp) ? dp.getX() : temp;
        }
        return temp;
    }

    private double getMaxYValue() {
        double temp = 0;
        temp = ((DataPoint) mDataPoints.elementAt(0)).getY();
        for (int i = 0; i < mDataPoints.size(); i++) {
            DataPoint dp = (DataPoint) mDataPoints.elementAt(i);
            temp = (dp.getY() > temp) ? dp.getY() : temp;
        }
        return temp;
    }

    private double getMinYValue() {
        double temp = 0;
        temp = ((DataPoint) mDataPoints.elementAt(0)).getY();
        for (int i = 0; i < mDataPoints.size(); i++) {
            DataPoint dp = (DataPoint) mDataPoints.elementAt(i);
            temp = (dp.getY() < temp) ? dp.getY() : temp;
        }
        return temp;
    }

    public int getKValue() {
        return clusters.length;
    }

    public int getIterations() {
        return miter;
    }

    public int getTotalDataPoints() {
        return mDataPoints.size();
    }

    public double getSWCSS() {
        return mSWCSS;
    }

    public Cluster getCluster(int pos) {
        return clusters[pos];
    }

		@Override
		protected Void doInBackground() throws Exception {
		  int progress = 0;
		//Initialize progress property.
		  setProgress(0);
		//set Starting centroid positions - Start of Step 1
      setInitialCentroids();
      int n = 0;
      //assign DataPoint to clusters
      loop1: while (true) {
          for (int l = 0; l < clusters.length; l++) 
          {
              clusters[l].addDataPoint((DataPoint)mDataPoints.elementAt(n));
              n++;
              if (n >= mDataPoints.size())
                  break loop1;
          }
      }

   // isCancelled() returns true if the cancel() method
   // is invoked on this class. That is the proper way
   // to stop this thread. See the actionPerformed
   // method.
   if (isCancelled())
	   return null;
      //calculate E for all the clusters
      calcSWCSS();
      // isCancelled() returns true if the cancel() method
      // is invoked on this class. That is the proper way
      // to stop this thread. See the actionPerformed
      // method.
      if (isCancelled())
   	   return null;
      //recalculate Cluster centroids - Start of Step 2
      for (int i = 0; i < clusters.length; i++) {
          clusters[i].getCentroid().calcCentroid();
      }
      
      //recalculate E for all the clusters
      calcSWCSS();
      for (int i = 0; i < miter; i++) {
          //enter the loop for cluster 1
          for (int j = 0; j < clusters.length; j++) {
              for (int k = 0; k < clusters[j].getNumDataPoints(); k++) {
            	   // isCancelled() returns true if the cancel() method
            	   // is invoked on this class. That is the proper way
            	   // to stop this thread. See the actionPerformed
            	   // method.
            	   if (isCancelled())
            		   return null;
                  //pick the first element of the first cluster
                  //get the current Euclidean distance
                  double tempEuDt = clusters[j].getDataPoint(k).getCurrentEuDt();
                  Cluster tempCluster = null;
                  boolean matchFoundFlag = false;
                  
                  //call testEuclidean distance for all clusters
                  for (int l = 0; l < clusters.length; l++) {
                  
                  //if testEuclidean < currentEuclidean then
                      if (tempEuDt > clusters[j].getDataPoint(k).testEuclideanDistance(clusters[l].getCentroid())) {
                          tempEuDt = clusters[j].getDataPoint(k).testEuclideanDistance(clusters[l].getCentroid());
                          tempCluster = clusters[l];
                          matchFoundFlag = true;
                      }
                      //if statement - Check whether the Last EuDt is > Present EuDt 
                      
                      }
//for variable 'l' - Looping between different Clusters for matching a Data Point.
//add DataPoint to the cluster and calcSWCSS

     if (matchFoundFlag) {
	tempCluster.addDataPoint(clusters[j].getDataPoint(k));
	clusters[j].removeDataPoint(clusters[j].getDataPoint(k));
                      for (int m = 0; m < clusters.length; m++) {
                          clusters[m].getCentroid().calcCentroid();
                      }

//for variable 'm' - Recalculating centroids for all Clusters

                      calcSWCSS();
                      progress=Math.round((float)(i+1*j+1*k*100)/(n));
                      setProgress(Math.min(progress, 100));
                  }
                  
//if statement - A Data Point is eligible for transfer between Clusters.
              }
              //for variable 'k' - Looping through all Data Points of the current Cluster.
          }//for variable 'j' - Looping through all the Clusters.
      }//for variable 'i' - Number of iterations.
      setProgress(100);
			return null;
		}
}


