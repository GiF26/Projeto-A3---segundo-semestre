package com.mycompany.a3.Recursos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Giovana Ferreira
 */
public class DataFormatFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null){
            return;
        } 
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
        StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.replace(offset, offset + length, text);
        String digits = builder.toString().replaceAll("\\D", "");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length() && i < 8; i++) {
            if (i == 2 || i == 4) {
                formatted.append('/');
            }
            formatted.append(digits.charAt(i));
        }

        fb.replace(0, fb.getDocument().getLength(), formatted.toString(), attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.delete(offset, offset + length);
        String digits = builder.toString().replaceAll("\\D", "");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length() && i < 8; i++) {
            if (i == 2 || i == 4) {
                formatted.append('/');
            }
            formatted.append(digits.charAt(i));
        }

        fb.replace(0, fb.getDocument().getLength(), formatted.toString(), null);
    }
}
