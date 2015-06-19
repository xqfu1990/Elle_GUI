package com.elle.ellegui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;

import javax.swing.JOptionPane;

public class Reconcile_8949 {

	protected String query;

	public Reconcile_8949() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Reconcile 8949sY.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			query = sb.toString();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	}
}
