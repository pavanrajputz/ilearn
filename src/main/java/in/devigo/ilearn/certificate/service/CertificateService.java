package in.devigo.ilearn.certificate.service;

import in.devigo.ilearn.certificate.dto.CertificateRequest;
import in.devigo.ilearn.certificate.dto.CertificateResponse;

import java.util.List;

public interface CertificateService {

    CertificateResponse createCertificate(
            Long userId,
            CertificateRequest request
    );

    List<CertificateResponse> getMyCertificates(
            Long userId
    );

    CertificateResponse getCertificateById(
            Long userId,
            Long certificateId
    );

    CertificateResponse updateCertificate(
            Long userId,
            Long certificateId,
            CertificateRequest request
    );

    void deleteCertificate(
            Long userId,
            Long certificateId
    );
}
