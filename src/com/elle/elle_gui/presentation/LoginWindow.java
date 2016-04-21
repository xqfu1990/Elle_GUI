/**
 * @author Louis W.
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
package com.elle.elle_gui.presentation;

import com.elle.elle_gui.database.DBConnection;
import com.elle.elle_gui.admissions.Authorization;
import com.elle.elle_gui.database.Database;
import com.elle.elle_gui.database.Server;
import com.elle.elle_gui.logic.LoggingAspect;
import static com.elle.elle_gui.presentation.LogWindow.HYPHENS;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class LoginWindow extends JFrame {

    // class attributes 
    private String selectedServer;              // selected server
    private String selectedDB;                  // selected database
    private String userName;                    // user name to login 
    private String userPassword;                // user password to login

    // class component instances
    private ELLE_GUI_Frame elle_gui;
    private EditDatabaseWindow editDatabaseList;
    private LogWindow logWindow;
    private ArrayList<Server> servers;

    public LoginWindow() {

        initComponents();
        this.setTitle("Log in");
        loadServers();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jInputPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        comboBoxServer = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        passwordFieldPW = new javax.swing.JPasswordField();
        textFieldUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboBoxDatabase = new javax.swing.JComboBox();
        jButtonPanel = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnEditDB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Please input your username and password to log in.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jTextPanelLayout = new javax.swing.GroupLayout(jTextPanel);
        jTextPanel.setLayout(jTextPanelLayout);
        jTextPanelLayout.setHorizontalGroup(
            jTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jTextPanelLayout.setVerticalGroup(
            jTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("Server");

        comboBoxServer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pupone", "Local" }));
        comboBoxServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxServerActionPerformed(evt);
            }
        });

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        passwordFieldPW.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordFieldPW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldPWActionPerformed(evt);
            }
        });
        passwordFieldPW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldPWKeyPressed(evt);
            }
        });

        textFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsernameActionPerformed(evt);
            }
        });

        jLabel5.setText("Database");

        comboBoxDatabase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dummy", "Elle2015", "pupone_dummy", "pupone_Analyster" }));
        comboBoxDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDatabaseActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel/ Log off");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnLogin.setText("Log in");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jButtonPanelLayout = new javax.swing.GroupLayout(jButtonPanel);
        jButtonPanel.setLayout(jButtonPanelLayout);
        jButtonPanelLayout.setHorizontalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jButtonPanelLayout.setVerticalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditDB.setText("Edit");
        btnEditDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInputPanelLayout = new javax.swing.GroupLayout(jInputPanel);
        jInputPanel.setLayout(jInputPanelLayout);
        jInputPanelLayout.setHorizontalGroup(
            jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInputPanelLayout.createSequentialGroup()
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordFieldPW)
                            .addComponent(textFieldUsername)))
                    .addGroup(jInputPanelLayout.createSequentialGroup()
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxServer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxDatabase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditDB)
                .addContainerGap())
            .addComponent(jButtonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInputPanelLayout.setVerticalGroup(
            jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(comboBoxServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboBoxDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditDB))
                .addGap(18, 18, 18)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(jInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordFieldPW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.close();

    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * Close down application properly
     */
    public void close() {

        // terminate window and return resources
        this.dispose();
        System.exit(0); // Terminates the currently running Java Virtual Machine.
    }
    private void btnLoginActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void passwordFieldPWKeyPressed(KeyEvent evt) {//GEN-FIRST:event_passwordFieldPWKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_passwordFieldPWKeyPressed

    private void comboBoxDatabaseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_comboBoxDatabaseActionPerformed


    }//GEN-LAST:event_comboBoxDatabaseActionPerformed

    private void comboBoxServerActionPerformed(ActionEvent evt) {//GEN-FIRST:event_comboBoxServerActionPerformed

        String selectedServer;
        selectedServer = comboBoxServer.getSelectedItem().toString();
        comboBoxDatabase.setModel(getDatabasesCBModel(selectedServer));
        int server = comboBoxServer.getSelectedIndex();
        comboBoxDatabase.setSelectedIndex(getDefaultDatabase(server));
    }//GEN-LAST:event_comboBoxServerActionPerformed

    private void btnEditDBActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnEditDBActionPerformed

        // create a new edit selectedDB window
        editDatabaseList = new EditDatabaseWindow(this); // maybe we can make it not dependant on this
        editDatabaseList.setLocationRelativeTo(this);
        editDatabaseList.setVisible(true);
    }//GEN-LAST:event_btnEditDBActionPerformed

    private void textFieldUsernameActionPerformed(ActionEvent evt) {//GEN-FIRST:event_textFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsernameActionPerformed

    private void passwordFieldPWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldPWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldPWActionPerformed
    public String getUserName() {
        // local host testing or if does not contain pupone
        if (userName.startsWith("pupone")) {
            String userNameToAL = userName.substring(7);
            return userNameToAL;
        } else {
            return userName;
        }
    }

    /**
     * login
     */
    public void login() {

        // get user data
        selectedServer = comboBoxServer.getSelectedItem().toString();
        selectedDB = comboBoxDatabase.getSelectedItem().toString();
        userName = textFieldUsername.getText();
        char[] pw = passwordFieldPW.getPassword();
        userPassword = String.valueOf(pw);

        // logwindow
        logWindow = new LogWindow();
        logWindow.setUserLogFileDir(this.getUserName());
        // write to log file
        String date = logWindow.dateFormat.format(new Date());
        logWindow.addMessage(HYPHENS + date + HYPHENS);
        logWindow.readMessages(); // read log messages from the log file

        // connect to database
        logWindow.addMessageWithDate("Start to connect local database...");
        if (DBConnection.connect(selectedServer, selectedDB, userName, userPassword)) {
            logWindow.addMessageWithDate("Connect successfully!");

            // authorize user
            if (!Authorization.getInfoFromDB()) {

                logWindow.addMessageWithDate("This user has not been authorized!"
                        + "\n Access denied!");
                JOptionPane.showMessageDialog(this, "You have not been authorized. Default user access.");
            }

            // create an Analyster object
            elle_gui = new ELLE_GUI_Frame();

            // pass the log window to analyster
            elle_gui.setLogWindow(logWindow);

            // pass the selectedDB to Analyster
            // it is used in sql statements
            elle_gui.setDatabase(selectedDB);

            // show Analyster
            elle_gui.setLocationRelativeTo(this);
            elle_gui.setVisible(true);

            // terminate this object
            this.dispose(); // returns used resources

        } else {

            JOptionPane.showMessageDialog(this,
                    "There was an error.\n Please try again or contact support if you need further assistance.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);

            passwordFieldPW.setText("");
        }
    }

    public String getSelectedServer() {
        return selectedServer;
    }

    public void setSelectedServer(String selectedServer) {
        this.selectedServer = selectedServer;
    }

    public String getSelectedDB() {
        return selectedDB;
    }

    public void setSelectedDB(String selectedDB) {
        this.selectedDB = selectedDB;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public ELLE_GUI_Frame getElleGui() {
        return elle_gui;
    }

    public void setElleGui(ELLE_GUI_Frame elle_gui) {
        this.elle_gui = elle_gui;
    }

    public EditDatabaseWindow getEditDatabaseList() {
        return editDatabaseList;
    }

    public void setEditDatabaseList(EditDatabaseWindow editDatabaseList) {
        this.editDatabaseList = editDatabaseList;
    }

    public LogWindow getLogWindow() {
        return logWindow;
    }

    public void setLogWindow(LogWindow logWindow) {
        this.logWindow = logWindow;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public JButton getBtnEditDB() {
        return btnEditDB;
    }

    public void setBtnEditDB(JButton btnEditDB) {
        this.btnEditDB = btnEditDB;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    public JComboBox getComboBoxDatabase() {
        return comboBoxDatabase;
    }

    public void setComboBoxDatabase(JComboBox comboBoxDatabase) {
        this.comboBoxDatabase = comboBoxDatabase;
    }

    public JComboBox getComboBoxServer() {
        return comboBoxServer;
    }

    public void setComboBoxServer(JComboBox comboBoxServer) {
        this.comboBoxServer = comboBoxServer;
    }

    public JPanel getjButtonPanel() {
        return jButtonPanel;
    }

    public void setjButtonPanel(JPanel jButtonPanel) {
        this.jButtonPanel = jButtonPanel;
    }

    public JPanel getjInputPanel() {
        return jInputPanel;
    }

    public void setjInputPanel(JPanel jInputPanel) {
        this.jInputPanel = jInputPanel;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }

    public JPanel getjTextPanel() {
        return jTextPanel;
    }

    public void setjTextPanel(JPanel jTextPanel) {
        this.jTextPanel = jTextPanel;
    }

    public JPasswordField getPasswordFieldPW() {
        return passwordFieldPW;
    }

    public void setPasswordFieldPW(JPasswordField passwordFieldPW) {
        this.passwordFieldPW = passwordFieldPW;
    }

    public JTextField getTextFieldUsername() {
        return textFieldUsername;
    }

    public void setTextFieldUsername(JTextField textFieldUsername) {
        this.textFieldUsername = textFieldUsername;
    }

    private DefaultComboBoxModel getServersCBModel() {
        Vector serverNames = new Vector();
        for (Server server : servers) {
            serverNames.addElement(server.getName());
        }
        if (serverNames.isEmpty()) {
            serverNames.addElement("");
        }
        return new DefaultComboBoxModel(serverNames);
    }

    private DefaultComboBoxModel getDatabasesCBModel(String serverName) {
        Vector databases = new Vector();
        for (Server server : servers) {
            if (server.getName().equals(serverName)) {
                for (Database db : server.getDatabases()) {
                    databases.addElement(db.getName());
                }
            }
        }
        if (databases.isEmpty()) {
            databases.addElement("");
        }
        return new DefaultComboBoxModel(databases);
    }

    public void loadServers() {
        servers = DBConnection.readServers();
        // set comboboxes for servers and databases
        comboBoxServer.setModel(getServersCBModel());
        comboBoxDatabase.setModel(getDatabasesCBModel(servers.get(0).getName()));
        comboBoxServer.setSelectedIndex(getDefaultServer());
        int server = comboBoxServer.getSelectedIndex();
        comboBoxDatabase.setSelectedIndex(getDefaultDatabase(server));
    }

    private int getDefaultServer() {
        int server = 0;
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).isDefaultSelection()) {
                server = i;
                break;
            }
        }
        return server;
    }

    private int getDefaultDatabase(int server) {
        int database = 0;
        ArrayList<Database> databases = servers.get(server).getDatabases();
        for (int i = 0; i < databases.size(); i++) {
            if (databases.get(i).isDefaultSelection()) {
                database = i;
                break;
            }
        }
        return database;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEditDB;
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox comboBoxDatabase;
    private javax.swing.JComboBox comboBoxServer;
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JPanel jInputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jTextPanel;
    private javax.swing.JPasswordField passwordFieldPW;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables

}
