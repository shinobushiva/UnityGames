package unity.service;

import java.util.List;

import org.slim3.util.ByteUtil;

import unity.model.GameData;
import unity.model.ThumbNailData;
import unity.model.UploadedDataFragment;

public class UploadService {

    // public GameData getData1(Key key, Long version) {
    //
    // return Datastore.get(dd, key, version);
    // }
    //
    // public UploadedDataFragment getData2(String type) {
    //
    // return Datastore
    // .query(f)
    // .filter(UploadedDataFragmentMeta.get().type.equal("ThumbNail"))
    // .asSingle();
    // }
    //
    // public GameData upload(Key key, String GameName, String GameURL,
    // FileItem GameFile, FileItem ThumbNail, String ThumbNailURL,
    // String Contents, String Operations, String HpURL, String Pass,
    // String ThumbNailType, String GameType, String fixTag, String code) {
    //
    // List<Object> models = new ArrayList<Object>();
    //
    // GameData d = new GameData();
    //
    // models.add(d);
    // Key allocateId = Datastore.allocateId(dd);
    // d.setKey(allocateId);
    // d.setGameName(GameName);
    //
    // if (ThumbNailURL.isEmpty() && ThumbNail == null) {
    // d.setThumbNailURL("http://unity-games.appspot.com/"
    // + "DefaultSet/UnityGames.png");
    // d.setThumbNailType("url");
    // } else {
    // d.setThumbNailURL(ThumbNailURL);
    // d.setThumbNailType(ThumbNailType);
    // }
    //
    // if (GameURL.isEmpty() && HpURL.isEmpty() && GameFile == null) {
    // d
    // .setGameURL("http://unity-games.appspot.com/" +
    // "DefaultSet/UnityGames.unity3d");
    // d.setGameType("url");
    // } else {
    // d.setGameURL(GameURL);
    // d.setGameType(GameType);
    // }
    //
    // d.setHpURL(HpURL);
    // d.setDate(new Date());
    // d.setLastDate(new Date());
    // d.setPass(Pass);
    // d.setContents(Contents);
    // d.setOperations(Operations);
    // d.setCode(code);
    //
    // // List<Tag> taglist = new ArrayList<Tag>();
    // // Iterator<Key> tagKeys = Datastore.allocateIds(allocateId,
    // // Tag.class,1).iterator();
    // // List<ThumbNailData> list = new ArrayList<ThumbNailData>();
    // // Iterator<Key> keyss = Datastore.allocateIds(allocateId,
    // // ThumbNailData.class,1).iterator();
    //
    // // Key tagKeys = Datastore.allocateId(allocateId, Tag.class);
    // Key keyss = Datastore.allocateId(allocateId, ThumbNailData.class);
    // // Tag tag = new Tag();
    // // tag.setKey(tagKeys);
    // String[] tags = fixTag.split(",");
    // for (String t : tags) {
    // t = t.trim();
    // if (t.isEmpty()) {
    // continue;
    // }
    // Tag tag2 = ts.getTag(t);
    // d.getFixTags().add(tag2);
    //
    // TagGame tt = new TagGame();
    // tt.getGameRef().setKey(d.getKey());
    // tt.getTagRef().setModel(tag2);
    // GlobalTransaction tx = Datastore.beginGlobalTransaction();
    // Datastore.put(tt);
    // tx.commit();
    // }
    //
    // // tag.setFixTag(fixTag);
    // // tag.setTag("");
    //
    // ThumbNailData thumb = new ThumbNailData();
    // thumb.setKey(keyss);
    // thumb.setGameName(GameName);
    // thumb.setDate(new Date());
    //
    // if (ThumbNail != null) {
    //
    // thumb.setLength(ThumbNail.getData().length);
    //
    // byte[] bytes2 = ThumbNail.getData();
    // byte[][] bytesArray2 = ByteUtil.split(bytes2, FRAGMENT_SIZE);
    //
    // Iterator<Key> keys2 =
    // Datastore
    // .allocateIds(d.getKey(), f, bytesArray2.length)
    // .iterator();
    //
    // for (int i = 0; i < bytesArray2.length; i++) {
    // byte[] fragmentData2 = bytesArray2[i];
    // UploadedDataFragment fragment2 = new UploadedDataFragment();
    // models.add(fragment2);
    // fragment2.setKey(keys2.next());
    // fragment2.setBytes(fragmentData2);
    // fragment2.setType("ThumbNail");
    //
    // fragment2.setIndex(i);
    // fragment2.getUploadDataRef2().setModel(thumb);
    // }
    //
    // }
    //
    // // Datastore.put(tag);
    // Datastore.put(thumb);
    //
    // if (GameFile != null) {
    // d.setLength(GameFile.getData().length);
    // byte[] bytes = GameFile.getData();
    // byte[][] bytesArray1 = ByteUtil.split(bytes, FRAGMENT_SIZE);
    // Iterator<Key> keys =
    // Datastore
    // .allocateIds(d.getKey(), f, bytesArray1.length)
    // .iterator();
    // for (int i = 0; i < bytesArray1.length; i++) {
    // byte[] fragmentData = bytesArray1[i];
    // UploadedDataFragment fragment = new UploadedDataFragment();
    // models.add(fragment);
    // fragment.setKey(keys.next());
    // fragment.setBytes(fragmentData);
    // fragment.setType("GameFile");
    // fragment.setIndex(i);
    // fragment.getUploadDataRef().setModel(d);
    // }
    // }
    //
    // Transaction tx = Datastore.beginTransaction();
    //
    // for (Object model : models) {
    // Datastore.put(tx, model);
    // }
    // tx.commit();
    //
    // return d;
    // }

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
