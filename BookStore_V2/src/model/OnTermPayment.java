package model;

public class OnTermPayment implements IPayment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OnTermPayment(ITermDate termDate) {
		super();
		this.termDate = termDate;
	}

	private ITermDate termDate;

	@Override
	public String toString() {
		return "On Term Payment";
	}

	@Override
	public ITermDate getTermDate() {
		return termDate;
	}

	@Override
	public void setTermDate(ITermDate date) {
		this.termDate = date;
	}

	@Override
	public void decrementTermDays() {
		termDate.decrement();
	}
}
