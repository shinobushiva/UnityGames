package unity.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.arnx.jsonic.JSON;

public class CodeBlockUtils {

    public static String toCodeJson(String string) {

        try {

            Pattern p =
                Pattern.compile(
                    "^>\\|([^\\|<>]*)\\|$(((?!^\\|\\|<).)*?)^(\\|\\|<)$",
                    Pattern.MULTILINE | Pattern.DOTALL);

            ArrayList<String[]> result = new ArrayList<String[]>();

            Matcher matcher = p.matcher(string);
            int offset = 0;

            if (matcher.find()) {
                matcher.reset();

                while (matcher.find()) {

                    String group = matcher.group(0);
                    String text =
                        string.substring(offset, string.indexOf(group)).trim();
                    offset = string.indexOf(group) + group.length();

                    if (text.length() > 0)
                        result.add(new String[] { "text", text });

                    result.add(new String[] {
                        matcher.group(1).trim(),
                        matcher.group(2).trim() });
                }

            }

            String text = string.substring(offset, string.length()).trim();
            if (text.length() > 0)
                result.add(new String[] { "text", text });

            String encode = JSON.encode(result);

            System.out.println(encode);

            return encode;
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }
}
