package it.unibz.algorithms.dbscan;

public class Point {

		private int x;

		private int y;

		private boolean isKey;

		private boolean isClassed;

		public boolean isKey () {

		return isKey;

		}

		public void setKey (boolean isKey) {

		this.isKey = isKey;

		this.isClassed = true;

		}

		public boolean isClassed () {

		return isClassed;

		}

		public void setClassed (boolean isClassed) {

		this.isClassed = isClassed;

		}

		public int getX () {

		return x;

		}

		public void setX (int x) {

		this.x = x;

		}

		public int getY () {

		return y;

		}

		public void setY (int y) {

		this.y = y;

		}

		public Point () {

		x = 0;

		y = 0;

		}

		public Point (int x, int y) {

		this.x = x;

		this.y = y;

		}

		public Point (String str) {

		String [] p = str.split (",");

		this.x = Integer.parseInt (p [0]);

		this.y = Integer.parseInt (p [1]);

		}

		public String print () {

		return "<" + this.x +","+ this.y +">";

		}

		}
