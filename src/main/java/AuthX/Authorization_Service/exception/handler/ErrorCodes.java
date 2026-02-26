package AuthX.Authorization_Service.exception.handler;

import AuthX.Authorization_Service.exception.*;

import java.util.NoSuchElementException;

public final class ErrorCodes {

  public static final int API_ERROR_CODE = 10;
  public static final int ALREADY_EXISTS_ERROR_CODE = 11;
  public static final int NOT_FOUND_ERROR_CODE = 12;
  public static final int NULL_POINTER_ERROR_CODE = 13;
  public static final int UNSUPPORTED_OPERATION_ERROR_CODE = 14;
  public static final int INVALID_ARGUMENT_ERROR_CODE = 15;
  public static final int INVALID_CREDENTIALS_ERROR_CODE = 16;
  public static final int INVALID_TOKEN_ERROR_CODE = 17;
  public static final int INVALID_OPERATION_ERROR_CODE = 18;
  private static final int INTERNAL_ERROR_CODE = 0;

  // ← добавить этот метод
  public static int resolve(Exception e) {
    if (e instanceof ApiException)                  return API_ERROR_CODE;
    if (e instanceof AlreadyExistsException)        return ALREADY_EXISTS_ERROR_CODE;
    if (e instanceof NotFoundException)             return NOT_FOUND_ERROR_CODE;
    if (e instanceof NoSuchElementException)        return NOT_FOUND_ERROR_CODE;
    if (e instanceof NullPointerException)          return NULL_POINTER_ERROR_CODE;
    if (e instanceof InvalidCredentialsException)   return INVALID_CREDENTIALS_ERROR_CODE;
    if (e instanceof InvalidTokenException)         return INVALID_TOKEN_ERROR_CODE;
//    if (e instanceof ForbiddenException)            return FORBIDDEN_ERROR_CODE;
//    if (e instanceof UnauthorizedException)         return UNAUTHORIZED_ERROR_CODE;
    if (e instanceof UnsupportedOperationException) return UNSUPPORTED_OPERATION_ERROR_CODE;
    if (e instanceof IllegalArgumentException)      return INVALID_ARGUMENT_ERROR_CODE;
    return INTERNAL_ERROR_CODE;
  }
}
