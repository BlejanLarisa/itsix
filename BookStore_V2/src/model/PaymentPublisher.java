package model;

import java.util.List;

import view.IPaymentSubscriber;

public class PaymentPublisher implements IInnerPaymentPublisher {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient List<IPaymentSubscriber> subscribers;

	@Override
	public void notifyTermChangedSubscribers() {
		for (IPaymentSubscriber subscriber : subscribers) {
			subscriber.updateTerm();
		}
	}

	public PaymentPublisher(List<IPaymentSubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public void subscribe(IPaymentSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void unsubscribe(IPaymentSubscriber subscriber) {
		subscribers.remove(subscriber);

	}
}
