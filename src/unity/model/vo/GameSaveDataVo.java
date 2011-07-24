package unity.model.vo;

import java.io.Serializable;

public class GameSaveDataVo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2542599165955789412L;

    private String saveData;

    public String getSaveData() {
        return saveData;
    }

    public void setSaveData(String saveData) {
        this.saveData = saveData;
    }
}
