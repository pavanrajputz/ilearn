package in.devigo.ilearn.user.dtos;

import in.devigo.ilearn.user.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String email;
    private Role role;
}
