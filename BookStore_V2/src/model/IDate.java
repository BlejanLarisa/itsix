package model;

import java.io.Serializable;

public interface IDate extends Serializable {

	Integer getDays();

	boolean isMoreRecentThan(IDate buyDate);

	boolean isOlderThan(Integer days);

	@Override
	String toString();

}
