package it.unibz.algorithms.dbscan;

import java.io.IOException;

import java.util.*;



 public class DBScan {

  private static List<Point> pointsList=new ArrayList<Point>();
  private static List<List<Point>> resultList=new ArrayList<List<Point>>();
  

   public static List<List<Point>>  applyDbscan(String[] s, int e,int minp) throws IOException{

    pointsList=Utility.getPointsList(s);

    for(int index=0;index<pointsList.size();++index){

      List<Point> tmpLst=new ArrayList<Point>();

     Point p=pointsList.get(index);

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

