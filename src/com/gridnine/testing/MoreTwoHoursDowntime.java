package com.gridnine.testing;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MoreTwoHoursDowntime implements Filter {
    @Override
    public void filter(List<Flight> ListOfFlights) {
        List<Flight> result = new ArrayList<>();
        ListOfFlights.forEach(segments -> {
            if (segments.getSegments().size() >= 2) {
                int countHours = 0;
                for (int i = 0; i < segments.getSegments().size() - 1; i++) {
                    countHours += (int) ChronoUnit.HOURS.between(segments.getSegments().get(i).getArrivalDate(),
                            segments.getSegments().get(i + 1).getDepartureDate());
                }
                if (countHours >= 2) {
                    result.add(segments);
                }
            }
        });
        System.out.println("3. Общее время, проведённое на земле превышает два часа: " + result);
    }
}