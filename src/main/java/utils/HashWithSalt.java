package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class HashWithSalt {
    private String hash;

    public HashWithSalt(String toHash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(toHash.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        this.hash = enc.encodeToString(hash);
    }

    public String getHash() {
        return hash;
    }

    public boolean checkHash(String hash){
        return this.hash.equals(hash);
    }
}
