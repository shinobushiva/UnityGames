package unity.controller.user.action;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import twitter4j.Twitter;
import unity.meta.UserMeta;

public class WebUrlRegisterController extends JsonController {

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        // ajaxで行う。登録・変更・削除すべてここで

        String webUrl = asString("webUrl");

        // 自分のアカウント
        Twitter twitter = (Twitter) sessionScope("twitter");

        // Userモデル取得
        unity.model.User uk =
            Datastore
                .query(unity.model.User.class)
                .filter(UserMeta.get().userId.equal(twitter.getId()))
                .asSingle();
        //webUrlをセット
        uk.setWebUrl(webUrl);
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(uk);
        tx.commit();

        map.put("webUrl", uk.getWebUrl());

        return map;
    }
}
