package controller;

import java.util.ArrayList;
import java.util.List;

import builders.BookBuilder;
import builders.IBillBuilder;
import builders.IBookBuilder;
import builders.IPaymentBuilder;
import builders.OnTermPaymentBillBuilder;
import builders.OnTheRoadPaymentBillBuilder;
import model.BillsTableModel;
import model.IBill;
import model.IBook;
import model.IDate;
import model.IInnerPublisher;
import model.IPayment;
import model.IPricing;
import model.ISubscriber;
import model.ISupplier;
import model.Publisher;
import repository.IBillsRepository;
import repository.ParseObjectsFile;
import view.IBookStoreFrame;

public class BookStoreController implements IBookStoreController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IBillsRepository billsRepository;

	private IBookStoreFrame bookStoreFrame;

	private ParseObjectsFile parser;

	private List<ISubscriber> subscribers = new ArrayList<>();

	private IInnerPublisher publisher = new Publisher(subscribers);

	private IPaymentBuilder paymentBuilder;
	private IPaymentBuilder onTheRoadPaymentBuilder;
	private IPaymentBuilder onTermPaymentBuilder;

	private IBillBuilder billBuilder;
	private IBillBuilder onTheRoadPaymentBillBuilder;
	private IBillBuilder onTermPaymentBillBuilder;

	private IBookBuilder iBookBuilder;

	public BookStoreController(IBillsRepository repository, IPaymentBuilder onTermPayment,
			IPaymentBuilder onTheRoadPayment) {
		super();
		iBookBuilder = new BookBuilder();
		onTheRoadPaymentBillBuilder = new OnTheRoadPaymentBillBuilder();
		onTermPaymentBillBuilder = new OnTermPaymentBillBuilder();
		billBuilder = onTermPaymentBillBuilder;
		this.onTermPaymentBuilder = onTermPayment;
		this.onTheRoadPaymentBuilder = onTheRoadPayment;
		this.paymentBuilder = onTermPayment;
		parser = new ParseObjectsFile();
		this.billsRepository = repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.IBookController#setBookStoreFrame(view.BookStoreFrame)
	 */
	@Override
	public void setBookStoreFrame(IBookStoreFrame bookStoreFrame) {
		this.bookStoreFrame = bookStoreFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.IBookController#eliminateBook(java.lang.String)
	 */
	@Override
	public void addBookToPaidBooks(String iSBN) {
		System.out.println(billsRepository.getUnpaidBills().size());
		for (IBill bill : billsRepository.getUnpaidBills()) {
			if (bill.bookWasEliminated(iSBN) == true) {
				return;
			}
		}
		for (IBill bill : billsRepository.getPaidBills()) {
			if (bill.bookWasEliminated(iSBN) == true) {
				return;
			}
		}
		System.out.println(billsRepository.getPaidBills().size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.IBookController#addBillToRepository()
	 */
	@Override
	public void addBillToRepository() {
		ISupplier supplier = bookStoreFrame.getSupplier();
		IPayment payment = paymentBuilder.build(bookStoreFrame.getExpirationTerm());
		IPricing pricing = bookStoreFrame.getPricingForNewBill();
		String billCode = bookStoreFrame.getBillCode();
		IDate buyDate = bookStoreFrame.getBuyDate();
		List<IBook> books = bookStoreFrame.getBillBooksForNewBill();

		if (books.size() > 0) {
			IBill bill = billBuilder.build(publisher, books, supplier, payment, pricing, billCode, buyDate);
			bill.subscribe(bookStoreFrame);
			billsRepository.addBill(bill);
		}
	}

	@Override
	public IBill getBillFromTable(BillsTableModel tableModel) {

		IBill fBill = tableModel.getUserAt(bookStoreFrame.getSelectedRowFromBillPanel());
		fBill.subscribe(bookStoreFrame);
		return fBill;

	}

	@Override
	public void showBill(IBill bill) {
		bookStoreFrame.setSelectedBillPayment(bill.getPayment().toString());
		bookStoreFrame.setSelectedBillPricing(bill.getPricing().toString());
		bookStoreFrame.setSelectedBillSupplier((bill.getSupplier()).getName());
		bookStoreFrame.setSelectedBillDuePayment(bill.getDuePayment());
		bookStoreFrame.setSelectedBillBuyDate(bill.getBuyDate());
		bookStoreFrame.setSelectedBillCode(bill.getBillCode());
		List<IBook> bookList = getBooksFromBill(bill);
		bookStoreFrame.showBill(bookList);

	}

	private List<IBook> getBooksFromBill(IBill bill) {
		List<IBook> bookList = new ArrayList<IBook>();
		for (IBill iBill : billsRepository.getUnpaidBills())
			if (iBill.isTheSameAs(bill))
				bookList = iBill.getAvailableBooksList();

		return bookList;

	}

	@Override
	public void save() {
		parser.save(billsRepository);
	}

	@Override
	public void payAmountOfMoneyforBill(IBill bill) {
		Double moneyAmount = bookStoreFrame.getSelectedMoneyAmount();
		bill.pay(moneyAmount);
		if(bill.getDuePayment() <= 0.0 && bill.getAvailableBooksList().isEmpty()){
			billsRepository.payBill(bill);
		}
	}

	@Override
	public void changetoOnTheRoadPaymentBuilder() {
		paymentBuilder = onTheRoadPaymentBuilder;

	}

	@Override
	public void changeToOnTermPaymentBuilder() {
		paymentBuilder = onTermPaymentBuilder;
	}

	@Override
	public void finishBill() {
		addBillToRepository();

		bookStoreFrame.reset();
	}

	@Override
	public void changeToOnTheRoadPaymentBillBuilder() {
		billBuilder = onTheRoadPaymentBillBuilder;
	}

	@Override
	public void changeToOnTermPaymentBillBuilder() {
		billBuilder = onTermPaymentBillBuilder;
	}

	@Override
	public IBook makeEmptyBook() {
		return iBookBuilder.buildEmptyBook();
	}
}
