package com.example.pdfservice.service;

import com.example.pdfservice.entity.Certificate;
import com.example.pdfservice.exceptions.DataBaseException;
import com.example.pdfservice.utils.PdfGenerator;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PdfService {
    private PdfGenerator pdfGenerator;
    private CertificateService certificateService;

    public byte[] getPdfByCertId(@NonNull Long certId) throws IOException, DataBaseException {
        Optional <Certificate> certificate=certificateService.getById(certId);
        if(certificate.isPresent()){
            return  pdfGenerator.generatePdf(certificate.get());
        }else{
            throw new DataBaseException("Certificate with id="+certId+" doesn't exist");
        }
    }
}
