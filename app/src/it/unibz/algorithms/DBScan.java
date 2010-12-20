package it.unibz.algorithms;

import it.unibz.algorithms.types.DataPoint;
import java.util.*;

import javax.swing.SwingWorker;



 public class DBScan extends SwingWorker<Void,Void>{

  private static List<DataPoint> pointsList=new ArrayList<DataPoint>();
  private static List<List<DataPoint>> resultList=new ArrayList<List<DataPoint>>();
  private int e=0;
  private int minp=0;
  
  public DBScan (List<DataPoint> dataPoints, int e,int minp){
  	this.e=e;
  	this.minp=minp;
    pointsList=dataPoints;
  }

   public static List<List<DataPoint>>  getScanResult(){

    

    return resultList;

   }


	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
  int progress = 0;
  int hundred=pointsList.size();
//Initialize progress property.
  setProgress(0);
  for(int index=0;index<hundred;++index){

    List<DataPoint> tmpLst=new ArrayList<DataPoint>();

   DataPoint p=pointsList.get(index);

    if(p.isClassed())

      continue;

   tmpLst=Utility.isKeyPoint(pointsList, p, e, minp);

   if(tmpLst!=null){
     resultList.add(tmpLst);

   }
   //if(index>0)
  progress=Math.round((float)(index*100)/(hundred*2));
  setProgress(Math.min(progress, 100));
  }

   int length=resultList.size();

  for(int i=0;i<length;++i){

    for(int j=0;j<length;++j){

      if(i!=j){

        if(Utility.mergeList(resultList.get(i), resultList.get(j))){

          resultList.get(j).clear();

        }

      }

   }

 }
  setProgress(100);

		return null;
	}
 }

