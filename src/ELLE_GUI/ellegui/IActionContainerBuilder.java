package ELLE_GUI.ellegui;

import java.util.Collection;

import javax.swing.Action;
import javax.swing.JComponent;

public interface IActionContainerBuilder<T extends JComponent> {

    T build( Collection<Action> actions );

}