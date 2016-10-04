package model;

public class Date implements IDate {
	Integer days;

	public Date(Integer days) {
		this.days = days;
	}

	@Override
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	@Override
	public boolean isMoreRecentThan(IDate buyDate) {
		return buyDate.isOlderThan(days);
	}

	@Override
	public boolean isOlderThan(Integer days) {
		return this.days > days;
	}

	@Override
	public String toString() {
		return days.toString();
	}
}
