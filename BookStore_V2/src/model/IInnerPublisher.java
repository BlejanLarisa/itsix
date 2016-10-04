package model;

public interface IInnerPublisher extends IPublisher {

	void notifyRepositorySubscribers();

	void notifyDebtSubscribers();

}
