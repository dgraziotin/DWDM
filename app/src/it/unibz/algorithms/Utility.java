package it.unibz.algorithms;


import it.unibz.algorithms.types.Cluster;
import it.unibz.algorithms.types.DataSet;
import it.unibz.algorithms.types.Row;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * General Utility class used to perform basic printing and data loading tasks
 *
 */
public  class Utility {

	/**
	 * Method is used to parse an arff file into the system.
	 * @param path Is the path to the arff file
	 * @return DataSet representing arff file
	 * @throws Exception if its unable to read arff file
	 */
	//path = "/Users/tom/Documents/Unibz/Master/DWDM/datasets/census-income/census-income.arff";
	public static DataSet loadDataSet(String path) throws Exception{
		DataSet ret = new DataSet();
		ArrayList<Row> datal = new ArrayList<Row>();
		FileReader f = new FileReader(path);
		BufferedReader in = new BufferedReader(f);
		String line = "";//in.readLine();
		while (line != null) {
			line = in.readLine();
			if (line.contains("RELATION")){
				ret.setName(line.toString().split(" ")[1]);
			}
			if (line.contains("ATTRIBUTE"))
				ret.setAttributes(line.toString());
			if (line.contains("DATA")){
				while (line != null) {
					line = in.readLine();
					if (line != null&&!line.isEmpty()){
						if(!line.contains("?")) 
							datal.add(new Row(line));
						else
							ret.incSkippedamount();
					}
					
				}
			}
		}
		ret.setData(datal);
		f.close();
		if(datal.size()==0){
			ret=null;
			throw new Exception("ALLMISSING");
			}
		return ret;
	}

	/**
	 * Method returns formatted string ready to be displayed in the GUI's output box
	 * @param clusters List of clusters to display
	 * @param rename True if Clusters have to be renamed (For DBSCAN)
	 * @return Formatted output String
	 */
	public static String display(List<Cluster> clusters,boolean rename){
		int index=1;
		String result ="";
		Cluster temp=null;
		for(Iterator<Cluster> it=clusters.iterator();it.hasNext();){
			temp = it.next();
			if(temp.getInstances().isEmpty()){
				continue;
			}
			result+=("----- "+(rename?"Cluster "+index:temp.getName())+" -----\n");
			for(int i=0;i<temp.getNumInstances();i++){
				result+=(temp.getInstance(i).print());
			}
			result+="\n";
			index++;
		}
		return result;
	}

}

