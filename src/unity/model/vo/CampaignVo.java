package unity.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import unity.model.GameData;

public class CampaignVo implements Serializable {
    public static final int STATE_NOW = 1;
    public static final int STATE_FINISHED = 0;
    private static final long serialVersionUID = 1L;

    private String title;

    private List<GameData> games = new ArrayList<GameData>();

    private Date start;

    private Date end;

    private int state;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGames(List<GameData> games) {
        this.games = games;
    }

    public List<GameData> getGames() {
        return games;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getEnd() {
        return end;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
