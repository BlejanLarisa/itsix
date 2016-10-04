package builders;

import java.util.List;

import model.IBill;
import model.IBook;
import model.IDate;
import model.IInnerPublisher;
import model.IPayment;
import model.IPricing;
import model.ISupplier;
import model.OnTheRoadBill;
import model.OnTheRoadPayment;

public class OnTheRoadPaymentBillBuilder implements IBillBuilder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IBillBuilder billBuilder;

	@Override
	public IBill build(IInnerPublisher publisher, List<IBook> books, ISupplier supplier, IPayment payment,
			IPricing pricing, String billCode, IDate buyDate) {
		billBuilder = new BillBuilder();
		IBill bill = billBuilder.build(publisher, books, supplier, payment, pricing, billCode, buyDate);
		return new OnTheRoadBill((OnTheRoadPayment) payment, bill);
	}
}
