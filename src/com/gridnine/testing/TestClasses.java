package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Factory class to get sample list of flights.
 * Фабричный класс, чтобы получить примерный список рейсов
 */
class FlightBuilder {
    static List<Flight> createFlights() { // список со списками полетов;
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration // Обычный полет продолжительностью два часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight // Обычный многосегментный рейс
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past // Рейс, вылетающий в прошлом
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives // Рейс, который отправляется позже, чем прибывает
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
// _________________________________________

                //A flight departing in the past // Рейс, вылетающий в прошлом
                createFlight(threeDaysFromNow.minusDays(12), threeDaysFromNow),

                //A flight that departs before it arrives // Рейс, который отправляется позже, чем прибывает
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(9)),
// _________________________________________


                //A flight with more than two hours ground time // Полет продолжительностью более двух часов на земле
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time // Еще один рейс с более чем двумя часами наземного времени
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dates) { // создает список полетов;
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2); // default capacity - 10
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}

/**
 * Bean that represents a flight.
 * Класс, в котором хранятся информация о всех полетах;
 */
class Flight {
    private final List<Segment> segments; // переменная, которая содержит список всех полетов;

    Flight(final List<Segment> segs) { // конструктор класса, при создании обьета передается список полетов;
        segments = segs;
    }

    List<Segment> getSegments() {  // Геттер segments (списка полетов);
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
    // Стринговое представление обьекта, где с помощью потока stream и методов map и collect
    // cклеивает элементы потока в одну строку
}

/**
 * Bean that represents a flight segment.
 * Класс, в котором хранится информация о полете, а именно о дате и времени вылета и дате и времени прилета;
 */
class Segment {  // класс, в котором храниться информация о дате и времени вылета и прилета;
    private final LocalDateTime departureDate; // дата вылета тип LocalDateTime;

    private final LocalDateTime arrivalDate;   // дата прилета тип LocalDateTime;

    Segment(final LocalDateTime dep, final LocalDateTime arr) {
        departureDate = Objects.requireNonNull(dep); // requireNonNull - проверяет, что указанная ссылка на объект не является;
        arrivalDate = Objects.requireNonNull(arr);   // если Null - выбрасывает ошибку NullPointerException;
    }

    LocalDateTime getDepartureDate() { // Геттер даты вылета;
        return departureDate;
    }

    LocalDateTime getArrivalDate() {  // Геттер даты прилета;
        return arrivalDate;
    }

    @Override
    public String toString() { // Стринговое представления обьякта при вывыде в консоль;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt) + ']';
    }
}