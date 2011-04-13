package unity.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.model.Tag;

public class SearchController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        
        String word = asString("word");
        String tag = asString("tag");

        System.out.println("わーど:"+word+"　"+"たぐ:"+tag);
        
        
        if(tag.isEmpty() && !word.isEmpty()){
            System.out.println("タグが空");
            
            List<GameData> g = Datastore.query(GameData.class).asList();
            List<GameData> gr = new ArrayList<GameData>();
            
            for(GameData gameName : g){
                if(gameName
                        .getGameName()
                        .toLowerCase()
                        .contains(word.toLowerCase())){
                    
                    
                    if(gameName.getContents().length() >= 80){
                        String s = gameName.getContents().substring(0,80);
                        gameName.setContents(s+"...");
                        }
                        if(gameName.getOperations().length() >= 80){
                        String o = gameName.getOperations().substring(0,80);
                        gameName.setOperations(o+"...");
                        }
                    
                    
                    gr.add(gameName);
                    System.out.println(gr);
                    
                }
                
                
                    
                
                
            }
            requestScope("word",word);
            requestScope("type","keyword");
            requestScope("Result",gr);
       
          
        }
//        return forward("search?word="+word);
        if(word == null ||word.isEmpty() && !tag.isEmpty()){
            System.out.println("ワードが空");
            
            List<Tag> t = Datastore.query(Tag.class).asList();
            List<GameData> tr = new ArrayList<GameData>();
            
            for(Tag tagg : t){
                if(tagg.getFixTag().contains(tag) || tagg.getTag().toLowerCase().contains(tag.toLowerCase())){
                    
                  GameData ggg = Datastore.get(GameData.class,tagg.getKey().getParent());
                    System.out.println("あ:"+ggg.getGameName());
                   
                    long l = ggg.getDate().getTime() + 1000 * 60 * 60 * 9;
                    ggg.getDate().setTime(l);
                    
                    if(ggg.getContents().length() >= 80){
                    String s = ggg.getContents().substring(0,80);
                    ggg.setContents(s+"...");
                    }
                    if(ggg.getOperations().length() >= 80){
                    String o = ggg.getOperations().substring(0,80);
                    ggg.setOperations(o+"...");
                    }
                    
                    
                    
                    
                    tr.add(ggg);
                    
                    
                }
               
                    
                
                
            }
            requestScope("word",tag);
            requestScope("type","tag");
            requestScope("Result",tr);
            
            
        }
        return forward("search.jsp"); 
        
        
        
    
        
      
        
       
    }
}
