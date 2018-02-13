package com.aditya.model;

import java.util.Date;

public class Airline {
	private String flightNumber;
	private String departureLocation;
	private String arrivalLocation;
	private Date validTill;
	private int flightTime;
	private double flightDuration;
	private double fare;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	@Override
	public String toString() {
		return "Airline [flightNumber=" + flightNumber + ", departureLocation=" + departureLocation
				+ ", arrivalLocation=" + arrivalLocation + ", flightTime=" + flightTime + ", flightDuration="
				+ flightDuration + ", fare=" + fare + "]";
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public double getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(double flightDuration) {
		this.flightDuration = flightDuration;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
}
