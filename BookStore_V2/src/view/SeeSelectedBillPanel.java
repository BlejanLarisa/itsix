package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.IBookStoreController;
import model.BooksTableModel;
import model.IBook;
import model.IDate;

public class SeeSelectedBillPanel extends JPanel implements ISeeSelectedBillPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane selectedBillScrollPane;

	private JTable selectedBillsTable;
	private BooksTableModel myTableModel;

	private JButton btnGoToBillsPage;

	private JLabel lblSelectedBillSupplier;
	private JLabel lblSelectedBillPayment;
	private JLabel lblSelectedBillPricing;

	private JPanel billsTab;
	private JPanel billsPanel;

	protected IBookStoreController bookStoreController;

	private JTextField moneyAmountTextField;
	private JTextField textFieldDebt;
	private JLabel lblShowBillCode;
	private JLabel lblDate;
	private JLabel lblShowdate;
	private JLabel lblBillCode;

	/**
	 * Create the panel.
	 */
	public SeeSelectedBillPanel() {
		List<IBook> books = new ArrayList<IBook>();
		myTableModel = new BooksTableModel(books);
		setLayout(null);
		selectedBillScrollPane = new JScrollPane();
		selectedBillScrollPane.setBounds(10, 55, 400, 63);
		add(selectedBillScrollPane);

		selectedBillsTable = new JTable();
		selectedBillScrollPane.setViewportView(selectedBillsTable);
		selectedBillsTable.setModel(myTableModel);
		selectedBillsTable.getColumnModel().getColumn(4).setPreferredWidth(109);
		btnGoToBillsPage = new JButton("Go to main bills page");
		btnGoToBillsPage.setBounds(10, 126, 168, 23);
		add(btnGoToBillsPage);

		lblSelectedBillSupplier = new JLabel("Supplier");
		lblSelectedBillSupplier.setBounds(217, 30, 77, 14);
		add(lblSelectedBillSupplier);

		lblSelectedBillPayment = new JLabel("Payment");
		lblSelectedBillPayment.setBounds(217, 11, 77, 14);
		add(lblSelectedBillPayment);

		lblSelectedBillPricing = new JLabel("Pricing");
		lblSelectedBillPricing.setBounds(304, 11, 70, 14);
		add(lblSelectedBillPricing);

		JButton btnPayMoneyAmount = new JButton("Pay amount of money");
		btnPayMoneyAmount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				bookStoreController.payAmountOfMoneyforBill(((BillsPanel) billsPanel).getBill());
				
				moneyAmountTextField.setText("");
			}
		});
		btnPayMoneyAmount.setBounds(244, 156, 175, 23);
		add(btnPayMoneyAmount);

		moneyAmountTextField = new DoubleJTextField();
		moneyAmountTextField.setBounds(333, 127, 86, 20);
		add(moneyAmountTextField);
		moneyAmountTextField.setColumns(10);

		JLabel lblIntroduceAmount = new JLabel("Introduce amount of money");
		lblIntroduceAmount.setBounds(188, 130, 135, 14);
		add(lblIntroduceAmount);

		JLabel lblDebt = new JLabel("Debt");
		lblDebt.setBounds(20, 160, 46, 14);
		add(lblDebt);

		textFieldDebt = new JTextField();
		textFieldDebt.setEditable(false);
		textFieldDebt.setBounds(69, 160, 86, 20);
		add(textFieldDebt);
		textFieldDebt.setColumns(10);

		lblShowBillCode = new JLabel("Show bill code");
		lblShowBillCode.setBounds(69, 11, 54, 14);
		add(lblShowBillCode);

		lblDate = new JLabel("Date");
		lblDate.setBounds(20, 36, 46, 14);
		add(lblDate);

		lblShowdate = new JLabel("ShowDate");
		lblShowdate.setBounds(69, 36, 49, 14);
		add(lblShowdate);

		lblBillCode = new JLabel("Bill code");
		lblBillCode.setBounds(10, 11, 56, 14);
		add(lblBillCode);

		btnGoToBillsPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(billsPanel);
			}

		});

	}

	private void changePanel(JPanel panel) {
		billsTab.removeAll();
		billsTab.repaint();
		billsTab.revalidate();

		billsTab.add(panel);
		billsTab.repaint();
		billsTab.revalidate();
	}

	@Override
	public void setSelectedBillPayment(String payment) {
		lblSelectedBillPayment.setText(payment);
	}

	@Override
	public void setSelectedBillPricing(String pricing) {
		lblSelectedBillPricing.setText(pricing);
	}

	@Override
	public void setSelectedBillSupplier(String supplier) {
		lblSelectedBillSupplier.setText(supplier);
	}

	@Override
	public void setBillsTab(JPanel billsTab) {
		this.billsTab = billsTab;
	}

	@Override
	public void setBillsPanel(JPanel billsPanel) {
		this.billsPanel = billsPanel;
	}

	@Override
	public void fillBookTable(List<IBook> books) {
		myTableModel.setData(books);
	}

	@Override
	public String getSelectedBookTitle() {
		return (String) selectedBillsTable.getValueAt(selectedBillsTable.getSelectedRow(), 0);
	}

	@Override
	public String getSelectedBookAuthors() {
		return (String) selectedBillsTable.getValueAt(selectedBillsTable.getSelectedRow(), 1);
	}

	@Override
	public String getSelectedBookISBN() {
		return (String) selectedBillsTable.getValueAt(selectedBillsTable.getSelectedRow(), 2);
	}

	@Override
	public Integer getSelectedBookNoOfExemplaries() {
		return (Integer) selectedBillsTable.getValueAt(selectedBillsTable.getSelectedRow(), 3);
	}

	@Override
	public Double getSelectedBookPrice() {
		return (Double) selectedBillsTable.getValueAt(selectedBillsTable.getSelectedRow(), 4);
	}

	public void setBookStoreController(IBookStoreController bookStoreController) {
		this.bookStoreController = bookStoreController;
	}

	@Override
	public Double getSelectedMoneyAmount() {
		String stringMoneyAmount = moneyAmountTextField.getText();
		if (stringMoneyAmount.isEmpty())
			return 0.0;
		return Double.parseDouble(stringMoneyAmount);
	}

	@Override
	public void setSelectedBillDuePayment(Double duePayment) {
		textFieldDebt.setText(duePayment.toString());
	}

	@Override
	public void updateDebt() {
		textFieldDebt.setText(((IBillsPanel) billsPanel).getBill().getDuePayment().toString());

	}

	

	@Override
	public void setSelectedBillBuyDate(IDate buyDate) {
		lblShowdate.setText(buyDate.getDays().toString());
	}

	@Override
	public void setSelectedBillCode(String billCode) {
		lblShowBillCode.setText(billCode);
	}

}
