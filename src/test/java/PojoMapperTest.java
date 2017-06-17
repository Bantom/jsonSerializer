import jsonSerializer.JsonSerializer;
import org.junit.Before;
import org.junit.Test;
import testModels.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class PojoMapperTest {
    JsonSerializer jsonSerializer;
    Map<Integer, String> marksAndSubjects;
    List<String> subjects;
    Student student;

    @Before
    public void initJsonSerializer() {
        jsonSerializer = new JsonSerializer();
        subjects = new ArrayList<String>();
        subjects.add("Math");
        subjects.add("DB");
        subjects.add("History");
        marksAndSubjects = new HashMap<Integer, String>();
        marksAndSubjects.put(4, "Math");
        marksAndSubjects.put(5, "DB");
        marksAndSubjects.put(3, "History");
        student = new Student("Anton", "Bulgakov", 20, true, "KPI", subjects, marksAndSubjects);
    }

    @Test
    public void testSerialization() throws IOException {
        String tmp = jsonSerializer.serialize(student);
        assertTrue(tmp.equals("{\"subjects\":[\"Math\",\"DB\",\"History\"],\"marksAndSubjects\":{\"3\":\"History\",\"4\":\"Math\",\"5\":\"DB\"},\"name\":\"Anton\",\"surname\":\"Bulgakov\",\"age\":20}"));
    }
}
