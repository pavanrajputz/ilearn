package in.devigo.ilearn.certificate.controller;

import in.devigo.ilearn.certificate.dto.CertificateRequest;
import in.devigo.ilearn.certificate.dto.CertificateResponse;
import in.devigo.ilearn.certificate.service.CertificateService;
import in.devigo.ilearn.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainee/certificates")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificateService service;
    private final SecurityUtils utils;

    @PostMapping
    public ResponseEntity<CertificateResponse> createCertificate(
            @Valid @RequestBody CertificateRequest request){
        Long userId = utils.getCurrentUserId();
        CertificateResponse response = service.createCertificate(userId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CertificateResponse>> getAllCertificates(){
        Long userId = utils.getCurrentUserId();

        return ResponseEntity.ok(
                service.getMyCertificates(userId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateResponse> getCertificateById(@PathVariable Long id){
        Long userId = utils.getCurrentUserId();
        return ResponseEntity.ok(
                service.getCertificateById(userId, id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateResponse> updateCertificate(
            @PathVariable Long id,
            @Valid @RequestBody CertificateRequest request
    ) {

        Long userId = utils.getCurrentUserId();

        return ResponseEntity.ok(
                service.updateCertificate(
                        userId,
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(
            @PathVariable Long id
    ) {

        Long userId = utils.getCurrentUserId();

        service.deleteCertificate(userId, id);

        return ResponseEntity.noContent().build();
    }
}
