package org.example.utils;

import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidDateFormatException;
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
    public static void dateChecker(String date){
        String regex =  "^(0[1-9]|[12][0-9]|3[01])"+"(\\/| -)(0[1-9]|1[1,2])"+"(\\/| -)(19|20)\\d{2}$";
        //        "^(0[1-9]|[12][0-9]|3[01])"+"(\\/| -)(0[1-9]|1[1,2])"+"(\\/| -)(19|20)\\d{2}$";
//                "^(0[1-9]|[12][0-9]|3[01])" + "(-)(0[1-9]|1[1,2])" + "(-)(19|20)\\d{2}$";
        if(!Pattern.matches(regex,date)) throw new InvalidDateFormatException("Invalid date format");

    }

}
