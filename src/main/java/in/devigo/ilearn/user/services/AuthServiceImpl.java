package in.devigo.ilearn.user.services;

import in.devigo.ilearn.exception.DuplicateResourceException;
import in.devigo.ilearn.security.CustomUserDetails;
import in.devigo.ilearn.security.JwtService;
import in.devigo.ilearn.user.dtos.AuthResponse;
import in.devigo.ilearn.user.dtos.LoginRequest;
import in.devigo.ilearn.user.dtos.RegisterRequest;
import in.devigo.ilearn.user.entities.Role;
import in.devigo.ilearn.user.entities.User;
import in.devigo.ilearn.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRepo.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email is already registered");
        }

        User user = new User();

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        Role role = request.getRole();

        if(role == null){
            role = Role.TRAINEE;
        }

        user.setRole(role);
        user.setActive(true);

        User saved = userRepo.save(user);

        CustomUserDetails userDetails =
                new CustomUserDetails(saved);

        String token =
                jwtService.generateToken(userDetails);

        return new AuthResponse(
                token,
                "Bearer",
                saved.getId(),
                saved.getEmail(),
                saved.getRole()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(userDetails);

        return new AuthResponse(
                token,
                "Bearer",
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getAuthorities()
                        .stream()
                        .map(authority ->
                                authority.getAuthority()
                        )
                        .findFirst()
                        .map(authority ->
                                Role.valueOf(
                                        authority.replace(
                                                "ROLE_", ""
                                        )
                                )
                        )
                        .orElseThrow()
        );
    }
}
