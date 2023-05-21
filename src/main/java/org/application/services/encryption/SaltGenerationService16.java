package org.application.services.encryption;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SaltGenerationService16{
    SecureRandom random = new SecureRandom();

    public byte[] getSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
