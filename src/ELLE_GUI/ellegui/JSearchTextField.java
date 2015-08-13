package ELLE_GUI.ellegui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * A text field with search symbol painted to indicate 
 * that it is used as search field 
 * 
 * @author Eugene Ryzhikov
 *
 */
public class JSearchTextField extends JTextField {

    private static final String ICON_NAME = "imag_11.png";
    private static final long serialVersionUID = 1L;
    
    private static ImageIcon icon;

    private static Image getScaledImage( int size ) {
        
        if (icon == null) {
            icon = new ImageIcon( ICON_NAME);
        }
        return new ImageIcon(icon.getImage().getScaledInstance( size, size, Image.SCALE_SMOOTH )).getImage();
    }
    
    private static int PAD = 4;
    private static int PAD2 = PAD*2;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int size = getHeight()-PAD2;
        g.drawImage( getScaledImage(size), getWidth()-size-PAD, PAD, null);
    }

}