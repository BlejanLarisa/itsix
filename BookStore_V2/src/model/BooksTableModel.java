package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BooksTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IBook> books;
	private String[] columnNames = { "Title", "Authors", "ISBN", "Price", "No. of exemplaries" };

	public BooksTableModel(List<IBook> books) {
		this.books = books;
		// new ArrayList<IBook>(books);
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void addRow(IBook rowData) {
		books.add(rowData);
		fireTableDataChanged();
		// fireTableRowsInserted(books.size() - 1, books.size() - 1);
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		IBook book = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			book.setTitle((String) value);
			break;
		case 1:
			book.setAuthorsAsString((String) value);
			break;
		case 2:
			book.setISBN((String) value);
			break;
		case 3:
			book.setPrice((Double) value);
			break;
		case 4:
			book.setNoOfExemplaries((Integer) value);
			break;
		}

	}

	@Override
	public boolean isCellEditable(int row, int columnIndex) {
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		IBook user = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = user.getTitle();
			break;
		case 1:
			value = user.getAuthorsAsString();
			break;
		case 2:
			value = user.getISBN();
			break;
		case 3:
			value = user.getPrice();
			break;
		case 4:
			value = user.getNoOfExemplaries();
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
	public IBook getBookAt(int row) {
		return books.get(row);
	}

	public void setData(List<IBook> books) {
		this.books = books;
		fireTableDataChanged();
	}

	private boolean isNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	private boolean isValidSignal(char ch, String text) {
		if ((text == null || "".equals(text.trim())) && ch == '-') {
			return true;
		}

		return false;
	}

}
