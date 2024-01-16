package org.example.utils;

import org.example.dto.request.RegisterRequest;
import org.example.exception.PasswordTooWeakException;

import java.util.regex.Pattern;

public class Verification {
    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        return email.matches(regex);
    }
    public static void passwordChecker(RegisterRequest registerRequest) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&-+=()])" + "(?=\\S+$).{8,20}$";
        if (!Pattern.matches(regex, registerRequest.getPassword()))
            throw new PasswordTooWeakException("Password too weak");
    }

}
