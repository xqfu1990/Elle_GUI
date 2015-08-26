package com.elle.elle_gui.old;

import java.awt.Component;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


class ActionBuilderHelper {

    protected <T> T createGroup( MenuAdapter<T> menu, Collection<Action> actions ) {
        int item = 1; // has to be 1 to compare to size
        int size = actions.size();
        for( Action a: actions ) {
            createAction( menu, a, item++ == size );
        }
        return menu.get();
    }

    @SuppressWarnings("unchecked")
    private <T> T createAction( MenuAdapter<T> menu, Action a, boolean isLast ) {
        
        if ( a instanceof Collection ) {

            if ( isCollapsed(a)) {
                JMenu m = createGroup( new JMenuAdapter(new JMenu(a)), (Collection<Action>)a );
                menu.add(m);
            } else {
                menu.addSeparator();
                createGroup( menu, (Collection<Action>)a );
                if ( !isLast ) menu.addSeparator();
            }

        } else {

            if ( Actions.isSeparator(a)) {
                if ( !isLast ) menu.addSeparator();
            } else {
                
                if ( isCheckAction(a)) {
                    menu.add(new JCheckBoxMenuItem(prepareCheckAction(a)));
                } else if ( isRadioAction(a)) {
                    JMenuItem item = menu.add(new JRadioButtonMenuItem(prepareCheckAction(a)));
                    menu.getButtonGroup().add( item );
                } else {
                    menu.add(new JMenuItem(a));
                }
            }

        }

        return menu.get();

    }
    
    private Action prepareCheckAction( Action a ) {
        if ( a.getValue(Action.SELECTED_KEY) == null ) {
            a.putValue(Action.SELECTED_KEY, Boolean.FALSE);
        }
        return a;
    }
    
    private boolean isCheckAction( Action a ) {
        return a.getClass().getAnnotation(CheckAction.class) != null;
    }

    private boolean isRadioAction( Action a ) {
        return a.getClass().getAnnotation(RadioAction.class) != null;
    }
    
    private boolean isCollapsed( Action a ) {
        return a instanceof ActionGroup && ((ActionGroup)a).isCollapsed();
    }

    
 /////// ADAPTERS ///////////////////////////////////////////////////////////////////////    
    
    abstract class MenuAdapter<T> {

        protected T target;
        private ButtonGroup bg;

        public MenuAdapter( T target ) {
            this.target = target;
        }

        public T get() { return target;    }

        public void addSeparator() {}
        
        public ButtonGroup getButtonGroup() {
            if (bg == null) {
                bg = new ButtonGroup();
            }
            return bg;
        }

        public abstract JMenuItem add( JMenuItem  menuItem );

        

    }

    class JMenuBarAdapter extends MenuAdapter<JMenuBar>{

        public JMenuBarAdapter( JMenuBar mb ) {
            super( mb );
        }

        @Override
        public JMenuItem add(JMenuItem menuItem) {

            Component c = target.add(menuItem);
            if ( c instanceof JMenu ) {
                ((JMenu)c).setIcon(null);
            }
            return menuItem;

        }

    }

    class JToolBarAdapter extends MenuAdapter<JToolBar>{

        public JToolBarAdapter( JToolBar tb ) {
            super( tb );
        }

        @Override
        public JMenuItem add(JMenuItem menuItem) {
            Action action = menuItem.getAction();
//            Action action = menuItem instanceof JMenu? new ActionDropDownMenu((ActionGroup) menuAction): menuAction;
            
            AbstractButton b = add( action, menuItem );
            b.setHorizontalTextPosition(SwingConstants.LEADING);
            b.putClientProperty("hideActionText", action.getValue(Action.SMALL_ICON) != null);
            return menuItem;
        }
        
        private AbstractButton add( Action action, JMenuItem item ) {
            
            if ( item instanceof JCheckBoxMenuItem || item instanceof JRadioButtonMenuItem ) {
                return (AbstractButton) target.add( new JToggleButton( action ));
            } else if ( item instanceof JMenu ) {
                return target.add( new ActionDropDownMenu((ActionGroup) action) );
            } else {
                return target.add( action );
            }
            
        }
        

    }

    class JPopupMenuAdapter extends MenuAdapter<JPopupMenu>{

        public JPopupMenuAdapter( JPopupMenu menu ) {
            super( menu );
        }

        @Override
        public JMenuItem add(JMenuItem menuItem) {
            target.add(menuItem);
            return menuItem;
        }

        @Override
        public void addSeparator() {
            int count = target.getComponentCount();
            boolean canAddSeparator = count != 0 &&
                    target.getComponent(count-1).getClass() != JPopupMenu.Separator.class;
            if ( canAddSeparator ) target.addSeparator();
        }

    }

    class JMenuAdapter extends MenuAdapter<JMenu> {

        public JMenuAdapter( JMenu menu ) {
            super(menu);
        }

        @Override
        public JMenuItem add(JMenuItem menuItem) {
            target.add(menuItem);
            return menuItem;
        }

        @Override
        public void addSeparator() {
            int count = target.getMenuComponentCount();
            boolean canAddSeparator = count != 0 &&
                    target.getMenuComponent(count-1).getClass() != JPopupMenu.Separator.class;
            if ( canAddSeparator ) target.addSeparator();
        }

    }

}