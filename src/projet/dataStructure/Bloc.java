package projet.dataStructure;

import java.util.HashSet;

public interface Bloc extends Unit {
    String getCode();
    String getName();
    int getCredits();
    boolean isOptional();
    HashSet<String> getCoursIds();
    double getMoyenne(Student student);
}
