package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class ArrivalBeforeDeparture implements Filter {
    @Override
    public void filter(List<Flight> ListOfFlights) {
        List<Flight> result = new ArrayList<>();
        ListOfFlights.forEach(segments -> segments.getSegments().stream()
                .filter(segment ->
                        segment.getArrivalDate().isBefore(segment.getDepartureDate()))
                .forEach(segment -> result.add(segments)));

        System.out.println("2. Cегменты с датой прилёта раньше даты вылета: " + result);
    }
}