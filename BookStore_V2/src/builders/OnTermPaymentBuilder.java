package builders;

import model.IPayment;
import model.ITermDate;
import model.OnTermPayment;
import model.TermDate;

public class OnTermPaymentBuilder implements IPaymentBuilder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build(Double days) {
		ITermDate date = new TermDate(days);
		return new OnTermPayment(date);
	}

}
