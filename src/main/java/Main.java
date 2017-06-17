import jsonSerializer.JsonSerializer;
import testModels.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bulgakov
 * @since 11.12.2016
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<String> subjects = new ArrayList<String>();
        subjects.add("Math");
        subjects.add("DB");
        subjects.add("History");
        Map<Integer, String> marksAndSubjects = new HashMap<Integer, String>();
        marksAndSubjects.put(4, "Math");
        marksAndSubjects.put(5, "DB");
        marksAndSubjects.put(3, "History");
        Student student = new Student("Anton", "Bulgakov", 20, true, "KPI", subjects, marksAndSubjects);


        JsonSerializer jsonSerializer = new JsonSerializer();
        jsonSerializer.setIndent(true);
        System.out.println(jsonSerializer.serialize(student));
    }
}
