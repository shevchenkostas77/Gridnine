package com.gridnine.testing;

import java.util.List;

public class FlightFiltration {
    private List<Flight> ListOfFlights;
    private Filter filter;

    public FlightFiltration(List<Flight> ListOfFlights, Filter filter) {
        this.filter = filter;
        this.ListOfFlights = ListOfFlights;
    }

    public void check() {
        filter.filter(ListOfFlights);
    }
}
