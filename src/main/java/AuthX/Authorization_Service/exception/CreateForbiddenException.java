package AuthX.Authorization_Service.exception;

public class CreateForbiddenException extends RuntimeException {
  public CreateForbiddenException(String message) {
    super(message);
  }

  public CreateForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }
}
