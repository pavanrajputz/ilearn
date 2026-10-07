package in.devigo.ilearn.certificate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    @NotBlank(message = "Issuer is required")
    @Size(max = 200, message = "Issuer cannot exceed 200 characters")
    private String issuer;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    private LocalDate expiryDate;

    @Size(max = 200, message = "Credential ID cannot exceed 200 characters")
    private String credentialId;

    @Size(max = 500, message = "Credential URL cannot exceed 500 characters")
    private String credentialUrl;

    private String description;

    @Size(max = 500, message = "Certificate image URL cannot exceed 500 characters")
    private String certificateImageUrl;
}
