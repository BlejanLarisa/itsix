package builders;

import java.util.List;

import model.Bill;
import model.IBill;
import model.IBook;
import model.IDate;
import model.IInnerPublisher;
import model.IPayment;
import model.IPricing;
import model.ISupplier;

public class BillBuilder implements IBillBuilder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IBill build(IInnerPublisher publisher, List<IBook> books, ISupplier supplier, IPayment payment,
			IPricing pricing, String billCode, IDate buyDate) {

		System.out.println("supplier" + supplier + "payment" + payment + "term days" + payment.getTermDate() + "pricing"
				+ pricing);
		return new Bill(publisher, books, supplier, payment, pricing, billCode, buyDate);
	}

}
