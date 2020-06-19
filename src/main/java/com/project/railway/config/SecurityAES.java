package com.project.railway.config;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class SecurityAES {

    private static SecretKeySpec secretKey;

    private static byte[] key;

    public static void setKey(String myKey){
        MessageDigest sha = null;
        try{
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
    }

    public static String encrypt(String stringToEncrypt, String secret){
        try{
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes("UTF-8")));
        }
        catch(IllegalBlockSizeException ex){
            ex.printStackTrace();
        }
        catch(BadPaddingException ex){
            ex.printStackTrace();
        }
        catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        catch(InvalidKeyException ex){
            ex.printStackTrace();
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        catch(NoSuchPaddingException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String stringToDecrypt, String secret){
        try{
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        }
        catch(IllegalBlockSizeException ex){
            ex.printStackTrace();
        }
        catch(BadPaddingException ex){
            ex.printStackTrace();
        }
        catch(InvalidKeyException ex){
            ex.printStackTrace();
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        catch(NoSuchPaddingException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
