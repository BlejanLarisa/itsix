package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.BillsTermsModel;
import model.IBill;
import repository.IBillsRepository;

public class ShowBillsTermPanel extends JPanel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane billsScrollPane;
	private JTable billsTable;
	private BillsTermsModel billsTermsModel;
	private IBillsRepository billsRepository;

	/**
	 * Create the panel.
	 */
	public ShowBillsTermPanel(IBillsRepository billsRepository) {

		this.billsRepository = billsRepository;
		List<IBill> bills = new ArrayList<>();
		billsTermsModel = new BillsTermsModel(bills);
		setLayout(null);

		billsScrollPane = new JScrollPane();
		billsScrollPane.setBounds(10, 11, 394, 216);
		add(billsScrollPane);

		billsTable = new JTable();
		billsTable.setModel(billsTermsModel);

		billsScrollPane.setViewportView(billsTable);

	}

	public void updateBillsTermTable() {
		billsTermsModel.setData(billsRepository.getUnpaidBills());
	}

}
