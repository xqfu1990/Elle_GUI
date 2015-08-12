package ELLE_GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFileChooser {

	private JFrame frame;
	private JFileChooser fc;
	private JList list;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel label;

	public MyFileChooser() {
		fc = new JFileChooser();
		fc.setApproveButtonText("Load");
		removeComponents();
		int val = fc.showOpenDialog(frame);
		if (val == JFileChooser.APPROVE_OPTION)
			JOptionPane.showMessageDialog(fc, "Test pass");
		
	}
	
	public void installLoadButton() {
		fc.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
					System.out.println("clicked");
				}
			}
		});
	}

	public void removeComponents() {
		// int count = fc.getComponentCount();
		Component[] component = fc.getComponents();
		for (Component c1 : component) {
			if (c1 instanceof JPanel) {
				for (Component c2 : ((JPanel) c1).getComponents()) {
					if (c2 instanceof JPanel) {
						for (Component c3 : ((JPanel) c2).getComponents()) {
							if (c3 instanceof JLabel) {
								if (((JLabel) c3).getText()
										.equals("File Name:"))
									c3.getParent().getParent()
											.remove(c3.getParent());
								else if (((JLabel) c3).getText().equals(
										"Files of Type:")) {
									((JLabel) c3).setText("Table");
								}
							} else if (c3 instanceof JComboBox) {
								Container container = c3.getParent();
								container.remove(c3);
								String[] tables = new String[] { "Trades",
										"Positions", "IB_8949", "TL_8949" };
								JComboBox c4 = new JComboBox(tables);
								((JComboBox) c4).setSelectedIndex(0);
								container.add(c4);
							}
						}
					}
				}
			}
		}
	}

}
