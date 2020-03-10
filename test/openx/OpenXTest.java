package openx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kamil
 */
public class OpenXTest {
    /**
     * Test of connect_user_post method, of class OpenX.
     */
    @Test
    public void testConnect_user_post() throws Exception {
        String json1 = "{\n" +"  \"userId\": 1,\n" +"  \"id\": 1,\n" +
                        "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                        "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                        "}";
        
        String json2 = "{\n" +"  \"id\": 1,\n" +"  \"name\": \"Leanne Graham\",\n" +"  \"username\": \"Bret\",\n" +"  \"email\": \"Sincere@april.biz\",\n" +
                        "  \"address\": {\n" +"    \"street\": \"Kulas Light\",\n" +"    \"suite\": \"Apt. 556\",\n" +"    \"city\": \"Gwenborough\",\n" +"    \"zipcode\": \"92998-3874\",\n" +
                        "    \"geo\": {\n" +"      \"lat\": \"-37.3159\",\n" +"      \"lng\": \"81.1496\"\n" +"    }\n" +"  },\n" +"  \"phone\": \"1-770-736-8031 x56442\",\n" +"  \"website\": \"hildegard.org\",\n" +
                        "  \"company\": {\n" +"    \"name\": \"Romaguera-Crona\",\n" +"    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +"    \"bs\": \"harness real-time e-markets\"\n" + "  }\n" + "}" ;
        JSONArray l_u = new JSONArray();
        JSONArray l_p = new JSONArray();
        JSONParser jsonParser=new JSONParser();
        JSONObject jo_p = new JSONObject();
        JSONObject jo_u = new JSONObject();
        Object object = null;

        try{
            object=jsonParser.parse(json2);
            l_u = (JSONArray) object;
            object = jsonParser.parse(json1);
            l_p = (JSONArray) object;
            jo_p = (JSONObject) l_p.get(0);
            jo_u = (JSONObject) l_u.get(0);
        }catch(Exception e){
            e.getMessage();
        }
        
        OpenX instance = new OpenX();
        List<JSONObject> list = new ArrayList<>();
        list.add(jo_p);
        Map<JSONObject, List<JSONObject>> expResult = null;
        expResult.put(jo_u, list);
        Map<JSONObject, List<JSONObject>> result = instance.connect_user_post(l_p, l_u);
        assertEquals(expResult, result, "Incorrect value");
    }

    /**
     * Test of count_post method, of class OpenX.
     */
    @Test
    public void testCount_post() {
        String json1 = "{\n" +"  \"userId\": 1,\n" +"  \"id\": 1,\n" +
                        "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                        "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                        "}";
        
        String json2 = "{\n" +"  \"id\": 1,\n" +"  \"name\": \"Leanne Graham\",\n" +"  \"username\": \"Bret\",\n" +"  \"email\": \"Sincere@april.biz\",\n" +
                        "  \"address\": {\n" +"    \"street\": \"Kulas Light\",\n" +"    \"suite\": \"Apt. 556\",\n" +"    \"city\": \"Gwenborough\",\n" +"    \"zipcode\": \"92998-3874\",\n" +
                        "    \"geo\": {\n" +"      \"lat\": \"-37.3159\",\n" +"      \"lng\": \"81.1496\"\n" +"    }\n" +"  },\n" +"  \"phone\": \"1-770-736-8031 x56442\",\n" +"  \"website\": \"hildegard.org\",\n" +
                        "  \"company\": {\n" +"    \"name\": \"Romaguera-Crona\",\n" +"    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +"    \"bs\": \"harness real-time e-markets\"\n" + "  }\n" + "}" ;
        JSONArray l_u = new JSONArray();
        JSONArray l_p = new JSONArray();
        JSONParser jsonParser=new JSONParser();
        JSONObject jo_p = new JSONObject();
        JSONObject jo_u = new JSONObject();
        Object object = null;

        try{
            object=jsonParser.parse(json2);
            l_u = (JSONArray) object;
            object = jsonParser.parse(json1);
            l_p = (JSONArray) object;
            jo_p = (JSONObject) l_p.get(0);
            jo_u = (JSONObject) l_u.get(0);
        }catch(Exception e){
            e.getMessage();
        }
        
        OpenX instance = new OpenX();
        List<JSONObject> list = new ArrayList<>();
        list.add(jo_p);
        Map<JSONObject, List<JSONObject>> map = null;
        map.put(jo_u, list);
        
        List<String> expResult = null;
        expResult.add("User_name: " + jo_u.get("name") + " napisał(a) " + 1 + " postow.");
        List<String> result = instance.count_post(map);
        assertEquals(expResult, result);
    }

    /**
     * Test of title_non_unique method, of class OpenX.
     */
    @Test
    public void testTitle_non_unique() {
        String json1 = "{\n" +"    \"userId\": 1,\n" +"    \"id\": 6,\n" +"    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
                        "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +"  },";
        
        String json2 = "{\n" +"    \"userId\": 1,\n" +"    \"id\": 8,\n" +"    \"title\": \"dolorem dolore est ipsam\",\n" +
                        "    \"body\": \"dignissimos aperiam dolorem qui eum\\nfacilis quibusdam animi sint suscipit qui sint possimus cum\\nquaerat magni maiores excepturi\\nipsam ut commodi dolor voluptatum modi aut vitae\"\n" +"  },";
        JSONArray l_p1 = new JSONArray();
        JSONParser jsonParser=new JSONParser();
        JSONObject jo_p = new JSONObject();
        Object object = null;
        List<String> expResult = null;


        try{
            object=jsonParser.parse(json1);
            jo_p = (JSONObject) object;
            l_p1.add(jo_p);
            expResult.add((String) jo_p.get("title"));
            object = jsonParser.parse(json2);
            jo_p = (JSONObject) object;
            l_p1.add(jo_p);
            expResult.add((String) jo_p.get("title"));
        }catch(Exception e){
            e.getMessage();
        }
        
        OpenX instance = new OpenX();
        List<String> result = instance.title_non_unique(l_p1);
        assertEquals(expResult, result, "Incorrect value");
    }

    /**
     * Test of distance method, of class OpenX.
     */
    @Test
    public void testDistance() {
        Double expResult = 9306.10732349838;
        Double result = OpenX.distance(-37.3159, 81.1496, -43.9509, -34.4618);
        assertEquals(expResult, result, "Incorrect value");
    }    
}
