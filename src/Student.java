package projet;

import projet.Bloc;
import projet.Program;

import java.util.Collection;
import java.util.HashMap;

public class Student implements Comparable<Student>{
    private String id;
    private String name;
    private String surname;
    private Program program;
    private HashMap<String, Double> grades;

    public Student(String id, String name, String surname, Program program, HashMap<String, Double> grades) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        checkCompatibility(program,grades,id);
        this.program = program;
        this.grades = grades;
        program.addStudent(this);
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
        for (Bloc bloc : program.getBlocs()){
            if (bloc.isOptional()){
                checkOptionalCompatibility(bloc.getCoursIds(), grades.keySet(), studentId);
            }else{
                checkUnoptionalCompatibility(bloc.getCoursIds(), grades.keySet(), studentId);
            }
        }
    }


    private void checkOptionalCompatibility(Collection<String> blocIds, Collection<String> gradesIds, String studentId){
        int compt = 0;
        for (String id: blocIds){
            if (gradesIds.contains(id)){
                compt +=1 ;
            }
        }
        if (compt < 1){
            System.out.println("option exeption " + studentId);
            throw new IllegalArgumentException("L'étudiant "+studentId+" ne remplie pas les conditions d'inscription a son programme");
        }
    }

    private void checkUnoptionalCompatibility(Collection<String> blocIds, Collection<String> gradesIds, String studentId){
        if (! gradesIds.containsAll(blocIds)){
            System.out.println("Unoptional exeption" + studentId);
            throw new IllegalArgumentException("L'étudiant "+studentId+" ne remplie pas les conditions d'inscription a son programme");
        }
    }

    public int compareTo(Student student){
        if (student.getSurname().compareTo(this.getSurname())>0){
            return -1;
        } else if (student.getSurname().compareTo(this.getSurname())<0){
            return 1;
        }
        return 0;
    }


    @Override
    public String toString() {
        return "Student : " +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", program=" +program.getName();
    }
}
