package unity.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class SessionGameData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    private Key twitterUserKey;

    private Key apiData;

    @Attribute(lob = true)
    private String contents;
    @Attribute(lob = true)
    private String operations;
    @Attribute(lob = true)
    private String code;
    @Attribute(version = true)
    private Long version;

    private String gameName;

    private String gameURL;

    private Date date;

    private Date lastDate;

    private String thumbNailURL;

    private String hpURL;

    private String pass;

    private String thumbNailType;

    private String gameType;

    private int access;

    private int comment;

    private int assessment;

    private int point;

    private String gameScreenSize;

    private boolean editCode;

    private String fixTags;

    @Attribute(persistent = false)
    private org.slim3.datastore.InverseModelListRef<unity.model.UploadedDataFragment, unity.model.SessionGameData> fragmentListRef =
        new org.slim3.datastore.InverseModelListRef<unity.model.UploadedDataFragment, unity.model.SessionGameData>(
            unity.model.UploadedDataFragment.class,
            "uploadDataRef",
            this,
            new Sort("index"));

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
        SessionGameData other = (SessionGameData) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public InverseModelListRef<UploadedDataFragment, SessionGameData> getFragmentListRef() {
        return fragmentListRef;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public String getThumbNailURL() {
        return thumbNailURL;
    }

    public void setThumbNailURL(String thumbNailURL) {
        this.thumbNailURL = thumbNailURL;
    }

    public String getHpURL() {
        return hpURL;
    }

    public void setHpURL(String hpURL) {
        this.hpURL = hpURL;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getAccess() {
        return access;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getComment() {
        return comment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameType() {
        return gameType;
    }

    public void setThumbNailType(String thumbNailType) {
        this.thumbNailType = thumbNailType;
    }

    public String getThumbNailType() {
        return thumbNailType;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setTwitterUserKey(Key twitterUserKey) {
        this.twitterUserKey = twitterUserKey;
    }

    public Key getTwitterUserKey() {
        return twitterUserKey;
    }

    public void setApiData(Key apiData) {
        this.apiData = apiData;
    }

    public Key getApiData() {
        return apiData;
    }

    public void setGameScreenSize(String gameScreenSize) {
        this.gameScreenSize = gameScreenSize;
    }

    public String getGameScreenSize() {
        return gameScreenSize;
    }

    public void setFixTags(String fixTags) {
        this.fixTags = fixTags;
    }

    public String getFixTags() {
        return fixTags;
    }

    public void setEditCode(boolean editCode) {
        this.editCode = editCode;
    }

    public boolean isEditCode() {
        return editCode;
    }

}
