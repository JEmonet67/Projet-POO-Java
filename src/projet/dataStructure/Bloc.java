package projet.dataStructure;

import java.util.HashSet;

public interface Bloc extends Unit {
    //Interface représentant un bloc

    String getCode();
    String getName();
    int getCredits();
    void addCours(Cours cours);
    int calculCredits();
    HashSet<String> getCoursIds();
    double getMoyenne(Student student);
    void checkCompatibility(Student student);
}
