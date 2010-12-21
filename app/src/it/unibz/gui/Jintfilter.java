package it.unibz.gui;

import javax.swing.text.*;

/**
 * A representation for creating personalized filter on user inputs. In this
 * class we represent what we intend for an acceptable user input as parameter
 * for the algorithms.
 */
public class Jintfilter extends PlainDocument {

	/**
	 * Needed for new versions of Java
	 */
	private static final long serialVersionUID = -1915431371745857789L;
	/**
	 * We accept just numeric values for input
	 */
	protected String acceptedChars = "0123456789";
	/**
	 * We don't accept negative numbers
	 */
	protected boolean negativeAccepted = false;

	/**
	 * Method invoked when the user inputs a string in the Input Text. It checks
	 * for empty strings, then it loops the whole input characters checking for
	 * bad values. If noone are found, calls the same method of the super class
	 * and let the Swing system handle the rest.
	 */
	public void insertString(int offset, String string,
			AttributeSet attributeSet) throws BadLocationException {
		if (string == null || string.isEmpty())
			return;

		for (int i = 0; i < string.length(); i++) {
			if (acceptedChars.indexOf(String.valueOf(string.charAt(i))) == -1)
				return;
		}

		if (negativeAccepted && string.indexOf("-") != -1) {
			if (string.indexOf("-") != 0 || offset != 0) {
				return;
			}
		}

		super.insertString(offset, string, attributeSet);
	}
}
