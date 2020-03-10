
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
    JSONArray response(String url) throws IOException, JSONException, ParseException{
        //####################################
        //Connect with website to get response
        //####################################
        URL object = new URL(url);
        URLConnection con = object.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;

        JSONParser parse = new JSONParser();
        Collection<JSONObject> items = new ArrayList<JSONObject>();
        
        StringBuffer response = new StringBuffer();


        while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
        }
    
        in.close();
        JSONArray users = (JSONArray)parse.parse(response.toString());
        return users;
    }
}
