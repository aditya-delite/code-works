package com.aditya.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.aditya.comparator.FareComparator;
import com.aditya.comparator.FareNFlightDurationComparator;
import com.aditya.model.Airline;

public class SearchEngine {
	public static Set<String> arrivalLocationSet = new HashSet<String>();
	public static Set<String> departureLocationSet = new HashSet<String>();

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File("D:/Users/aditya-gu/Eclipse-workspace/FlightSearchEngine/src/dataFiles");

		Map<String, List<Airline>> finalDataMap = new HashMap<>();
		File[] airlineFiles = file.listFiles();
		for (File airlineFile : airlineFiles) {
			String airlineName = airlineFile.getName().split("\\.")[0];
			switch (airlineName) {
			case "airfrance":
				List<Airline> airFranceList = populateAirLineList(airlineFile);
				finalDataMap.put("airfrance", airFranceList);
				break;
			case "lufthansa":
				List<Airline> lufthansaList = populateAirLineList(airlineFile);
				finalDataMap.put("lufthansa", lufthansaList);
				break;
			case "britishairways":
				List<Airline> britishAirwaysList = populateAirLineList(airlineFile);
				finalDataMap.put("britishairways", britishAirwaysList);
				break;
			}
		}
		searchFlight(finalDataMap);
	}

	private static void searchFlight(Map<String, List<Airline>> finalDataMap) throws ParseException {
		System.out.println("Enter departure location");
		Scanner sc = new Scanner(System.in);
		String departureLocation = sc.next();
		System.out.println("Enter arrival location");
		String arrivalLocation = sc.next();
		System.out.println("Enter flight date");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date flightDate = format.parse(sc.next());
		System.out.println("Enter output preference");
		String opPreference = sc.next();
		if (!departureLocationSet.contains(departureLocation) || !arrivalLocationSet.contains(arrivalLocation)) {
			System.out.println("Either arrival or departure location is invalid");
		}
		List<Airline> availableFlights = getAvailableFlights(finalDataMap, departureLocation, arrivalLocation,
				flightDate);
		if (opPreference == "Fare")
			Collections.sort(availableFlights, new FareComparator());
		else
			Collections.sort(availableFlights, new FareNFlightDurationComparator());
		System.out.println(availableFlights);
	}

	private static List<Airline> populateAirLineList(File airlineFile) throws IOException, ParseException {
		String str;
		int i = 0;
		List<Airline> airlineList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(airlineFile));
		while ((str = br.readLine()) != null) {
			i++;
			if (i == 1) {
				continue;
			}
			Airline airline = new Airline();
			String[] dataArr = str.split("\\|");
			airline.setFlightNumber(dataArr[0]);
			airline.setDepartureLocation(dataArr[1]);
			departureLocationSet.add(airline.getDepartureLocation());
			airline.setArrivalLocation(dataArr[2]);
			arrivalLocationSet.add(airline.getArrivalLocation());
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

			airline.setValidTill(format.parse(dataArr[3]));
			airline.setFlightTime(Integer.valueOf(dataArr[4]));
			airline.setFlightDuration(Double.valueOf(dataArr[5]));
			airline.setFare(Double.valueOf(dataArr[6]));
			airlineList.add(airline);
		}
		return airlineList;
	}

	private static List<Airline> getAvailableFlights(Map<String, List<Airline>> finalDataMap, String departureLocation,
			String arrivalLocation, Date flightDate) {
		List<Airline> filterAirlineList = new ArrayList<>();
		for (Map.Entry<String, List<Airline>> entry : finalDataMap.entrySet()) {
			List<Airline> airlineEntry = entry.getValue();
			for (Airline airLine : airlineEntry) {
				if (departureLocation.equalsIgnoreCase(airLine.getDepartureLocation())
						&& arrivalLocation.equalsIgnoreCase(airLine.getArrivalLocation())
						&& airLine.getValidTill().compareTo(flightDate) > 0)
					filterAirlineList.add(airLine);
			}
		}
		return filterAirlineList;
	}
}
