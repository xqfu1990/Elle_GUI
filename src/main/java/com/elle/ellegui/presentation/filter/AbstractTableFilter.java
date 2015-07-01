package com.elle.ellegui.presentation.filter;

import com.elle.ellegui.DistinctColumnItem;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Partial implementation of table filter
 *
 * Created on Feb 10, 2011
 *
 * @author Eugene Ryzhikov
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public abstract class AbstractTableFilter<T extends JTable> implements ITableFilter<T> {

    private final Set<IFilterChangeListener> listeners = Collections.synchronizedSet(new HashSet<IFilterChangeListener>());

    private final Map<Integer, Collection<DistinctColumnItem>> distinctItemCache
            = Collections.synchronizedMap(new HashMap<Integer, Collection<DistinctColumnItem>>());

    private final T table;
    private final TableFilterState filterState = new TableFilterState();

    public AbstractTableFilter(T table) {
        this.table = table;
        setupDistinctItemCacheRefresh();
    }

    private void setupDistinctItemCacheRefresh() {
        clearDistinctItemCache();
        this.table.addPropertyChangeListener("model", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                clearDistinctItemCache();
                TableModel model = (TableModel) e.getNewValue();
                if (model != null) {
                    model.addTableModelListener(new TableModelListener() {

                        @Override
                        public void tableChanged(TableModelEvent e) {
                            clearDistinctItemCache();
                        }
                    });
                }
            }
        });
    }

    private void clearDistinctItemCache() {
        distinctItemCache.clear();
    }

    @Override
    public T getTable() {
        return table;
    }

    protected abstract boolean execute(int col, Collection<DistinctColumnItem> items);

    @Override
    public boolean apply(int col, Collection<DistinctColumnItem> items) {
        setFilterState(col, items);
        boolean result = execute(col, items);
        if (result) {
            fireFilterChange();
        }
        return result;
    }

    @Override
    public boolean apply(int col, Object objectSelected) {
        Collection<DistinctColumnItem> item = new ArrayList<>();
        DistinctColumnItem distinctColumnItem = new DistinctColumnItem(objectSelected, col);
        item.add(distinctColumnItem);
        return apply(col, item);
    }

    @Override
    public  boolean applyFilterBySymbol(int col, String symbolSelected, ITableFilter filter){    //method use in a main window filter
        Collection<DistinctColumnItem> items = new ArrayList<>();
        Collection<DistinctColumnItem> listItems = filter.getDistinctColumnItems(col);
          for(DistinctColumnItem item: listItems){
                   if(item.getValue().toString().startsWith(symbolSelected)){
                      items.add(item);
                   }
          }
           return apply(col,items);
    }
    /**
     * Method to apply filter with date from main window search box.
     * @param col
     * @param dateInit
     * @param dateEnd
     * @param filter
     * @return 
     */
    @Override
    public boolean applyFilterByDate(int col, String dateInit, String dateEnd, ITableFilter filter) {
        Collection<DistinctColumnItem> items = new ArrayList<>();
        Collection<DistinctColumnItem> listItems = filter.getDistinctColumnItems(col);
        ArrayList<DistinctColumnItem> itemArrayList = new ArrayList<>(listItems);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date date_End= null;
        try {
            date_End = format.parse(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();  
        }
        for(int i=0; i<itemArrayList.size();i++){
            if(itemArrayList.get(i).getValue().equals(dateInit)){
                for(int j=i; j<itemArrayList.size();j++){

                    try {
                        date = format.parse(itemArrayList.get(j).getValue().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();  
                    }
                  
                    if(date.before(date_End) || date.equals(date_End)){
                        items.add(itemArrayList.get(j));
                    }

                }
            }
            else {
                continue;
            }
        }
        return apply(col,items);
    }

    @Override
    public final void addChangeListener(IFilterChangeListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    @Override
    public final void removeChangeListener(IFilterChangeListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    public final void fireFilterChange() {
        for (IFilterChangeListener l : listeners) {
            l.filterChanged(AbstractTableFilter.this);
        }
    }

    @Override
    public Collection<DistinctColumnItem> getDistinctColumnItems(int column) {
        Collection<DistinctColumnItem> result = distinctItemCache.get(column);
        if (result == null) {

            int col = table.getColumnModel().getColumnCount();
            String title = table.getColumnName(column - 1);
            if (title.equals("Lot_Time")) {
                column = col - 1;
            } else if (title.equals("OCE_Time")) {
                column = col;
            }

            result = collectDistinctColumnItems(column);
        }
        return result;

    }

    private Collection<DistinctColumnItem> collectDistinctColumnItems(int column) {   // items in pop-up menu
        Set<DistinctColumnItem> result = new TreeSet<DistinctColumnItem>(); // to collect unique items

        for (int row = 0; row < table.getModel().getRowCount(); row++) {
            Object value = table.getModel().getValueAt(row, column);
            result.add(new DistinctColumnItem(value, row));
        }

        return result;
    }

    @Override
    public Collection<DistinctColumnItem> getFilterState(int column) {
        return filterState.getValues(column);
    }

    @Override
    public boolean isFiltered(int column) {
        Collection<DistinctColumnItem> checks = getFilterState(column);
        return !(CollectionUtils.isEmpty(checks)) && getDistinctColumnItems(column).size() != checks.size();
    }

    @Override
    public boolean includeRow(ITableFilter.Row row) {
        return filterState.include(row);
    }

    public void setFilterState(int column, Collection<DistinctColumnItem> values) {
        filterState.setValues(column, values);
    }

    public void clear() {
        filterState.clear();
        Collection<DistinctColumnItem> items = Collections.emptyList();
        for (int column = 0; column < table.getModel().getColumnCount(); column++) {
            execute(column, items);
        }
        fireFilterChange();
    }

}
