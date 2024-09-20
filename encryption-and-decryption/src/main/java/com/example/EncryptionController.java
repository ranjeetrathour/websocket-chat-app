package com.example;

import com.example.crypto.Chipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/encrypt")
public class EncryptionController {

    @Autowired
    private Chipher chipher;

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String data) {
        try {
            return chipher.encrypt(data);
        } catch (GeneralSecurityException e) {
            return "Error encrypting data: " + e.getMessage();
        }
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String encryptedData) {
        try {
            return chipher.decrypt(encryptedData);
        } catch (GeneralSecurityException e) {
            return "Error decrypting data: " + e.getMessage();
        }
    }
}
