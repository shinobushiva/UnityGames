package unity.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.Sort;

import unity.model.ThumbNailData;
import unity.model.UploadedDataFragment;

@Model(schemaVersion = 1)
public class ThumbNailData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    private int length;
    private Date date;
    private String GameName;
    
    
    @Attribute(version = true)
    private Long version;

    
    
    @Attribute(persistent = false)
    private org.slim3.datastore.InverseModelListRef<unity.model.UploadedDataFragment, unity.model.ThumbNailData> fragmentListRef =
        new org.slim3.datastore.InverseModelListRef<unity.model.UploadedDataFragment, unity.model.ThumbNailData>(
            unity.model.UploadedDataFragment.class,
            "uploadDataRef2",
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
        ThumbNailData other = (ThumbNailData) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

 

   



    public InverseModelListRef<UploadedDataFragment, ThumbNailData> getFragmentListRef() {
        return fragmentListRef;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getGameName() {
        return GameName;
    }



   }
