package it.unibz.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Specialization of Swing's default text field.
 * 
 */
public class IntTextField extends JTextField {
	/**
	 * needed for new Java versions
	 */
	private static final long serialVersionUID = 3188150447562319473L;

	/**
	 * The constructor let us specify a default value and a fixed size for the
	 * text field
	 */
	public IntTextField(int defaultValue, int fieldSize) {
		super("" + defaultValue, fieldSize);
	}
	
	/**
	 * Returns a Swing internal Document object. This is needed by Swing when subclassing
	 * its types.
	 */
	protected Document createDefaultModel() {
		return new IntTextDocument();
	}

	/**
	 * Helper method to define when the text input by user is valid or not. In
	 * our case, it must be a valid Integer number
	 * 
	 * @return true if the text input is valid for us
	 */
	public boolean isValid() {
		try {
			Integer.parseInt(getText());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Method called to get user input
	 * 
	 * @return the number given by the user, 0 elsewhere
	 */
	public int getValue() {
		try {
			return Integer.parseInt(getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * A plain document that maintains no character attributes. The default
	 * element structure for this document is a map of the lines in the text.
	 * In other words: it is needed by Swing.
	 */
	class IntTextDocument extends PlainDocument {

		/**
		 * needed for new Java versions
		 */
		private static final long serialVersionUID = 7369391161518579233L;

		public void insertString(int offset, String string, AttributeSet attributeSet)
				throws BadLocationException {
			if (string == null)
				return;
			String oldString = getText(0, getLength());
			String newString = oldString.substring(0, offset) + string
					+ oldString.substring(offset);
			try {
				Integer.parseInt(newString + "0");
				super.insertString(offset, string, attributeSet);
			} catch (NumberFormatException e) {
			}
		}
	}

}
