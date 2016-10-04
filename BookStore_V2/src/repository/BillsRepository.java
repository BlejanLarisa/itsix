package repository;

import java.util.ArrayList;
import java.util.List;

import model.IBill;
import model.IInnerPaymentPublisher;
import model.IInnerPublisher;
import model.ISubscriber;
import view.IPaymentSubscriber;

public class BillsRepository implements IBillsRepository {

	private static final long serialVersionUID = -7949411026049514755L;
	
	private List<IBill> unpaidBills;
	private List<IBill> paidBills;
	
	private IInnerPublisher publisher;
	private IInnerPaymentPublisher innerPaymentPublisher;

	public BillsRepository(IInnerPublisher publisher, IInnerPaymentPublisher innerPaymentPublisher) {
		super();
		unpaidBills = new ArrayList<>();
		paidBills = new ArrayList<IBill>();
		this.publisher = publisher;
		this.innerPaymentPublisher = innerPaymentPublisher;
	}
	
	@Override
	public List<IBill> getPaidBills() {
		return paidBills;
	}

	public void setPaidBills(List<IBill> paidBills) {
		this.paidBills = paidBills;
	}

	@Override
	public void setPublisher(IInnerPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public List<IBill> getUnpaidBills() {
		return unpaidBills;
	}

	@Override
	public void setUnpaidBills(List<IBill> bills) {
		this.unpaidBills = bills;
	}

	@Override
	public void addBill(IBill bill) {

		insert(bill);
		for (IBill ibill : unpaidBills) {
			System.out.println(ibill.getPayment());
		}
		publisher.notifyRepositorySubscribers();

	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);
	}

	@Override
	public void decrementBillsPaymentTerm() {
		for (IBill iBill : unpaidBills) {
			System.out.println("supplier " + iBill.getSupplier() + "payment " + iBill.getPayment() + "term days "
					+ iBill.getTermDate() + "pricing " + iBill.getPricing());

			iBill.decrementPaymentTerm();

			System.out.println("supplier " + iBill.getSupplier() + "payment " + iBill.getPayment() + "term days "
					+ iBill.getTermDate() + "pricing " + iBill.getPricing());

			innerPaymentPublisher.notifyTermChangedSubscribers();
		}
	}

	@Override
	public void subscribe(IPaymentSubscriber subscriber) {
		innerPaymentPublisher.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(IPaymentSubscriber subscriber) {
		innerPaymentPublisher.unsubscribe(subscriber);
	}

	@Override
	public void setPaymentPublisher(IInnerPaymentPublisher paymentPublisher) {
		this.innerPaymentPublisher = paymentPublisher;
	}

	@Override
	public void setBillPublishers(List<IInnerPublisher> innerBillPublisher) {
		int index = 0;
		for (IBill iBill : unpaidBills) {
			iBill.setPublisher(innerBillPublisher.get(index));
			index++;
		}
	}

	private void insert(IBill x) {
		// loop through all elements
		int index = 0;
		for (IBill bill : unpaidBills) {
			// if the element you are looking at is smaller than x,
			// go to the next element
			if (bill.isMoreRecentThan(x))
				continue;
			// if the element equals x, return, because we don't add duplicates
			if (bill == x)
				return;
			// otherwise, we have found the location to add x
			unpaidBills.add(index, x);
			index++;
			return;

		}
		// we looked through all of the elements, and they were all
		// smaller than x, so we add ax to the end of the list
		unpaidBills.add(x);
	}

	@Override
	public void payBill(IBill bill) {
		paidBills.add(bill);
		unpaidBills.remove(bill);
		
	}

}
