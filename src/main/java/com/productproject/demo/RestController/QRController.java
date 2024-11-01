package com.productproject.demo.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productproject.demo.QRCreation.QRGenerator;

@RestController
@RequestMapping("/store")
public class QRController {
    @GetMapping("/QRCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("text") String text) {
        try {
            byte[] qrCode = QRGenerator.generateQRCode(text, 500, 500);  

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return ResponseEntity.ok().headers(headers).body(qrCode);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
