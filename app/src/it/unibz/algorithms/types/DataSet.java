package it.unibz.algorithms.types;

import java.util.ArrayList;

/**
 * An abstract representation of an arff file. This class represents the content of a dataset.
 * It contains the dataset name, the attributes names and a list of values.
 */
public class DataSet {

	private String name;
	private Row attributes;
	private ArrayList<Row> data;
	private int skippedamount=0;

	/**
	 * Returns the amount of skipped items
	 */
	public int getSkippedamount() {
		return skippedamount;
	}

	/**
	 * Increments the number of skipped items
	 */
	public void incSkippedamount() {
		this.skippedamount++;
	}

	/**
	 * The constructor that initialize to null all class attributes
	 */
	public DataSet(){
		name = null;
		attributes= null;
		data =  null;
		skippedamount=0;
	}

	/**
	 * Setter for the dataset name
	 * @param s dataset name
	 */
	public void setName(String s){
		this.name = s;
	}


	/**
	 * This method extract from a string the attribute name
	 * @param s retrieved from an arff file
	 */
	public void setAttributes(String s){
		String value[] = s.split(" ");
		if (this.attributes == null)
			this.attributes = new Row();
		this.attributes.addRowAttribute(value[1]);
	}

	/**
	 * This is the setter for the dataset content
	 * @param data a list of row objects
	 */
	public void setData(ArrayList<Row> data) {
		this.data = data;
	}


	/**
	 * It returns the attribute names
	 * @return an object of type Row
	 */
	public Row getAttributes() {
		return attributes;
	}


	/**
	 * This method returns the data contained into the dataset
	 * @return a list of row objects
	 */
	public ArrayList<Row> getData() {
		return data;
	}


	/**
	 * This method returns the dataset name
	 * @return dataset name
	 */
	public String getName(){
		return this.name;
	}


	/**
	 * This method gives a verbose version of the dataset content
	 * USED ONLY FOT TESTING
	 * @return the dataset content
	 */
	public String print(){
		String ret="";
		ret+="DB Name: " + this.name + " ("+ this.data.size() +" tuples)\n";
		for (int i=0; i<this.attributes.getSize(); i++)
			ret+="Attribute: " + this.attributes.getValue(i)+"\n";
		for (int i=0; i<this.data.size(); i++)
			ret+=this.data.get(i).printRow();
		return ret;
	}
}
