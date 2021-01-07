package projet.dataStructure;

import java.util.List;

public interface Unit {
    //Interface représentant une unité d'enseignement (cours, bloc ou programme)

    //Méthodes implementées dans la classe AbsrtactUnit
    String getCode();
    String getName();
    String toString();
    void addStudent(Student student);
    List<Student> getStudents();

    //Methodes implémentées dans les classes filles d'AbstractUnit
    boolean isCours();
    Unit[] getChildren();
    double getMoyenne(Student student);
    int getCredits();
}
