package com.elle.ellegui.presentation.filter;

import com.elle.ellegui.DistinctColumnItem;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.lang.reflect.Method;

@SuppressWarnings("serial")
public class TableAwareCheckListRenderer extends CheckListRenderer {
    
    private final JTable table;
    private final int column;

    public TableAwareCheckListRenderer( JTable table, int column ) {
        this.table = table;
        this.column = column;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // try to retrieve the text from table's cell renderer
        if (value instanceof DistinctColumnItem) {

            DistinctColumnItem item = (DistinctColumnItem) value;
            TableCellRenderer renderer = table.getCellRenderer( item.getRow(), column);
            
            try {
                
                Component cmpt = renderer.getTableCellRendererComponent(
                        table, item.getValue(), isSelected, hasFocus(), item.getRow(), column );

                Method method = cmpt.getClass().getMethod("getText");
                Object s = method.invoke(cmpt);
                
                if ( s instanceof String ) {
                    setText( (String)s );
                }
                
            } catch (Throwable e) {
//                e.printStackTrace();
            }
            
        }
        return this;
        
    }
    
}