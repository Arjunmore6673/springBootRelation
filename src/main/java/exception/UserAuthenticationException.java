/**
 * 
 */
package exception;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author WeTech_Digital
 *
 */
@Component
@Scope("prototype")
public class UserAuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	String statusCode;

	String statusType;

	HttpStatus httpStatus;

	public UserAuthenticationException(String message, String statusCode, String statusType, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.statusType = statusType;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
