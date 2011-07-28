package unity.controller.cron;

import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.EveryDayGameRanking;
import unity.model.GameData;

public class EveryDayRankingController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        // firstSet
        // List<GameData> asList = Datastore.query(GameData.class).asList();
        //
        // for (GameData g : asList) {
        //
        // EveryDayGameRanking egr = new EveryDayGameRanking();
        // egr.setKey(Datastore.allocateId(
        // g.getKey(),
        // EveryDayGameRanking.class));
        // egr.setNowPoint(g.getPoint());
        // egr.setLastUpdatedTime(new Date());
        //
        // GlobalTransaction tx = Datastore.beginGlobalTransaction();
        // Datastore.put(egr);
        // tx.commit();
        //
        // }
        // List<EveryDayGameRanking> asList =
        // Datastore.query(EveryDayGameRanking.class).asList();
        //
        // for (EveryDayGameRanking e : asList) {
        // GameData g = Datastore.get(GameData.class, e.getKey().getParent());
        //
        // e.setLastPoint(e.getNowPoint());
        // e.setNowPoint(g.getPoint());
        // e.setDeltaPoint(e.getNowPoint() - e.getLastPoint());
        // e.setLastUpdatedTime(new Date());
        // GlobalTransaction tx = Datastore.beginGlobalTransaction();
        // Datastore.put(e);
        // tx.commit();
        //
        // }

        List<GameData> asList = Datastore.query(GameData.class).asList();

        for (GameData g : asList) {

            EveryDayGameRanking e =
                Datastore
                    .query(EveryDayGameRanking.class, g.getKey())
                    .asSingle();
            if (e != null) {
                e.setLastPoint(e.getNowPoint());
                e.setNowPoint(g.getPoint());
                e.setDeltaPoint(e.getNowPoint() - e.getLastPoint());
                e.setLastUpdatedTime(new Date());
                
            } else {
                e = new EveryDayGameRanking();
                e.setKey(Datastore.allocateId(
                    g.getKey(),
                    EveryDayGameRanking.class));
                e.setNowPoint(g.getPoint());
                e.setLastUpdatedTime(new Date());

            }
            save(e);
        }

        return null;
    }

    public void save(EveryDayGameRanking e) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(e);
        tx.commit();
    }
}
