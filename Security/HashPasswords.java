package Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * The HashPasswords class is responsible for hashing and verifying passwords
 * using the SHA-512 algorithm.
 */
public class HashPasswords {
    private String hPassword;
    private static byte[] salt;

    /**
     * Constructs a new HashPasswords object with the given password.
     * The password is hashed using the SHA-512 algorithm and stored as a base64
     * encoded string.
     *
     * @param password the password to be hashed
     */

    public static void GenerateSalt() {

        if (salt == null) {
            SecureRandom random = new SecureRandom();
            salt = new byte[16];
            random.nextBytes(salt);
        }
    }

    public HashPasswords(String password) {
        GenerateSalt();
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

    /**
     * Returns the hashed password.
     *
     * @return the hashed password
     */
    public String getHashedPassword() {
        return this.hPassword;
    }

    /**
     * Verifies if the given password matches the hashed password.
     *
     * @param password the password to be verified
     * @return true if the password matches the hashed password, false otherwise
     */
    public boolean verifyPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            String hPassword = Base64.getEncoder().encodeToString(hashedPassword);
            return this.hPassword.equals(hPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found.", e);
        }
    }
}
