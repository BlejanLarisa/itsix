package model;

public interface IPayment extends java.io.Serializable {

	@Override
	String toString();

	ITermDate getTermDate();

	void setTermDate(ITermDate date);

	void decrementTermDays();
}
