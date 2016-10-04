package repository;

import java.util.List;

import model.IBill;
import model.IInnerPaymentPublisher;
import model.IInnerPublisher;
import model.IPaymentPublisher;
import model.IPublisher;

public interface IBillsRepository extends IPublisher, java.io.Serializable, IPaymentPublisher {

	void addBill(IBill bill);
	
	void payBill(IBill bill);

	List<IBill> getUnpaidBills();

	void setUnpaidBills(List<IBill> bills);

	void setPublisher(IInnerPublisher publisher);

	void decrementBillsPaymentTerm();

	void setPaymentPublisher(IInnerPaymentPublisher paymentPublisher);

	void setBillPublishers(List<IInnerPublisher> innerBillPublisher);

	List<IBill> getPaidBills();

}
