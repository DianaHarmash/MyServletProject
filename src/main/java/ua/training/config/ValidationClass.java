package ua.training.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationClass {

    public boolean checkInputLogin(String login) {;
        Pattern pattern = Pattern.compile("^[0-9]{0,}[a-zA-Z]{4,}[_]{0,}[a-zA-Z]{0,9}[0-9]{0,}$");
        Matcher matcher = pattern.matcher(login);
        return matcher.find() ? true : false;
    }

    public boolean checkInputPassword(String password) {
        Pattern pattern = Pattern.compile("^[0-9]{0,}[a-zA-Z]{4,}[_]{0,}[a-zA-Z]{0,9}[0-9]{0,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find() ? true : false;
    }

}
