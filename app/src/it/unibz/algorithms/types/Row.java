package it.unibz.algorithms.types;

import java.util.ArrayList;


/**
 * This class represents both the dataset attributes and the data content of our database. The number of attributes are
 * obviously equal to the number of elements contained in a single dataset row.
 *
 */
public class Row {

	ArrayList<String> row = new ArrayList<String>();

	/**
	 * This constructor initializes the arraylist named row
	 */
	public Row (){
		row = new ArrayList<String>();
	}


	/**
	 * This constructor receives a string straight from the arff file. Since all data
	 * are separated by a comma, we split the string a save the data into the arraylist.
	 * @param s String that follows arff files structure
	 */
	public Row (String s){
		String data[] = s.split(",");
		for (int i=0; i<data.length; i++)
			row.add(data[i]);
	}

	/**
	 * Returns the number of element contained into the arraylist
	 * @return Integer the size of the arraylist
	 */
	public int getSize(){
		return row.size();
	}


	/**
	 * This method simply adds data into the arraylist
	 * @param o String dataset value
	 */
	public void addRowAttribute(String o){
		row.add(o);
	}

	/**
	 * This method returns an indexed value contained into the arraylist
	 * @param i Index
	 * @return String the indexed value
	 */
	public String getValue(int i){
		return row.get(i);
	}

	/**
	 * This method returns a formatted form of the arraylyst content
	 * @return String The content of the arraylist
	 */
	public String printRow(){
		String s = "";
		for (int i=0; i<row.size(); i++)
			s = s + row.get(i) + " ";
		return s+"\n";
	}
}
