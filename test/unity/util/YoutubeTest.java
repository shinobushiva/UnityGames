package unity.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.arnx.jsonic.JSON;

public class YoutubeTest {

    public static void main(String[] args) {

        String nl = "\n";
        String string =
            "" + "Hello"
                + nl
                + "http://www.youtube.com/watch?v=Lbmdr4gvliI"
                + nl
                + ">|Japanese|"
                + nl
                + "//javascript code<"
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
                + "SogeHoge";

        Pattern p =
            Pattern
                .compile(
                    ".*(youtu(?:\\.be|be\\.com)/(?:.*v(?:/|=)|(?:.*/)?)([a-zA-Z0-9-_]+)).*",
                    Pattern.DOTALL);


        Matcher matcher = p.matcher(string);
       System.out.println(matcher.matches());
       System.out.println(matcher.group(0));
       System.out.println(matcher.group(2));
    }
}
