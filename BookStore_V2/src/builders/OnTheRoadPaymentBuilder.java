package builders;

import model.IPayment;
import model.ITermDate;
import model.OnTheRoadPayment;
import model.TermDate;

public class OnTheRoadPaymentBuilder implements IPaymentBuilder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build(Double days) {
		ITermDate date = new TermDate(Double.POSITIVE_INFINITY);
		return new OnTheRoadPayment(date);
	}

}
