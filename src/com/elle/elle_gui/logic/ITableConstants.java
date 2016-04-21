/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.elle_gui.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ITableConstants This interface stores all the table constants
 *
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
public interface ITableConstants {

    // Edit the version and date it was created for new archives and jars
    public static final String CREATION_DATE = "2016-04-21";
    public static final String VERSION = "1.0.0";

    // account names
    public static final String IB9048_ACCOUNT_NAME = "IB9048";
    public static final String TOS3622_ACCOUNT_NAME = "TOS3622";
    public static final String COMBINED_ACCOUNT_NAME = "Combined";

    // table names
    public static final String POSITIONS_TABLE_NAME = "positions";
    public static final String TRADES_TABLE_NAME = "tradesALL";
    public static final String TRADES_TABLE_VIEW_NAME = "Trades_DefaultView";
    public static final String ALLOCATIONS_TABLE_NAME = "allocations";

    // column header name constants
    public static final String SYMBOL_COLUMN_NAME = "Symbol";

    public static final Map<String, Integer> COL_WIDTH_PER_POSITIONS
            = Collections.unmodifiableMap(new HashMap<String, Integer>() {
                {
                    put("symbol", 155);
                    put("lot_Time", 160);
                    put("Q", 80);
                    put("line", 50);
                    put("OCE", 40);
                    put("OCE_Time", 160);
                    put("LS", 25);
                    put("Qori", 70);
                    put("price_adj", 90);
                    put("basis_adj", 75);
                    put("price", 45);
                    put("basis", 45);
                    put("How", 40);
                    put("wash", 20);
                    put("ksflag", 55);
                    put("codes", 55);
                    put("account", 65);
                    put("L_codes", 40);
                    put("secType", 58);
                    put("multi", 40);
                    put("underlying", 85);
                    put("expiry", 88);
                    put("strike", 75);
                    put("O_Type", 55);
                    put("notes", 55);
                    put("filecode", 60);
                    put("inputLine", 62);
                    put("pos_id", 50);
                    put("grp", 20);
                }
            });

    /**
     *
     */
    public static final Map<String, Integer> COL_WIDTH_PER_ALLOCATIONS = 
            Collections.unmodifiableMap(new HashMap<String, Integer>() {
        {
            put("id", 30);
            put("symbol", 55);
            put("trade_Time", 160);
            put("tradeQ", 60);
            put("tradePrice", 85);
            put("method", 80);
            put("mathQ", 80);
            put("mathProceeds", 90);
            put("lot_Time", 160);
            put("line", 50);
            put("price_adj", 30);
            put("matchBasis", 75);
            put("realized_PL", 90);
            put("term", 55);
            put("account", 65);
        }
    });
    /**
     *
     */
    public static final Map<String, Integer> COL_WIDTH_PER_TRADES  = 
            Collections.unmodifiableMap(new HashMap<String, Integer>() {
        {
            put("id", 40);
            put("trade_Time", 160);
            put("OC", 30);
            put("LS", 80);
            put("symbol", 155);
            put("Q", 85);
            put("price", 80);
            put("comm", 80);
            put("proceeds", 90);
            put("basis", 75);
            put("price_adj", 90);
            put("processed", 80);
            put("lot_Time", 160);
            put("realized_PL", 90);
            put("codes", 55);
            put("ksflag", 55);
            put("notes", 55);
            put("account", 65);
            put("filecode", 60);
            put("inputLine", 62);
            put("locked", 60);
            put("secType", 58);
            put("multi", 40);
            put("underlying", 85);
            put("expiry", 88);
            put("strike", 75);
            put("O_Type", 55);
            put("Lnotes", 55);
            put("strategy", 60);
            put("Xchange", 85);
            put("order", 50);
            put("fill", 30);
            put("TotalQ", 50);
            put("t_grp", 60);
            put("matching", 50);
            put("method", 50);
        }
    });

    /**
     *
     */
    public static final Map<String, Integer> COL_WIDTH_PER_TRADES_VIEW = 
            Collections.unmodifiableMap(new HashMap<String, Integer>() {
        {
//            put("T_Group", 60);
            put("id", 40);
            put("trade_Time", 160);
            put("OC", 30);
            put("symbol", 155);
            put("Q", 85);
            put("price", 80);
//            put("CommTax", 80);
            put("proceeds", 90);
            put("basis", 75);
            put("realized_PL", 90);
            put("lot_Time", 160);
            put("codes", 55);
            put("account", 65);
            put("order", 50);
            put("fill", 30);
        }
    });

//// column width percent constants
//public static final float[] COL_WIDTH_PER_POSITIONSa = {
//        155, // Symbol
//        160, // Lot_Time
//        80, // Q
//        50, //line
//        40, // OCE
//        160, // OCE_Time
//        25, // LS
//        70, // Qori
//        90, // Adj_Price
//        75, // Adj_Basis
//        45, // Price
//        45, // Basis
//        40, // How
//        20, // wash
//        55, //ksflag
//        55, // Codes
//        65, // Account
//        65, // L_codes
//        58, // SecType
//        40, // Multi
//        85, // Underlying
//        88, // Expiry
//        75, // Strike
//        55, // O_Type
//        55,// Notes
//        60, // filecode
//        62, // inputLine
//        50}; // post_id
//
//    public static final float[] COL_WIDTH_PER_TRADESa = {
//        40, // id
//        160, // Trade_Time
//        30, // OC
//        80, // LS
//        155, // Symbol
//        85, // Q
//        85, // Price
//        80, // CommTax
//        90, // Proceeds
//        75, // Basis
//        80, //Price_adj
//        80, // Processed
//        160, // Lot_Time
//        90, // Realized_PL
//        65, // Codes
//        60, // ksflag
//        55, // Notes
//        65, // Account
//        60, // filecode
//        70, // inputLine
//        60, // Locked
//        65, // SecType
//        40, // Multi
//        85, // Underlying
//        95, // Expiry
//        75, // Strike
//        55, // O_Type
//        55, // Lnotes
//        60, // strategy
//        85, // Xchange
//        50, // order
//        30, // fill
//        50, // TotalQ
//        60, // T_Group
//        50, // Matching
//        50}; //Method 
//    public static final float[] COL_WIDTH_PER_TRADES_VIEWa = {
//        45, // T_Group
//        40, // id
//        160, // Trade_Time
//        30, // OC
//        155, // Symbol
//        85, // Q
//        85, // Price
//        80, // CommTax
//        90, // Proceeds
//        80, // Basis
//        90, // Realized_PL
//        160, // Lot_Time
//        65, // Codes
//        65, // Account
//        40, // order
//        30}; // fill
//    public static final float[] COL_WIDTH_PER_ALLOCATIONS = {35, 65, 80, 70, 99, 99};
}
