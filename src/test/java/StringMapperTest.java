import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class StringMapperTest {
    JsonSerializer jsonSerializer;
    String vString;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        vString = "Hello world!";
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(vString);
        assertTrue(tmp.equals("\"" + vString + "\""));
    }
}
