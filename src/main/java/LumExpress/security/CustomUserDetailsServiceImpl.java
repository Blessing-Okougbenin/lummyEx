package LumExpress.security;

import LumExpress.Data.Models.LumiExpressUser;
import LumExpress.services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LumiExpressUser foundUser = userService.getUsername(username);
        return new SecuredUser(foundUser);
    }
}
