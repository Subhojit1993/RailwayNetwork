package com.Railway.Plotting;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboPopup;

public class ComboTest {

    private Vector<String> listSomeString = new Vector<String>();
    private JComboBox editableComboBox = new JComboBox(listSomeString);
    private JFrame frame;
    public final static Color COLOR_BORDER = new Color(122, 138, 153);

    public ComboTest() {
        listSomeString.add("row 1");
        listSomeString.add("row 2");
        listSomeString.add("row 3");
        listSomeString.add("row 4");

        editableComboBox.setEditable(true);
        editableComboBox.setBackground(Color.white);
        Object child = editableComboBox.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup) child;
        JList list = popup.getList();
        list.setBackground(Color.white);
        list.setSelectionBackground(Color.red);
        JTextField tf = ((JTextField) editableComboBox.getEditor().getEditorComponent());
        tf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, COLOR_BORDER));


        frame = new JFrame();
        frame.setLayout(new GridLayout(0, 1, 10, 10));
        frame.add(editableComboBox);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ComboTest ct = new ComboTest();
            }
        });
    }
}