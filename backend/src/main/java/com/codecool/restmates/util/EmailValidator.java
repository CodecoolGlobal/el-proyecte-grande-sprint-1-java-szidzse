package com.codecool.restmates.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /*
    The following restrictions are imposed in the email address’ local part by using this regex:
    - It allows numeric values from 0 to 9.
    - Both uppercase and lowercase letters from a to z are allowed.
    - Underscore "_", hyphen "-", and dot "." are allowed
    - Dot isn’t allowed at the start and end of the local part.
    - Consecutive dots aren’t allowed.
    - For the local part, a maximum of 64 characters are allowed.
    Restrictions for the domain part in this regular expression include:
    - It allows numeric values from 0 to 9.
    - We allow both uppercase and lowercase letters from a to z.
    - Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.
    - No consecutive dots.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
