package Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashPasswords {
    private String hPassword;
    private byte[] salt;

    public HashPasswords(String password) {
        SecureRandom random = new SecureRandom();
        this.salt = new byte[16];
        random.nextBytes(this.salt);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            this.hPassword = Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found.", e);
        }
    }

    public String getHashedPassword() {
        return this.hPassword;
    }

    public boolean verifyPassword(String password) {
        HashPasswords aux = new HashPasswords(password);
        return this.hPassword.equals(aux.getHashedPassword());
    }
}
