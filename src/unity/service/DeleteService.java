package unity.service;


import org.slim3.datastore.Datastore;

import unity.meta.UploadedDataFragmentMeta;
import unity.model.GameData;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;


public class DeleteService {
    
    public GameData delete(Key key){
    Transaction tx = Datastore.beginTransaction();    
    GameData g = Datastore.get(GameData.class,key);
    Datastore.deleteAll(g.getKey());
     tx.commit();
    return g;
    }

}
