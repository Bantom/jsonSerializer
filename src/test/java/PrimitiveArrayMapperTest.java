import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class PrimitiveArrayMapperTest {
    JsonSerializer jsonSerializer;
    int[] arrayInteger = new int[3];

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        arrayInteger[0] = 5;
        arrayInteger[1] = 9;
        arrayInteger[2] = 10;
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(arrayInteger);
        assertTrue(tmp.equals("[5,9,10]"));
    }
}
