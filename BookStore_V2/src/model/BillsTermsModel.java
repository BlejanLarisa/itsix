package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BillsTermsModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IBill> bills;
	private String[] columnNames = { "Bill", "Expiration date" };

	public BillsTermsModel(List<IBill> bills) {
		this.bills = new ArrayList<IBill>(bills);
	}

	@Override
	public int getRowCount() {
		return bills.size();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			bills.get(rowIndex).setSupplier((ISupplier) value);
			break;
		case 1:
			bills.get(rowIndex).setTermDate((ITermDate) value);
			break;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		IBill bill = bills.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = bill.getSupplier();
			break;
		case 1:
			value = bill.getTermDate();
			break;
		}
		return value;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();

	}

	/*
	 * Override this if you want the values to be editable...
	 * 
	 * @Override public void setValueAt(Object aValue, int rowIndex, int
	 * columnIndex) { //.... }
	 */

	/**
	 * This will return the user at the specified row...
	 * 
	 * @param row
	 * @return
	 */
	public IBill getUserAt(int row) {
		return bills.get(row);
	}

	public void addRow(IBill rowData) {
		bills.add(rowData);
		fireTableRowsInserted(bills.size() - 1, bills.size() - 1);
	}

	public void setData(List<IBill> bills) {
		this.bills = bills;
	}

}
