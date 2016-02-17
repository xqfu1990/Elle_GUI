/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.elle_gui.logic;
import com.elle.elle_gui.database.DBConnection;
import com.elle.elle_gui.logic.ColumnPopupMenu;
import com.elle.elle_gui.logic.AccountTable;
import com.elle.elle_gui.logic.CreateDocumentFilter;
import com.elle.elle_gui.logic.EditableTableModel;
import com.elle.elle_gui.logic.ITableConstants;
import com.elle.elle_gui.logic.PrintWindow;
import com.elle.elle_gui.logic.TableFilter;
import com.elle.elle_gui.logic.Validator;
import java.util.Vector;

import javax.swing.JTable;

/**
 *This class is used to create a object of a trade in tables
 * @author fuxiaoqian
 * @since Feb 16, 2016
 * @version 0.9.2
 */
public class ATrade {
    
    private int rowInTable;
    private AccountTable accountTable;
    private JTable table; 
    
    private Vector<Object> fieldValues;
    
    
    public ATrade(int row, AccountTable tab) {
        rowInTable = row;
        accountTable = tab;
        table = accountTable.getTable();
        fieldValues = new Vector<Object>();
        for(int i = 0; i < table.getColumnCount(); i++ ){
//            System.out.println(rowInTable +" " + i + ": " + table.getValueAt(rowInTable, i));
            Object value = table.getValueAt(rowInTable, i);
            if(value == null){
               fieldValues.addElement(""); 
            }else{
               fieldValues.addElement(value); 
            }
            
        }
    }
    
    public Vector<Object> getFieldValues(){
        return fieldValues;
    }
    
    public AccountTable getAccountTable(){
        return accountTable;
    }
    
}
