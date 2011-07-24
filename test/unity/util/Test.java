package unity.util;

public class Test {

    public static void main(String[] args) {
      
        String a = "unity.unity3d";
        int num = a.lastIndexOf(".");
        String b = a.substring(num+1);
        System.out.println(b);

    }
}
