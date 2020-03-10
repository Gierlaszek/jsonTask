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
    
    JSONArray response(String url) throws IOException, JSONException, ParseException{
        URL object = new URL(url);
        URLConnection con = object.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        JSONParser parse = new JSONParser();
        
        while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
        }
        in.close();
        
        JSONArray posts = (JSONArray)parse.parse(response.toString());
        return posts;
    }   
}

    

