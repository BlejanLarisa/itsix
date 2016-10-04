package model;

public interface IBill extends java.io.Serializable, IInnerBill, IPublisher {

	IPayment getPayment();

	void pay(Double amountOfMoney);

	public Double getDuePayment();

	void setDuePayment(Double duePayment);

	void setSupplier(ISupplier supplier);

	void setPayment(IPayment value);

	void setPricing(IPricing value);

	boolean bookWasEliminated(String iSBN);

	IInnerPublisher getPublisher();

	ITermDate getTermDate();

	void setTermDate(ITermDate date);

	void decrementPaymentTerm();

	void setPublisher(IInnerPublisher publisher);

	String getBillCode();

	void setBillCode(String billCode);

	IDate getBuyDate();

	boolean isMoreRecentThan(IBill x);

	boolean isOlderThan(IDate buyDate);

	void setBuyDate(IDate date);
}