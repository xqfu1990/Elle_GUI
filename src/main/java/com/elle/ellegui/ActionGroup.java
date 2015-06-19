package com.elle.ellegui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public final class ActionGroup extends AbstractAction implements Collection<Action> {

    private static final long serialVersionUID = -8373261802979340928L;

    private final List<Action> actions = new ArrayList<Action>();
    private boolean collapsed = true;


    protected ActionGroup( String name, Icon icon ) {
        super( name, icon );
    }

    public ActionGroup actions( Collection<Action> actions ) {
        addAll(actions);
        return this;
    }

    public ActionGroup actions( Action... actions ) {
        return actions( Arrays.asList(actions));
    }

    public ActionGroup collapsed( boolean collapsed ) {
        this.collapsed = collapsed;
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public boolean isCollapsed() {
        return collapsed;
    }

    protected void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public String getName() {
        return (String) getValue(Action.NAME);
    }

    public Icon getIcon() {
        return (Icon) getValue(Action.SMALL_ICON);
    }

    /// Collection methods

    @Override
    public int size() { return actions.size(); }

    @Override
    public boolean isEmpty() { return actions.isEmpty(); }

    @Override
    public boolean contains(Object o) { return actions.contains(o); }

    @Override
    public Iterator<Action> iterator() { return actions.iterator(); }

    @Override
    public Object[] toArray() { return actions.toArray(); }

    @Override
    public <T> T[] toArray(T[] a) { return actions.toArray(a); }

    @Override
    public boolean add(Action a) { return actions.add(a); }

    @Override
    public boolean remove(Object o) { return actions.remove(o); }

    @Override
    public boolean containsAll(Collection<?> c) { return actions.containsAll(c);}

    @Override
    public boolean addAll(Collection<? extends Action> c) { return actions.addAll(c); }

    @Override
    public boolean removeAll(Collection<?> c) { return actions.removeAll(c); }

    @Override
    public boolean retainAll(Collection<?> c) { return actions.retainAll(c); }

    @Override
    public void clear() { actions.clear(); }

}