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
public class getUserTest {
    /**
     * Test of response method, of class getUser.
     */
    @Test
    public void testResponse() throws Exception {
        getUser instance = new getUser();
        String json = "[{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"Leanne Graham\",\n" +
            "  \"username\": \"Bret\",\n" +
            "  \"email\": \"Sincere@april.biz\",\n" +
            "  \"address\": {\n" +
            "    \"street\": \"Kulas Light\",\n" +
            "    \"suite\": \"Apt. 556\",\n" +
            "    \"city\": \"Gwenborough\",\n" +
            "    \"zipcode\": \"92998-3874\",\n" +
            "    \"geo\": {\n" +
            "      \"lat\": \"-37.3159\",\n" +
            "      \"lng\": \"81.1496\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"phone\": \"1-770-736-8031 x56442\",\n" +
            "  \"website\": \"hildegard.org\",\n" +
            "  \"company\": {\n" +
            "    \"name\": \"Romaguera-Crona\",\n" +
            "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "    \"bs\": \"harness real-time e-markets\"\n" +
            "  }\n" +
            "}]";
        
        Object object = null;
        JSONParser jsonParser=new JSONParser();
        object=jsonParser.parse(json);
        JSONArray expResult = (JSONArray) object;
        JSONArray result = instance.response("https://jsonplaceholder.typicode.com/users/1");
        assertEquals(expResult, result, "IncorrectValue");
    }
    
}
