package org.deslre.utils;

import org.deslre.entity.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: VerifyUtils
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 21:27
 * Version: 1.0
 */
public class VerifyUtils {

    public static boolean verify(String regex, String value) {
        if (StringUtil.isEmpty(value)) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean verify(VerifyRegexEnum regex, String value) {
        return verify(regex.getRegex(), value);
    }
}
