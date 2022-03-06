package cinema.models;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class CinemaStat {
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public CinemaStat(Map<Seat,Ticket> data) {
        Map<Seat,Ticket> localCopy = Map.copyOf(data);
        var entries = localCopy.entrySet();

        currentIncome = 0;
        numberOfAvailableSeats = entries.size();
        numberOfPurchasedTickets = 0;

        for (var entry : entries) {
            Seat seat = entry.getKey();
            Ticket ticket = entry.getValue();
            if (ticket.isSold()) {
                currentIncome += seat.getPrice();
                numberOfAvailableSeats--;
                numberOfPurchasedTickets++;
            }
        }

    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }
}
