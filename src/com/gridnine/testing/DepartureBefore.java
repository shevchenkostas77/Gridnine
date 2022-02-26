package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartureBefore implements Filter {
    @Override
    public void filter(List<Flight> ListOfFlights) {
        LocalDateTime timeNow = LocalDateTime.now();
        List<Flight> result = new ArrayList<>();
        ListOfFlights.forEach(segments -> segments.getSegments().stream()
                .filter(segment ->
                        timeNow.isAfter(segment.getDepartureDate()))
                .forEach(segment -> result.add(segments)));

        System.out.println("1. Вылет до текущего момента времени: " + result);
    }
}