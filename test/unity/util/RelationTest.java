package unity.util;

import java.util.Set;
import java.util.TreeSet;

public class RelationTest {

    public static void main(String[] args) {

        Set<String> sett = new TreeSet<String>();

        sett.add("tag1");
        sett.add("tag2");
        sett.add("tag3");
        Set<String> sett2 = new TreeSet<String>();
        sett2.add("tag3");
        sett2.add("tag4");
        sett2.add("tag5");
        System.out.println("sett"+sett);
        System.out.println("sett2"+sett2);

        Object[] array = sett2.toArray();
        for (Object s : array) {

            boolean c = sett.contains(s);
            if (c) {
                sett2.remove(s);
            }

        }

        
        System.out.println(sett);
        System.out.println(sett2);

        // Object[] array = sett.toArray();

        // String[] array = new String[] { sett.toString()};

        // Set<String> set = new TreeSet<String>();
        //
        // for (int i = 0; i < array.length; i++) {
        //
        // for (int j = 0; j < array.length; j++) {
        //
        // if (array[i] != array[j]) {
        // if (array[i].hashCode() <= array[j].hashCode())
        // set.add("" + array[i] + "&" + array[j]);
        // else
        // set.add("" + array[j] + "&" + array[i]);
        //
        // }
        // }
        //
        // }
        //
        // System.out.println(set);

    }

}
