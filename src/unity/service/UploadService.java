package unity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.util.ByteUtil;

import unity.meta.GameDataMeta;
import unity.meta.UploadedDataFragmentMeta;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.ThumbNailData;
import unity.model.UploadedDataFragment;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;


public class UploadService {

 private static final int FRAGMENT_SIZE = 900000;

    
    private GameDataMeta dd = GameDataMeta.get();
   
    
    private UploadedDataFragmentMeta f = UploadedDataFragmentMeta.get();

    
   
    
//    public GameData getData1(Key key, Long version) {
//        
//        return Datastore.get(dd, key, version);
//       }
//    public UploadedDataFragment getData2(String type) {
//        
//        return Datastore.query(f).filter(UploadedDataFragmentMeta.get().type.equal("ThumbNail")).asSingle();
//       }
    
    
    public GameData upload(String GameName,String GameURL,FileItem GameFile,FileItem ThumbNail,String ThumbNailURL,String Contents,String Operations,String HpURL,String Pass,String ThumbNailType,String GameType,String fixTag,String code) {
      
        
        List<Object> models = new ArrayList<Object>();

        GameData d = new GameData();
        
        
        models.add(d);
        Key allocateId = Datastore.allocateId(dd);
        d.setKey(allocateId);
        d.setGameName(GameName);
        d.setThumbNailURL(ThumbNailURL);
        d.setGameURL(GameURL);
        d.setHpURL(HpURL);
        d.setDate(new Date());
        d.setLastDate(new Date());
        d.setPass(Pass);
        d.setContents(Contents);
        d.setOperations(Operations);
        d.setCode(code);
        d.setThumbNailType(ThumbNailType);
        d.setGameType(GameType);
        

        List<Tag> taglist = new ArrayList<Tag>();
        Iterator<Key> tagKeys = Datastore.allocateIds(allocateId, Tag.class,1).iterator();
        List<ThumbNailData> list = new ArrayList<ThumbNailData>();
        Iterator<Key> keyss = Datastore.allocateIds(allocateId, ThumbNailData.class,1).iterator();
        while (keyss.hasNext() && tagKeys.hasNext()){    
            
            Tag tag = new Tag();
            tag.setFixTag(fixTag);
            tag.setGameDataKey(d.getKey());
            taglist.add(tag);
            
            ThumbNailData child = new ThumbNailData();
            child.setKey(keyss.next());
            child.setGameName(GameName);
            child.setDate(new Date());
            
            if(ThumbNail != null){
                
                child.setLength(ThumbNail.getData().length);
                
                byte[] bytes2 = ThumbNail.getData();
                byte[][] bytesArray2 = ByteUtil.split(bytes2, FRAGMENT_SIZE);
                
                Iterator<Key> keys2 =
                    Datastore
                        .allocateIds(d.getKey(), f, bytesArray2.length)
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

                
            }
            d.setThumbNailKey(child.getKey());
            list.add(child);
        }
        
        Datastore.put(list);
        Datastore.put(taglist);
        
        
  if (GameFile != null) {
      d.setLength(GameFile.getData().length);  
      byte[] bytes = GameFile.getData();
      byte[][] bytesArray1 = ByteUtil.split(bytes, FRAGMENT_SIZE);
      Iterator<Key> keys =
          Datastore
              .allocateIds(d.getKey(), f, bytesArray1.length)
              .iterator();
      for (int i = 0; i < bytesArray1.length; i++) {
          byte[] fragmentData = bytesArray1[i];
          UploadedDataFragment fragment = new UploadedDataFragment();
          models.add(fragment);
          fragment.setKey(keys.next());
          fragment.setBytes(fragmentData);
          fragment.setType("GameFile");
          fragment.setIndex(i);
          fragment.getUploadDataRef().setModel(d);
      }
   }
       
        Transaction tx = Datastore.beginTransaction();
        
        for (Object model : models) {
            Datastore.put(tx, model);
        }
        tx.commit();
       
        return d;
    }
    

    
    public byte[] getBytes(GameData uploadedData) {
        if (uploadedData == null) {
            throw new NullPointerException(
                "The uploadedData parameter must not be null.");
        }
      
             
        
            List<UploadedDataFragment> fragmentList = 
            uploadedData.getFragmentListRef().getModelList();
        byte[][] bytesArray2 = new byte[fragmentList.size()][0];
        for (int i = 0; i < fragmentList.size(); i++) {
            bytesArray2[i] = fragmentList.get(i).getBytes();
          
        }
        
        return ByteUtil.join(bytesArray2);
    }
    

public byte[] getBytes2(ThumbNailData uploadedData2) {
    if (uploadedData2 == null) {
        throw new NullPointerException(
            "The uploadedData parameter must not be null.");
    }
  
       
        List<UploadedDataFragment> fragmentList2 = 
        uploadedData2.getFragmentListRef().getModelList();
    byte[][] bytesArray2 = new byte[fragmentList2.size()][0];
    for (int i = 0; i < fragmentList2.size(); i++) {
        bytesArray2[i] = fragmentList2.get(i).getBytes();
      
    }
    
    return ByteUtil.join(bytesArray2);
}

}
