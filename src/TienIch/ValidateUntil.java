
package TienIch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateUntil {

    public static boolean validatePhone(String text) {
        // Biểu thức chính quy cho số điện thoại
        String phoneRegex = "\\d{3}-\\d{3}-\\d{4}";

        Pattern phonePattern = Pattern.compile(phoneRegex);

        Matcher phoneMatcher = phonePattern.matcher(text);

        // Kiểm tra xem chuỗi có chứa số điện thoại hoặc email không
        if (phoneMatcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean validateEmail(String text) {
        // Biểu thức chính quy cho địa chỉ email
        String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(text);
        if (emailMatcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
