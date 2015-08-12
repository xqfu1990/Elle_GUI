package ELLE_GUI;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

public final class Actions {

    private static final Action SEPARATOR = new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {}

    };


    private Actions() {}

    public static final Action separator() { return SEPARATOR; }

    public static final boolean isSeparator(Action a) {
        return SEPARATOR == a;
    }

    public static final ActionGroup collapsedGroup( String name, Icon icon ) {
        return new ActionGroup(name, icon ).collapsed(true);
    }

    public static final ActionGroup collapsedGroup( String name) {
        return collapsedGroup(name,null);
    }

    public static final ActionGroup expandedGroup( String name, Icon icon ) {
        return new ActionGroup(name, icon ).collapsed(false);
    }

    public static final ActionGroup expandedGroup( String name) {
        return expandedGroup(name, null);
    }

    public static final Collection<Action> actions( Action... actions ) {
        return Arrays.asList(actions);
    }


}