package com.elle.ellegui.presentation.filter;

import com.elle.ellegui.DistinctColumnItem;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.Serializable;
import java.util.Collection;

public class JTableFilter extends AbstractTableFilter<JTable> {

    private static final long serialVersionUID = 1L;

    private final TableRowFilter filter = new TableRowFilter();

    public JTableFilter( JTable table ) {
        super(table);
    }

    @Override
    protected boolean execute(int col, Collection<DistinctColumnItem> items) {

        RowSorter<?> rs = getTable().getRowSorter();

        if (!( rs instanceof DefaultRowSorter )) return false;

        DefaultRowSorter<?, ?> drs = (DefaultRowSorter<?, ?>) rs;

        @SuppressWarnings("unchecked")
        RowFilter<Object,Object> prevFilter = (RowFilter<Object, Object>) drs.getRowFilter();
        if ( !(prevFilter instanceof TableRowFilter)) {
            filter.setParentFilter(prevFilter);
        }

        drs.setRowFilter(filter);
        return true;


    }

    class TableRowFilter extends RowFilter<Object,Object> implements Serializable {

        private static final long serialVersionUID = 1L;

        private RowFilter<Object, Object> parentFilter;

        public RowFilter<Object, Object> getParentFilter() {
            return parentFilter;
        }

        public void setParentFilter( RowFilter<Object, Object> parentFilter ) {
            this.parentFilter = (parentFilter == null ||  parentFilter == this )? null: parentFilter;
        }

        @Override
        public boolean include( final Entry<? extends Object, ? extends Object> entry) {

            // use parent filter condition
            if ( parentFilter != null && !parentFilter.include(entry)) return false;

            return includeRow( new ITableFilter.Row() {

                @Override
                public Object getValue(int column) { return entry.getValue(column); }

                @Override
                public int getValueCount() { return entry.getValueCount(); }

            });

        }

    }

    public void modelChanged( TableModel model ) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( model );
        sorter.setSortsOnUpdates(true);
        getTable().setRowSorter( sorter );
    }


}