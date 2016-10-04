package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.IBookStoreController;
import model.BillsTableModel;
import model.IBill;
import model.IPayment;
import model.IPricing;
import model.ISupplier;
import repository.IBillsRepository;

public class BillsPanel extends JPanel implements IBillsPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane billsScrollPane;
	private JTable billsTable;
	private BillsTableModel billsTableModel;

	private JButton addNewBillButton;
	private JPanel billsTab;
	private JPanel addNewBillPanel;
	private JPanel seeSelectedBillPanel;
	private JButton btnSeeSelectedBill;
	private IBookStoreController bookStoreControler;
	private IBillsRepository billsRepository;
	private IBill bill = null;

	/**
	 * Create the panel.
	 */
	public BillsPanel(IBillsRepository billsRepository, IBookStoreController bookStoreController) {
		this.bookStoreControler = bookStoreController;
		this.billsRepository = billsRepository;
		List<IBill> bills = new ArrayList<>();
		billsTableModel = new BillsTableModel(bills);
		setLayout(null);

		billsScrollPane = new JScrollPane();
		billsScrollPane.setBounds(10, 11, 399, 165);
		add(billsScrollPane);

		billsTable = new JTable();
		billsTable.setModel(billsTableModel);

		billsScrollPane.setViewportView(billsTable);

		addNewBillButton = new JButton("Add new bill");
		addNewBillButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				changePanel(addNewBillPanel);
				
			}
		});
		addNewBillButton.setBounds(10, 176, 104, 23);
		add(addNewBillButton);

		btnSeeSelectedBill = new JButton("See selected bill");
		btnSeeSelectedBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (billsTable.getSelectedRow() != -1) {

					bill = bookStoreControler.getBillFromTable(billsTableModel);

					changePanel(seeSelectedBillPanel);

					bookStoreControler.showBill(bill);
				} else {
					System.out.print("Null pointer exception");

				}

			}

		});
		btnSeeSelectedBill.setBounds(124, 176, 127, 23);
		add(btnSeeSelectedBill);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#updateBillsTable()
	 */
	@Override
	public void updateBillsTable() {

		billsTableModel.setData(billsRepository.getUnpaidBills());
	}

	private void changePanel(JPanel panel) {
		billsTab.removeAll();
		billsTab.repaint();
		billsTab.revalidate();

		billsTab.add(panel);
		billsTab.repaint();
		billsTab.revalidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#getSelectedSupplier()
	 */
	@Override
	public ISupplier getSelectedSupplier() {
		return (ISupplier) billsTable.getValueAt(billsTable.getSelectedRow(), 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#getSelectedPayment()
	 */
	@Override
	public IPayment getSelectedPayment() {
		return (IPayment) billsTable.getValueAt(billsTable.getSelectedRow(), 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#getSelectedPricing()
	 */
	@Override
	public IPricing getSelectedPricing() {
		return (IPricing) billsTable.getValueAt(billsTable.getSelectedRow(), 2);
	}

	@Override
	public IBill getBill() {
		return bill;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#setSeeSelectedBillPanel(javax.swing.JPanel)
	 */
	@Override
	public void setSeeSelectedBillPanel(JPanel seeSelectedBillPanel) {
		this.seeSelectedBillPanel = seeSelectedBillPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#setAddNewBillPanel(javax.swing.JPanel)
	 */
	@Override
	public void setAddNewBillPanel(JPanel addNewBillPanel) {
		this.addNewBillPanel = addNewBillPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBillsPanel#setBillsTab(javax.swing.JPanel)
	 */
	@Override
	public void setBillsTab(JPanel billsTab) {
		this.billsTab = billsTab;
	}

	@Override
	public int getSelectedRowFromBillPanel() {
		return billsTable.getSelectedRow();
	}

}
