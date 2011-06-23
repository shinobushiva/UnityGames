package unity.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;


@Model(schemaVersion = 1)
public class RelationTag implements Serializable {
    public static final int STATE_NOW = 1;
    public static final int STATE_FINISHED = 0;
    private static final long serialVersionUID = 1L;

    
@Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }    
    
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private Key tag1;

    private Key tag2;

    private int relationCount;
    
//    @Attribute(lob = true)
    private Set<Key> games = new HashSet<Key>();
//    private Set<GameData> games = new HashSet<GameData>();

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
        RelationTag other = (RelationTag) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public void setTag1(Key tag1) {
        this.tag1 = tag1;
    }

    public Key getTag1() {
        return tag1;
    }

    public void setTag2(Key tag2) {
        this.tag2 = tag2;
    }

    public Key getTag2() {
        return tag2;
    }


    public void setRelationCount(int relationCount) {
        this.relationCount = relationCount;
    }

    public int getRelationCount() {
        return relationCount;
    }

    public void setGames(Set<Key> games) {
        this.games = games;
    }

    public Set<Key> getGames() {
        return games;
    }

}
