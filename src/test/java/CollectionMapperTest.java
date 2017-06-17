import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class CollectionMapperTest {
    JsonSerializer jsonSerializer;
    List<String> subjects;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        subjects = new ArrayList<String>();
        subjects.add("Math");
        subjects.add("DB");
        subjects.add("History");
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(subjects);
        assertTrue(tmp.equals("[\"Math\",\"DB\",\"History\"]"));
    }
}
