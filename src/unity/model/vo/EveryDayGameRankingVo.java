package unity.model.vo;

import java.io.Serializable;

import org.slim3.datastore.ModelRef;

import unity.model.GameData;

public class EveryDayGameRankingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private int deltaPoint;

    private ModelRef<GameData> gameRef = new ModelRef<GameData>(GameData.class);

    public int getDeltaPoint() {
        return deltaPoint;
    }

    public void setDeltaPoint(int deltaPoint) {
        this.deltaPoint = deltaPoint;
    }

    public ModelRef<GameData> getGameRef() {
        return gameRef;
    }

}
