package com.elle.ellegui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class FilterOfTextField extends DocumentFilter {

	int startPosition;
	int endPosition;
	int year_month;
	int month_day;

	public FilterOfTextField(int startPosition, int endPosition,
			int year_month, int month_day) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.year_month = year_month;
		this.month_day = month_day;
	}

	@Override
	public void insertString(DocumentFilter.FilterBypass fb, int offset,
			String string, AttributeSet attr) throws BadLocationException {
		if (offset >= startPosition & offset <= endPosition)
			super.insertString(fb, offset + 1, "-", attr);
	}

	@Override
	public void remove(final FilterBypass fb, final int offset, final int length)
			throws BadLocationException {
		if (offset >= startPosition & offset <= endPosition) {
			super.remove(fb, offset, length);
		}
	}

	@Override
	public void replace(final FilterBypass fb, final int offset,
			final int length, final String text, final AttributeSet attrs)
			throws BadLocationException {
		if (offset >= startPosition & offset <= endPosition) {
			super.replace(fb, offset, length, text, attrs);
		}
	}

}
