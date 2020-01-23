package com.ids.server;

import javax.swing.*;

public class JTableExample {
	  public static void main(String[] args) {
	    Object[] column = {"One", "Two"};
	    Object[][] data = {{1, 2}, {3, 4}, {5, 6}};

	    JTable toDoTable = new JTable(data, column);
	    JScrollPane jpane = new JScrollPane(toDoTable);
	    JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    panel.add(jpane);
	    panel.setVisible(false);
	    frame.add(new JScrollPane(panel));
	    frame.setVisible(true);
	  }
	}