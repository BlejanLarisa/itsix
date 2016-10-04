package repository;

import java.util.ArrayList;
import java.util.List;

import model.IInnerPaymentPublisher;
import model.IInnerPublisher;
import model.ISubscriber;
import model.PaymentPublisher;
import model.Publisher;
import view.IPaymentSubscriber;

public class ParseEmptyFile implements IParser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IBillsRepository parse() {
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);

		List<IPaymentSubscriber> paymentSubscribers = new ArrayList<>();
		IInnerPaymentPublisher paymentPublisher = new PaymentPublisher(paymentSubscribers);

		return new BillsRepository(publisher, paymentPublisher);
	}

}
