package projet.dataStructure;

import java.util.HashSet;

public interface Bloc extends Unit {
    //Interface représentant un bloc

    String getCode();
    String getName();
    int getCredits();
    //boolean isOptional();
    int calculCredits();
    HashSet<String> getCoursIds();
    double getMoyenne(Student student);
    void checkCompatibility(Student student);
}
