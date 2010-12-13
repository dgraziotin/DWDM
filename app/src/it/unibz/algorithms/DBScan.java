package it.unibz.algorithms;

import it.unibz.algorithms.types.DataPoint;

import java.io.IOException;

import java.util.*;



 public class DBScan {

  private static List<DataPoint> pointsList=new ArrayList<DataPoint>();
  private static List<List<DataPoint>> resultList=new ArrayList<List<DataPoint>>();
  

   public static List<List<DataPoint>>  applyDbscan(List<DataPoint> dataPoints, int e,int minp) throws IOException{

    pointsList=dataPoints;

    for(int index=0;index<pointsList.size();++index){

      List<DataPoint> tmpLst=new ArrayList<DataPoint>();

     DataPoint p=pointsList.get(index);

      if(p.isClassed())

        continue;

     tmpLst=Utility.isKeyPoint(pointsList, p, e, minp);

     if(tmpLst!=null){
       resultList.add(tmpLst);

     }
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

    return resultList;

   }
 }

