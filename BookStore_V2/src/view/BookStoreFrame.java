package view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.IBookStoreController;
import model.IBook;
import model.IDate;
import model.IPayment;
import model.IPricing;
import model.ISupplier;
import repository.IBillsRepository;

public class BookStoreFrame extends JFrame implements IBookStoreFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IBookStoreController bookStoreController;

	private IBillsRepository billsRepository;

	private JPanel addNewBillPanel;
	private JPanel billsPanel;
	private JPanel billsTab;
	private JPanel buyBooksPanel;
	private JPanel buyBooksTab;
	private JPanel seeSelectedBillPanel;

	private JTabbedPane tabbedPane;

	// private IInnerPublisher publisher;

	/**
	 * Create the application.
	 */
	public BookStoreFrame(IBillsRepository billsRepository, IBookStoreController bookStoreControler) {
		this.bookStoreController = bookStoreControler;
		this.billsRepository = billsRepository;
		this.billsRepository.subscribe(this);
		initialize();

		updateBillsTable();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	private void initialize() {

		setBounds(100, 100, 450, 419);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 250);
		getContentPane().add(tabbedPane);

		buyBooksTab = new JPanel();

		/////////////////////////////////////////////////////////////
		buyBooksTab.setLayout(new CardLayout(0, 0));

		buyBooksPanel = new BuyBooksPanel(bookStoreController);
		buyBooksTab.add(buyBooksPanel, "name_1470806450237781");

		tabbedPane.addTab("Buy books", null, buyBooksTab, null);
		//////////////////////////////////////////////////////////////

		billsTab = new JPanel();
		tabbedPane.addTab("Bills", null, billsTab, null);
		billsTab.setLayout(new CardLayout(0, 0));

		billsPanel = new BillsPanel(billsRepository, bookStoreController);
		((IBillsPanel) billsPanel).setBillsTab(billsTab);

		billsTab.add(billsPanel, "name_1404825328511628");

		addNewBillPanel = new AddNewBillPanel();
		((IBillsPanel) billsPanel).setAddNewBillPanel(addNewBillPanel);
		((IAddNewBillPanel) addNewBillPanel).setBookStoreControler(bookStoreController);
		((IAddNewBillPanel) addNewBillPanel).setBillsTab(billsTab);
		((IAddNewBillPanel) addNewBillPanel).setBillsPanel(billsPanel);
		billsTab.add(addNewBillPanel, "name_1405082044400225");

		seeSelectedBillPanel = new SeeSelectedBillPanel();
		((IBillsPanel) billsPanel).setSeeSelectedBillPanel(seeSelectedBillPanel);
		((SeeSelectedBillPanel) seeSelectedBillPanel).setBillsPanel(billsPanel);
		((SeeSelectedBillPanel) seeSelectedBillPanel).setBillsTab(billsTab);
		((SeeSelectedBillPanel) seeSelectedBillPanel).setBookStoreController(bookStoreController);

		billsTab.add(seeSelectedBillPanel, "name_1483216752253609");

		JButton btnAddDays = new JButton("Add days");
		btnAddDays.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				billsRepository.decrementBillsPaymentTerm();

				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							ShowBillsTermFrame frame = new ShowBillsTermFrame(
									billsRepository/* , bookStoreController */);
							frame.setVisible(true);
							frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		btnAddDays.setBounds(10, 346, 89, 23);
		getContentPane().add(btnAddDays);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Closed");
				bookStoreController.save();
				e.getWindow().dispose();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBookStoreView#getSupplier()
	 */
	@Override
	public ISupplier getSupplier() {
		return ((IAddNewBillPanel) addNewBillPanel).getSupplier();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBookStoreView#getPricing()
	 */
	@Override
	public IPricing getPricingForNewBill() {
		return ((IAddNewBillPanel) addNewBillPanel).getPricingForNewBill();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IBookStoreView#getBillBooks()
	 */
	@Override
	public List<IBook> getBillBooksForNewBill() {
		return ((IAddNewBillPanel) addNewBillPanel).getBillBooksForNewBill();
	}

	@Override
	public void updateBillsTable() {
		((IBillsPanel) billsPanel).updateBillsTable();
	}

	@Override
	public IPayment getSelectedPayment() {
		return ((IBillsPanel) billsPanel).getSelectedPayment();
	}

	@Override
	public IPricing getSelectedPricing() {
		return ((IBillsPanel) billsPanel).getSelectedPricing();
	}

	@Override
	public ISupplier getSelectedSupplier() {
		return ((IBillsPanel) billsPanel).getSelectedSupplier();
	}

	@Override
	public void setSelectedBillPayment(String payment) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).setSelectedBillPayment(payment);
	}

	@Override
	public void setSelectedBillPricing(String pricing) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).setSelectedBillPricing(pricing);
	}

	@Override
	public void setSelectedBillSupplier(String supplier) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).setSelectedBillSupplier(supplier);
	}

	@Override
	public void showBill(List<IBook> books) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).fillBookTable(books);

	}

	@Override
	public String getSelectedBookTitle() {
		return ((ISeeSelectedBillPanel) seeSelectedBillPanel).getSelectedBookTitle();
	}

	@Override
	public String getSelectedBookAuthors() {
		return ((ISeeSelectedBillPanel) seeSelectedBillPanel).getSelectedBookAuthors();
	}

	@Override
	public String getSelectedBookISBN() {
		return ((SeeSelectedBillPanel) seeSelectedBillPanel).getSelectedBookISBN();
	}

	@Override
	public Integer getSelectedBookNoOfExemplaries() {
		return ((ISeeSelectedBillPanel) seeSelectedBillPanel).getSelectedBookNoOfExemplaries();
	}

	@Override
	public Double getSelectedBookPrice() {
		return ((ISeeSelectedBillPanel) seeSelectedBillPanel).getSelectedBookPrice();
	}

	@Override
	public Double getSelectedMoneyAmount() {
		return ((SeeSelectedBillPanel) seeSelectedBillPanel).getSelectedMoneyAmount();
	}

	@Override
	public void setSelectedBillDuePayment(Double duePayment) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).setSelectedBillDuePayment(duePayment);

	}

	@Override
	public void updateDebt() {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).updateDebt();

	}


	@Override
	public int getSelectedRowFromBillPanel() {
		return ((IBillsPanel) billsPanel).getSelectedRowFromBillPanel();
	}

	@Override
	public JPanel getBuyBooksPanel() {
		return buyBooksPanel;
	}

	@Override
	public Double getExpirationTerm() {
		return ((IAddNewBillPanel) addNewBillPanel).getExpirationDate();
	}

	@Override
	public void reset() {
		((IAddNewBillPanel) addNewBillPanel).reset();

	}

	@Override
	public String getBillCode() {
		return ((IAddNewBillPanel) addNewBillPanel).getBillCode();
	}

	@Override
	public IDate getBuyDate() {

		return ((IAddNewBillPanel) addNewBillPanel).getBuyDate();
	}

	@Override
	public void setSelectedBillBuyDate(IDate buyDate) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).setSelectedBillBuyDate(buyDate);
	}

	@Override
	public void setSelectedBillCode(String billCode) {
		((ISeeSelectedBillPanel) seeSelectedBillPanel).setSelectedBillCode(billCode);
	}

}
