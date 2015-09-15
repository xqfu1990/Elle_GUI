
package com.elle.elle_gui.presentation;

import com.elle.elle_gui.database.DBConnection;
import com.elle.elle_gui.logic.ColumnPopupMenu;
import com.elle.elle_gui.logic.AccountTable;
import com.elle.elle_gui.logic.CreateDocumentFilter;
import com.elle.elle_gui.logic.EditableTableModel;
import com.elle.elle_gui.logic.ITableConstants;
import com.elle.elle_gui.logic.PrintWindow;
import com.elle.elle_gui.logic.TableFilter;
import com.elle.elle_gui.logic.Validator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;

/**
 * ELLE_GUI_Frame
 * @author Carlos Igreja
 * @since 8-14-2015
 * @version ELLE_GUI-0.6.9
 */
public class ELLE_GUI_Frame extends JFrame implements ITableConstants {
    
    // Edit the version and date it was created for new archives and jars
    private final String CREATION_DATE = "2015-08-14";  
    private final String VERSION = "0.6.9a";   
    
    // attributes
    private Map<String,Map<String,AccountTable>> tabs; // stores individual tab objects 
    private static Statement statement;
    private String database;
    
    // components
    private static ELLE_GUI_Frame instance;
    private LogWindow logWindow;
    private LoginWindow loginWindow;
    
    // button colors
    private Color colorBtnDefault;
    private Color colorBtnSelected;
    

