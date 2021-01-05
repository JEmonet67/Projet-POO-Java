package projet;

import java.util.HashSet;

public interface Bloc {
    String getCode();
    String getName();
    int getCredits();
    boolean isOptional();
    HashSet<String> getCoursIds();
    double getMoyenne(Student student);
}
