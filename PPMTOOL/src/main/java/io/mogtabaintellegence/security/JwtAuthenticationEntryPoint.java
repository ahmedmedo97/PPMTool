package io.mogtabaintellegence.security;

import com.google.gson.Gson;
import io.mogtabaintellegence.exceptions.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Interface 3 on 2/21/2020.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(jsonLoginResponse);

    }
}
