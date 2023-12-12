/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TienIch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author trinh
 */
public class Validator {

    private static final String EMAIL_REGEX
            = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern patternEmail = Pattern.compile(EMAIL_REGEX);

    private static final String PHONE_NUMBER_REGEX = "^\\d{10,15}$";
    private static final Pattern phoneNumberPattern = Pattern.compile(PHONE_NUMBER_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
