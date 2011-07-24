package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import unity.meta.MovieRankingMeta;
import unity.model.MovieRanking;

public class RankingService {

    public List<MovieRanking> movieRanking() {

        List<MovieRanking> asList =
            Datastore
                .query(MovieRanking.class)
                .sort(MovieRankingMeta.get().point.asc)
                .asList();
        return asList;
    }

}