    /**
     * ELLE_GUI_Frame
     * Creates the ELLE_GUI_Frame 
     * which is the main window of the application
     */
    public ELLE_GUI_Frame() {
        initComponents();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setTitle("Elle GUI");
        
        // the statement is used for sql statements with the database connection
        // the statement is created in LoginWindow and passed to ELLE_GUI.
        statement = DBConnection.getStatement();
        database = DBConnection.getDatabase();
        instance = this;                         // this is used to call this instance of ELLE_GUI 
        colorBtnDefault = btnPositions.getBackground();
        colorBtnSelected = Color.RED;
        
        // initialize tabs
        tabs = new HashMap();
        
        /***************** IB9048 Account ****************************/
        // create hashmap for IB9048 tables
        Map<String,AccountTable> tabIB9048 = new HashMap();
        tabIB9048.put(POSITIONS_TABLE_NAME, new AccountTable());
        tabIB9048.put(TRADES_TABLE_NAME, new AccountTable());
        // initialize tables for IB9048 -Postions table
        tabIB9048.get(POSITIONS_TABLE_NAME).setTable(new JTable());
        tabIB9048.get(POSITIONS_TABLE_NAME).setTableName(POSITIONS_TABLE_NAME);
        tabIB9048.get(POSITIONS_TABLE_NAME).setColWidthPercent(COL_WIDTH_PER_POSITIONS);
        tabIB9048.get(POSITIONS_TABLE_NAME).setFilter(new TableFilter(tabIB9048.get(POSITIONS_TABLE_NAME).getTable()));
        tabIB9048.get(POSITIONS_TABLE_NAME)
                .setColumnPopupMenu(new ColumnPopupMenu(tabIB9048.get(POSITIONS_TABLE_NAME).getFilter()));
        // initialize tables for IB9048 -Trades table
        tabIB9048.get(TRADES_TABLE_NAME).setTable(new JTable());
        tabIB9048.get(TRADES_TABLE_NAME).setTableName(TRADES_TABLE_NAME);
        tabIB9048.get(TRADES_TABLE_NAME).setColWidthPercent(COL_WIDTH_PER_TRADES);
        tabIB9048.get(TRADES_TABLE_NAME).setFilter(new TableFilter(tabIB9048.get(TRADES_TABLE_NAME).getTable()));
        tabIB9048.get(TRADES_TABLE_NAME)
                .setColumnPopupMenu(new ColumnPopupMenu(tabIB9048.get(TRADES_TABLE_NAME).getFilter()));
        // add tables to the IB9048 account tab
        tabs.put(IB9048_ACCOUNT_NAME, tabIB9048);
        
        /***************** IB9048b Account ****************************/
        // create hashmap for IB9048b tables
        Map<String,AccountTable> tabIB9048b = new HashMap();
        tabIB9048b.put(POSITIONS_TABLE_NAME, new AccountTable());
        tabIB9048b.put(TRADES_TABLE_NAME, new AccountTable());
        // initialize tables for IB9048b -Postions table
        tabIB9048b.get(POSITIONS_TABLE_NAME).setTable(new JTable());
        tabIB9048b.get(POSITIONS_TABLE_NAME).setTableName(POSITIONS_TABLE_NAME);
        tabIB9048b.get(POSITIONS_TABLE_NAME).setColWidthPercent(COL_WIDTH_PER_POSITIONS);
        tabIB9048b.get(POSITIONS_TABLE_NAME).setFilter(new TableFilter(tabIB9048b.get(POSITIONS_TABLE_NAME).getTable()));
        tabIB9048b.get(POSITIONS_TABLE_NAME)
                .setColumnPopupMenu(new ColumnPopupMenu(tabIB9048b.get(POSITIONS_TABLE_NAME).getFilter()));
        // initialize tables for IB9048b -Trades table
        tabIB9048b.get(TRADES_TABLE_NAME).setTable(new JTable());
        tabIB9048b.get(TRADES_TABLE_NAME).setTableName(TRADES_TABLE_NAME);
        tabIB9048b.get(TRADES_TABLE_NAME).setColWidthPercent(COL_WIDTH_PER_TRADES);
        tabIB9048b.get(TRADES_TABLE_NAME).setFilter(new TableFilter(tabIB9048b.get(TRADES_TABLE_NAME).getTable()));
        tabIB9048b.get(TRADES_TABLE_NAME)
                .setColumnPopupMenu(new ColumnPopupMenu(tabIB9048b.get(TRADES_TABLE_NAME).getFilter()));
        // add tables to the IB9048b account tab
        tabs.put(TOS3622_ACCOUNT_NAME, tabIB9048b);
        
        /***************** Combined Accounts ****************************/
        // create hashmap for Combined tables
        Map<String,AccountTable> tabCombined = new HashMap();
        tabCombined.put(POSITIONS_TABLE_NAME, new AccountTable());
        tabCombined.put(TRADES_TABLE_NAME, new AccountTable());
        // initialize tables for Combined -Postions table
        tabCombined.get(POSITIONS_TABLE_NAME).setTable(new JTable());
        tabCombined.get(POSITIONS_TABLE_NAME).setTableName(POSITIONS_TABLE_NAME);
        tabCombined.get(POSITIONS_TABLE_NAME).setColWidthPercent(COL_WIDTH_PER_POSITIONS);
        tabCombined.get(POSITIONS_TABLE_NAME).setFilter(new TableFilter(tabCombined.get(POSITIONS_TABLE_NAME).getTable()));
        tabCombined.get(POSITIONS_TABLE_NAME)
                .setColumnPopupMenu(new ColumnPopupMenu(tabCombined.get(POSITIONS_TABLE_NAME).getFilter()));
        // initialize tables for Combined -Trades table
        tabCombined.get(TRADES_TABLE_NAME).setTable(new JTable());
        tabCombined.get(TRADES_TABLE_NAME).setTableName(TRADES_TABLE_NAME);
        tabCombined.get(TRADES_TABLE_NAME).setColWidthPercent(COL_WIDTH_PER_TRADES);
        tabCombined.get(TRADES_TABLE_NAME).setFilter(new TableFilter(tabCombined.get(TRADES_TABLE_NAME).getTable()));
        tabCombined.get(TRADES_TABLE_NAME)
                .setColumnPopupMenu(new ColumnPopupMenu(tabCombined.get(TRADES_TABLE_NAME).getFilter()));
        // add tables to the Combined account tab
        tabs.put(COMBINED_ACCOUNT_NAME, tabCombined);
        
        // this sets the KeyboardFocusManger
        //setKeyboardFocusManager();
        
        // load data from database to tables
        loadTables(tabs);
        
        // now that the tables are loaded, 
        // the columnnames string array can be loaded for each table
        // this may not even be needed for this application
        tabs.get(IB9048_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).setTableColNames(tabs.get(IB9048_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).getTable());
        tabs.get(IB9048_ACCOUNT_NAME).get(TRADES_TABLE_NAME).setTableColNames(tabs.get(IB9048_ACCOUNT_NAME).get(TRADES_TABLE_NAME).getTable());
        tabs.get(TOS3622_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).setTableColNames(tabs.get(TOS3622_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).getTable());
        tabs.get(TOS3622_ACCOUNT_NAME).get(TRADES_TABLE_NAME).setTableColNames(tabs.get(TOS3622_ACCOUNT_NAME).get(TRADES_TABLE_NAME).getTable());
        tabs.get(COMBINED_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).setTableColNames(tabs.get(COMBINED_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).getTable());
        tabs.get(COMBINED_ACCOUNT_NAME).get(TRADES_TABLE_NAME).setTableColNames(tabs.get(COMBINED_ACCOUNT_NAME).get(TRADES_TABLE_NAME).getTable());
        
        // hide sql panel by default
        panelSQL.setVisible(false);
        this.setSize(this.getWidth(), 493);
        
        // show IB9048 positions table (initial start up)
        AccountTable IB9048_positions = tabs.get(IB9048_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME);
        JTable table = IB9048_positions.getTable();
        JScrollPane scroll = new JScrollPane(table);
        setScrollBarFormat(scroll, table);                  // fix issue with scroll bar dissappearing
        panelIB9048.removeAll();
        panelIB9048.setLayout(new BorderLayout());
        panelIB9048.add(scroll, BorderLayout.CENTER);
        IB9048_positions.setTableSelected(true);
        
        // set initial records label
        String recordsText = IB9048_positions.getRecordsLabel();
        labelRecords.setText(recordsText);
        
        // start table with positions button selected
        btnPositions.setBackground(colorBtnSelected);
        btnPositions.requestFocus();
        
        // start the other tables initially on positions
        tabs.get(TOS3622_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).setTableSelected(true);
        tabs.get(COMBINED_ACCOUNT_NAME).get(POSITIONS_TABLE_NAME).setTableSelected(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCTRLPanel = new javax.swing.JPanel();
        btnTrades = new javax.swing.JButton();
        btnAllocations = new javax.swing.JButton();
        btnSymbol = new javax.swing.JButton();
        btnDateRange = new javax.swing.JButton();
        checkBoxSymbol = new javax.swing.JCheckBox();
        checkBoxDateRange = new javax.swing.JCheckBox();
        labelHyphen = new javax.swing.JLabel();
        textFieldSymbol = new javax.swing.JTextField();
        textFieldStartDate = new javax.swing.JTextField();
        textFieldEndDate = new javax.swing.JTextField();
        btnClearAllFilters = new java.awt.Button();
        btnPositions = new javax.swing.JButton();
        labelRecords = new javax.swing.JLabel();
        panelSQL = new javax.swing.JPanel();
        scrollPaneSQL = new javax.swing.JScrollPane();
        textAreaSQL = new javax.swing.JTextArea();
        btnEnterSQL = new javax.swing.JButton();
        btnClearSQL = new javax.swing.JButton();
        panelAccounts = new javax.swing.JPanel();
        tabbedPaneAccounts = new javax.swing.JTabbedPane();
        panelIB9048 = new javax.swing.JPanel();
        scrollPaneIB9048 = new javax.swing.JScrollPane();
        tableIB9048 = new javax.swing.JTable();
        panelTOS3622 = new javax.swing.JPanel();
        scrollPaneTOS3622 = new javax.swing.JScrollPane();
        tableTOS3622 = new javax.swing.JTable();
        panelCombined = new javax.swing.JPanel();
        scrollPaneCombined = new javax.swing.JScrollPane();
        tableCombined = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuConnections = new javax.swing.JMenu();
        menuItemAWS = new javax.swing.JMenuItem();
        menuItemPupone = new javax.swing.JMenuItem();
        menuItemLocal = new javax.swing.JMenuItem();
        menuItemRead = new javax.swing.JMenuItem();
        menuPrint = new javax.swing.JMenu();
        menuItemPrintGUI = new javax.swing.JMenuItem();
        menuItemPrintDisplayWindow = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        menuItemConnection = new javax.swing.JMenuItem();
        menuFind = new javax.swing.JMenu();
        menuReports = new javax.swing.JMenu();
        menuTools = new javax.swing.JMenu();
        menuItemReconcile = new javax.swing.JMenuItem();
        menuItemShowMatches = new javax.swing.JMenuItem();
        menuItemIB8949 = new javax.swing.JMenuItem();
        menuItemTL8949 = new javax.swing.JMenuItem();
        menuLoad = new javax.swing.JMenu();
        menuItemLoadFile = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        menuItemCheckBoxLog = new javax.swing.JCheckBoxMenuItem();
        menuItemCheckBoxSQL = new javax.swing.JCheckBoxMenuItem();
        menuItemTrades = new javax.swing.JMenuItem();
        menuItemPositions = new javax.swing.JMenuItem();
        menuItemIB = new javax.swing.JMenuItem();
        menuItemTL = new javax.swing.JMenuItem();
        menuItemLoadsTable = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuOther = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTrades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/elle/elle_gui/images/button1.png"))); // NOI18N
        btnTrades.setText("Trades");
        btnTrades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTradesActionPerformed(evt);
            }
        });

        btnAllocations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/elle/elle_gui/images/button3.png"))); // NOI18N
        btnAllocations.setText("Allocations");
        btnAllocations.setEnabled(false);
        btnAllocations.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAllocations.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAllocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllocationsActionPerformed(evt);
            }
        });

        btnSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/elle/elle_gui/images/filter.png"))); // NOI18N
        btnSymbol.setText(" ");
        btnSymbol.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSymbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSymbolActionPerformed(evt);
            }
        });

        btnDateRange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/elle/elle_gui/images/filter.png"))); // NOI18N
        btnDateRange.setText(" ");
        btnDateRange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDateRange.setIconTextGap(0);
        btnDateRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateRangeActionPerformed(evt);
            }
        });

        checkBoxSymbol.setText("Symbol");
        checkBoxSymbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxSymbolActionPerformed(evt);
            }
        });

        checkBoxDateRange.setText("Date Range");
        checkBoxDateRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxDateRangeActionPerformed(evt);
            }
        });

        labelHyphen.setText("-");

        btnClearAllFilters.setActionCommand("Clear All Filters");
        btnClearAllFilters.setLabel("Clear All Filters");
        btnClearAllFilters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllFiltersActionPerformed(evt);
            }
        });

        btnPositions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/elle/elle_gui/images/button1.png"))); // NOI18N
        btnPositions.setText("Positions");
        btnPositions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPositions.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPositions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPositionsActionPerformed(evt);
            }
        });

        labelRecords.setText("records label");

        javax.swing.GroupLayout panelCTRLPanelLayout = new javax.swing.GroupLayout(panelCTRLPanel);
        panelCTRLPanel.setLayout(panelCTRLPanelLayout);
        panelCTRLPanelLayout.setHorizontalGroup(
            panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(labelRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(294, 294, 294)
                        .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                                .addComponent(textFieldSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkBoxSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                        .addComponent(btnPositions, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTrades, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAllocations)))
                .addGap(40, 40, 40)
                .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxDateRange)
                    .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                        .addComponent(textFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                                .addComponent(labelHyphen, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnClearAllFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        panelCTRLPanelLayout.setVerticalGroup(
            panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                        .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPositions)
                            .addComponent(btnAllocations)
                            .addComponent(btnTrades))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                        .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelHyphen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textFieldStartDate)
                            .addComponent(textFieldEndDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCTRLPanelLayout.createSequentialGroup()
                        .addGroup(panelCTRLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxDateRange)
                            .addComponent(checkBoxSymbol))
                        .addGap(9, 9, 9)
                        .addComponent(btnClearAllFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        scrollPaneSQL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textAreaSQL.setBackground(new java.awt.Color(153, 255, 204));
        textAreaSQL.setColumns(20);
        textAreaSQL.setRows(5);
        textAreaSQL.setText("Please input an SQL statement:\n>>");
        textAreaSQL.setText("Please input an SQL statement:\n>>");
        ((AbstractDocument) textAreaSQL.getDocument())
        .setDocumentFilter(new CreateDocumentFilter(33));
        scrollPaneSQL.setViewportView(textAreaSQL);

        btnEnterSQL.setText("Enter");
        btnEnterSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterSQLActionPerformed(evt);
            }
        });

        btnClearSQL.setText("Clear");
        btnClearSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSQLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSQLLayout = new javax.swing.GroupLayout(panelSQL);
        panelSQL.setLayout(panelSQLLayout);
        panelSQLLayout.setHorizontalGroup(
            panelSQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSQLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneSQL)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSQLLayout.createSequentialGroup()
                .addGap(455, 455, 455)
                .addComponent(btnEnterSQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearSQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(426, 426, 426))
        );
        panelSQLLayout.setVerticalGroup(
            panelSQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSQLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneSQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnterSQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClearSQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        tabbedPaneAccounts.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneAccountsStateChanged(evt);
            }
        });

        tableIB9048.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneIB9048.setViewportView(tableIB9048);

        javax.swing.GroupLayout panelIB9048Layout = new javax.swing.GroupLayout(panelIB9048);
        panelIB9048.setLayout(panelIB9048Layout);
        panelIB9048Layout.setHorizontalGroup(
            panelIB9048Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
            .addGroup(panelIB9048Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneIB9048, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE))
        );
        panelIB9048Layout.setVerticalGroup(
            panelIB9048Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
            .addGroup(panelIB9048Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIB9048Layout.createSequentialGroup()
                    .addComponent(scrollPaneIB9048, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabbedPaneAccounts.addTab("IB9048", panelIB9048);

        tableTOS3622.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneTOS3622.setViewportView(tableTOS3622);

        javax.swing.GroupLayout panelTOS3622Layout = new javax.swing.GroupLayout(panelTOS3622);
        panelTOS3622.setLayout(panelTOS3622Layout);
        panelTOS3622Layout.setHorizontalGroup(
            panelTOS3622Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
            .addGroup(panelTOS3622Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneTOS3622, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE))
        );
        panelTOS3622Layout.setVerticalGroup(
            panelTOS3622Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
            .addGroup(panelTOS3622Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTOS3622Layout.createSequentialGroup()
                    .addComponent(scrollPaneTOS3622, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabbedPaneAccounts.addTab("TOS3622", panelTOS3622);

        tableCombined.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneCombined.setViewportView(tableCombined);

        javax.swing.GroupLayout panelCombinedLayout = new javax.swing.GroupLayout(panelCombined);
        panelCombined.setLayout(panelCombinedLayout);
        panelCombinedLayout.setHorizontalGroup(
            panelCombinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
            .addGroup(panelCombinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneCombined, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE))
        );
        panelCombinedLayout.setVerticalGroup(
            panelCombinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
            .addGroup(panelCombinedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCombinedLayout.createSequentialGroup()
                    .addComponent(scrollPaneCombined, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabbedPaneAccounts.addTab("Combined", panelCombined);

        javax.swing.GroupLayout panelAccountsLayout = new javax.swing.GroupLayout(panelAccounts);
        panelAccounts.setLayout(panelAccountsLayout);
        panelAccountsLayout.setHorizontalGroup(
            panelAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccountsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneAccounts)
                .addContainerGap())
        );
        panelAccountsLayout.setVerticalGroup(
            panelAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneAccounts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        menuConnections.setText("Select Connections");

        menuItemAWS.setText("AWS");
        menuItemAWS.setEnabled(false);
        menuItemAWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAWSActionPerformed(evt);
            }
        });
        menuConnections.add(menuItemAWS);

        menuItemPupone.setText("Pupone");
        menuItemPupone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPuponeActionPerformed(evt);
            }
        });
        menuConnections.add(menuItemPupone);

        menuItemLocal.setText("Local");
        menuItemLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLocalActionPerformed(evt);
            }
        });
        menuConnections.add(menuItemLocal);

        menuFile.add(menuConnections);

        menuItemRead.setText("Read from Text File");
        menuItemRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemReadActionPerformed(evt);
            }
        });
        menuFile.add(menuItemRead);

        menuPrint.setText("Print");

        menuItemPrintGUI.setText("Print GUI");
        menuItemPrintGUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPrintGUIActionPerformed(evt);
            }
        });
        menuPrint.add(menuItemPrintGUI);

        menuItemPrintDisplayWindow.setText("Print Display Window");
        menuItemPrintDisplayWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPrintDisplayWindowActionPerformed(evt);
            }
        });
        menuPrint.add(menuItemPrintDisplayWindow);

        menuFile.add(menuPrint);

        menuItemSave.setText("Save File");
        menuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuItemSave);

        menuBar.add(menuFile);

        menuEdit.setText("Edit");

        menuItemConnection.setText("Connection...");
        menuItemConnection.setEnabled(false);
        menuItemConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConnectionActionPerformed(evt);
            }
        });
        menuEdit.add(menuItemConnection);

        menuBar.add(menuEdit);

        menuFind.setText("Find");
        menuBar.add(menuFind);

        menuReports.setText("Reports");
        menuBar.add(menuReports);

        menuTools.setText("Tools");

        menuItemReconcile.setText("Reconcile 8949s");
        menuItemReconcile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemReconcileActionPerformed(evt);
            }
        });
        menuTools.add(menuItemReconcile);

        menuItemShowMatches.setText("Show Matches");
        menuItemShowMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemShowMatchesActionPerformed(evt);
            }
        });
        menuTools.add(menuItemShowMatches);

        menuItemIB8949.setText("IB 8949");
        menuItemIB8949.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemIB8949ActionPerformed(evt);
            }
        });
        menuTools.add(menuItemIB8949);

        menuItemTL8949.setText("TL 8949");
        menuItemTL8949.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTL8949ActionPerformed(evt);
            }
        });
        menuTools.add(menuItemTL8949);

        menuBar.add(menuTools);

        menuLoad.setText("Load");

        menuItemLoadFile.setText("Load File...");
        menuItemLoadFile.setEnabled(false);
        menuItemLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoadFileActionPerformed(evt);
            }
        });
        menuLoad.add(menuItemLoadFile);

        menuBar.add(menuLoad);

        menuView.setText("View");

        menuItemCheckBoxLog.setText("Log");
        menuItemCheckBoxLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCheckBoxLogActionPerformed(evt);
            }
        });
        menuView.add(menuItemCheckBoxLog);

        menuItemCheckBoxSQL.setText("SQL Command");
        menuItemCheckBoxSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCheckBoxSQLActionPerformed(evt);
            }
        });
        menuView.add(menuItemCheckBoxSQL);

        menuItemTrades.setText("Display Trades-All Fields");
        menuItemTrades.setEnabled(false);
        menuItemTrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTradesActionPerformed(evt);
            }
        });
        menuView.add(menuItemTrades);

        menuItemPositions.setText("Display Positions-All Fields");
        menuItemPositions.setEnabled(false);
        menuItemPositions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPositionsActionPerformed(evt);
            }
        });
        menuView.add(menuItemPositions);

        menuItemIB.setText("Display IB_8949-All Fields");
        menuItemIB.setEnabled(false);
        menuItemIB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemIBActionPerformed(evt);
            }
        });
        menuView.add(menuItemIB);

        menuItemTL.setText("Display TL_8949-All Fields");
        menuItemTL.setEnabled(false);
        menuItemTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTLActionPerformed(evt);
            }
        });
        menuView.add(menuItemTL);

        menuItemLoadsTable.setText("Display Loads Table");
        menuItemLoadsTable.setEnabled(false);
        menuItemLoadsTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoadsTableActionPerformed(evt);
            }
        });
        menuView.add(menuItemLoadsTable);

        menuBar.add(menuView);

        menuHelp.setText("Help");
        menuBar.add(menuHelp);

        menuOther.setText("Other");
        menuBar.add(menuOther);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelAccounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCTRLPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCTRLPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAccounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelAccounts.getAccessibleContext().setAccessibleParent(panelAccounts);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTradesActionPerformed

        // local variables
        String tabName = getSelectedTabName();
        AccountTable tab = tabs.get(tabName).get(TRADES_TABLE_NAME);
        JTable table = tab.getTable();
        TableFilter filter = tab.getFilter();
        JScrollPane scroll = new JScrollPane(table);
        setScrollBarFormat(scroll, table);            // fix issue with scroll bar dissappearing
        
        // update button colors
        btnTrades.setBackground(colorBtnSelected);
        btnPositions.setBackground(colorBtnDefault);
        
        // set the trades table as selected
        tabs.get(tabName).get(TRADES_TABLE_NAME).setTableSelected(true);
        tabs.get(tabName).get(POSITIONS_TABLE_NAME).setTableSelected(false);
        
        // change panel table to trades
        JPanel panel = getSelectedTabPanel();
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        
        // apply filter for the trades table
        filter.applyFilter();
        filter.applyColorHeaders();
        
        // update records label
        String recordsText = tab.getRecordsLabel();
        labelRecords.setText(recordsText);

    }//GEN-LAST:event_btnTradesActionPerformed

    private void menuItemPuponeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPuponeActionPerformed

        loginWindow = new LoginWindow(this);
        loginWindow.getComboBoxServer().setSelectedIndex(0);
        loginWindow.setLocationRelativeTo(this);
        loginWindow.setVisible(true);
    }//GEN-LAST:event_menuItemPuponeActionPerformed

    private void menuItemLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLocalActionPerformed

        loginWindow = new LoginWindow(this);
        loginWindow.getComboBoxServer().setSelectedIndex(1);
        loginWindow.setLocationRelativeTo(this);
        loginWindow.setVisible(true);
    }//GEN-LAST:event_menuItemLocalActionPerformed

    private void menuItemAWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAWSActionPerformed

    }//GEN-LAST:event_menuItemAWSActionPerformed

    private void menuItemReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemReadActionPerformed

        Path path = Paths.get("./IB 9048 AS 2013-0606C.csv");
        File file = path.toFile();
        
        String[] columnNames = new String[0];
        Object[][] data = new Object[0][0];

        String line = "";
        String splitSign = ",";
        int i = 0;
        try {
            //initialize the data array
            BufferedReader br = 
                     new BufferedReader(
                     new FileReader(file));
            while (br.readLine() != null) {
                i++;
            }
            br.close();
            data = new Object[i - 1][];
            
            i = 0;
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            columnNames = line.split(splitSign);
            String[] rowNumber = {"#"};
            System.arraycopy(rowNumber, 0, columnNames, 0, 1);
            line = br.readLine();
            while (line != null) {
                data[i] = new Object[line.split(splitSign).length + 1];
                data[i][0] = i + 1;
                for (int j = 1; j < data[i].length; j++) {
                    data[i][j] = line.split(splitSign)[j - 1];
                }
                i++;
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        // testing the file
        JTable table = new JTable(data, columnNames);
        JScrollPane scroll = new JScrollPane(table);
        panelAccounts.removeAll();
        panelAccounts.setLayout(new BorderLayout());
        panelAccounts.add(scroll, BorderLayout.CENTER);
        
    }//GEN-LAST:event_menuItemReadActionPerformed

    private void menuItemPrintGUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrintGUIActionPerformed

        print(this);
    }//GEN-LAST:event_menuItemPrintGUIActionPerformed

    private void menuItemPrintDisplayWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrintDisplayWindowActionPerformed

        // this should print table actually
        print(panelAccounts);
        
    }//GEN-LAST:event_menuItemPrintDisplayWindowActionPerformed

    private void menuItemTradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTradesActionPerformed

    }//GEN-LAST:event_menuItemTradesActionPerformed

    private void menuItemLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLoadFileActionPerformed

    }//GEN-LAST:event_menuItemLoadFileActionPerformed

    private void btnAllocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllocationsActionPerformed

        // this button is grayed out for now
