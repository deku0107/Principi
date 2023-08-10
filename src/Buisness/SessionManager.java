package Buisness;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    public static final String LOGGED_USER="logged_user";
    public static final String LOGGED_MANAGER="logged_manager";
    public static final String LOGGED_ADMIN="logged_admin";
    public static final String ARTICOLI="articoli";
    public static final String CATEGORIE="categorie";
    //...
    private  static final HashMap<String, Object> session= new HashMap<>();




    public static HashMap getSession(){
        return session;
    }
    public static void printMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
