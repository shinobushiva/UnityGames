package unity.service;


public class UnityGamesService {

    //どこに属すかわからないものはここへ
    
    public String split(String result,String regex){
        
        return result.split(regex)[1].split("&")[0];
    }
   
}
