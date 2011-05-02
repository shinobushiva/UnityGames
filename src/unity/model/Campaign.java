package unity.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import unity.meta.CampaignGameMeta;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Campaign implements Serializable {
    public static final int STATE_NOW = 1;
    public static final int STATE_FINISHED = 0;
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String title;
    @Attribute(persistent = false)
    private InverseModelListRef<CampaignGame, Campaign> campaignGameRef =
        new InverseModelListRef<CampaignGame, Campaign>(
            CampaignGame.class,
            CampaignGameMeta.get().gameRef.getName(),
            this);

    private Date start;

    private Date end;

    private int state;

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
        Campaign other = (Campaign) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
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

    public InverseModelListRef<CampaignGame, Campaign> getCampaignGameRef() {
        return campaignGameRef;
    }
}
