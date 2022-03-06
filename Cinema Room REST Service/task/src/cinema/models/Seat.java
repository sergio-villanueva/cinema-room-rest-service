package cinema.models;

public class Seat {
    private int row;
    private int column;
    private int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    @Override
    public boolean equals(Object obj) {
        // Edge Case: Same instances
        if (this == obj) { return true; }
        // Edge Case: Null or different type given
        if (obj == null || getClass() != obj.getClass()) { return false; }

        Seat seat = (Seat) obj;
        return row == seat.getRow() && column == seat.getColumn();
    }

    @Override
    public int hashCode() {
        return row;
    }

}
