package in.devigo.ilearn.user.contollers;

import in.devigo.ilearn.user.dtos.AuthResponse;
import in.devigo.ilearn.user.dtos.LoginRequest;
import in.devigo.ilearn.user.dtos.RegisterRequest;
import in.devigo.ilearn.user.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(
            @Valid @RequestBody RegisterRequest request
    ){
        AuthResponse authResponse = service.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(
            @Valid @RequestBody LoginRequest request
            ){
        AuthResponse authResponse = service.login(request);
        return ResponseEntity.ok(authResponse);
    }
}
