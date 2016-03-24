
package com.elle.elle_gui.dao;

import com.elle.elle_gui.database.DBConnection;
import com.elle.elle_gui.logic.EditableTableModel;
import com.elle.elle_gui.logic.LoggingAspect;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * SqlOutputWindowDAO
 * @author Carlos Igreja
 * @since  Mar 24, 2016
 */
public class SqlOutputWindowDAO {

    public DefaultTableModel getTableModel(String sqlCommand){
        
        DefaultTableModel tableModel = null;
        
        Vector data = new Vector();
        Vector columnNames = new Vector();
        int columns;

        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        
        DBConnection.close();
        DBConnection.open();
        try {
            rs = DBConnection.getStatement().executeQuery(sqlCommand);
            metaData = rs.getMetaData();
        } catch (Exception ex) {
            LoggingAspect.afterThrown(ex);
            return tableModel;
        }
        try {
            columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnName(i));
            }
            while (rs.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
            rs.close();

        } catch (SQLException ex) {
            LoggingAspect.afterThrown(ex);
        }

        tableModel = new DefaultTableModel(data, columnNames);
        
        return tableModel;
    }
}
