package openx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kamil
 */
public class OpenX {

    //#####################
    //method that assigns posts to relevant users
    //#####################
    Map<JSONObject, List<JSONObject>> connect_user_post(JSONArray l_p, JSONArray l_u){
        Map<JSONObject, List<JSONObject>> map_u_p = new HashMap<>();

        for(Object u: l_u){
            JSONObject JOu = (JSONObject) u;
            List<JSONObject> list = new ArrayList<>();
            for(Object p: l_p){
                JSONObject JOp = (JSONObject) p;
                if(JOp.get("userId") == JOu.get("id")){
                   list.add(JOp);
                }
            }
            map_u_p.put(JOu, list);
        }  
        return map_u_p;
    }  
    
    //############################
    //method which return a list with 
    //the number of posts written by one user
    //############################
    List<String> count_post(Map<JSONObject, List<JSONObject>> map){ 
        //map - map contains all users and written by them post
        List<String> count_post = new ArrayList<>();
        for(JSONObject JOu: map.keySet()){
            String countOfPost;
            int count = 0;
            for(JSONObject p: map.get(JOu)){
                count ++;
            }
            countOfPost = "User_name: " + JOu.get("name") + " napisał(a) " + count + " postow.";
            count_post.add(countOfPost);
        }
        return count_post;
    }
    
    List<JSONObject> title_non_unique(JSONArray l_p){
        //l_p - list which contains all post
        List<JSONObject> title = new ArrayList<>();
        
        for(Object elem: l_p){
            JSONObject JOp = (JSONObject) elem;
            
        }
        
        return title;
    }
    
    //#######################
    //method to search users who is the nearest to other
    //#######################
    public void near_user(JSONArray l_u){
        //l_u - list which contains all users
        Map<Object, Object> map = new HashMap<>();
        Map<List<Object>, Object> map_user = new HashMap<>();
        List<String> x = new ArrayList<>();
        
        for(Object elem: l_u){
           JSONObject jo = (JSONObject) elem;
           JSONObject jo_a = (JSONObject) jo.get("address");
           JSONObject jo_g = (JSONObject) jo_a.get("geo");
           
           x.add((String) jo_g.get("lat"));  // list with coordinate x all users
           map_user.put(Arrays.asList(jo_g.get("lat"), jo_g.get("lng")), jo);  //map where key is list with coordinate users and value is all data of users
           map.put(jo_g.get("lat"), jo_g.get("lng")); //map where key is coordinate x and value is coordinate y 
        }
        
        //I count the distance between one x and all values from list with cooridnates
        for(int i = 0; i < x.size(); i++){
            Map<Double, List<Double>> dist = new HashMap<>();
            for(int j = 0; j < x.size(); j++){
                //coordinates of two users
                double lat1 = Double.parseDouble((String)x.get(i)); //x1 - first user
                double lat2 = Double.parseDouble((String)x.get(j)); //x2 - second user
                double lng1 = Double.parseDouble((String)map.get(x.get(i))); //y1
                double lng2 = Double.parseDouble((String)map.get(x.get(j))); //y2
                dist.put(distance(lat1, lng1, lat2, lng2), Arrays.asList(lat1, lat2)); //map dist where key is distance and value is list with x1 and x2 
            }
            
            //list with all distance for one user 
            List<Double> list = new ArrayList<Double>(); 
            for(Double elem : dist.keySet()){
                list.add(elem);
            }
            list.remove(null);
            list.sort(null); //chronological list sorting
            
            DecimalFormat dec = new DecimalFormat("#0.0000");
            
            //from list with all distance i choose first, because it have the lowest value   
            //from map dist i choose coordinate x1 and x2 where key is the lowest value
            String x1 = dec.format(dist.get(list.get(0)).get(0)).toString().replace(",", ".");
            // from this map i get y for x1
            Object y1 = map.get(dec.format(dist.get(list.get(0)).get(0)).toString().replace(",", "."));
            String x2 = dec.format(dist.get(list.get(0)).get(1)).toString().replace(",", ".");
            Object y2 = map.get(dec.format(dist.get(list.get(0)).get(1)).toString().replace(",", "."));
            
            //i get data of user for coordinate x1 and y1
            //for user1 the nearest lives user2
            JSONObject jo_user1 = (JSONObject) map_user.get(Arrays.asList(x1, y1)); 
            JSONObject jo_user2 = (JSONObject) map_user.get(Arrays.asList(x2, y2));
            
            
            String nearestUser = "Dla uzytkownika: " + jo_user1.get("name") + " najblizej mieszkajacy jest uzytkownik: " + jo_user2.get("name");
            System.out.println(nearestUser);
        }
        
    }
    
    //##########################
    // method to calculate distance between two coordinate 
    // I use Great-circle distance for calculation
    //##########################
    public static Double distance(double lat1, double lon1, double lat2, double lon2) {        
        if ((lat1 == lat2) && (lon1 == lon2)) {
                return null;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return dist;
        }
    } 
    
    
    
    public static void main(String[] args) throws IOException, JSONException, ParseException
    {
        Map<JSONObject, List<JSONObject>> map_user_and_post = new HashMap<>();
        List<String> count_post = new ArrayList<>();
        JSONArray list_of_posts = new JSONArray(); 
        JSONArray list_of_users = new JSONArray(); 

        OpenX openx = new OpenX();
        
        getPost get = new getPost();
        list_of_posts = get.response();   
        
        getUser users = new getUser();
        list_of_users = users.response(); 
        
        map_user_and_post = openx.connect_user_post(list_of_posts, list_of_users);
        count_post = openx.count_post(map_user_and_post);
        openx.near_user(list_of_users);
        
//        JSONObject job = (JSONObject) list_of_users.get(0);
//        JSONObject jobb = (JSONObject) job.get("address");
//        System.out.println(job);
//        System.out.println(jobb);
        
//        for(Object u: list_of_users)
//        {
//            JSONObject jo = (JSONObject) u;
//            System.out.println(u);
//        }
        
//        System.out.println(list_of_users.get(0));      
    }
}


