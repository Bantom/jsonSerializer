import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class NumberMapperTest {
    JsonSerializer jsonSerializer;
    Integer vInteger;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        vInteger = 52;
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(vInteger);
        assertTrue(tmp.equals("52"));
    }
}
