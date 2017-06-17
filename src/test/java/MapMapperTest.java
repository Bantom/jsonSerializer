import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class MapMapperTest {
    JsonSerializer jsonSerializer;
    Map<Integer, String> marksAndSubjects;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        marksAndSubjects = new HashMap<Integer, String>();
        marksAndSubjects.put(4, "Math");
        marksAndSubjects.put(5, "DB");
        marksAndSubjects.put(3, "History");
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(marksAndSubjects);
        assertTrue(tmp.equals("{\"3\":\"History\",\"4\":\"Math\",\"5\":\"DB\"}"));
    }
}
