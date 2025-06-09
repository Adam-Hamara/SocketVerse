package com.socketverse.backend.helper;
import java.security.SecureRandom;
import java.util.UUID;

public class SecureRandomGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static UUID generateSecureUUID() {
        byte[] randomBytes = new byte[16]; // 128 bits
        secureRandom.nextBytes(randomBytes);
        long msb = 0;
        long lsb = 0;

        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        }
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);
        }
        return new UUID(msb, lsb);
    }
}
