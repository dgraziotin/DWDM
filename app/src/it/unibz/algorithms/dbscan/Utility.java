package it.unibz.algorithms.dbscan;


import java.io.IOException;

 import java.util.*;

 

 public  class Utility {


  public static double getDistance(Point p,Point q){

   int dx=p.getX()-q.getX();

    int dy=p.getY()-q.getY();

    double distance=Math.sqrt(dx*dx+dy*dy);

     return distance;

  }


  public static List<Point> isKeyPoint(List<Point> lst,Point p,int e,int minp){

   int count=0;

    List<Point> tmpLst=new ArrayList<Point>();

    for(Iterator<Point> it=lst.iterator();it.hasNext();){

     Point q=it.next();

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


  public static boolean mergeList(List<Point> a,List<Point> b){

    boolean merge=false;

   if(a==null || b==null){

      return false;

    }

   for(int index=0;index<b.size();++index){

     Point p=b.get(index);

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


   public static List<Point> getPointsList(String[] s) throws IOException{

  	 /*
  	  *  List<Point> lst=new ArrayList<Point>();

     String txtPath="src\\com\\sunzhenxing\\points.txt";

    BufferedReader br=new BufferedReader(new FileReader(txtPath));

     String str="";

    while((str=br.readLine())!=null && str!=""){

      lst.add(new Point(str));

     }

    br.close();

     return lst;
  	  */
  	 
    List<Point> lst=new ArrayList<Point>();

    int i =0;
    for (i=0;i<s.length;i++)
      lst.add(new Point(s[i]));

     return lst;

   }


  public static String display(List<List<Point>> resultList){

     int index=1;
String result ="";
     for(Iterator<List<Point>> it=resultList.iterator();it.hasNext();){

     List<Point> lst=it.next();

      if(lst.isEmpty()){

       continue;

       }

      result+=("-----"+index+"-----\n");

       for(Iterator<Point> it1=lst.iterator();it1.hasNext();){

       Point p=it1.next();

       result+=(p.print());

     }
       result+="\n";

     index++;

    }
return result;
 }

}

