package openx;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kamil
 */
public class getPostTest {
    /**
     * Test of response method, of class getPost.
     */
    @Test
    public void testResponse() throws Exception {
        String json1 = "{\n" +"  \"userId\": 1,\n" +"  \"id\": 1,\n" +
                        "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                        "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                        "}";
        
        getUser instance = new getUser();
        Object object = null;
        JSONParser jsonParser=new JSONParser();
        object=jsonParser.parse(json1);
        JSONArray expResult = (JSONArray) object;
        JSONArray result = instance.response("https://jsonplaceholder.typicode.com/posts/1");
        assertEquals(expResult, result, "Incorrect value");
    }
    
}
