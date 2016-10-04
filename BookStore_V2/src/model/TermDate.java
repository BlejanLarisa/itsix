package model;

public class TermDate implements ITermDate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double days;

	public TermDate(Double positiveInfinity) {
		this.days = positiveInfinity;
	}

	public Double getDays() {
		return days;
	}

	public void setDays(Double days) {
		this.days = days;
	}

	@Override
	public void decrement() {
		days--;
	}

	@Override
	public String toString() {
		return days + " days";
	}

}
