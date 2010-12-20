package it.unibz.gui;

import javax.swing.text.*;

public class Jintfilter extends PlainDocument {

	/**
	 * Needed for new versions of Java
	 */
	private static final long serialVersionUID = -1915431371745857789L;
	protected String acceptedChars = "0123456789";
	protected boolean negativeAccepted = false;

	public Jintfilter() {
	}

	public void insertString(int offset, String string,
			AttributeSet attributeSet) throws BadLocationException {
		if (string == null)
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
