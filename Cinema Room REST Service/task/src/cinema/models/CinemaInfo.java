package cinema.models;

import java.util.Set;

public class CinemaInfo {
    private int totalRows;
    private int totalColumns;
    private Set<Seat> availableSeats;

    public CinemaInfo(int totalRows, int totalColumns, Set<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Set<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Set<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
