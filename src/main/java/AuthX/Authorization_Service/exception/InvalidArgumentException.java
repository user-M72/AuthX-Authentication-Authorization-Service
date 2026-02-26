package AuthX.Authorization_Service.exception;

public class InvalidArgumentException extends RuntimeException {
  public InvalidArgumentException(String message) {
    super(message);
  }

  public InvalidArgumentException(String message, Throwable cause) {
    super(message, cause);
  }
}
