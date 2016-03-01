package com.elle.elle_gui.admissions;

import com.elle.elle_gui.presentation.*;

/**
 * 
 * @author Carlos Igreja
 */
public interface IAdminComponent {
    
    public abstract void setComponent(ELLE_GUI_Frame window);
    public abstract void setComponent(EditDatabaseWindow window);
    public abstract void setComponent(LogWindow window);
    public abstract void setComponent(LoginWindow window);
    public abstract void setComponent(ViewATradeWindow window);
}
