/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.elle_gui.logic;

/**
 * ITableConstants This interface stores all the table constants
 *
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
public interface ITableConstants {
    
    // Edit the version and date it was created for new archives and jars
    public static final String CREATION_DATE = "2016-03-10";
    public static final String VERSION = "0.9.7";

    // account names
    public static final String IB9048_ACCOUNT_NAME = "IB9048";
    public static final String TOS3622_ACCOUNT_NAME = "TOS3622";
    public static final String COMBINED_ACCOUNT_NAME = "Combined";

    // table names
    public static final String POSITIONS_TABLE_NAME = "positions";
    public static final String TRADES_TABLE_NAME = "trades";
    public static final String TRADES_TABLE_VIEW_NAME = "Trades_DefaultView";
    public static final String ALLOCATIONS_TABLE_NAME = "allocations";

    // column header name constants
    public static final String SYMBOL_COLUMN_NAME = "Symbol";

    // column width percent constants
    public static final float[] COL_WIDTH_PER_POSITIONS = {
        155, // Symbol
        160, // Lot_Time
        80, // Q
        50, //line
        40, // OCE
        160, // OCE_Time
        25, // LS
        70, // Qori
        90, // Adj_Price
        75, // Adj_Basis
        45, // Price
        45, // Basis
        40, // How
        20, // wash
        55, //ksflag
        55, // Codes
        65, // Account
        65, // L_codes
        58, // SecType
        40, // Multi
        85, // Underlying
        88, // Expiry
        75, // Strike
        55, // O_Type
        55,// Notes
        60, // filecode
        62, // inputLine
        50}; // post_id
    
    // column width percent constants
    public static final float[] COL_WIDTH_PER_ALLOCATIONS = {
        30,  //id
        55, // Symbol
        160, // trade_Time
        80, // tradeQ
        80, // tradePrice
        80, // method
        80, // mathQ
        100, // mathProceeds
        160, // lot_Time
        40, // line
        90, // price_adj
        75, // matchBasis
        90, // realize_PL
        50, // term
        65}; // account

    public static final float[] COL_WIDTH_PER_TRADES = {
        40,  // id
        160, // Trade_Time
        30, // OC
        80, // LS
        155, // Symbol
        85, // Q
        85, // Price
        80, // CommTax
        90, // Proceeds
        75, // Basis
        80, //Price_adj
        80, // Processed
        160, // Lot_Time
        90, // Realized_PL
        65, // Codes
        60, // ksflag
        55, // Notes
        65, // Account
        60, // filecode
        70, // inputLine
        60, // Locked
        65, // SecType
        40, // Multi
        85, // Underlying
        95, // Expiry
        75, // Strike
        55, // O_Type
        55, // Lnotes
        60, // strategy
        85, // Xchange
        50, // order
        30, // fill
        50, // TotalQ
        60, // T_Group
        50, // Matching
        50}; //Method 
    
     public static final float[] COL_WIDTH_PER_TRADES_VIEW = {
        45,  // T_Group
        40,  // id
        160, // Trade_Time
        30, // OC
        155, // Symbol
        85, // Q
        85, // Price
        80, // CommTax
        90, // Proceeds
        80, // Basis
        90, // Realized_PL
        160, // Lot_Time
        65, // Codes
        65, // Account
        40, // order
        30}; // fill
     
//    public static final float[] COL_WIDTH_PER_ALLOCATIONS = {35, 65, 80, 70, 99, 99};

}
