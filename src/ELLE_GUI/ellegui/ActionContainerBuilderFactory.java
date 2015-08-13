package ELLE_GUI.ellegui;

import ELLE_GUI.ellegui.ActionBuilderHelper;
import java.util.Collection;

import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

public final class ActionContainerBuilderFactory {

    private ActionContainerBuilderFactory() {}

    private static MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
    private static PopupMenuBuilder popupMenuBuilder = new PopupMenuBuilder();
    private static ToolBarBuilder toolBarBuilder = new ToolBarBuilder();

    public static IActionContainerBuilder<JMenuBar> getMenuBarBuilder() {
        return menuBarBuilder;
    }

    public static IActionContainerBuilder<JPopupMenu> getPopupMenuBuilder() {
        return popupMenuBuilder;
    }

    public static IActionContainerBuilder<JToolBar> getToolBarBuilder() {
        return toolBarBuilder;
    }
}

class MenuBarBuilder extends ActionBuilderHelper implements IActionContainerBuilder<JMenuBar> {

    @Override
    public  JMenuBar build(Collection<Action> actions) {
        return createGroup( new JMenuBarAdapter(new JMenuBar()), actions );
    }

}

class PopupMenuBuilder extends ActionBuilderHelper implements IActionContainerBuilder<JPopupMenu> {

    @Override
    public JPopupMenu build(Collection<Action> actions) {
        return createGroup( new JPopupMenuAdapter(new JPopupMenu()), actions );
    }

}

class ToolBarBuilder extends ActionBuilderHelper implements IActionContainerBuilder<JToolBar> {

    @Override
    public JToolBar build(Collection<Action> actions) {
        return createGroup( new JToolBarAdapter(new JToolBar()), actions );
    }

}