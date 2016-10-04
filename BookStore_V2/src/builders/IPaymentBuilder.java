package builders;

import java.io.Serializable;

import model.IPayment;

public interface IPaymentBuilder extends Serializable {

	IPayment build(Double days);
}
