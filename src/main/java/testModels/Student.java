package testModels;

import serializationInterfaces.JsonIgnore;
import serializationInterfaces.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class Student extends Person {
    private transient Boolean haveGrant;
    @JsonIgnore
    public String university;
    @JsonProperty
    public transient List<String> subjects;
    public Map<Integer, String> marksAndSubjects;

    public Student(String name, String surname, Integer age, Boolean haveGrant, String university, List<String> subjects, Map<Integer, String> marksAndSubjects) {
        super(name, surname, age);
        this.haveGrant = haveGrant;
        this.subjects = subjects;
        this.university = university;
        this.marksAndSubjects = marksAndSubjects;
    }
}
