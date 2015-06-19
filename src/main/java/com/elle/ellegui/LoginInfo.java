/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.ellegui;

import javax.swing.JOptionPane;

/**
 *
 * @author Louis W.
 */
public class LoginInfo {
    private String name, url, username, password, iconAddr; // do not use static here
    private String driver = "com.mysql.jdbc.Driver";
    private boolean isSet = false;
    
    LoginInfo() {
        // to do
    }
    
    LoginInfo(String str) {
        name = str;
    }
    
    LoginInfo(String addr, String user, String pw) {
        url = addr;
        username = user;
        password = pw;
        isSet = true;
    }
    
    public String getDriver() {
        return driver;
    }
    
    public String getURL() {
        return url;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getServerName() {
        return name;
    }
    
    public String getIconAddress() {
        return iconAddr;
    }
    
    public void setURL(String str) {
        url = str;
    }
    
    public void setUsername(String str) {
        username = str;
    }
    
    public void setPassword(String str) {
        password = str;
    }
    
    public void setServerName(String str) {
        name = str;
    }
    
    public void setIconAddr(String str) {
        iconAddr = str;
    }
    
    public boolean checkInfo() {
        if(isSet)
            return true;
        else if(url != null && username != null && password != null) {
            isSet = true;
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Your login information is not complete.\nPlease login.");
            return false;
        }
         
    }
    
}
