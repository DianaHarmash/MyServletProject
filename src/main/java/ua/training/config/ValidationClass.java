package ua.training.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationClass {

    public static boolean checkInputLogin(String login) {;
        Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9@.\\-_]{3,15}$");
        Matcher matcher = pattern.matcher(login);
        return matcher.find() ? true : false;
    }

    public static boolean checkInputPassword(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_*~#@.\\-]{4,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find() ? true : false;
    }

    public static boolean checkInputHours(String password) {
        Pattern pattern = Pattern.compile("^[\\d]{1,2}[hHdm]$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find() ? true : false;
    }

}
