package com.example.crypto;

import java.security.GeneralSecurityException;

public interface Chipher {
    String encrypt(String rawData) throws GeneralSecurityException;

    String decrypt(String encrypted) throws GeneralSecurityException;

    boolean matches(String rawData, String encrypted) throws GeneralSecurityException;
}
