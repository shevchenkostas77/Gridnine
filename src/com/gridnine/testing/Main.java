package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        DepartureBefore filterDepartureBefore = new DepartureBefore();
        FlightFiltration departureBefore = new FlightFiltration(flights, filterDepartureBefore);
        departureBefore.check();

        ArrivalBeforeDeparture filterArrivalBeforeDeparture = new ArrivalBeforeDeparture();
        FlightFiltration arrivalBeforeDeparture = new FlightFiltration(flights, filterArrivalBeforeDeparture);
        arrivalBeforeDeparture.check();

        MoreTwoHoursDowntime filterMoreTwoHoursDowntime = new MoreTwoHoursDowntime();
        FlightFiltration moreTwoHoursDowntime = new FlightFiltration(flights, filterMoreTwoHoursDowntime);
        moreTwoHoursDowntime.check();
    }
}
