package unity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.arnx.jsonic.JSON;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;

public class GameDataService {

    public void save(GameData c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(c);
        tx.commit();

    }

    public GameData load(long id) {

        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, id));

    }

    public GameData addPoint(GameData g) {

        int point = g.getAccess() + g.getComment() * 3;

        g.setPoint(point);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();

        return null;
    }
    
    public String toCodeJson(String str) {

        try {
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
                    "^>\\|([a-zA-Z]*)\\|$([^\\|\\|<]*)^(\\|\\|<)$",
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
                        // int num = matcher.groupCount();
                        // System.out.println("numGroup : " + num);

                        // for (int i = 0; i <= num; i++) {
                        // String group = matcher.group(i);
                        // // System.out.println("" + i + ":" + group);
                        // }

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

            return encode;
        } catch (Exception e) {
            return str;
        }
    }
    
}
