package unity.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.arnx.jsonic.JSON;

public class CodeBlockUtilsTest {

    public static void main(String[] args) {

        String nl = "\n";
        String string =
            "" + "Hello"
                + nl
                + "<code>"
                + nl
                + ">|js|"
                + nl
                + "//javascript code"
                + nl
                + "//javascript code"
                + nl
                + "||<"
                + nl
                + ">|csharp|"
                + nl
                + "//csharp code"
                + nl
                + "||<"
                + nl
                + ">|c\\|"
                + nl
                + "//java code"
                + nl
                + "||<"
                + nl
                + "</code>"
                + nl
                + "SogeHoge";

        Pattern p =
            Pattern.compile(
                "^>\\|([a-zA-Z#]*)\\|$([^\\|\\|<]*)^(\\|\\|<)$",
                Pattern.MULTILINE);

        ArrayList<String[]> result = new ArrayList<String[]>();

        Matcher matcher = p.matcher(string);
        int offset = 0;

        if (matcher.find()) {
            matcher.reset();

            while (matcher.find()) {
                // int num = matcher.groupCount();
                // System.out.println("numGroup : " + num);

                // for (int i = 0; i <= num; i++) {
                // String group = matcher.group(i);
                // // System.out.println("" + i + ":" + group);
                // }
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

        // System.out.println(al);

    }
}
