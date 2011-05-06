package unity.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class Test2Controller extends Controller {

    @SuppressWarnings("unchecked")
    @Override
    public Navigation run() throws Exception {
        // String searchedResult = null;
        // URL url = new URL("http://localhost:8888/test?id=3398");
        // URLConnection con = url.openConnection();
        // con.setReadTimeout(30 * 10000000);
        // con.setConnectTimeout(30 * 10000000);
        // BufferedReader reader =
        // new BufferedReader(new InputStreamReader(
        // con.getInputStream(),
        // "utf-8"));
        // String line;
        // StringBuffer sb = new StringBuffer();
        //
        // while ((line = reader.readLine()) != null) {
        // sb.append(line);
        // }
        // reader.close();
        //
        // searchedResult = sb.toString();
        // // System.out.println(searchedResult);
        //
        // Map<String, Object> map =
        // (Map<String, Object>) JSON.decode(searchedResult);
        // List<Map<String, ?>> list = (List<Map<String, ?>>)
        // map.get("results");
        // for (Map<String, ?> map2 : list) {
        //
        // System.out.println(map2);
        //
        // }
        return forward("test.jsp");
    }
}
