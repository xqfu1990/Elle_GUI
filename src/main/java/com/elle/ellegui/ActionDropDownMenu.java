package com.elle.ellegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * An action representing a drop down menu.
 * Intended to be used for drop down buttons. 
 * When assigned to the button shows a pop up menu on click.
 * Also automatically adds "drop down" arrow icon to the right of action's icon.  
 * 
 * @author Eugene Ryzhikov
 *
 */
class ActionDropDownMenu extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private static final Icon DROPDOWN_ICON =  new ImageIcon( ActionDropDownMenu.class.getResource("dropdown.png"));

    private JPopupMenu menu = null;
    private final ActionGroup action;

    /**
     * Action will create a pop up menu out of give ActionGroup
     * @param actionGroup
     */
    public ActionDropDownMenu( ActionGroup actionGroup ) {
        super( actionGroup.getName(), createIcon(actionGroup));
        this.action = actionGroup;
    }

    private static Icon createIcon( ActionGroup action ) {
        Icon mainIcon = action.getIcon();
        return mainIcon == null? DROPDOWN_ICON: new CompoundIcon(mainIcon, DROPDOWN_ICON);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if ( !( e.getSource() instanceof Component ) ||
             action == null || action.isEmpty() ||
             !action.isEnabled() ) return;

        if ( menu == null ) {
            menu = ActionContainerBuilderFactory.getPopupMenuBuilder().build(action);
        }

        Component cmpt = (Component) e.getSource();
        menu.show( cmpt , 0, cmpt.getHeight() );

    }

}