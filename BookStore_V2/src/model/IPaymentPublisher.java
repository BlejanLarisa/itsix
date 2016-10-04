package model;

import view.IPaymentSubscriber;

public interface IPaymentPublisher extends java.io.Serializable {

	public void subscribe(IPaymentSubscriber subscriber);

	public void unsubscribe(IPaymentSubscriber subscriber);
}
