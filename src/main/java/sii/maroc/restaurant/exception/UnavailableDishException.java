package sii.maroc.restaurant.exception;

public class UnavailableDishException extends RestaurantException {
    public UnavailableDishException(String message, Throwable cause) {
        super(message, cause);
    }
}
