package ua.training.config;

import ua.training.model.dao.impl.JDBCActivityDao;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordEncode {
    private static final Logger logger = Logger.getLogger(String.valueOf(PasswordEncode.class));

    public String encode(String text) {
        try {
            return getEncode(text);
        } catch (Exception ex) {
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return null;
    }

    private String getEncode(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
