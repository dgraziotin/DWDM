package it.unibz.algorithms.types;


import java.util.ArrayList;

public class DataSet {
	
	private String name;
	private Row attributes;
	private ArrayList<Row> data;
	
	public DataSet(){
	
	}
	
	
	
    public void setName(String s){
		this.name = s;
	}
    
    public void setAttributes(String s){
    	String value[] = s.split(" ");
    	if (this.attributes == null)
    		this.attributes = new Row();
		this.attributes.addRowAttribute(value[1]);
	}
    
    public void setData(ArrayList<Row> data) {
			this.data = data;
		}



		public Row getAttributes() {
			return attributes;
		}



		public ArrayList<Row> getData() {
			return data;
		}



		public String getName(){
    	return this.name;
    }
    
    
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
