package com.elle.ellegui;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class CreateTableColumnModel extends DefaultTableColumnModel {

	boolean first = true;

	public CreateTableColumnModel() {

	}

	@Override
	public void addColumn(TableColumn tc) {
		if (first) {
			first = false;
			return;
		}
		tc.setMinWidth(30);
		super.addColumn(tc);
	}

}
