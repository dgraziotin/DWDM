package it.unibz.algorithms;


import it.unibz.algorithms.types.DataPoint;
import it.unibz.algorithms.types.DataSet;
import it.unibz.algorithms.types.Row;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

 

 public  class Utility {


  public static double getDistance(DataPoint p,DataPoint q){

   double dx=p.getX()-q.getX();

    double dy=p.getY()-q.getY();

    double distance=Math.sqrt(dx*dx+dy*dy);

     return distance;

  }
  
  public static String testloading(String path) throws IOException{
  	DataSet a = Utility.loadDataSet(path);
		return a.print();
  }
  
  //path = "/Users/tom/Documents/Unibz/Master/DWDM/datasets/census-income/census-income.arff";
  public static DataSet loadDataSet(String path) throws IOException{
  	DataSet ret = new DataSet();
		ArrayList<Row> datal = new ArrayList<Row>();
		FileReader f = new FileReader(path);
		BufferedReader in = new BufferedReader(f);
		Row e;
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
					if (line != null&&!line.isEmpty()) {
					  datal.add(new Row(line));
					}
				}
			}
		}
		ret.setData(datal);
		f.close();
		return ret;
	}


  public static List<DataPoint> isKeyPoint(List<DataPoint> lst,DataPoint p,int e,int minp){

   int count=0;

    List<DataPoint> tmpLst=new ArrayList<DataPoint>();

    for(Iterator<DataPoint> it=lst.iterator();it.hasNext();){

     DataPoint q=it.next();

     if(getDistance(p,q)<=e){

       ++count;

       if(!tmpLst.contains(q)){

         tmpLst.add(q);

       }

     }

    }

    if(count>=minp){

     p.setKey(true);

      return tmpLst;

    }

     return null;

   }


  public static boolean mergeList(List<DataPoint> a,List<DataPoint> b){

    boolean merge=false;

   if(a==null || b==null){

      return false;

    }

   for(int index=0;index<b.size();++index){

     DataPoint p=b.get(index);

    if(p.isKey() && a.contains(p)){

         merge=true;

        break;

      }

    }

    if(merge){

      for(int index=0;index<b.size();++index){

         if(!a.contains(b.get(index))){

           a.add(b.get(index));
        }

       }

     }

    return merge;

   }


   

  public static String display(List<List<DataPoint>> resultList){

     int index=1;
String result ="";
     for(Iterator<List<DataPoint>> it=resultList.iterator();it.hasNext();){

     List<DataPoint> lst=it.next();

      if(lst.isEmpty()){

       continue;

       }

      result+=("-----"+index+"-----\n");

       for(Iterator<DataPoint> it1=lst.iterator();it1.hasNext();){

       DataPoint p=it1.next();

       result+=(p.print());

     }
       result+="\n";

     index++;

    }
return result;
 }

}

