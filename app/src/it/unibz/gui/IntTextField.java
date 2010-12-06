package it.unibz.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntTextField extends JTextField {
  public IntTextField(int defval, int size) {
    super("" + defval, size);
  }

  protected Document createDefaultModel() {
    return new IntTextDocument();
  }

  public boolean isValid() {
    try {
      Integer.parseInt(getText());
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public int getValue() {
    try {
      return Integer.parseInt(getText());
    } catch (NumberFormatException e) {
      return 0;
    }
  }
  class IntTextDocument extends PlainDocument {
    public void insertString(int offs, String str, AttributeSet a)
        throws BadLocationException {
      if (str == null)
        return;
      String oldString = getText(0, getLength());
      String newString = oldString.substring(0, offs) + str
          + oldString.substring(offs);
      try {
        Integer.parseInt(newString + "0");
        super.insertString(offs, str, a);
      } catch (NumberFormatException e) {
      }
    }
  }

}
