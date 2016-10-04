package model;

import java.util.List;

public class OnTheRoadBill implements IBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OnTheRoadPayment payment;
	private IBill bill;

	public OnTheRoadBill(OnTheRoadPayment payment, IBill bill) {
		super();
		this.payment = payment;
		this.bill = bill;
		this.bill.setDuePayment(0.0);
	}

	@Override
	public IPayment getPayment() {
		return payment;
	}

	@Override
	public ISupplier getSupplier() {
		return bill.getSupplier();
	}

	@Override
	public IPricing getPricing() {
		return bill.getPricing();
	}

	@Override
	public boolean isTheSameAs(IBill bill) {
		return this.bill.isTheSameAs(bill);
	}

	@Override
	public List<IBook> getTakenBooksList() {
		return bill.getTakenBooksList();
	}

	@Override
	public List<IBook> getAvailableBooksList() {
		return bill.getAvailableBooksList();
	}

	@Override
	public void pay(Double amountOfMoney) {
		bill.pay(amountOfMoney);
	}

	public void setNextState(OnTermBill onTermBill) {
		bill = onTermBill;

	}

	@Override
	public Double getDuePayment() {
		return bill.getDuePayment();
	}

	@Override
	public void setDuePayment(Double duePayment) {
		bill.setDuePayment(duePayment);
		bill.getPublisher().notifyDebtSubscribers();

	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		bill.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		bill.unsubscribe(subscriber);
	}

	@Override
	public void setSupplier(ISupplier supplier) {
		bill.setSupplier(supplier);

	}

	@Override
	public void setPayment(IPayment value) {
		bill.setPayment(value);
	}

	@Override
	public void setPricing(IPricing value) {
		bill.setPricing(value);
	}

	@Override
	public boolean bookWasEliminated(String iSBN) {
		setDuePayment(getDuePayment() + getBookWithIsbn(iSBN).getPrice());
		return bill.bookWasEliminated(iSBN);

	}

	private IBook getBookWithIsbn(String iSBN) {
		for (IBook book : getAvailableBooksList())
			if (book.isSameAs(iSBN))
				return book;
		return null;
	}

	@Override
	public IInnerPublisher getPublisher() {
		return bill.getPublisher();
	}

	@Override
	public ITermDate getTermDate() {
		return bill.getTermDate();
	}

	@Override
	public void setTermDate(ITermDate date) {
		bill.setTermDate(date);
	}

	@Override
	public void decrementPaymentTerm() {
		bill.decrementPaymentTerm();
	}

	@Override
	public void setPublisher(IInnerPublisher publisher) {
		bill.setPublisher(publisher);
	}

	@Override
	public String getBillCode() {
		return bill.getBillCode();
	}

	@Override
	public void setBillCode(String billCode) {
		bill.setBillCode(billCode);
	}

	@Override
	public IDate getBuyDate() {
		return bill.getBuyDate();
	}

	@Override
	public boolean isMoreRecentThan(IBill x) {
		return bill.isMoreRecentThan(x);
	}

	@Override
	public boolean isOlderThan(IDate buyDate) {
		return bill.isOlderThan(buyDate);
	}

	@Override
	public void setBuyDate(IDate date) {
		bill.setBuyDate(date);
	}
}
