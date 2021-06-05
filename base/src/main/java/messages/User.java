package messages;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for get username and password.
 */
public class User implements Serializable {
    private String username;
    private String password;

    public User(String name, String pass) {
        username = name.trim();
        password = pass.trim();
    }

    /**
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Password.
     */
    public String getPassword() {
        return password;
    }

    public void hash(){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            BigInteger integers = new BigInteger(1, bytes);
            String newPassword = integers.toString(16);
            while (newPassword.length() < 32) {
                newPassword = "0" + newPassword;
            }
            password = newPassword;
        } catch (NoSuchAlgorithmException exception) {
            return;
        }
    }

    @Override
    public String toString() {
        return username + ":" + password;
    }

    public void update(User usr){
        username = usr.getUsername();
        password = usr.getPassword();
    }

    @Override
    public int hashCode() {
        return username.hashCode() + password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User) {
            User userObj = (User) obj;
            return username.equals(userObj.getUsername()) && password.equals(userObj.getPassword());
        }
        return false;
    }
}

