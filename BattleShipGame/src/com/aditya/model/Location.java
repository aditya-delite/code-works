package com.aditya.model;

/**
 * Location class
 * 
 * @author aditya-gu
 *
 */
public class Location {
	/*
	 * integer representation of x coordinate
	 */
	private int positionX;
	/*
	 * integer representation of y coordinate
	 */
	private int positionY;
	/*
	 * actual xy coordinate entered by user
	 */
	private String coordinates;

	public Location(int positionX, int positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public Location() {
		super();
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
}
