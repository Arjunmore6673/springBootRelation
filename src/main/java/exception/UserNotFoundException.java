package exception;

/**
 * Created by isuraksha3 on 1/25/2019.
 */

/**
 * Thrown when the user is not found
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
