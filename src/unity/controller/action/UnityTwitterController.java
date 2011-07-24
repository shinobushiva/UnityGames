package unity.controller.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import unity.service.UnityGamesService;

public class UnityTwitterController extends JsonController {
    private UnityGamesService ug = new UnityGamesService();

    @Override
    protected Map<String, Object> handle() throws Exception {

        String urlstr = asString("url");
        urlstr = urlstr.replaceAll("-unitygames-", "&");
        Boolean contains = urlstr.contains("oauth_token");
        String[] split = urlstr.split("callback=");
        String callback = URLEncoder.encode(split[1].split("&")[0], "UTF-8");
        String signature =
            URLEncoder.encode(split[1].split("oauth_signature=")[1], "UTF-8");

        if (contains) {
            String oauth_token =
                split[1].split("oauth_token=")[1].split("&")[0];
            String oauth_verifier =
                split[1].split("oauth_verifier=")[1].split("&")[0];
            urlstr =
                split[0]
                    + "callback="
                    + callback
                    + "&oauth_token="
                    + oauth_token
                    + "&oauth_verifier="
                    + oauth_verifier
                    + "&oauth_signature="
                    + signature;
        } else {

            urlstr =
                split[0]
                    + "callback="
                    + callback
                    + "&oauth_signature="
                    + signature;
        }
        String result = "";
        System.out.println("ここ");
        System.out.println(urlstr);
         try {
         URL url = new URL(urlstr);
         URLConnection con = url.openConnection();
        
         BufferedReader reader =
         new BufferedReader(new InputStreamReader(
         con.getInputStream(),
         "utf-8"));
         String line;
         StringBuffer sb = new StringBuffer();
        
         while ((line = reader.readLine()) != null) {
         sb.append(line);
         }
         reader.close();
        
         result = sb.toString();
         response.setHeader("Access-Control-Allow-Origin", "*");
         } catch (MalformedURLException e) {
         e.printStackTrace();
         }
         System.out.println(result);
        
        Map<String, Object> map = new HashMap<String, Object>();
        
            map.put("oauth_token", ug.split(result, "oauth_token="));
            map.put(
                "oauth_token_secret",
                ug.split(result, "oauth_token_secret="));
            if (contains) {
                map.put("user_id", ug.split(result, "user_id="));
                map.put("screen_name", ug.split(result, "screen_name="));
            }
        System.out.println(map);
        return map;

    }
}
