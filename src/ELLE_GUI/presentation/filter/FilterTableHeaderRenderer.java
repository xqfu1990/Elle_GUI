package ELLE_GUI.presentation.filter;

import ELLE_GUI.CompoundIcon;
import ELLE_GUI.TableHeaderRenderer;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * Table header renderer to show the column filter state 
 * 
 * Created on Feb 10, 2011
 * @author Eugene Ryzhikov
 *
 */
class FilterTableHeaderRenderer extends TableHeaderRenderer {

    private static final long serialVersionUID = 1L;

    private ImageIcon icon;
    private final int filterIconPlacement;
    private final ITableFilter<?> tableFilter;

    
    public FilterTableHeaderRenderer(ITableFilter<?> tableFilter,
            int filterIconPlacement) {
        this.tableFilter = tableFilter;
        this.filterIconPlacement = filterIconPlacement;

        if (this.filterIconPlacement != SwingConstants.LEADING &&
                this.filterIconPlacement != SwingConstants.TRAILING) {
            throw new UnsupportedOperationException("The filter icon " +
                    "placement can only take the values of " +
                    "SwingConstants.LEADING or SwingConstants.TRAILING");
        }
    }
    
    private Icon getFilterIcon() {

        if (icon == null) {
            icon = new ImageIcon( getClass().getResource("funnel.png"));
            icon = new ImageIcon( icon.getImage().getScaledInstance( 12, 12, Image.SCALE_SMOOTH  ));
        }
        return icon;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        
        final JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int modelColumn =  table.convertColumnIndexToModel(column);
        if (tableFilter.isFiltered(modelColumn)) {
            Icon oldIcon = label.getIcon();
            Icon newIcon = null;
            if (oldIcon == null) {
                newIcon = getFilterIcon();     
            } else {
                ComponentOrientation orientation =
                        label.getComponentOrientation();
                if (ComponentOrientation.RIGHT_TO_LEFT.equals(orientation)) {
                    if (filterIconPlacement == SwingConstants.LEADING) {
                        newIcon = new CompoundIcon(oldIcon, getFilterIcon());
                    } else {
                        newIcon = new CompoundIcon(getFilterIcon(), oldIcon);
                    }
                } else {
                    if (filterIconPlacement == SwingConstants.LEADING) {
                        newIcon = new CompoundIcon(getFilterIcon(), oldIcon);
                    } else {
                        newIcon = new CompoundIcon(oldIcon, getFilterIcon());
                    }
                }
            }
            label.setIcon(newIcon);
        }
        
        return label;
    }

}