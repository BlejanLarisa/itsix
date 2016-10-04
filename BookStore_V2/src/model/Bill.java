package model;

import java.util.ArrayList;
import java.util.List;

public class Bill implements IBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IBook> takenBooksList;
	private List<IBook> availableBooksList;
	private List<IBook> allBooks;
	private ISupplier supplier;
	private IPricing pricing;
	private IPayment payment;
	private Double duePayment;
	private String billCode;
	private IDate buyDate;

	private IInnerPublisher publisher;

	public Bill(IInnerPublisher publisher, List<IBook> booksList, ISupplier supplier, IPayment payment,
			IPricing pricing, String billCode, IDate buyDate) {
		super();
		this.publisher = publisher;
		this.takenBooksList = new ArrayList<>();
		this.availableBooksList = booksList;
		this.supplier = supplier;
		this.pricing = pricing;
		this.payment = payment;
		this.billCode = billCode;
		this.buyDate = buyDate;
	}

	@Override
	public String getBillCode() {
		return billCode;
	}

	@Override
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public List<IBook> getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(List<IBook> allBooks) {
		this.allBooks = allBooks;
	}

	@Override
	public Double getDuePayment() {
		return duePayment;
	}

	@Override
	public void setDuePayment(Double duePayment) {
		this.duePayment = duePayment;
	}

	@Override
	public ISupplier getSupplier() {
		return supplier;
	}

	@Override
	public IPricing getPricing() {
		return pricing;
	}

	@Override
	public boolean isTheSameAs(IBill bill) {
		return billCode.equals(bill.getBillCode());
	}

	@Override
	public List<IBook> getTakenBooksList() {
		return takenBooksList;
	}

	@Override
	public List<IBook> getAvailableBooksList() {
		return availableBooksList;
	}

	@Override
	public IPayment getPayment() {
		return payment;
	}

	@Override
	public void pay(Double amountOfMoney) {
		duePayment -= amountOfMoney;
		publisher.notifyDebtSubscribers();
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);
	}

	@Override
	public void setPublisher(IInnerPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);
	}

	@Override
	public void setSupplier(ISupplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public void setPayment(IPayment payment) {
		this.payment = payment;
	}

	@Override
	public void setPricing(IPricing pricing) {
		this.pricing = pricing;
	}

	@Override
	public boolean bookWasEliminated(String iSBN) {
		List<IBook> found = new ArrayList<IBook>();
		for (IBook book : availableBooksList) {
			if (book.isSameAs(iSBN)) {
				if (book.thereIsOnlyOneExemplary()) {
					found.add(book);
					getAvailableBooksList().removeAll(found);
					getTakenBooksList().addAll(found);
				} else
					book.eliminateOneExemplary();
				System.out.println("found");
				return true;

			}
		}
		return false;

	}

	@Override
	public IInnerPublisher getPublisher() {
		return publisher;
	}

	@Override
	public ITermDate getTermDate() {
		return payment.getTermDate();
	}

	@Override
	public void setTermDate(ITermDate date) {
		payment.setTermDate(date);
	}

	@Override
	public void decrementPaymentTerm() {
		payment.decrementTermDays();
	}

	@Override
	public IDate getBuyDate() {
		return buyDate;
	}

	@Override
	public boolean isMoreRecentThan(IBill x) {
		return x.isOlderThan(buyDate);
	}

	@Override
	public boolean isOlderThan(IDate buyDate) {

		return buyDate.isMoreRecentThan(this.buyDate);
	}

	@Override
	public void setBuyDate(IDate buyDate) {
		this.buyDate = buyDate;

	}

}
