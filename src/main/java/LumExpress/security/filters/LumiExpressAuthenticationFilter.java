package LumExpress.security.filters;

import LumExpress.security.manager.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class LumiExpressAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final CustomAuthenticationManager lumiExpressAuthenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
//        TODO: 1. Create an Authentication object(whic contains auth credentials) that isn't authenticated
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info("email  => {} and password => {}",
                email,password);
        var authenticationobject = new UsernamePasswordAuthenticationToken(email,password);

//        TODO: 2.Use the authenticationManager to authenticate the user whos auth credentials are now contained
//         within the authentication object

//        TODO: 3. Get back the authentication object which has just been authenticated by the authentication manager
        Authentication authenticatedToken = lumiExpressAuthenticationManager.authenticate(authenticationobject);

//        TODO: 4.Store authentication in SecurityContext
        if(authenticatedToken != null) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticationobject);
            return authenticatedToken;
        }
        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    }
}
