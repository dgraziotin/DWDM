package it.unibz.algorithms;

import it.unibz.algorithms.types.Cluster;
import it.unibz.algorithms.types.Instance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingWorker;


/**
 * Class which represents the DBScan Algorithm in all its iterative steps.
 * Extends SwingWorker in order to be executed as a background Thread and
 * to keep control of progress.
 */
public class DBScan extends SwingWorker<Void,Void>{

	private static List<Instance> pointsList=new ArrayList<Instance>();
	private static List<Cluster> clusters;

	private int e=0;
	private int minp=0;

	/**
	 * Base constructor with the epsilon and min-points values
	 */
	public DBScan (List<Instance> Instances, int e,int minp){
		this.e=e;
		this.minp=minp;
		pointsList=Instances;
		clusters = new ArrayList<Cluster>();
	}

	/**
	 * Method returns all Clusters to be printed out
	 */
	public List<Cluster>  getClusters(){
		return clusters;
	}

	/**
	 * Executes the main DBScan algorithm composed by various steps
	 * 
	 * Note: The various isCancelled() are used to proper stop this thread if the user closes the operation.
	 * Note: The setProgress() method calls are used to keep the ProgressBar up to date
	 * 
	 * Source: Wikipedia
	 * 1. Starts with an arbitrary starting point that has not been visited.
	 * 2. This point's epsilon-neighborhood is retrieved, and if it contains sufficiently many points, a cluster is started.
	 * 		Otherwise, the point is labeled as noise. Note that this point might later be found in a sufficiently sized
	 * 		epsilon-environment of a different point and hence be made part of a cluster.
	 * 3. If a point is found to be part of a cluster, its epsilon-neighborhood is also part of that cluster.
	 * 		Hence, all points that are found within the epsilon-neighborhood are added, as is their own epsilon-neighborhood.
	 * 4. Continue this process until the cluster is completely found. Then, a new unvisited point is retrieved and processed,
	 * 		leading to the discovery of a further cluster or noise.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		int hundred=pointsList.size();
		setProgress(0);
		for(int index=0;index<hundred;++index){
			if (isCancelled())
				return null;
			List<Instance> tmpLst=new ArrayList<Instance>();
			//Step 1 --> 4
			Instance p=pointsList.get(index);
			if(p.isClassed())
				continue;
			//Step 2
			tmpLst=getneighbors(p);
			if(tmpLst!=null){
				Cluster c = new Cluster("Cluster " + (clusters.size()+1));
				c.addAll(tmpLst);
				clusters.add(c);

			}
			setProgress(Math.min(Math.round((float)(index*100)/(hundred*2)), 100));
		}

		int length=clusters.size();
		if (isCancelled())
			return null;
		for(int i=0;i<length;++i){
			for(int j=0;j<length;++j){
				if(i!=j){
					//Step 3
					if(addNeighbors(clusters.get(i), clusters.get(j))){
						clusters.get(j).clear();
						setProgress(Math.min(Math.round((float)(i*j*100)/(length*length)), 100));
					}
				}
			}
		}
		setProgress(100);

		return null;
	}

	/**
	 * Method calculates the squaredistance beetween 2 Instances
	 */
	private static double getDistance(Instance p,Instance q){
		double dx=p.getX()-q.getX();
		double dy=p.getY()-q.getY();
		double distance=Math.sqrt(dx*dx+dy*dy);
		return distance;
	}

	/**
	 * Method returns the list of Epsilonneighbors
	 * @param p The Current Instance to investigate on
	 * @return List of neighbors
	 */
	private List<Instance> getneighbors(Instance p){
		int count=0;
		List<Instance> temp=new ArrayList<Instance>();
		for(Iterator<Instance> it=pointsList.iterator();it.hasNext();){
			Instance q=it.next();
			if(getDistance(p,q)<=e){
				++count;
				if(!temp.contains(q)){
					temp.add(q);
				}
			}
		}

		if(count>=minp){
			p.setKey(true);
			return temp;
		}

		return null;

	}

	/**
	 * Method adds Instances of b to a if has to be updated
	 * @param cluster final Instance set
	 * @param cluster2 Instance set to add
	 * @return True if successfully merged
	 */
	private static boolean addNeighbors(Cluster cluster,Cluster cluster2){
		boolean merge=false;
		if(cluster==null || cluster2==null){
			return false;
		}
		try{
			for(int index=0;index<cluster2.getNumInstances();++index){
				Instance p=cluster2.getInstance(index);
				if(p.isKey() && cluster.contains(p)){
					merge=true;
					break;
				}
			}
			if(merge){
				for(int index=0;index<cluster2.getNumInstances();++index){
					if(!cluster.contains(cluster2.getInstance(index))){
						cluster.addInstance(cluster2.getInstance(index));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return merge;
	}
}

