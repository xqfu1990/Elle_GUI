package com.elle.ellegui.presentation.filter;


import com.elle.ellegui.DistinctColumnItem;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.util.Collection;

public interface ITableFilter<T extends JTable> extends Serializable {

    /**
     * The table under filter
     * @return
     */
    T getTable();

    /**
     *
     * @param column model column index
     * @return
     */
    Collection<DistinctColumnItem> getDistinctColumnItems(int column);

    /**
     *
     * @param column model column index
     * @return
     */
    Collection<DistinctColumnItem> getFilterState(int column);

    /**
     * Checks if column is filtered
     * @param column model column index
     * @return true if column is filtered
     */
    boolean isFiltered(int column);

    boolean includeRow(Row entry);

    /**
     * Apply filter for specified column based on collection of distinct items
     * @param col
     * @param items
     * @return
     */
    boolean apply(int col, Collection<DistinctColumnItem> items);
    boolean apply (int col, Object object);
    /**
     * Apply filter for specified column based on collection of distinct items get them from search text field.
     * @param col
     * @param symbolSelected
     * @return
     */
    boolean applyFilterBySymbol(int col, String symbolSelected, ITableFilter filter);

    /**
     * Apply filter for specified column based on collection of distinct items get them from search text field.
     * @param col
     * @param dateInit
     * @return
     */
    boolean applyFilterByDate(int col, String dateInit, String dateEnd, ITableFilter filter);

    public interface Row {
        int getValueCount();
        Object getValue(int column);
    }

    void addChangeListener(IFilterChangeListener listener);
    void removeChangeListener(IFilterChangeListener listener);

    /**
     * Clear the filter
     */
    void clear();

    /**
     * Describes what filter has to do when table model changes
     * @param model
     */
    void modelChanged(TableModel model);

}