//        // add allocations table to the panel 
//        setSelectedTab(ALLOCATIONS_TABLE_NAME);
//        JScrollPane scroll = new JScrollPane(allocations);
//        panelAccounts.removeAll();
//        panelAccounts.setLayout(new BorderLayout());
//        panelAccounts.add(scroll, BorderLayout.CENTER);
//        Tab tab = tabs.get(ALLOCATIONS_TABLE_NAME);
//        String recordsText = tab.getRecordsLabel();
//        labelRecords.setText(recordsText);
        
    }//GEN-LAST:event_btnAllocationsActionPerformed

    private void menuItemPositionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPositionsActionPerformed

    }//GEN-LAST:event_menuItemPositionsActionPerformed

    private void menuItemIBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIBActionPerformed

    }//GEN-LAST:event_menuItemIBActionPerformed

    private void menuItemTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTLActionPerformed

    }//GEN-LAST:event_menuItemTLActionPerformed

    private void menuItemLoadsTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLoadsTableActionPerformed

    }//GEN-LAST:event_menuItemLoadsTableActionPerformed

    private void menuItemConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConnectionActionPerformed

    }//GEN-LAST:event_menuItemConnectionActionPerformed

    private void menuItemReconcileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemReconcileActionPerformed

    }//GEN-LAST:event_menuItemReconcileActionPerformed

    private void btnSymbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSymbolActionPerformed

        applySymbolSearchFilter();
        
    }//GEN-LAST:event_btnSymbolActionPerformed

    private void btnDateRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateRangeActionPerformed

        applyDateRangeFilter();
    }//GEN-LAST:event_btnDateRangeActionPerformed

    /**
     * applyDateRangeFilter
     */
    private void applyDateRangeFilter(){
        String startDate = textFieldStartDate.getText();
        String endDate = textFieldEndDate.getText();
        String errorMsg = "Not a valid ";
        boolean isError = false;
        Component component = this;
        Date startDateRange = null;
        Date endDateRange = null;
        
        if(Validator.isValidDate(startDate)){
            if(Validator.isValidDate(endDate)){
                // parse the dates
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    startDateRange = simpleDateFormat.parse(startDate);
                    endDateRange = simpleDateFormat.parse(endDate);
                    // execute filter
                    AccountTable tab = getSelectedTab();
                    TableFilter filter = tab.getFilter();
                    int dateColumnIndex = filter.getDateColumnIndex();
                    filter.removeFilterItems(dateColumnIndex);
                    filter.addDateRange(startDateRange, endDateRange);
                    filter.applyFilter();
                    // apply checkbox selection
                    boolean isFiltering = filter.isDateRangeFiltering();
                    checkBoxDateRange.setSelected(isFiltering);
                    // update records label
                    String recordsLabelStr = tab.getRecordsLabel();
                    labelRecords.setText(recordsLabelStr);
                }
                catch(ParseException e){
                    e.printStackTrace();
                }
            }
            else{
                isError = true;
                errorMsg += "end date range";
                component = textFieldEndDate;
            }
        }
        else{
            isError = true;
            errorMsg += "start date range";
            component = textFieldStartDate;
        }
        
        if(isError){
            errorMsg += "\nDate format not correct: YYYY-MM-DD";
            JOptionPane.showMessageDialog(component, errorMsg);
            checkBoxDateRange.setSelected(false);
        }
    }
    
    /**
     * btnEnterSQLActionPerformed
     * @param evt 
     */
    private void btnEnterSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterSQLActionPerformed

        int commandStart = textAreaSQL.getText().lastIndexOf(">>") + 2;
        String command = textAreaSQL.getText().substring(commandStart);  
        if (command.toLowerCase().contains("select")){
            
            // display on current showingtable
            AccountTable tab = getSelectedTab();
            JTable table = tab.getTable();
            String tableName = table.getName();
            String accountName = getSelectedTabName();
            
            loadTable(command, table, tableName, accountName);
        } else {
            try {
                    statement.executeUpdate(command);
            } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEnterSQLActionPerformed

    private void btnClearSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSQLActionPerformed

        ((AbstractDocument) textAreaSQL.getDocument())
                .setDocumentFilter(new CreateDocumentFilter(0));
        textAreaSQL.setText("Please input an SQL statement:\n>>");
        ((AbstractDocument) textAreaSQL.getDocument())
                .setDocumentFilter(new CreateDocumentFilter(33));
    }//GEN-LAST:event_btnClearSQLActionPerformed

    private void menuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File As");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            logWindow.addMessageWithDate("Save file as: "
                    + fileToSave.getAbsolutePath() + "\n");
        }
    }//GEN-LAST:event_menuItemSaveActionPerformed

    private void checkBoxDateRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxDateRangeActionPerformed

        if(checkBoxDateRange.isSelected()){
            applyDateRangeFilter();
        }
        else{
            AccountTable tab = getSelectedTab();
            TableFilter filter = tab.getFilter();
            int dateColumnIndex = filter.getDateColumnIndex();
            filter.removeFilterItems(dateColumnIndex);
            filter.applyFilter();
            // update records label
            String recordsLabelStr = tab.getRecordsLabel();
            labelRecords.setText(recordsLabelStr);
            // apply checkbox selection
            boolean isFiltering =filter.isDateRangeFiltering();
            checkBoxDateRange.setSelected(isFiltering);
        }
    }//GEN-LAST:event_checkBoxDateRangeActionPerformed

    private void checkBoxSymbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxSymbolActionPerformed

        if(checkBoxSymbol.isSelected()){
            applySymbolSearchFilter();
        }
        else{
            AccountTable tab = getSelectedTab();
            TableFilter filter = tab.getFilter();
            // clear symbol search filter
            int underlyingColumnIndex = filter.getUnderlyingColumnIndex();
            filter.removeFilterItems(underlyingColumnIndex);
            int symbolColumnIndex = filter.getSymbolColumnIndex();
            filter.removeColorHeader(symbolColumnIndex);
            filter.applyFilter();
            // update records label
            String recordsLabelStr = tab.getRecordsLabel();
            labelRecords.setText(recordsLabelStr);
        }
    }//GEN-LAST:event_checkBoxSymbolActionPerformed

    private void btnClearAllFiltersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllFiltersActionPerformed

        // clear all filters
        //String tabName = getSelectedTabName();
        AccountTable tab = getSelectedTab();
        TableFilter filter = tab.getFilter();
        filter.clearAllFilters();
        filter.applyFilter();
        filter.applyColorHeaders();
        
        // apply checkbox selection
        boolean isFiltering =filter.isDateRangeFiltering();
        checkBoxDateRange.setSelected(isFiltering);

        // set label record information
        String recordsLabel = tab.getRecordsLabel();
        labelRecords.setText(recordsLabel); 
    }//GEN-LAST:event_btnClearAllFiltersActionPerformed

    private void menuItemShowMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemShowMatchesActionPerformed

    }//GEN-LAST:event_menuItemShowMatchesActionPerformed

    private void menuItemIB8949ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIB8949ActionPerformed

    }//GEN-LAST:event_menuItemIB8949ActionPerformed

    private void menuItemTL8949ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTL8949ActionPerformed

    }//GEN-LAST:event_menuItemTL8949ActionPerformed

    private void btnPositionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPositionsActionPerformed

        // local variables
        String tabName = getSelectedTabName();
        AccountTable accountTable = tabs.get(tabName).get(POSITIONS_TABLE_NAME);
        JTable table = accountTable.getTable();
        TableFilter filter = accountTable.getFilter();
        JScrollPane scroll = new JScrollPane(table);
        setScrollBarFormat(scroll, table);            // fix issue with scroll bar dissappearing
        
        // update button colors
        btnPositions.setBackground(colorBtnSelected);
        btnTrades.setBackground(colorBtnDefault);
        
        // set the positions table as selected
        tabs.get(tabName).get(POSITIONS_TABLE_NAME).setTableSelected(true);
        tabs.get(tabName).get(TRADES_TABLE_NAME).setTableSelected(false);
        
        // change panel table to positions
        JPanel panel = getSelectedTabPanel();
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        
        // apply filter for the positions table
        filter.applyFilter();
        filter.applyColorHeaders();
        
        // update records label
        String recordsText = accountTable.getRecordsLabel();
        labelRecords.setText(recordsText);
        
    }//GEN-LAST:event_btnPositionsActionPerformed

    private void menuItemCheckBoxSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCheckBoxSQLActionPerformed
        /**
         * ************* Strange behavior *************************
         * The jPanelSQL.getHeight() is the height before 
         * the jCheckBoxMenuItemViewSQLActionPerformed method was called.
         * 
         * The jPanelSQL.setVisible() does not change the size 
         * of the sql panel after it is executed.
         * 
         * The jPanel size will only change after 
         * the jCheckBoxMenuItemViewSQLActionPerformed is finished.
         * 
         * That is why the the actual integer is used rather than  getHeight().
         * 
         * Example:
         * jPanelSQL.setVisible(true);
         * jPanelSQL.getHeight(); // this returns 0
         */
        
        if(menuItemCheckBoxSQL.isSelected()){
            
            // show sql panel
            panelSQL.setVisible(true);
            this.setSize(this.getWidth(), 493 + 128); 
            
        }else{
            
            // hide sql panel
            panelSQL.setVisible(false);
            this.setSize(this.getWidth(), 493);
        }
    }//GEN-LAST:event_menuItemCheckBoxSQLActionPerformed

    private void menuItemCheckBoxLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCheckBoxLogActionPerformed
        if(menuItemCheckBoxLog.isSelected()){
            
            logWindow.setLocationRelativeTo(this);
            logWindow.setVisible(true); // show log window
            
            // remove check if window is closed from the window
            logWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        menuItemCheckBoxLog.setSelected(false);
                    }
                });
        }else{
            // hide log window
            logWindow.setVisible(false);
        }
    }//GEN-LAST:event_menuItemCheckBoxLogActionPerformed

    private void tabbedPaneAccountsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneAccountsStateChanged
        
        // display correct table
        displayTable();  // shows the correct table depending on tab and button selected
    }//GEN-LAST:event_tabbedPaneAccountsStateChanged

    
    /**
     * initTotalRowCounts
  called once to initialize the total rowIndex counts of each tabs table
     * @param tabs
     * @return 
     */
        
    public Map<String,AccountTable> initTotalRowCounts(Map<String,AccountTable> tabs) {
        
        int totalRecords;
 
        boolean isFirstTabRecordLabelSet = false;
        
        for (Map.Entry<String, AccountTable> entry : tabs.entrySet())
        {
            AccountTable tab = tabs.get(entry.getKey());
            JTable table = tab.getTable();
            totalRecords = table.getRowCount();
            tab.setTotalRecords(totalRecords);
            
            if(isFirstTabRecordLabelSet == false){
                String recordsLabel = tab.getRecordsLabel();
                labelRecords.setText(recordsLabel);
                isFirstTabRecordLabelSet = true; // now its set
            }
        }

        return tabs;
    }
    
    
    /**
     * loadTables
     * This method takes a tabs Map and loads all the tabs/tables
     * @param tabs
     * @return 
     */
    public Map<String,Map<String,AccountTable>> loadTables(Map<String,Map<String,AccountTable>> tabs) {
        
        for (Map.Entry<String, Map<String,AccountTable>> tabEntry : tabs.entrySet()){
            String accountName = tabEntry.getKey();
            Map<String,AccountTable> tables = tabs.get(accountName);
            for (Map.Entry<String,AccountTable> tableEntry : tables.entrySet()){
                String tableName = tableEntry.getKey();
                AccountTable tab = tables.get(tableName);
                JTable table = tab.getTable();
                loadTable(table, tableName, accountName);
                setTableListeners(tab);
                // set initial total records
                int totalRecords = table.getRowCount();
                tab.setTotalRecords(totalRecords);
            }
        }
        
        return tabs;
    }
    
    /**
    * loadTable
    * This method takes a table and loads it
    * Does not need to pass the table back since it is passed by reference
    * However, it can make the code clearer and it's good practice to return
    * @param table 
    */
    public JTable loadTable(JTable table, String tableName, String accountName) {
        
        String sql = "";
        sql = "SELECT * FROM " + tableName 
                + " ORDER BY symbol ASC";
        
        if(accountName == "Combined"){
            sql = "SELECT * FROM " + tableName 
                + " ORDER BY symbol ASC";
        }
        else if (accountName == "IB9048b"){
            sql = "SELECT * FROM " + tableName 
                + " WHERE Account = '" + "TOS3622"
                + "' ORDER BY symbol ASC";
        }
        
        return loadTable(sql, table, tableName, accountName);
    }
    
    /**
     * loadTable
     * @param sql
     * @param table
     * @return 
     */
    public JTable loadTable(String sql, JTable table, String tableName, String accountName) {
        
        Vector data = new Vector();
        Vector columnNames = new Vector();
        int columns;

        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        try {
            rs = statement.executeQuery(sql);
            metaData = rs.getMetaData();
        } catch (Exception ex) {
            System.out.println("SQL Error:");
            ex.printStackTrace();
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
            System.out.println("SQL Error:");
            ex.printStackTrace();
        }
        
        EditableTableModel model = new EditableTableModel(data, columnNames);

        // this has to be set here or else I get errors
        // I tried passing the model to the filter and setting it there
        // but it caused errors
        table.setModel(model);
        
        // check that the filter items are initialized
        AccountTable tab = tabs.get(accountName).get(tableName);
        TableFilter filter = tab.getFilter();
        if(filter.getFilterItems() == null){
            filter.initFilterItems();
        }
        // apply filter
        filter.applyFilter();
        filter.applyColorHeaders();
        
        // load all checkbox items for the checkbox column pop up filter
        ColumnPopupMenu columnPopupMenu = tab.getColumnPopupMenu();
        columnPopupMenu.loadAllCheckBoxItems();
        
        // set column format
        float[] colWidthPercent = tab.getColWidthPercent();
        setColumnFormat(colWidthPercent, table);
        
        // set the listeners for the table
        setTableListeners(tab);
        
        // format the table
        formatTable(table);
        
        System.out.println("Table loaded succesfully");
        
        return table;
    }
    
    /**
     * formatTable
     * This formats the table
     * @author Xiaoqian Fu
     * @param table 
     */
    private void formatTable(JTable table) {

        tableHeaderRenderer(table); // Make table headers align CENTER

        tableCellAlignment(table);   // Make table cells align CENTER

        tableCellDecimalFormat(table); // Make table cells have four decimals
        
        tableTimeCellFormat(table); // Make the table cells which shows time have
        
                                    // "yyyy-mm-dd hh:mm:ss" format
        
        //rename selected columns
        
        for (int i = 0; i<table.getColumnCount(); i++){
            if(table.getColumnName(i).equalsIgnoreCase("wash")){
                table.getTableHeader().getColumnModel().getColumn(i).setHeaderValue("W");
            }
        }

    }
    
    /**
     * setScrollBarFormat
     * This formats the scroll bar so that it is always visible.
     * This fixes the default behavior because the scrollbar
     * becomes smaller and smaller until it dissappears.
     * @param scroll 
     */
    private void setScrollBarFormat(JScrollPane scroll, JTable table){
        
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(924, 900));
        table.setPreferredSize(new Dimension(2000, 2000));
    }

    /**
     * tableHeaderRenderer
     * This is the table header renderer
     * @author Xiaoqian Fu
     * @param table 
     */
    private void tableHeaderRenderer(JTable table) {

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));

    }

    /**
     * tableCellDecimalFormat
     * This is the table cell decimal format 
     * @author Xiaoqian Fu
     * @param table 
     */
    private void tableCellDecimalFormat(JTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {

            TableColumn tableColumnI = table.getColumnModel().getColumn(i);

            if (table.getColumnName(i).toLowerCase().equals("price")
                    || table.getColumnName(i).toLowerCase().equals("adj_price")) {

                tableColumnI.setCellRenderer(new DecimalFormatRenderer());

            }
        }
    }

    /**
     * tableCellAlignment
     * This is the table cell alignment
     * @author Xiaoqian Fu
     * @param table 
     */
    private void tableCellAlignment(JTable table) {

        for (int i = 0; i < table.getColumnCount(); i++) {

            TableColumn tableColumnI = table.getColumnModel().getColumn(i);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

            if (table.getColumnName(i).toLowerCase().equals("price")
                    || table.getColumnName(i).toLowerCase().equals("adj_price")
                    || table.getColumnName(i).toLowerCase().equals("q")
                    || table.getColumnName(i).toLowerCase().equals("basis")
                    || table.getColumnName(i).toLowerCase().equals("strike")
                    || table.getColumnName(i).toLowerCase().equals("qori")
                    || table.getColumnName(i).toLowerCase().equals("adj_basis")
                    || table.getColumnName(i).toLowerCase().equals("totalq")
                    || table.getColumnName(i).toLowerCase().equals("realized_pl")) {

                renderer.setHorizontalAlignment(SwingConstants.RIGHT);

            } else {

                renderer.setHorizontalAlignment(SwingConstants.CENTER);

            }

            tableColumnI.setCellRenderer(renderer);

        }
    }

    /**
     * tableTimeCellFormat
     * This is the table time cell format
     * @author Xiaoqian Fu
     * @param table 
     */
    private void tableTimeCellFormat(JTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            
            TableColumn tableColumnI = table.getColumnModel().getColumn(i);
            
            if (table.getColumnName(i).toLowerCase().contains("time")) {
                
                tableColumnI.setCellRenderer(new DataRenderer());
            }
        }
    }
    
    /**
     * DataRenderer
     * This is the data renderer
     * @author Xiaoqian Fu
     */
    private static class DataRenderer extends DefaultTableCellRenderer {
        
        private SimpleDateFormat dateFormatNewValue = 
                new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        private String valueToString;
        
        public DataRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
        }
        
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            
            if(value !=null){
                valueToString = dateFormatNewValue.format(value);
                value = valueToString;
            }
            
            return super.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, col);
            
        }
        
    }

    /**
     * HeaderRenderer
     * This is the header renderer
     * @author Xiaoqian Fu
     */
    private static class HeaderRenderer implements TableCellRenderer {

        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
        }

    }

    /**
     * DecimalFormatRenderer
     * This is the decimal format renderer
     * @author Xiaoqian Fu
     */
    private static class DecimalFormatRenderer extends DefaultTableCellRenderer {

        private static final DecimalFormat formatter = new DecimalFormat("#.0000");

        public DecimalFormatRenderer() {
            super();
            setHorizontalAlignment(JLabel.RIGHT);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {

            Double doubleValue = 0.0;

            if (value != null) {
                doubleValue = Double.parseDouble(value.toString());
                value = formatter.format((Number) doubleValue);
            }

            return super.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, col);
        }
          
    }
    
    /**
     * setTableListeners
     * This adds mouselisteners and keylisteners to tables.
     * @param table 
     */
    public void setTableListeners(final AccountTable tab) { 
        
        JTable table = tab.getTable();
        ColumnPopupMenu columnPopupMenu = tab.getColumnPopupMenu();
        
        // this adds a mouselistener to the table header
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    if (e.getClickCount() == 2) {
                        clearFilterDoubleClick(e, table);
                    } 
                }
                
                /**
                 * Popup menus are triggered differently on different platforms
                 * Therefore, isPopupTrigger should be checked in both 
                 * mousePressed and mouseReleased events for proper 
                 * cross-platform functionality.
                 * @param e 
                 */
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        // this calls the column popup menu
                        columnPopupMenu.showPopupMenu(e);
                    }
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        // this calls the column popup menu
                        columnPopupMenu.showPopupMenu(e);
                    }
                }
            });
        }
        
        // add mouselistener to the table
        table.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        // if left mouse clicks
                        if(SwingUtilities.isLeftMouseButton(e)){
                            if (e.getClickCount() == 2 ) {
                                filterByDoubleClick(table);
                            } else if (e.getClickCount() == 1) {
//                                if (jLabelEdit.getText().equals("ON ")) {
//                                    selectAllText(e);
//                                }
                            }
                        } // end if left mouse clicks
                        
                        // if right mouse clicks
                        else if(SwingUtilities.isRightMouseButton(e)){
                            if (e.getClickCount() == 2 ) {
                                
                                // make table editable
                                //makeTableEditable(true);
                                
                                // get selected cell
                                int columnIndex = table.columnAtPoint(e.getPoint()); // this returns the column index
                                int rowIndex = table.rowAtPoint(e.getPoint()); // this returns the rowIndex index
                                if (rowIndex != -1 && columnIndex != -1) {
                                    
                                    // make it the active editing cell
                                    table.changeSelection(rowIndex, columnIndex, false, false);
                                    
                                    selectAllText(e);

                                } // end not null condition
                                
                            } // end if 2 clicks 
                        } // end if right mouse clicks
                        
                    }// end mouseClicked

                    private void selectAllText(MouseEvent e) {// Select all text inside jTextField

                        JTable table = (JTable) e.getComponent();
                        int row = table.getSelectedRow();
                        int column = table.getSelectedColumn();
                        if (column != 0) {
                            table.getComponentAt(row, column).requestFocus();
                            table.editCellAt(row, column);
                            JTextField selectCom = (JTextField) table.getEditorComponent();
                            if (selectCom != null) {
                                selectCom.requestFocusInWindow();
                                selectCom.selectAll();
                            }
                        }

                    }
                }
        );
        
        // add table model listener
        table.getModel().addTableModelListener(new TableModelListener() {  // add table model listener every time the table model reloaded
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int col = e.getColumn();
//                String tab = getSelectedTabName();
//                JTable table = tabs.get(tab).getTable();
//                ModifiedTableData data = tabs.get(tab).getTableData();
//                Object oldValue = data.getOldData()[row][col];
//                Object newValue = table.getModel().getValueAt(row, col);
//                
//                // disable the upload changes button
//                btnUploadChanges.setEnabled(false);
//
//                // check that data is different
//                if(!newValue.equals(oldValue)){
//
//                    String tableName = table.getName();
//                    String columnName = table.getColumnName(col);
//                    int id = (Integer) table.getModel().getValueAt(row, 0);
//                    data.getNewData().add(new ModifiedData(tableName, columnName, newValue, id));
//
//                    // color the cell
//                    JTableCellRenderer cellRender = tabs.get(tab).getCellRenderer();
//                    cellRender.getCells().get(col).add(row);
//                    table.getColumnModel().getColumn(col).setCellRenderer(cellRender);
//                }
            }
        });
        
        // add keyListener to the table
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_F2) {
                    
                    // I believe this is meant to toggle edit mode
                    // so I passed the conditional
                    //makeTableEditable(jLabelEdit.getText().equals("ON ")?false:true);
                } 
            }
        });
    }
    
    /**
     * setTableListeners
     * This method overloads the seTerminalFunctions 
     * to take tabs instead of a single table
     * @param tabs
     * @return 
     */
    public Map<String,AccountTable> setTableListeners(Map<String,AccountTable> tabs) {
        
        for (Map.Entry<String, AccountTable> entry : tabs.entrySet())
        {
            setTableListeners(tabs.get(entry.getKey()));
        }
        return tabs;
    }
    
    /**
     * filterByDoubleClick
     * this selects the item double clicked on to be filtered
     * @param table 
     */
    public void filterByDoubleClick(JTable table) {
        
        int columnIndex = table.getSelectedColumn(); // this returns the column index
        int rowIndex = table.getSelectedRow(); // this returns the rowIndex index
        if (rowIndex != -1) {
            Object selectedField = table.getValueAt(rowIndex, columnIndex);
            //String tabName = getSelectedTabName();
            AccountTable tab = getSelectedTab();
            TableFilter filter = tab.getFilter();
            filter.addFilterItem(columnIndex, selectedField);
            filter.applyFilter();
            String recordsLabel = tab.getRecordsLabel();
            labelRecords.setText(recordsLabel); 
            
            // apply checkbox selection
            int dateColIndex = filter.getDateColumnIndex();
            if(columnIndex == dateColIndex){
                // this is no longer using a date range filter if applicable
                checkBoxDateRange.setSelected(false);
            }
        }
    }

    /**
     * clearFilterDoubleClick
     * This clears the filters for that column by double clicking on that 
     * column header.
     */
    private void clearFilterDoubleClick(MouseEvent e, JTable table) {
        
        int columnIndex = table.getColumnModel().getColumnIndexAtX(e.getX());
        //String tabName = getSelectedTabName();
        AccountTable tab = getSelectedTab();
        TableFilter filter = tab.getFilter();
        
        // clear column filter
        int symbolColumnIndex = filter.getSymbolColumnIndex();
        if(columnIndex == symbolColumnIndex){
            int underlyingColumnIndex = filter.getUnderlyingColumnIndex();
            filter.clearColFilter(underlyingColumnIndex);
            filter.removeColorHeader(symbolColumnIndex);
            checkBoxSymbol.setSelected(false);
        }
        else{
            filter.clearColFilter(columnIndex);
        }
        filter.applyFilter();
        
        // update records label
        String recordsLabel = tab.getRecordsLabel();
        labelRecords.setText(recordsLabel);  
    }
    
    /**
     * setColumnFormat
     * sets column format for each table
     * @param width
     * @param table 
     */
    public void setColumnFormat(float[] colWidths, JTable table) {

        // this is needed for the horizontal scrollbar to appear
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for(int index = 0; index < table.getColumnCount(); index++){
            int colWidth = (int)colWidths[index];
            TableColumn column = table.getColumnModel().getColumn(index);
            column.setPreferredWidth(colWidth);
            column.setMinWidth(colWidth);
        }
    }
    
    /**
     * applySymbolSearchFilter
     * apply symbol search filter.
     */
    private void applySymbolSearchFilter() {
        
        // get selected tab
        AccountTable tab = getSelectedTab();
 
        // apply filter for the symbol
        String filterItem = textFieldSymbol.getText();
        TableFilter filter = tab.getFilter();
        int colIndexUL = filter.getUnderlyingColumnIndex();   // underlying column index
        filter.addFilterItem(colIndexUL, filterItem);
        filter.removeColorHeader(colIndexUL);
        filter.applyFilter();
        int colIndexSymbol = filter.getSymbolColumnIndex();  // symbol column index
        filter.addColorHeader(colIndexSymbol);
        
        checkBoxSymbol.setSelected(true);
        
        // update records label
        String recordsLabel = tab.getRecordsLabel();
        labelRecords.setText(recordsLabel);  
    }
    
    /**
     * print
     * prints the component passed (either JFrame or JPanel)
     * @param component 
     */
    public void print(Component component){
        
        PrinterJob job = PrinterJob.getPrinterJob();
        
        if(component instanceof JFrame){
            job.setPrintable(new PrintWindow((JFrame)component));
        }
        else if(component instanceof JPanel){
            job.setPrintable(new PrintWindow((JPanel)component));
        }
        
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.pageDialog(job.defaultPage());
                logWindow.addMessageWithDate("Start to print the display window...");
                job.print();
                logWindow.addMessageWithDate(job.getJobName()
                        + " is successfully printed!\n");
            } catch (PrinterException ex) {
                logWindow.addMessageWithDate(ex.getMessage() + "\n");
            }
        }
    }
    
    /**
     * displayTable
     * Displays the correct table depending on the tab and button selected
     */
    public void displayTable(){
        
        // called by init components so just skip if tabs is null
        if(tabs != null){
            // get the account
            String tabName = getSelectedTabName();        // tab/account name

            // get the table name
            String tableName ="";
            if(tabs.get(tabName).get(POSITIONS_TABLE_NAME).isTableSelected()){
                tableName = POSITIONS_TABLE_NAME;
                // update button colors
                btnPositions.setBackground(colorBtnSelected);
                btnTrades.setBackground(colorBtnDefault);
            }
            else if(tabs.get(tabName).get(TRADES_TABLE_NAME).isTableSelected()){
                tableName = TRADES_TABLE_NAME;
                // update button colors
                btnTrades.setBackground(colorBtnSelected);
                btnPositions.setBackground(colorBtnDefault);
            }
            
            // get the account table
            AccountTable accountTable = tabs.get(tabName).get(tableName);
            JTable table = accountTable.getTable();
            TableFilter filter = accountTable.getFilter();
            JScrollPane scroll = new JScrollPane(table);
            setScrollBarFormat(scroll, table);            // fix issue with scroll bar dissappearing

            // change panel table
            JPanel panel = getSelectedTabPanel();         // tab panel used to display the account table
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(scroll, BorderLayout.CENTER);

            // apply filter for the positions table
            filter.applyFilter();
            filter.applyColorHeaders();

            // update records label
            String recordsText = accountTable.getRecordsLabel();
            labelRecords.setText(recordsText);
        }
        
    }
    
    /**
     * getSelectedTab
     * Returns the selected tab and table selected
     * @return 
     */
    public AccountTable getSelectedTab(){
        String tabName = getSelectedTabName();
        if(tabs.get(tabName).get(POSITIONS_TABLE_NAME).isTableSelected()){
            return tabs.get(tabName).get(POSITIONS_TABLE_NAME);
        }
        else if(tabs.get(tabName).get(TRADES_TABLE_NAME).isTableSelected()){
            return tabs.get(tabName).get(TRADES_TABLE_NAME);
        }
        // this should never be reached
        return new AccountTable();
    }
    
    /**************************************************************************
     ******************* SETTERS AND GETTERS **********************************
     **************************************************************************/
    
    public Map<String, Map<String, AccountTable>> getTabs() {
        return tabs;
    }

    public void setTabs(Map<String, Map<String, AccountTable>> tabs) {
        this.tabs = tabs;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static void setStatement(Statement statement) {
        ELLE_GUI_Frame.statement = statement;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public LoginWindow getLoginWindow() {
        return loginWindow;
    }

    public void setLoginWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
    }

    public String getCREATION_DATE() {
        return CREATION_DATE;
    }

    public String getVERSION() {
        return VERSION;
    }

    public static ELLE_GUI_Frame getInstance() {
        return instance;
    }

    public JLabel getLabelNumOfRecords() {
        return labelRecords;
    }

    public LogWindow getLogWindow() {
        return logWindow;
    }

    public void setLogWindow(LogWindow logWindow) {
        this.logWindow = logWindow;
    }

    public String getSelectedTabName() {
        return tabbedPaneAccounts.getTitleAt(tabbedPaneAccounts.getSelectedIndex());
    }
    
    public JPanel getSelectedTabPanel(){
        
        // not sure of the index of the table
        //return (JTable)tabbedPaneAccounts.getComponentAt(0);
        
        // temporary test code
        String tabName = getSelectedTabName();

        if(tabName == "IB9048"){
            return panelIB9048;
        }
        else if(tabName == "IB9048b"){
            return panelTOS3622;
        }
        else{
            return panelCombined;
        }
    }

    @SuppressWarnings("unused")
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllocations;
    private java.awt.Button btnClearAllFilters;
    private javax.swing.JButton btnClearSQL;
    private javax.swing.JButton btnDateRange;
    private javax.swing.JButton btnEnterSQL;
    private javax.swing.JButton btnPositions;
    private javax.swing.JButton btnSymbol;
    private javax.swing.JButton btnTrades;
    private javax.swing.JCheckBox checkBoxDateRange;
    private javax.swing.JCheckBox checkBoxSymbol;
    private javax.swing.JLabel labelHyphen;
    private javax.swing.JLabel labelRecords;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuConnections;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuFind;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAWS;
    private javax.swing.JCheckBoxMenuItem menuItemCheckBoxLog;
    private javax.swing.JCheckBoxMenuItem menuItemCheckBoxSQL;
    private javax.swing.JMenuItem menuItemConnection;
    private javax.swing.JMenuItem menuItemIB;
    private javax.swing.JMenuItem menuItemIB8949;
    private javax.swing.JMenuItem menuItemLoadFile;
    private javax.swing.JMenuItem menuItemLoadsTable;
    private javax.swing.JMenuItem menuItemLocal;
    private javax.swing.JMenuItem menuItemPositions;
    private javax.swing.JMenuItem menuItemPrintDisplayWindow;
    private javax.swing.JMenuItem menuItemPrintGUI;
    private javax.swing.JMenuItem menuItemPupone;
    private javax.swing.JMenuItem menuItemRead;
    private javax.swing.JMenuItem menuItemReconcile;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItemShowMatches;
    private javax.swing.JMenuItem menuItemTL;
    private javax.swing.JMenuItem menuItemTL8949;
    private javax.swing.JMenuItem menuItemTrades;
    private javax.swing.JMenu menuLoad;
    private javax.swing.JMenu menuOther;
    private javax.swing.JMenu menuPrint;
    private javax.swing.JMenu menuReports;
    private javax.swing.JMenu menuTools;
    private javax.swing.JMenu menuView;
    private javax.swing.JPanel panelAccounts;
    private javax.swing.JPanel panelCTRLPanel;
    private javax.swing.JPanel panelCombined;
    private javax.swing.JPanel panelIB9048;
    private javax.swing.JPanel panelSQL;
    private javax.swing.JPanel panelTOS3622;
    private javax.swing.JScrollPane scrollPaneCombined;
    private javax.swing.JScrollPane scrollPaneIB9048;
    private javax.swing.JScrollPane scrollPaneSQL;
    private javax.swing.JScrollPane scrollPaneTOS3622;
    private javax.swing.JTabbedPane tabbedPaneAccounts;
    private javax.swing.JTable tableCombined;
    private javax.swing.JTable tableIB9048;
    private javax.swing.JTable tableTOS3622;
    private javax.swing.JTextArea textAreaSQL;
    private javax.swing.JTextField textFieldEndDate;
    private javax.swing.JTextField textFieldStartDate;
    private javax.swing.JTextField textFieldSymbol;
    // End of variables declaration//GEN-END:variables


}
