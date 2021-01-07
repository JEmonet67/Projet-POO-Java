package projet.dataStructure;

import java.util.List;

public interface Unit {
    String getCode();
    String getName();
    String toString();
    void addStudent(Student student);
    List<Student> getStudents();

    boolean isCours();
    Unit[] getChildren();
    double getMoyenne(Student student);
    int getCredits();
}
