import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class BooleanMapperTest {
    JsonSerializer jsonSerializer;
    Boolean vBoolean;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        vBoolean = true;
    }

    @Test
    public void testBooleanSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(vBoolean);
        assertTrue(tmp.equals("true"));
    }
}
