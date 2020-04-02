package exception;

/**
 * Created by isuraksha3 on 1/25/2019.
 */

/**
 * Thrown when the request is unauthorised
 */

public class UnauthorisedRequestException extends RuntimeException {
    private String details;
    public UnauthorisedRequestException(String message) {
        super(message);

    }
}
