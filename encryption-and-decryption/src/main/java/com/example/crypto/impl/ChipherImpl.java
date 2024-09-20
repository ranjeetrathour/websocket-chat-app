package com.example.crypto.impl;

import com.example.crypto.Chipher;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Configuration
public class ChipherImpl implements Chipher {

    private final String SECRET_KEY = "12345678";
    private final String SALT = "uuhhdsiids!!!";
    private final String ALGORITHM = "AES";
    private final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private Cipher encrypteCipher;
    private Cipher decrptyCipher;


    @PostConstruct
    private void configureCrypto() throws Exception {
        var key = SECRET_KEY.getBytes();
        final var sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        final var secretKey = new SecretKeySpec(key,ALGORITHM);
        encrypteCipher = Cipher.getInstance(TRANSFORMATION);
        encrypteCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        decrptyCipher = Cipher.getInstance(TRANSFORMATION);
        decrptyCipher.init(Cipher.DECRYPT_MODE, secretKey,new IvParameterSpec(new byte[16]));
    }



    @Override
    public String encrypt(String rawData) throws GeneralSecurityException {
        String saltedData = rawData+SALT;
        return Base64.getEncoder().encodeToString(encrypteCipher.doFinal(saltedData.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decrypt(String encrypted) throws GeneralSecurityException {
        byte[] decryptedData = decrptyCipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decryptedData, StandardCharsets.UTF_8).replace(SALT, "");
    }


    @Override
    public boolean matches(String rawData, String encrypted) throws GeneralSecurityException {
       String d = decrypt(encrypted);
       return d.equals(rawData);
     }
}
