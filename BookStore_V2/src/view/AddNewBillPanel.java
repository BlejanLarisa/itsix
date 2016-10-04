package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.IBookStoreController;
import model.BooksTableModel;
import model.Date;
import model.IBook;
import model.IDate;
import model.IPricing;
import model.ISupplier;
import model.NormalPricing;
import model.RebatePricing;
import model.Supplier;

public class AddNewBillPanel extends JPanel implements IAddNewBillPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane addNewBillScrollPane;

	private JTable tableBooks;
	private BooksTableModel buybooksModel;

	private JLabel lblSupplier;
	private JLabel lblPricing;
	private JLabel lblPayment;

	private JButton btnAddBook;
	private JButton btnFinishBill;
	private IBookStoreController bookStoreController;

	private JPanel billsTab;
	private JPanel billsPanel;

	private JComboBox<IPricing> comboBoxPricing;
	private JComboBox<ISupplier> comboBoxSupplier;
	private ComboBoxModel<ISupplier> supplierModel;
	private ComboBoxModel<IPricing> pricingModel;

	private JRadioButton rdbtnPayOnTerm;
	private JRadioButton rdbtnDebtOnTheRoad;
	private ButtonGroup buttonGroup = new ButtonGroup();

	private JTextField textFieldExpirationTerm;
	private JTextField textFieldBillCode;
	private JLabel lblDate;
	private JTextField textFieldDate;

	/**
	 * Create the panel.
	 */
	public AddNewBillPanel() {
		setLayout(null);

		addNewBillScrollPane = new JScrollPane();
		addNewBillScrollPane.setBounds(10, 122, 400, 59);
		add(addNewBillScrollPane);

		tableBooks = new JTable();
		addNewBillScrollPane.setViewportView(tableBooks);

		List<IBook> books = new ArrayList<>();
		buybooksModel = new BooksTableModel(books);
		tableBooks.setModel(buybooksModel);

		tableBooks.getColumnModel().getColumn(4).setPreferredWidth(108);

		lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(15, 0, 81, 14);
		add(lblSupplier);

		lblPricing = new JLabel("Pricing");
		lblPricing.setBounds(77, 0, 85, 14);
		add(lblPricing);

		lblPayment = new JLabel("Payment");
		lblPayment.setBounds(186, 0, 86, 14);
		add(lblPayment);

		btnAddBook = new JButton("Add book ");
		btnAddBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				IBook book = bookStoreController.makeEmptyBook();

				buybooksModel.addRow(book);
			}
		});
		btnAddBook.setBounds(10, 192, 109, 23);
		add(btnAddBook);

		btnFinishBill = new JButton("Finish bill");
		btnFinishBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				tableBooks.editCellAt(-1, -1);
				bookStoreController.finishBill();

			}

		});
		btnFinishBill.setBounds(129, 192, 102, 23);
		add(btnFinishBill);

		comboBoxSupplier = new JComboBox<ISupplier>();
		supplierModel = new DefaultComboBoxModel<ISupplier>();
		comboBoxSupplier.setModel(supplierModel);
		comboBoxSupplier.addItem(new Supplier("Ab-Art"));
		comboBoxSupplier.addItem(new Supplier("Adenium"));
		comboBoxSupplier.addItem(new Supplier("Ad Libri"));
		comboBoxSupplier.addItem(new Supplier("Aula"));
		comboBoxSupplier.addItem(new Supplier("Bookyard"));
		comboBoxSupplier.addItem(new Supplier("Corint"));
		comboBoxSupplier.addItem(new Supplier("Dacia"));
		comboBoxSupplier.addItem(new Supplier("Deceneu"));
		comboBoxSupplier.addItem(new Supplier("Elis"));
		comboBoxSupplier.addItem(new Supplier("Hipocrate"));
		comboBoxSupplier.addItem(new Supplier("Junimea"));
		comboBoxSupplier.addItem(new Supplier("Libris"));
		comboBoxSupplier.addItem(new Supplier("Litera"));
		comboBoxSupplier.addItem(new Supplier("Minerva"));
		comboBoxSupplier.addItem(new Supplier("Teora"));
		comboBoxSupplier.setBounds(10, 25, 62, 20);
		add(comboBoxSupplier);

		comboBoxPricing = new JComboBox<IPricing>();
		pricingModel = new DefaultComboBoxModel<>();
		comboBoxPricing.setModel(pricingModel);
		comboBoxPricing.addItem(new RebatePricing());
		comboBoxPricing.addItem(new NormalPricing());
		comboBoxPricing.setBounds(77, 25, 102, 20);
		add(comboBoxPricing);

		rdbtnPayOnTerm = new JRadioButton("Pay on term");
		rdbtnPayOnTerm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textFieldExpirationTerm.setEnabled(true);
				bookStoreController.changeToOnTermPaymentBuilder();
				bookStoreController.changeToOnTermPaymentBillBuilder();

			}
		});
		rdbtnPayOnTerm.setSelected(true);
		buttonGroup.add(rdbtnPayOnTerm);
		rdbtnPayOnTerm.setBounds(186, 21, 109, 23);
		add(rdbtnPayOnTerm);

		rdbtnDebtOnTheRoad = new JRadioButton("Debt on the road");
		rdbtnDebtOnTheRoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldExpirationTerm.setEnabled(false);
				bookStoreController.changetoOnTheRoadPaymentBuilder();
				bookStoreController.changeToOnTheRoadPaymentBillBuilder();

			}
		});
		buttonGroup.add(rdbtnDebtOnTheRoad);
		rdbtnDebtOnTheRoad.setBounds(186, 47, 120, 23);
		add(rdbtnDebtOnTheRoad);

		JLabel lblExpirationTerm = new JLabel("Expiration Term");
		lblExpirationTerm.setBounds(300, 0, 102, 14);
		add(lblExpirationTerm);

		textFieldExpirationTerm = new IntegerJTextField();
		textFieldExpirationTerm.setBounds(301, 25, 86, 20);
		add(textFieldExpirationTerm);
		textFieldExpirationTerm.setColumns(10);

		JLabel lblBillCode = new JLabel("Bill code");
		lblBillCode.setBounds(10, 56, 56, 14);
		add(lblBillCode);

		textFieldBillCode = new IntegerJTextField();
		textFieldBillCode.setBounds(76, 56, 103, 20);
		add(textFieldBillCode);
		textFieldBillCode.setColumns(10);

		lblDate = new JLabel("Date");
		lblDate.setBounds(10, 81, 46, 14);
		add(lblDate);

		textFieldDate = new IntegerJTextField();
		textFieldDate.setBounds(77, 87, 102, 20);
		add(textFieldDate);
		textFieldDate.setColumns(10);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IAddNewBillPanel#setBillsPanel(javax.swing.JPanel)
	 */
	@Override
	public void setBillsPanel(JPanel billsPanel) {
		this.billsPanel = billsPanel;
	}

	@Override
	public void reset() {
		textFieldExpirationTerm.setText("");
		textFieldBillCode.setText("");
		textFieldDate.setText("");
		emptyTable();
		returnToBillsPanel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IAddNewBillPanel#setBillsTab(javax.swing.JPanel)
	 */
	@Override
	public void setBillsTab(JPanel billsTab) {
		this.billsTab = billsTab;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IAddNewBillPanel#setBookStoreControler(controller.
	 * IBookStoreController)
	 */
	@Override
	public void setBookStoreControler(IBookStoreController bookStoreControler) {
		this.bookStoreController = bookStoreControler;
	}

	protected void emptyTable() {

		buybooksModel.setData(new ArrayList<IBook>());
		System.out.println(tableBooks.getRowCount());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IAddNewBillPanel#getBillBooksForNewBill()
	 */
	@Override
	public List<IBook> getBillBooksForNewBill() {
		List<IBook> books = new ArrayList<>();
		for (int count = 0; count < buybooksModel.getRowCount(); count++) {

			IBook book = buybooksModel.getBookAt(count);

			books.add(book);
		}
		return books;

	}

	protected void returnToBillsPanel() {
		billsTab.removeAll();
		billsTab.repaint();
		billsTab.revalidate();

		billsTab.add(billsPanel);
		billsTab.repaint();
		billsTab.revalidate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IAddNewBillPanel#getSupplier()
	 */
	@Override
	public ISupplier getSupplier() {
		String supplier = comboBoxSupplier.getSelectedItem().toString();
		return new Supplier(supplier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IAddNewBillPanel#getPricingForNewBill()
	 */
	@Override
	public IPricing getPricingForNewBill() {
		IPricing pricing = (IPricing) comboBoxPricing.getSelectedItem();
		return pricing;
	}

	@Override
	public Double getExpirationDate() {
		if (textFieldExpirationTerm.getText().isEmpty())
			return 0.0;
		else
			return Double.parseDouble(textFieldExpirationTerm.getText());
	}

	@Override
	public String getBillCode() {
		return textFieldBillCode.getText();
	}

	@Override
	public IDate getBuyDate() {
		return new Date(Integer.parseInt(textFieldDate.getText()));
	}
}
