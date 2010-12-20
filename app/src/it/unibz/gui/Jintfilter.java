package it.unibz.gui;

import javax.swing.*; 
import javax.swing.text.*; 

 public class Jintfilter extends PlainDocument {
   
   

   protected String acceptedChars = "0123456789";
   protected boolean negativeAccepted = false;
   
   public Jintfilter() {
     }
  
   

   public void insertString
      (int offset, String  str, AttributeSet attr)
         throws BadLocationException {
     if (str == null) return;

     for (int i=0; i < str.length(); i++) {
       if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
         return;
       }

     if (negativeAccepted && str.indexOf("-") != -1) {
        if (str.indexOf("-") != 0 || offset != 0 ) {
           return;
           }
        }

     super.insertString(offset, str, attr);
   }
}
