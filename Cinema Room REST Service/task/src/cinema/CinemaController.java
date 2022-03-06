package cinema;

import cinema.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class CinemaController {

    private final int totalRows = 9;
    private final int totalColumns = 9;
    // more writes than reads are expected
    // private Set<Seat> seats = Collections.synchronizedSet(new HashSet<>());
    private ConcurrentMap<Seat, Ticket> data;

    public CinemaController() {

        int size = Math.multiplyExact(totalRows, totalColumns);
        data = new ConcurrentHashMap<>(size + (int) Math.ceil(size * 0.25));

        // seating is one-based
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                Seat seat = new Seat(row, column);
                Ticket ticket = new Ticket(seat);
                data.put(seat, ticket);
            }
        }
    }

    @GetMapping(value = "/seats") // show all seats regardless of availability
    public CinemaInfo getSeats() {
        return new CinemaInfo(totalRows, totalColumns, data.keySet());
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody Seat seat) {

        // invalid row/column
        if (seat.getRow() > totalRows || seat.getRow() < 1 || seat.getColumn() > totalColumns || seat.getColumn() < 1) {
            String msg = "The number of a row or a column is out of bounds!";
            return new ResponseEntity<>(new PurchaseError(msg), HttpStatus.BAD_REQUEST);
        }

        Ticket ticket = data.get(seat);
        // unavailable ticket
        if (ticket.isSold()) {
            String msg = "The ticket has been already purchased!";
            return new ResponseEntity<>(new PurchaseError(msg), HttpStatus.BAD_REQUEST);
        }

        ticket.setSold(true);
        return new ResponseEntity<>(ticket, HttpStatus.OK);

    }

    @PostMapping(value = "/return")
    public ResponseEntity<Object> refundTicket(@RequestBody Map<String,String> token) {
        List<Ticket> ticketList = new ArrayList<>(data.values());
        Seat seat = null;
        for (Ticket ticket : ticketList) {
            if (ticket.getToken().equals(token.get("token")) && ticket.isSold()) {
                seat = ticket.getTicket();
                data.get(seat).setSold(false);
            }
        }

        // ticket never sold
        if (seat == null) {
            String msg = "Wrong token!";
            return new ResponseEntity<>(new PurchaseError(msg), HttpStatus.BAD_REQUEST);
        }

        // set up body response
        final Seat immutableSeat = seat;
        Object refund = new Object() {
          Seat returnedTicket;

            {
                returnedTicket = immutableSeat;
            }

            public Seat getReturnedTicket() {
                return returnedTicket;
            }
        };

        return new ResponseEntity<>(refund, HttpStatus.OK);
    }

    @PostMapping(value = "/stats")
    public ResponseEntity<Object> statistics(@RequestParam(required = false) String password) {
        if (password == null) {
            String msg = "The password is wrong!";
            return new ResponseEntity<>(new PurchaseError(msg), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(new CinemaStat(data), HttpStatus.OK);
    }

}
