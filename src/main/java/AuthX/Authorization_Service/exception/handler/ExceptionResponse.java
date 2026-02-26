package AuthX.Authorization_Service.exception.handler;

import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public record ExceptionResponse(
        int code,
        String status,
        String path,
        String message,
        String timestamp
) {
  // ← никаких лишних аннотаций
  public ExceptionResponse(Exception exception, String path, HttpStatus status) {
    this(
            findErrorCode(exception),
            String.format("%d %s", status.value(), status.getReasonPhrase()),
            path,
            exception.getMessage(),
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                    .withZone(ZoneId.of("UTC+5"))
                    .format(Instant.now())
    );
  }

  private static int findErrorCode(Exception e) {
    return ErrorCodes.resolve(e);
  }
}