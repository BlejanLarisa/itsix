package model;

import java.util.List;

public class Publisher implements IInnerPublisher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient List<ISubscriber> subscribers;

	public Publisher(List<ISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		subscribers.remove(subscriber);

	}

	@Override
	public void notifyRepositorySubscribers() {
		for (ISubscriber subscriber : subscribers) {
			subscriber.updateBillsTable();
		}

	}

	@Override
	public void notifyDebtSubscribers() {
		for (ISubscriber subscriber : subscribers) {
			subscriber.updateDebt();
		}

	}

}