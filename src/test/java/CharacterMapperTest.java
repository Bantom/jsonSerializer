import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class CharacterMapperTest {
    JsonSerializer jsonSerializer;
    Character vCharacter;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        vCharacter = 'a';
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(vCharacter);
        assertTrue(tmp.equals("\"" + vCharacter.toString() + "\""));
    }
}
