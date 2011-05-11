package unity.model.api;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import unity.model.Tag;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    // ０ゲームID
    // １ゲーム名 gameName
    // ２サムネ画像のURL thumbNail
    // ３登録タグ（固定タグ） fixTag
    // ４登録タグ（タグ） tag
    // ５アクセス数 access
    // ６コメント数 comment
    // ７ゲーム説明 explanation
    // ８操作説明 openation
    // ９投稿者(名前のみ） user
    // １０ゲームデータURL gameUrl
    // １１投稿日 entry

    private String gameId;
    private String gameName;
  
    private String thumbNail;
    
    private String game;
    
    @Attribute(lob = true)
    private Set<Tag> fixTags = new HashSet<Tag>();
    
    @Attribute(lob = true)
    private Set<Tag> tags = new HashSet<Tag>();
    
    private Date entry;
    
    private Date lastDate;
    
    private int access;
    
    private int comments;

    @Attribute(lob = true)
    private String explanation;
    
    @Attribute(lob = true)
    private String operations;
    
    @Attribute(lob = true)
    private String code;
    
    private String user;

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
        Game other = (Game) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setFixTags(Set<Tag> fixTags) {
        this.fixTags = fixTags;
    }

    public Set<Tag> getFixTags() {
        return fixTags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Date getEntry() {
        return entry;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getAccess() {
        return access;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getComments() {
        return comments;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getOperations() {
        return operations;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

}
