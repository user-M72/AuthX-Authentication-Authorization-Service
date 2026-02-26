package AuthX.Authorization_Service.config.properties;

import AuthX.Authorization_Service.exception.handler.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component  // ← добавить чтобы Spring видел
public class AuthenticationExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        // ✅ просто new вместо Builder
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                authException,
                request.getRequestURI(),   // ← getRequestURI() вместо getContextPath()
                HttpStatus.UNAUTHORIZED
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        OutputStream responseStream = response.getOutputStream();
        mapper.writeValue(responseStream, exceptionResponse);
        responseStream.flush();
    }
}