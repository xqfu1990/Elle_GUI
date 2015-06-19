package com.elle.ellegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

public class UserConnection {

    private static JFrame frame;
    private static Container c;
    private static JLabel url;
    private static JLabel user;
    private static JLabel pass;
    private static JLabel type;
    private static JTextField url_text;
    private static JTextField user_text;
    private static JTextField pass_text;
    private static JComboBox box;
    private static JButton connect;
    private static JButton cancel;

    public UserConnection() {
        String[] connect_type = new String[]{"Local", "AWS"};
        frame = new JFrame("Customize Connection");
        frame.setLocation(530, 300);
		//frame.setUndecorated(true);
        //frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        c = frame.getContentPane();
        c.setLayout(new GridLayout(4, 2));
		// type = new JLabel("Type");
        // box = new JComboBox(connect_type);
        // box.setSelectedIndex(0);

        url = new JLabel("Database URL");
        user = new JLabel("Username");
        pass = new JLabel("Password");
        url_text = new JTextField(20);
        user_text = new JTextField(20);
        pass_text = new JTextField(20);
        connect = new JButton("Set");
        cancel = new JButton("Cancel");
        setComponents();

        installConnect();
        installCancel();
        frame.pack();
        frame.setVisible(true);
    }

    public static void setComponents() {
		// c.add(type);
        // c.add(box);
        c.add(url);
        c.add(url_text);
        c.add(user);
        c.add(user_text);
        c.add(pass);
        c.add(pass_text);
        c.add(connect);
        c.add(cancel);
    }

    public static void installConnect() {
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    GUI.conn = DriverManager.getConnection(url_text.getText(),
                            user_text.getText(), pass_text.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }

            }
        });
    }

    public static void installCancel() {
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

}
