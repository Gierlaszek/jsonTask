
package openx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kamil
 */
public class getUser {
    JSONArray response() throws IOException, JSONException, ParseException{
        //####################################
        //Connect with website to get response
        //####################################
        
        //TODO wycioagnac do osbnej klasy laczenie ze strona 
        
        
        String url = "https://jsonplaceholder.typicode.com/users";
        URL object = new URL(url);
        URLConnection con = object.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;

        JSONParser parse = new JSONParser();
        Collection<JSONObject> items = new ArrayList<JSONObject>();
        
        StringBuffer response = new StringBuffer();


        while((inputLine = in.readLine()) != null){
            //################################
            // i - count of open new { 
            // j - count of end }
            // when i == j it means all data block is read in
            //################################
//            int i = 0; 
//            int j = 0; 
//            if(inputLine.contains("{")){
//                i++;
//                response.append(inputLine);
//                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
//                    if(inputLine.contains("{")){
//                        i++;
//                    }
//                    if(inputLine.contains("}")){
//                        j++;
//                        if(i == j){
//                            response.append(System.getProperty("line.separator"));  
//                            break;
//                        }
//                    }
//                }

//            }   
        }
    
        in.close();
//        JSONObject item = (JSONObject)parse.parse(response.toString());
        
        JSONArray users = (JSONArray)parse.parse(response.toString());
        
//        System.out.println(users);

        return users;
    }
}
