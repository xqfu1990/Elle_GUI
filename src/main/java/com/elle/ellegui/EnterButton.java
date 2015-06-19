package com.elle.ellegui;


import com.elle.ellegui.presentation.filter.CreateDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

public class EnterButton {

	int commandStart;
	String command;

	public EnterButton() {
		commandStart = 0;
		command = "";
	}

	public String getCommand(JTextArea text) {

		commandStart = text.getText().lastIndexOf(">>") + 2;
		command = text.getText().substring(commandStart);
		return command;
	}

	public void adjustText(JTextArea text) {
		text.append("\n>>");
		((AbstractDocument) text.getDocument())
				.setDocumentFilter(new CreateDocumentFilter(text.getText()
						.length()));
	}

	public boolean isCreateTable(JTextArea text) {
		if (getCommand(text).toLowerCase().contains("select"))
			return true;
		else
			return false;
	}

}
