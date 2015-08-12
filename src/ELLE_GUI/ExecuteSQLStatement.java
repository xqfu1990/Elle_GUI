package ELLE_GUI;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ExecuteSQLStatement {

	public static void updateDatabase(Connection conn, String str) {
		try {
			Statement state = conn.createStatement();
			state.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
