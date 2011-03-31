package unity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.util.ByteUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

import unity.meta.UploadedDataFragmentMeta;
import unity.model.GameData;
import unity.model.ThumbNailData;
import unity.model.UploadedDataFragment;


public class ChangeService {
    private static final int FRAGMENT_SIZE = 900000;
    private UploadedDataFragmentMeta f = UploadedDataFragmentMeta.get();
    public GameData change(Key key, String GameName,String GameURL,FileItem GameFile,FileItem ThumbNail,String ThumbNailURL,String Contents,String Operations,String HpURL,String Pass,String ThumbNailType,String GameType,String ThumbNailChange,String GameChange) {
    
        List<Object> models = new ArrayList<Object>();
       GameData g = Datastore.get(GameData.class, key);
       
       g.setGameName(GameName);
       g.setPass(Pass);
       g.setContents(Contents);
       g.setOperations(Operations);
       g.setLastDate(new Date());
       if(GameChange !=null){
           g.setGameType(GameType);
           g.setHpURL(HpURL);
           g.setGameURL(GameURL);
                     
           UploadedDataFragment uf1 = Datastore.query(UploadedDataFragment.class).filter(UploadedDataFragmentMeta.get().uploadDataRef.equal(g.getKey())).asSingle();
           if(uf1 != null){
               Datastore.delete(uf1.getKey());
           }
           if(GameFile !=null){
           g.setLength(GameFile.getData().length);  
           byte[] bytes = GameFile.getData();
           byte[][] bytesArray1 = ByteUtil.split(bytes, FRAGMENT_SIZE);
           Iterator<Key> keys =
               Datastore
                   .allocateIds(g.getKey(), f, bytesArray1.length)
                   .iterator();
           for (int i = 0; i < bytesArray1.length; i++) {
               byte[] fragmentData = bytesArray1[i];
               UploadedDataFragment fragment = new UploadedDataFragment();
               models.add(fragment);
               fragment.setKey(keys.next());
               fragment.setBytes(fragmentData);
               fragment.setType("GameFile");
               fragment.setIndex(i);
               fragment.getUploadDataRef().setModel(g);
       }
           for (Object model : models) {
               Datastore.put(model);
           }
           }
           }
       if(ThumbNailChange != null){
           g.setThumbNailURL(ThumbNailURL);
           g.setThumbNailType(ThumbNailType);
           ThumbNailData t = Datastore.get(ThumbNailData.class, g.getThumbNailKey());
                     
           UploadedDataFragment uf = Datastore.query(UploadedDataFragment.class).filter(UploadedDataFragmentMeta.get().uploadDataRef2.equal(t.getKey())).asSingle();
           if(uf != null){
               Datastore.delete(uf.getKey());
           }
     
          
         List<ThumbNailData> list = new ArrayList<ThumbNailData>();
         Iterator<Key> keyss = Datastore.allocateIds(g.getKey(), ThumbNailData.class,1).iterator();
         while (keyss.hasNext()){    
             ThumbNailData child = new ThumbNailData();
             child.setKey(keyss.next());
             child.setGameName(GameName);
             child.setDate(new Date());
             if(ThumbNail != null){
                 g.setThumbNailURL(null);
               Datastore.delete(t.getKey());
             child.setLength(ThumbNail.getData().length);
         byte[] bytes2 = ThumbNail.getData();
         byte[][] bytesArray2 = ByteUtil.split(bytes2, FRAGMENT_SIZE);
         Iterator<Key> keys2 =
             Datastore
                 .allocateIds(g.getKey(), f, bytesArray2.length)
                 .iterator();
         for (int i = 0; i < bytesArray2.length; i++) {
             byte[] fragmentData2 = bytesArray2[i];
             UploadedDataFragment fragment2 = new UploadedDataFragment();
             models.add(fragment2);
             fragment2.setKey(keys2.next());
             fragment2.setBytes(fragmentData2);
             fragment2.setType("ThumbNail");
             
             fragment2.setIndex(i);
             fragment2.getUploadDataRef2().setModel(child);
         }

         
     
     g.setThumbNailKey(child.getKey());
     list.add(child);
 }
 
 Datastore.put(list);
 for (Object model : models) {
     Datastore.put(model);
 }
       }
       }
       Transaction tx = Datastore.beginTransaction();
      
       Datastore.put(g);
       tx.commit();
       
//       g.setGameURL(GameURL);
//       g.setThumbNailURL(ThumbNailURL);
//       g.setContents(Contents);
//       g.setOperations(Operations);
//       g.setHpURL(HpURL);
//       g.setPass(Pass);
//       g.setThumbNailType(ThumbNailType);
//       g.setGameType(GameType);
       
        
        
        
    return g;
    }

}
