package ELLE_GUI;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class CreateTableColumnModel_RowHeader extends DefaultTableColumnModel {

	boolean first = true;

	public CreateTableColumnModel_RowHeader() {

	}

	@Override
	public void addColumn(TableColumn tc) {
		if (first) {
			tc.setMaxWidth(30);
			super.addColumn(tc);
			first = false;
		}
	}

}
