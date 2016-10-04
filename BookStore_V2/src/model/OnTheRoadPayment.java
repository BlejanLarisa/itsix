package model;

public class OnTheRoadPayment implements IPayment, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OnTheRoadPayment(ITermDate termDate) {
		super();
		this.termDate = termDate;
	}

	private ITermDate termDate;

	@Override
	public String toString() {
		return "On The Road Payment";
	}

	@Override
	public ITermDate getTermDate() {
		return termDate;
	}

	@Override
	public void setTermDate(ITermDate date) {
		this.termDate = new TermDate(Double.POSITIVE_INFINITY);
	}

	@Override
	public void decrementTermDays() {
		termDate.decrement();

	}
}
