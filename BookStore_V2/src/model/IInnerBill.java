package model;

import java.util.List;

public interface IInnerBill {
	List<IBook> getTakenBooksList();

	ISupplier getSupplier();

	IPricing getPricing();

	boolean isTheSameAs(IBill bill);

	List<IBook> getAvailableBooksList();

}
