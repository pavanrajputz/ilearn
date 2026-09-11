package in.devigo.ilearn.security;

import in.devigo.ilearn.exception.ResourceNotFound;
import in.devigo.ilearn.user.entities.User;
import in.devigo.ilearn.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "User not found with email: " + username
                        )
                );

        return new CustomUserDetails(user);
    }
}
