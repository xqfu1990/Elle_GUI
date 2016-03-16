package com.elle.elle_gui.logic;

import com.elle.elle_gui.presentation.ELLE_GUI_Frame;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JTable;

/**
 * Tab This class is used to create a tab object. This object contains all the
 * components of the tab on Analyster. Each tab may have its own attributes and
 * that is what this class is for.
 *
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
public class AccountTable implements ITableConstants {

    // attributes
    private String tableName;                    // name of the JTable
    private JTable table;                        // the JTable on the tab
    private TableFilter filter;                  // filter used for the table
    private Map<String, Integer> colWidthPercent;             // column width for each column
    private int totalRecords;                    // total records in table model
    private int recordsShown;                    // number of records shown on table
    private Vector<String> tableColNames;              // column header names
    private String[] searchFields;               // search combobox options
    private String[] batchEditFields;            // batch edit combobox options
    private Object [] idList;
    private ColumnPopupMenu ColumnPopupMenu;     // column filter pop up menu

    // these items are enabled differently for each tab
    private boolean activateRecordMenuItemEnabled; // enables activate record menu item
    private boolean archiveRecordMenuItemEnabled;  // enables archive record menu item
    private boolean addRecordsBtnVisible;          // sets the add records button visible
    private boolean batchEditBtnVisible;           // sets the batch edit button visible
    private boolean tableSelected;             // sets if this table is selected

    /**
     * CONSTRUCTOR Tab This is used if no table is ready such as before
     * initComponents of a frame.
     */
    public AccountTable() {
        tableName = "";
        table = new JTable();
        totalRecords = 0;
        recordsShown = 0;
        activateRecordMenuItemEnabled = false;
        archiveRecordMenuItemEnabled = false;
        addRecordsBtnVisible = false;
        batchEditBtnVisible = false;
        tableSelected = false;
        colWidthPercent = new HashMap<String, Integer>();
    }

    /**
     * CONSTRUCTOR This would be the ideal constructor, but there are issues
     * with the initcomponents in Analyster so the tab must be initialized first
     * then the table can be added
     *
     * @param table
     */
    public AccountTable(JTable table) {
        tableName = "";
        this.table = table;
        totalRecords = 0;
        recordsShown = 0;
        filter = new TableFilter(table);
        ColumnPopupMenu = new ColumnPopupMenu(filter);
        tableSelected = false;
        colWidthPercent = new HashMap<String, Integer>();

        // store the column names for the table
        for (int i = 0; i < table.getColumnCount(); i++) {
            tableColNames.addElement(table.getColumnName(i));
        }
    }

    /**
     * ************************************************************************
     ********************** Setters & Getters *********************************
     *************************************************************************
     */
    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public TableFilter getFilter() {
        return filter;
    }

    public void setFilter(TableFilter filter) {
        this.filter = filter;
    }

    public Map<String, Integer> getColWidthPercent() {
        return colWidthPercent;
    }

    public void setColWidthPercent(Map<String,Integer> colWidthPercent) {
        this.colWidthPercent = colWidthPercent;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getRecordsShown() {
        return getTable().getRowCount();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        table.setName(tableName);
        this.tableName = tableName;
    }

    public boolean isActivateRecordMenuItemEnabled() {
        return activateRecordMenuItemEnabled;
    }

    public void setActivateRecordMenuItemEnabled(boolean activateRecordMenuItemEnabled) {
        this.activateRecordMenuItemEnabled = activateRecordMenuItemEnabled;
    }

    public boolean isArchiveRecordMenuItemEnabled() {
        return archiveRecordMenuItemEnabled;
    }

    public void setArchiveRecordMenuItemEnabled(boolean archiveRecordMenuItemEnabled) {
        this.archiveRecordMenuItemEnabled = archiveRecordMenuItemEnabled;
    }

    public Vector<String> getTableColNames() {
        return tableColNames;
    }
    
    public Object[] getIDList(){
        return idList;
    }

    public void setTableColNames(Vector<String> tableColNames) {
        this.tableColNames = tableColNames;
    }

    public void setTableColNamesAndIDList(JTable table) {
        tableColNames = new Vector<String>();
        for (int i = 0; i < table.getColumnCount(); i++) {
            tableColNames.addElement(table.getColumnName(i));
        }
        idList = new Object[table.getRowCount()];
        for (int j = 0; j < table.getRowCount(); j++ ){
            
            idList[j] = table.getValueAt(j, 1);
        }
    }

    public String[] getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String[] searchFields) {
        this.searchFields = searchFields;
    }

    public boolean isAddRecordsBtnVisible() {
        return addRecordsBtnVisible;
    }

    public void setAddRecordsBtnVisible(boolean addRecordsBtnVisible) {
        this.addRecordsBtnVisible = addRecordsBtnVisible;
    }

    public ColumnPopupMenu getColumnPopupMenu() {
        return ColumnPopupMenu;
    }

    public void setColumnPopupMenu(ColumnPopupMenu ColumnPopupMenu) {
        this.ColumnPopupMenu = ColumnPopupMenu;
    }

    public String[] getBatchEditFields() {
        return batchEditFields;
    }

    public void setBatchEditFields(String[] batchEditFields) {
        this.batchEditFields = batchEditFields;
    }

    public boolean isBatchEditBtnVisible() {
        return batchEditBtnVisible;
    }

    public void setBatchEditBtnVisible(boolean batchEditBtnVisible) {
        this.batchEditBtnVisible = batchEditBtnVisible;
    }

    public boolean isTableSelected() {
        return tableSelected;
    }

    public void setTableSelected(boolean tableSelected) {
        this.tableSelected = tableSelected;
    }

    public void setTableParticularColName(int col, String str) {
        System.out.println(tableColNames.size());
        tableColNames.setElementAt(str, col);
    }

    /**
     * ************************************************************************
     *************************** Methods **************************************
     *************************************************************************
     */
    /**
     * This method subtracts an amount from the totalRecords value This is used
     * when records are deleted to update the totalRecords value
     *
     * @param amountOfRecordsDeleted
     */
    public void subtractFromTotalRowCount(int amountOfRecordsDeleted) {
        totalRecords = totalRecords - amountOfRecordsDeleted;
    }

    /**
     * This method subtracts an amount from the totalRecords value This is used
     * when records are deleted to update the totalRecords value
     *
     * @param amountOfRecordsDeleted
     */
    public void addToTotalRowCount(int amountOfRecordsAdded) {
        totalRecords = totalRecords + amountOfRecordsAdded;
    }

    /**
     * This method returns a string that displays the records.
     *
     * @return String This returns a string that has the records for both total
     * and shown
     */
    public String getRecordsLabel() {

        String output;
        System.out.println("here " + getTableName());

        switch (getTableName()) {
            case POSITIONS_TABLE_NAME:
                output = "<html><pre>"
                        + "        Number of records shown: " + getRecordsShown()
                        + "<br/> Number of records in Positions: " + getTotalRecords()
                        + "</pre></html>";
                break;
            case TRADES_TABLE_NAME:
                output = "<html><pre>"
                        + "     Number of records shown: " + getRecordsShown()
                        + "<br/> Number of records in Trades: " + getTotalRecords()
                        + "</pre></html>";
                break;
            case ALLOCATIONS_TABLE_NAME:
                output = "<html><pre>"
                        + "          Number of records shown: " + getRecordsShown()
                        + "<br/> Number of records in Allocations: " + getTotalRecords()
                        + "</pre></html>";
                break;
            case TRADES_TABLE_VIEW_NAME:
                output = "<html><pre>"
                        + "     Number of records shown: " + getRecordsShown()
                        + "<br/> Number of records in Trades: " + getTotalRecords()
                        + "</pre></html>";
                break;
            default:
                // this means an invalid table name constant was passed
                // this exception will be handled and thrown here
                // the program will still run and show the stack trace for debugging
                output = "<html><pre>"
                        + "*******ATTENTION*******"
                        + "<br/>Not a valid table name constant entered"
                        + "</pre></html>";
                try {
                    String errorMessage = "ERROR: unknown table";
                    throw new NoSuchFieldException(errorMessage);
                } catch (NoSuchFieldException ex) {
                    LoggingAspect.afterThrown(ex);
                }

                break;
        }

        return output;
    }

}// end Tab
