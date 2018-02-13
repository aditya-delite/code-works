package com.aditya.comparator;

import java.util.Comparator;

import com.aditya.model.Airline;

public class FareNFlightDurationComparator implements Comparator<Airline> {

	@Override
	public int compare(Airline a1, Airline a2) {
		if (a1.getFare() > a2.getFare())
			return 1;
		else if (a1.getFare() < a2.getFare())
			return -1;
		else if (a1.getFare() == a2.getFare()) {
			if (a1.getFlightDuration() > a2.getFlightDuration())
				return 1;
			if (a1.getFlightDuration() < a2.getFlightDuration())
				return -1;
			else
				return 0;
		}
		return 0;

	}

}
