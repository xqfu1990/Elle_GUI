package ELLE_GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ShowTableChooserFrame {

	JFrame frame;
	Container c;
	JComboBox<String> box;
	File file;
	String connect;
	String[] tableNames;
	Connection conn;
	ResultSet rs;
	Statement state;
	private JButton enter;
	private JTextField filecode;
	private JTextField account;

	public ShowTableChooserFrame(File file, JMenu connect, Connection conn) {
		frame = new JFrame("Table");
		c = frame.getContentPane();
		this.file = file;
		this.connect = connect.getText();
		this.conn = conn;

		c.setLayout(new GridLayout(3, 3));
		c.add(new JLabel("Table"));
		c.add(new JLabel("Filecode"));
		c.add(new JLabel("Account"));

		getTableNames();
		setCombox();
		filecode = new JTextField(20);
		account = new JTextField(20);
		c.add(filecode);
		c.add(account);
		setButton();
		
		frame.pack();
		frame.setVisible(true);
	}

	public void setButton() {
		enter = new JButton("Enter");
		c.add(enter);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) box.getSelectedItem();
				loadFileIntoDB(selectedItem);
			}
		});
	}

	public void loadFileIntoDB(String table) {
		try {
			//CHANGE THE NAME TO 'checkFileLoadedBefore
			//also changed in mysql
			String str = "{CALL CheckFile(?, ?, ?, ?)}";
			CallableStatement st = conn.prepareCall(str);
			st.setString(1, table);
			st.setString(2, filecode.getText());
			st.setString(3, account.getText());
			st.registerOutParameter(4, java.sql.Types.TINYINT);

			st.executeUpdate();
			String q1 = "LOAD DATA LOCAL INFILE "
					+ "'" + file.getPath() + "/" + file.getName() + "'"
					+ " INTO TABLE " + table + " "
					+ "FIELDS OPTIONALLY ENCLOSED BY '\"' TERMINATED BY ',' "
					+ "LINES TERMINATED BY '\n' "
					+ "IGNORE 1 LINES "
					+ "( filecode, inputLine, SecType, Symbol, Underlying, @Expiry, @Strike, @O_Type, Lot_Time, OCE_Time, Q, LS, "
					+ "Multi, @Adj_Price, @Adj_Basis, @dummy, @dummy, @dummy, @dummy, @Codes, L_codes, Account) "
					+ "SET Expiry = IF(@Expiry = '', NULL, @Expiry), Strike = IF(@Strike = '', NULL, @Strike), O_Type = IF(@O_Type = '', NULL, @O_Type), "
					+ "Adj_Price = IF(@Adj_Price = 0, NULL, @Adj_Price), Adj_Basis = IF(@Adj_Basis = 0, NULL, @Adj_Basis), "
					+ "Codes = IF(@Codes = '', NULL, @Codes), L_codes = IF(@L_codes = '', NULL, @L_codes);";
			String q2 = "DELETE FROM " + table + " WHERE Lot_Time = '0000-00-00 00:00:00' AND filecode = " + "'" + filecode.getText() + "';";
			String q3 = "INSERT INTO LOADS (tableName, Load_time, filecode, recordsNum, file, account) "
					+ "SELECT " + "'" + table + "', " + "now(), filecode, count(*), " + "'" + file.getPath() + file.getName() + "', " + "Account FROM positions;";
			PreparedStatement sq1 = conn.prepareStatement(q1);
			PreparedStatement sq2 = conn.prepareStatement(q2);
			PreparedStatement sq3 = conn.prepareStatement(q3);
			int isLoaded = st.getInt(4);
			if (isLoaded == 0) {
				sq1.executeUpdate();
				sq2.executeUpdate();
				sq3.executeUpdate();
				JOptionPane.showMessageDialog(frame, "Loaded successfully");
			} else {
				JOptionPane.showMessageDialog(frame,
						"File has already been loaded");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, ex.getMessage());
		}
	}

	public void setCombox() {
		box = new JComboBox(tableNames);
		box.setSelectedIndex(0);
		c.add(box);
	}

	public void getTableNames() {
		String sqlQuery;
		int rowNum = 0;
		int i = 0;
		if (connect.equals("Local")) {
			sqlQuery = "SHOW TABLES FROM Elle_Trades";
		} else {
			sqlQuery = "SHOW TABLES FROM Elle2014dev";
		}
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sqlQuery);
			while (rs.next()) {
				rowNum = rs.getRow();
			}
			tableNames = new String[rowNum];
			rs.beforeFirst();
			while (rs.next()) {
				tableNames[i] = rs.getString(1);
				i++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
