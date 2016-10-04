package model;

public interface IPublisher extends java.io.Serializable {

	public void subscribe(ISubscriber subscriber);

	public void unsubscribe(ISubscriber subscriber);
}
