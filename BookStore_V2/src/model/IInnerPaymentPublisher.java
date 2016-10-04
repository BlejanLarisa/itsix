package model;

public interface IInnerPaymentPublisher extends IPaymentPublisher {
	void notifyTermChangedSubscribers();
}
