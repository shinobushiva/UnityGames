package unity.model.api;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import unity.model.GameData;
import unity.model.User;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class GameSaveData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    @Attribute(lob = true)
    private String saveData;

    private String saveType;

    // @Attribute(persistent = false)
    // private InverseModelListRef<User, GameSaveData> userListRef = new
    // InverseModelListRef<User, GameSaveData>(User.class,
    // UserMeta.get().userRef.getName(), this);

    private ModelRef<User> userRef = new ModelRef<User>(User.class);

    private ModelRef<GameData> gameRef = new ModelRef<GameData>(GameData.class);

    /**
     * Returns the key.
     * 
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     * 
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     * 
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GameSaveData other = (GameSaveData) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public String getSaveData() {
        return saveData;
    }

    public void setSaveData(String saveData) {
        this.saveData = saveData;
    }

    public ModelRef<GameData> getGameRef() {
        return gameRef;
    }

    public String getSaveType() {
        return saveType;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    public ModelRef<User> getUserRef() {
        return userRef;
    }

    // public InverseModelListRef<User, GameSaveData> getUserListRef() {
    // return userListRef;
    // }

}
