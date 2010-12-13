package it.unibz.algorithms.types;

import java.util.ArrayList;


public class Row {
	
	ArrayList<String> row = new ArrayList<String>();
	
	public Row (){
		row = new ArrayList<String>();
	}
	
	public Row (String s){
		String data[] = s.split(",");
		for (int i=0; i<data.length; i++)
		 row.add(data[i]);
	}
	
	public int getSize(){
		return row.size();
	}
	
	
	public void addRowAttribute(String o){
		  row.add(o);		
	}
	
	public String getValue(int i){
		return row.get(i);
	}
	
	public String printRow(){
		String s = "";
		for (int i=0; i<row.size(); i++)
		  s = s + row.get(i) + " ";
		return s+"\n";
	}
}
