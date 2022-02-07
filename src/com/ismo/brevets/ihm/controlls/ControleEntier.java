package com.ismo.brevets.ihm.controlls;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ControleEntier extends PlainDocument{
	
	@Override
	public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException {

		try {
			Integer.parseInt(arg1);
			super.insertString(arg0, arg1, arg2);
		}catch(Exception e) {
			
		}
	}

}
