package openx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kamil
 */
public class getPost{
    
    JSONArray response() throws IOException, JSONException, ParseException{
        String url = "https://jsonplaceholder.typicode.com/posts";
        URL object = new URL(url);
        URLConnection con = object.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        JSONParser parse = new JSONParser();
        
        while((inputLine = in.readLine()) != null){
//            //#######################
//            // if line contains {it means, a data block is start
//            //#######################
//            if(inputLine.contains("{")){
//                StringBuffer response = new StringBuffer();
//                response.append(inputLine);
//                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
//                    if(inputLine.contains("}")){
//                        response.append(System.getProperty("line.separator"));  //add enter after block of data is end
//                        break;
//                    }
//                }
//                JSONObject item = new JSONObject(response.toString());
//                items.add(item);
//            }
        }
        in.close();
        
        JSONArray posts = (JSONArray)parse.parse(response.toString());
        return posts;
    }   
}

    

