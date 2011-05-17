package unity.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.arnx.jsonic.JSON;

public class CodeBlockUtilsTest {

    public static void main(String[] args) {

        String nl = "\n";
        String str =
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

        String[] split = str.split("<code>");

        List<String> al = new ArrayList<String>();
        for (String str2 : split) {
            String[] split2 = str2.split("</code>");
            for (String string : split2) {
                al.add(string.trim());
            }
        }

        Pattern p =
            Pattern.compile(
                "^>\\|([a-zA-Z#]*)\\|$([^\\|\\|<]*)^(\\|\\|<)$",
                Pattern.MULTILINE);

        ArrayList<String[]> result = new ArrayList<String[]>();

        for (String string : al) {
            Matcher matcher = p.matcher(string);
            // byte[] bytes = string.getBytes();
            // for (byte b : bytes) {
            // System.out.print(" " + b);
            // }
            // System.out.println();

            if (matcher.find()) {
                matcher.reset();

                while (matcher.find()) {
                    int num = matcher.groupCount();
                    // System.out.println("numGroup : " + num);

                    for (int i = 0; i <= num; i++) {
                        String group = matcher.group(i);
                        // System.out.println("" + i + ":" + group);
                    }

                    result.add(new String[] {
                        matcher.group(1).trim(),
                        matcher.group(2).trim() });
                }
            } else {
                if (string.trim().length() > 0)
                    result.add(new String[] { "text", string.trim() });
            }
        }

        String encode = JSON.encode(result);

        System.out.println(encode);

        // System.out.println(al);

    }
}
