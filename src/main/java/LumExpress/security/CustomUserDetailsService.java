package LumExpress.security;

import LumExpress.Data.Models.LumiExpressUser;
import LumExpress.exceptions.UserNotFoundException;
import LumExpress.services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        try {
            LumiExpressUser user = userService.getUsername(username);
            return new SecuredUserDetails(user);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
