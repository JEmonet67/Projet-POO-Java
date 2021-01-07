package projet.dataStructure;

import java.util.HashMap;

public class Student implements Comparable<Student>{
    //Classe représentant un étudiant

    private String id;
    private String name;
    private String surname;
    private Program program;
    private HashMap<String, Double> grades;

    public Student(String id, String name, String surname, Program program, HashMap<String, Double> grades) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.grades = grades;
        checkCompatibility(program,grades,id);
        this.program = program;
        if (program != null){
            program.addStudent(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Program getProgram() {
        return program;
    }

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    private void checkCompatibility(Program program, HashMap<String, Double> grades, String studentId){
        //verifie qu'un étudiant rempli les conditions pour être inscrit au programme
        for (Unit unit : program.getChildren()){
            Bloc bloc = (Bloc) unit;
            bloc.checkCompatibility(this);
        }
    }

    public int compareTo(Student student){
        return (-1 * student.getSurname().compareTo(this.getSurname())) ;
    }


    public String toString() {
        return id + " - " +surname+' '+ name ;
    }
}
