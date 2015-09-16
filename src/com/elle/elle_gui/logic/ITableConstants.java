/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.elle_gui.logic;

/**
 * ITableConstants
 * This interface stores all the table constants
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
public interface ITableConstants {
    
    // account names
    public static final String IB9048_ACCOUNT_NAME = "IB9048";
    public static final String TOS3622_ACCOUNT_NAME = "TOS3622";
    public static final String COMBINED_ACCOUNT_NAME = "Combined";
    
    // table names
    public static final String POSITIONS_TABLE_NAME = "positions";
    public static final String TRADES_TABLE_NAME = "trades";
    public static final String ALLOCATIONS_TABLE_NAME = "allocations";
    
    // column header name constants
    public static final String SYMBOL_COLUMN_NAME = "Symbol";
    
    // column width percent constants
    public static final float[] COL_WIDTH_PER_POSITIONS = { 
        60, // filecode
        62, // inputLine
        20, // wash
        57, // lineNum
        165, // Lot_Time
        40, // OCE
        165, // OCE_Time
        25, // LS
        155, // Symbol
        80, // Q
        70, // Qori
        90, // Adj_Price
        75, // Adj_Basis
        45, // Price
        45, // Basis
        40, // How
        55, // Codes
        65, // Account
        65, // L_codes
        58, // SecType
        40, // Multi
        85, // Underlying
        88, // Expiry
        75, // Strike
        55, // O_Type
        55 };// Notes
    
    public static final float[] COL_WIDTH_PER_TRADES = { 
        160, // Trade_Time
        30, // OC
        155, // Symbol
        85, // Q
        90, // TotalQ
        85, // Price
        80, // CommTax
        75, // Proceeds
        75, // Basis
        80, // Processed
        175, // Lot_Time
        85, // Realized_PL
        65, // Codes
        55, // Notes
        65, // Account
        60, // filecode
        70, // inputLine
        65, // SecType
        40, // Multi
        85, // Underlying
        88, // Expiry
        75, // Strike
        55, // O_Type
        65, // T_group
        55, // Lnotes
        65, // strategy
        85 };// Xchange
    public static final float[] COL_WIDTH_PER_ALLOCATIONS = {35, 65, 80, 70, 99, 99};
    
}
