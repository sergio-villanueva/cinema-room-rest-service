package cinema.models;

public class PurchaseError {
    private final String error;

    public PurchaseError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
