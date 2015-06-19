package com.elle.ellegui.presentation.filter;


import com.elle.ellegui.DistinctColumnItem;

import java.io.Serializable;
import java.util.*;

class TableFilterState implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // no set - filter cleared; set - some kind of filtering
    private final Map<Integer,Set<DistinctColumnItem>> data = new HashMap<Integer,Set<DistinctColumnItem>>();
    
    /**
     * Clears filtering for specific column
     */
    public void clear( int column ) {
        data.remove(column);
    }
    
    
    /**
     * Clears all filtering
     */
    public void clear() {
        data.clear();
    }
    
    private Set<DistinctColumnItem> prepareValueSet( int column ) {
        Set<DistinctColumnItem> vals =  data.get(column);
        if ( vals == null ) {
            vals = new HashSet<DistinctColumnItem>();
            data.put(column, vals);
        }
        return vals;
    }
    
    
    /**
     * Adds filter value for specified column 
     * @param column
     * @param value
     */
    public void addValue( int column, DistinctColumnItem value ) {
        prepareValueSet(column).add(value);
    }

    
    /**
     * Adds a collection of filter values for specified column 
     * @param column
     * @param values
     */
    public void addValues( int column, Collection<DistinctColumnItem> values ) {
        prepareValueSet(column).addAll(values);
    }

    /**
     * Resets a collection of filter values for specified column
     * @param column
     * @param values
     */
    public void setValues( int column, Collection<DistinctColumnItem> values ) {
        data.remove(column);
        if ( !(CollectionUtils.isEmpty(values))) {
            prepareValueSet(column).addAll(values);
        }
    }
    
    public Collection<DistinctColumnItem> getValues( int column ) {
        Set<DistinctColumnItem> vals =  data.get(column);
        return vals == null? Collections.<DistinctColumnItem>emptySet(): vals;
    }
    
    /**
     * Standard test for row inclusion using current filter values
     * @param entry
     * @return true if row has to be included
     */
    public boolean include( ITableFilter.Row entry ) {
    
        for( int col=0; col< entry.getValueCount(); col++ ) {
            Collection<DistinctColumnItem> values = getValues(col);
            if ( CollectionUtils.isEmpty(values) ) continue; // no filtering for this column
            if ( !values.contains( new DistinctColumnItem( entry.getValue(col), 0))) return false;
        }
        return true;
        
    }
    
    
}