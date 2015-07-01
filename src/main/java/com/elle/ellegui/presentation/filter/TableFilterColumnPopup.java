package com.elle.ellegui.presentation.filter;


import com.elle.ellegui.DistinctColumnItem;
import com.elle.ellegui.GUI;
import com.elle.ellegui.JSearchTextField;
import com.elle.ellegui.PopupWindow;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class TableFilterColumnPopup extends PopupWindow implements MouseListener {

    static class ColumnAttrs {

        public Dimension preferredSize;
    }

    private boolean enabled = false;

    private final static CheckList<DistinctColumnItem> filterList = new CheckList.Builder().build();
    private final JSearchTextField searchField = new JSearchTextField();

    private final Map<Integer, ColumnAttrs> colAttrs = new HashMap<Integer, ColumnAttrs>();
    private static int mColumnIndex = -1;

    private final ITableFilter<?> filter;
    private boolean searchable;
    private IListFilter searchFilter = CheckListFilterType.CONTAINS;
    private static IObjectToStringTranslator translator;
    private boolean actionsVisible = true;
    private boolean useTableRenderers = false;

    @SuppressWarnings("static-access")
    public TableFilterColumnPopup(ITableFilter<?> filter) {

        super(true);

        this.filter = filter;
        filterList.getList().setVisibleRowCount(8);

        setupTableHeader();
        filter.getTable().addPropertyChangeListener("tableHeader", (PropertyChangeEvent evt) -> {
            setupTableHeader();
        });
        filter.getTable().addPropertyChangeListener("model", (PropertyChangeEvent evt) -> {
            colAttrs.clear();
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                perform(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                perform(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                perform(e);
            }

            private void perform(DocumentEvent e) {
                filterList.filter(searchField.getText(), translator, searchFilter);
            }

        });

    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @SuppressWarnings("static-access")
    public void setSearchTranslator(IObjectToStringTranslator tranlsator) {
        this.translator = tranlsator;
    }

    public void setSearchFilter(IListFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public void setActionsVisible(boolean actionsVisible) {
        this.actionsVisible = actionsVisible;
    }

    public void setUseTableRenderers(boolean reuseRenderers) {
        this.useTableRenderers = reuseRenderers;
    }

    private void setupTableHeader() {
        JTableHeader header = filter.getTable().getTableHeader();
        if (header != null) {
            header.addMouseListener(this);
        }
    }

    @SuppressWarnings("serial")
    @Override
    protected JComponent buildContent() {

        JPanel owner = new JPanel(new BorderLayout(3, 3));
        owner.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        owner.setPreferredSize(new Dimension(250, 150)); // default popup size

        Box commands = new Box(BoxLayout.LINE_AXIS);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setOpaque(false);
        toolbar.add(new PopupWindow.CommandAction(
                "Clear all column filters",
                new ImageIcon("imag_10.png")) {
                    @Override
                    protected boolean perform() {
                        return clearAllFilters();
                    }
                });
        commands.add(toolbar);

        commands.add(Box.createHorizontalGlue());

        JButton apply = new JButton(new PopupWindow.CommandAction("Apply") {

            @Override
            protected boolean perform() {
                return applyColumnFilter();
            }
        });
        commands.add(apply);

        commands.add(Box.createHorizontalStrut(5));
        commands.add(new JButton(new PopupWindow.CommandAction("Cancel")));
        commands.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        commands.setBackground(UIManager.getColor("Panel.background"));
        commands.setOpaque(true);

        if (searchable) {
            owner.add(searchField, BorderLayout.NORTH);
        }
        owner.add(new JScrollPane(filterList.getList()), BorderLayout.CENTER);
        owner.add(commands, BorderLayout.SOUTH);

        return owner;

    }

    public boolean applyColumnFilter() {    // Excel Filter
        Collection<DistinctColumnItem> checked = filterList.getCheckedItems();
        ICheckListModel<DistinctColumnItem> model = filterList.getModel();

        model.filter("", translator, CheckListFilterType.CONTAINS); // clear filter by "" to get true results
        filter.apply(mColumnIndex, checked);
        
        String title = filter.getTable().getColumnName(mColumnIndex - 1);
        switch (title) {
            case "Lot_Date":
                // highlight the correct column
                mColumnIndex = 5;
                break;
            case "OCE_Date":
                mColumnIndex = 7;
                break;
        }
        GUI.monitorTableChange(mColumnIndex-1);
        return true;
    }

    private boolean clearAllFilters() {
        filter.clear();
        GUI.monitorTableChange(-1);
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

        // Popup menus are triggered differently on different platforms
    // Therefore, isPopupTrigger should be checked in both mousePressed and mouseReleased
    // events for for proper cross-platform functionality
    @Override
    public void mousePressed(MouseEvent e) {
        if (enabled && e.isPopupTrigger()) {
            showFilterPopup(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (enabled && e.isPopupTrigger()) {
            showFilterPopup(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void showFilterPopup(MouseEvent e) {
        JTableHeader header = (JTableHeader) (e.getSource());
        TableColumnModel colModel = filter.getTable().getColumnModel();

        // The index of the column whose header was clicked
        int vColumnIndex = colModel.getColumnIndexAtX(e.getX());
        if (vColumnIndex < 0) {
            return;
        }

        // Determine if mouse was clicked between column heads
        Rectangle headerRect = filter.getTable().getTableHeader().getHeaderRect(vColumnIndex);
        if (vColumnIndex == 0) {
            headerRect.width -= 2;
        } else {
            headerRect.grow(-2, 0);
        }

        // Mouse was clicked between column heads
        if (!headerRect.contains(e.getX(), e.getY())) {
            return;
        }

        // restore popup's size for the column
        mColumnIndex = filter.getTable().convertColumnIndexToModel(vColumnIndex);
        
        // Z: if it is a date + time column, then redirect it to the last date-only column 
        int col = filter.getTable().getColumnModel().getColumnCount();
        String title = filter.getTable().getColumnName(mColumnIndex - 1);
        if (title.equals("Lot_Time")) {
            mColumnIndex = col - 1;
        } else if (title.equals("OCE_Time")) {
            mColumnIndex = col;
        }

        setPreferredSize(getColumnAttrs(vColumnIndex).preferredSize);

        Collection<DistinctColumnItem> distinctItems = filter.getDistinctColumnItems(mColumnIndex);

        DefaultCheckListModel<DistinctColumnItem> model = new DefaultCheckListModel<DistinctColumnItem>(distinctItems);
        filterList.setModel(actionsVisible ? new ActionCheckListModel<DistinctColumnItem>(model) : model);
        Collection<DistinctColumnItem> checked = filter.getFilterState(mColumnIndex);

        // replace empty checked items with full selection
        filterList.setCheckedItems(CollectionUtils.isEmpty(checked) ? distinctItems : checked);

        if (useTableRenderers) {
            filterList.getList().setCellRenderer(new TableAwareCheckListRenderer(filter.getTable(), vColumnIndex));
        }

        // show pop-up
        show(header, headerRect.x, header.getHeight());
    }

    private ColumnAttrs getColumnAttrs(int column) {
        ColumnAttrs attrs = colAttrs.get(column);
        if (attrs == null) {
            attrs = new ColumnAttrs();
            colAttrs.put(column, attrs);
        }

        return attrs;
    }

    @Override
    protected void beforeShow() {
        if (searchable) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    searchField.setText("");
                    searchField.requestFocusInWindow();
                }
            });
        }
    }

    @Override
    public void beforeHide() {
        // save pop-up's dimensions before pop-up becomes hidden
        getColumnAttrs(mColumnIndex).preferredSize = getPreferredSize();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
