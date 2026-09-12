package in.devigo.ilearn.user.services;

import in.devigo.ilearn.user.dtos.AuthResponse;
import in.devigo.ilearn.user.dtos.LoginRequest;
import in.devigo.ilearn.user.dtos.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
