package builders;

import java.io.Serializable;
import java.util.List;

import model.IBill;
import model.IBook;
import model.IDate;
import model.IInnerPublisher;
import model.IPayment;
import model.IPricing;
import model.ISupplier;

public interface IBillBuilder extends Serializable {

	IBill build(IInnerPublisher publisher, List<IBook> books, ISupplier supplier, IPayment payment, IPricing pricing,
			String billCode, IDate buyDate);
}
